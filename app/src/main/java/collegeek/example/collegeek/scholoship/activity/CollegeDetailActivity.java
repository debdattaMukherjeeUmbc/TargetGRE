package collegeek.example.collegeek.scholoship.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.collegeek.scholoship.R;

import java.util.Map;

import collegeek.example.collegeek.scholoship.object.CollegeResponse;

public class CollegeDetailActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_detail);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        //list view animation
        this.overridePendingTransition(R.anim.slide_right, R.anim.slide_left);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        final CollegeResponse response  = intent
                .getParcelableExtra("response");

        TextView txtName = (TextView) findViewById(R.id.textCollege);
        TextView txtLocation = (TextView) findViewById(R.id.textLocation);
        TextView txtTuition = (TextView) findViewById(R.id.textTuition);
        TextView txtDeadline = (TextView) findViewById(R.id.textDeadline);
        TextView txtType = (TextView) findViewById(R.id.textType);
        TextView txtDifficultyLevel =(TextView) findViewById(R.id.textAdmissionDiff);
        TextView txtTotalStudentByGradStudents = (TextView) findViewById(R.id.textGradByNoOfStudents);
        TextView txtApplicationFee= (TextView) findViewById(R.id.textApplicationFee);
        TextView textResearchExp= (TextView) findViewById(R.id.textResearchExp);
        TextView textIelts= (TextView) findViewById(R.id.textIelts);
        TextView textToeflIBT= (TextView) findViewById(R.id.textToeflIBT);
        TextView textToeflPBT= (TextView) findViewById(R.id.textToeflPBT);

        TextView txtAviation = (TextView) findViewById(R.id.aviation);
        TextView txtChemicalEngineering = (TextView) findViewById(R.id.chemicalEngineering);
        TextView txtChemistry = (TextView) findViewById(R.id.chemistry);
        TextView txtCivil = (TextView) findViewById(R.id.civil);
        TextView txtComputerEngineering = (TextView) findViewById(R.id.computerEngineering);
        TextView txtComputerScience = (TextView) findViewById(R.id.computerScience);
        TextView txtElectricalEngineering = (TextView) findViewById(R.id.electricalEngineering);
        TextView txtElectronicsCommunication = (TextView) findViewById(R.id.electronicsCommunication);
        TextView txtIndustrialEngineering = (TextView) findViewById(R.id.industrialEngineering);
        TextView txtMechanicalEngineering = (TextView) findViewById(R.id.mechanicalEngineering);
        TextView txtInformationSystems = (TextView) findViewById(R.id.informationSystems);
        TextView txtEngineeringManagement = (TextView) findViewById(R.id.engineeringManagement);
        TextView txtArchitecture = (TextView) findViewById(R.id.architecture);
        TextView txtPhyscis = (TextView) findViewById(R.id.physics);


        ImageView imgWeb = (ImageView) findViewById(R.id.imgWeb);
        imgWeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.getWebsite()));
                startActivity(intent);
            }
        });

        ImageView imgCall = (ImageView) findViewById(R.id.imgCall);
        imgCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+response.getPhone()));
                startActivity(intent);
            }
        });

        txtName.setText(response.getCollegeName());
        txtTuition.setText(response.getCollegeTuition());
        txtDeadline.setText(response.getDeadline());
        txtLocation.setText( response.getLocationState());
        txtType.setText(response.getType());
        txtDifficultyLevel.setText(response.getAdmissionDifficulty());
        txtTotalStudentByGradStudents.setText(response.getTotalStudentsByTotalGrads());
        txtApplicationFee.setText(response.getApplicationFee());
        textResearchExp.setText(response.getRdExpenditure());
        textIelts.setText(response.getIelts());
        textToeflIBT.setText(response.getMinToeflIBT());
        textToeflPBT.setText(response.getMinToeflPBT());

        Map<String,String> subjects = response.getSubjects();

        if(subjects.get("Physics").equalsIgnoreCase("0"))
        {
            txtPhyscis.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Architecture").equalsIgnoreCase("0"))
        {
            txtArchitecture.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Aviation").equalsIgnoreCase("0"))
        {
            txtAviation.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Chemical_Engineering").equalsIgnoreCase("0"))
        {
            txtChemicalEngineering.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Chemistry").equalsIgnoreCase("0"))
        {
            txtChemistry.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Civil").equalsIgnoreCase("0"))
        {
            txtCivil.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Computer_Engineering").equalsIgnoreCase("0"))
        {
            txtComputerEngineering.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Computer_Science").equalsIgnoreCase("0"))
        {
            txtComputerScience.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Electrical_Engineering").equalsIgnoreCase("0"))
        {
            txtElectricalEngineering.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Electronics_Communication").equalsIgnoreCase("0"))
        {
            txtElectronicsCommunication.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Industrial_Engineering").equalsIgnoreCase("0"))
        {
            txtIndustrialEngineering.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Mechanical_Engineering").equalsIgnoreCase("0"))
        {
            txtMechanicalEngineering.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("MIS_IS").equalsIgnoreCase("0"))
        {
            txtInformationSystems.setTextColor(getResources().getColor(R.color.gray));
        }
        if(subjects.get("Engineering_Management").equalsIgnoreCase("0"))
        {
            txtEngineeringManagement.setTextColor(getResources().getColor(R.color.gray));
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_college_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
