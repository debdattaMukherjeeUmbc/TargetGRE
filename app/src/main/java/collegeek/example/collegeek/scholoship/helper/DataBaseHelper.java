package collegeek.example.collegeek.scholoship.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import collegeek.example.collegeek.scholoship.object.CollegeResponse;
import collegeek.example.collegeek.scholoship.object.Query;


public class DataBaseHelper extends SQLiteOpenHelper {

    private String DB_PATH= "";

    private static String DB_NAME = "Colleges.sqlite";
    private static String DOLLAR_SIGN = "$";
    private static String PERCENT_SIGN = "%";

    private SQLiteDatabase myDataBase;

    private final Context myContext;
    protected static final String TAG = "DataAdapter";

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 10);// 1? Always change Database Version


        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.myContext = context;
        myContext.deleteDatabase(DB_NAME);
        //InitializeSQLCipher();
    }


        /**
         * Creates a empty database on the system and rewrites it with your own
         * database.
         */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;

        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.NO_LOCALIZED_COLLATORS
                        | SQLiteDatabase.CREATE_IF_NECESSARY);

    }

    public List<CollegeResponse> getCollegeData(Query query) {

        List<CollegeResponse> result = new ArrayList<CollegeResponse>();
        String sql = "";
        Map<String, String> states = generateStates();
        boolean isItemSelected = false;

        try {

                sql = "SELECT * FROM Colleges";

                if(query.getState() == null
                        && query.getCollegeType() == null
                        && query.getDifficultyLevel() == null
                        && query.getField() == null
                        && query.getAdmission() != null)
                {
                sql += " ";
                }else if((query.getState() != null && !query.getState().equalsIgnoreCase("All"))
                        || (query.getCollegeType() != null && !query.getCollegeType().equalsIgnoreCase("Both"))
                        || (query.getDifficultyLevel() != null && !(query.getDifficultyLevel().equalsIgnoreCase("ALL")))
                        || (query.getField() != null  && !query.getField().equalsIgnoreCase("All")))
                {
                    sql += " where ";
                }

                if(query.getState() != null && !query.getState().equalsIgnoreCase("All"))
                {
                sql += " Location_State = '"+query.getState()+"'";
                    isItemSelected = true;
                }

                if(query.getCollegeType() != null && !query.getCollegeType().equalsIgnoreCase("Both"))
                {
                    if(isItemSelected)
                    {
                        sql += " and Type = '" + query.getCollegeType()+"'";
                    }else {
                        sql += " Type = '" + query.getCollegeType()+"'";
                    }
                    isItemSelected = true;
                }

                if(query.getDifficultyLevel() != null && !(query.getDifficultyLevel().equalsIgnoreCase("ALL")))
                {
                    if(isItemSelected) {
                        sql += " and GRE = '" + query.getDifficultyLevel()+"'";
                    }else{
                        sql += " GRE = '" + query.getDifficultyLevel()+"'";
                    }
                    isItemSelected = true;
                }

                if(query.getField() != null && !query.getField().equalsIgnoreCase("All"))
                {
                    if(isItemSelected) {
                        sql += " and " + query.getField() + " = '1'";
                    }else{
                        sql += " " + query.getField() + " = '1'";
                    }
                }


            Cursor cursor = myDataBase.rawQuery(sql, null);

            if (cursor != null) {

                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                        .moveToNext()) {

                    CollegeResponse response = new CollegeResponse();

                    String id = cursor.getString(cursor.getColumnIndex("_Id"));

                    if(id == null)
                    {
                        break;
                    }
                    String name = cursor.getString(cursor.getColumnIndex("College_Name"));
                    String locState = cursor.getString(cursor.getColumnIndex("Location_State"));
                    String locPlace = cursor.getString(cursor.getColumnIndex("Location_Place"));
                    String website = cursor.getString(cursor.getColumnIndex("Website"));
                    String phone = cursor.getString(cursor.getColumnIndex("Phone"));
                    String type = cursor.getString(cursor.getColumnIndex("Type"));
                    String admissionDifficulty = cursor.getString(cursor.getColumnIndex("GRE"));
                    String totalStudents = cursor.getString(cursor.getColumnIndex("Total_Students"));
                    String totalGraduates = cursor.getString(cursor.getColumnIndex("Total_Graduates"));
                    String fallDate = cursor.getString(cursor.getColumnIndex("Fall_Date"));
                    String springDate = cursor.getString(cursor.getColumnIndex("Spring_Date"));
                    String summerDate = cursor.getString(cursor.getColumnIndex("Summer_Date"));
                    String applicationFee = cursor.getString(cursor.getColumnIndex("Application_Fee"));
                    String inStateUnderGrad = cursor.getString(cursor.getColumnIndex("In_Undergrad_Tuition_PY"));
                    String inStateGrad = cursor.getString(cursor.getColumnIndex("In_Grad_Tuition_PY"));
                    String outStateUnderGrad = cursor.getString(cursor.getColumnIndex("Out_Undergrad_Tuition_PY"));
                    String outStateGrad = cursor.getString(cursor.getColumnIndex("Out_Grad_Tuition_PY"));
                    String rdExpenditure = cursor.getString(cursor.getColumnIndex("Research_Development_Expenditure_In_Thousand"));
                    String aviation = cursor.getString(cursor.getColumnIndex("Aviation"));
                    String chemicalEngineering = cursor.getString(cursor.getColumnIndex("Chemical_Engineering"));
                    String civil = cursor.getString(cursor.getColumnIndex("Civil"));
                    String chemistry = cursor.getString(cursor.getColumnIndex("Chemistry"));
                    String computerEngineering = cursor.getString(cursor.getColumnIndex("Computer_Engineering"));
                    String computerScience = cursor.getString(cursor.getColumnIndex("Computer_Science"));
                    String electricalEngineering = cursor.getString(cursor.getColumnIndex("Electrical_Engineering"));
                    String electronicsCommunication = cursor.getString(cursor.getColumnIndex("Electronics_Communication"));
                    String industrialEngineering = cursor.getString(cursor.getColumnIndex("Industrial_Engineering"));
                    String mechanicalEngineering = cursor.getString(cursor.getColumnIndex("Mechanical_Engineering"));
                    String mis = cursor.getString(cursor.getColumnIndex("MIS_IS"));
                    String engineeringManagement = cursor.getString(cursor.getColumnIndex("Engineering_Management"));
                    String minToeflIBT = cursor.getString(cursor.getColumnIndex("TOELF_PBT_MIN"));
                    String minToeflPBT = cursor.getString(cursor.getColumnIndex("TOELF_IBT_MIN"));
                    String ielts = cursor.getString(cursor.getColumnIndex("IELTS_MIN"));
                    String physcis = cursor.getString(cursor.getColumnIndex("Physics"));
                    String architecture = cursor.getString(cursor.getColumnIndex("Architecture"));

                    Map<String, String> subjects = new HashMap<String, String>();
                    subjects.put("Aviation",aviation.trim());
                    subjects.put("Chemical_Engineering",chemicalEngineering.trim());
                    subjects.put("Chemistry",chemistry.trim());
                    subjects.put("Civil",civil.trim());
                    subjects.put("Computer_Engineering",computerEngineering.trim());
                    subjects.put("Computer_Science",computerScience.trim());
                    subjects.put("Electrical_Engineering",electricalEngineering.trim().trim());
                    subjects.put("Electronics_Communication",electronicsCommunication.trim());
                    subjects.put("Industrial_Engineering",industrialEngineering.trim());
                    subjects.put("Mechanical_Engineering",mechanicalEngineering.trim());
                    subjects.put("MIS_IS", mis.trim());
                    subjects.put("Engineering_Management", engineeringManagement.trim());
                    subjects.put("Physics", physcis.trim());
                    subjects.put("Architecture", architecture.trim());


                    response.setSubjects(subjects);
                    response.setId(id);
                    response.setName(name);
                    response.setPhone(phone);
                    response.setLocationState(locPlace + " , " + states.get(locState.trim()));

                    response.setType(type);
                    response.setAdmissionDifficulty(admissionDifficulty);
                    response.setTotalStudents(totalStudents);
                    response.setTotalGraduates(totalGraduates);
                    response.setTotalStudentsByTotalGrads(formatStringToCommaSeperatedString(totalGraduates) + " / " + formatStringToCommaSeperatedString(totalStudents));
                    response.setApplicationFee(applicationFee + DOLLAR_SIGN);
                    response.setRdExpenditure(formatStringToCommaSeperatedString(rdExpenditure) + DOLLAR_SIGN);
                    response.setMinToeflIBT(minToeflIBT);
                    response.setMinToeflPBT(minToeflPBT);
                    response.setIelts(ielts);

                    String admission = query.getAdmission();

                    if(admission == null)
                    {
                        response.setCollegeTuition(formatStringToCommaSeperatedString(inStateGrad)
                                + DOLLAR_SIGN + " | "
                                + formatStringToCommaSeperatedString(outStateGrad)
                                + DOLLAR_SIGN);

                    }else {
                        if (admission.equalsIgnoreCase("Bachelor's Degree")) {

                            response.setCollegeTuition(formatStringToCommaSeperatedString(inStateUnderGrad)
                                    + DOLLAR_SIGN + " | "
                                    + formatStringToCommaSeperatedString(outStateUnderGrad)
                                    + DOLLAR_SIGN);


                        } else if (admission.equalsIgnoreCase("Master's Degree") || admission.equalsIgnoreCase("PHD")) {
                            response.setCollegeTuition(formatStringToCommaSeperatedString(inStateGrad)
                                    + DOLLAR_SIGN + " | "
                                    + formatStringToCommaSeperatedString(outStateGrad)
                                    + DOLLAR_SIGN);
                        }
                    }


                    String semesterDate = query.getSemester();
                    DateTime current = new DateTime();
                    DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss aa");

                    if (semesterDate.equalsIgnoreCase("Fall_Date")) {
                        if (fallDate.equalsIgnoreCase("Rolling") || fallDate.equalsIgnoreCase("NA")) {
                            response.setDeadline(fallDate);
                        } else {
                            DateTime dt = formatter.parseDateTime(fallDate);
                            response.setDeadline(dt.monthOfYear().getAsText() + " " + dt.getDayOfMonth());
                        }
                    } else if (semesterDate.equalsIgnoreCase("Summer_Date")) {
                        if (summerDate.equalsIgnoreCase("Rolling") || summerDate.equalsIgnoreCase("NA")) {
                            response.setDeadline(summerDate);
                        } else {
                            DateTime dt = formatter.parseDateTime(summerDate);
                            response.setDeadline(dt.monthOfYear().getAsText() + " " + dt.getDayOfMonth());
                        }
                    } else if (semesterDate.equalsIgnoreCase("Spring_Date")) {
                        if (springDate.equalsIgnoreCase("Rolling") || springDate.equalsIgnoreCase("NA")) {
                            response.setDeadline(springDate);
                        } else {
                            DateTime dt = formatter.parseDateTime(springDate);
                            response.setDeadline(dt.monthOfYear().getAsText() + " " + dt.getDayOfMonth());
                        }
                    }

                    response.setWebsite(website);
                    result.add(response);
                }

            }

            cursor.close();
            return result;

        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();


        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    private String formatStringToCommaSeperatedString(String string) {

        if(string.equalsIgnoreCase("NA"))
        {
            return "NA";
        }
        return NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(string));
    }


    private Map<String, String> generateStates() {
        Map<String, String> states = new HashMap<String, String>();
        states.put("AL", "Alabama");
        states.put("AK", "Alaska");
        states.put("AZ", "Arizona");
        states.put("AR", "Arkansas"); // space in AR
        states.put("CA", "California");
        states.put("CO", "Colorado");
        states.put("CT", "Connecticut");
        states.put("DE", "Delaware");
        states.put("FL", "Florida");
        states.put("GA", "Georgia");
        states.put("HI", "Hawaii");
        states.put("ID", "Idaho");
        states.put("IL", "Illinois");
        states.put("IN", "Indiana");
        states.put("IA", "Iowa");
        states.put("KS", "Kansas");
        states.put("KY", "Kentucky");
        states.put("LA", "Louisiana");
        states.put("ME", "Maine");
        states.put("MD", "Maryland");
        states.put("MA", "Massachusetts");
        states.put("MI", "Michigan");
        states.put("MN", "Minnesota");
        states.put("MS", "Mississippi");
        states.put("MO", "Missouri");
        states.put("MT", "Montana");
        states.put("NE", "Nebraska");
        states.put("NV", "Nevada");
        states.put("NH", "New Hampshire");
        states.put("NJ", "New Jersey");
        states.put("NM", "New Mexico");
        states.put("NY", "New York");
        states.put("NC", "North Carolina");
        states.put("ND", "North Dakota");
        states.put("OH", "Ohio");
        states.put("OK", "Oklahoma");
        states.put("OR", "Oregon");
        states.put("PA", "Pennsylvania");
        states.put("RI", "Rhode Island");
        states.put("SC", "South Carolina");
        states.put("SD", "South Dakota");
        states.put("TN", "Tennessee");
        states.put("TX", "Texas");
        states.put("UT", "Utah");
        states.put("VT", "Vermont");
        states.put("VA", "Virginia");
        states.put("WA", "Washington");
        states.put("WV", "West Virginia");
        states.put("WI", "Wisconsin");
        states.put("WY", "Wyoming");

        return states;

    }

}