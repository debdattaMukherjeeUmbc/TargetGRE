package collegeek.example.collegeek.scholoship.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.collegeek.scholoship.R;

import collegeek.example.collegeek.scholoship.adapter.NothingSelectedSpinnerAdapter;
import collegeek.example.collegeek.scholoship.object.Criteria;


public class MainActivity extends ActionBarActivity {

    private Button btnSubmit;
    private Spinner admission;
    private Spinner field;
    private Spinner semester;
    private Spinner collegeType;
    private Spinner state;
    private Spinner difficulty;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar_main);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        // set the icon
       toolbar.setLogo(R.mipmap.ic_launcher);

        setSupportActionBar(toolbar);

        admission = (Spinner) findViewById(R.id.admission_filter);
        admission.setBackgroundResource(R.mipmap.ic_spinner);
        semester = (Spinner) findViewById(R.id.semester_filter);
        semester.setBackgroundResource(R.mipmap.ic_spinner);
        field = (Spinner) findViewById(R.id.field_filter);
        field.setBackgroundResource(R.mipmap.ic_spinner);
        collegeType = (Spinner) findViewById(R.id.college_type_filter);
        collegeType.setBackgroundResource(R.mipmap.ic_spinner);
        state = (Spinner) findViewById(R.id.state_filter);
        state.setBackgroundResource(R.mipmap.ic_spinner);
        difficulty = (Spinner) findViewById(R.id.admission_difficulty_filter);
        difficulty.setBackgroundResource(R.mipmap.ic_spinner);
        btnSubmit = (Button) findViewById(R.id.submit);

        ArrayAdapter<CharSequence> admAdapter = ArrayAdapter.createFromResource(this, R.array.admission_filter, R.layout.spinner_layout);
        admAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        admission.setPrompt("Degree Type");
        admission.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        admAdapter,
                        R.layout.spinner_row_nothing_selected_degree_type,
                        this));

        ArrayAdapter<CharSequence> fieldAdapter = ArrayAdapter.createFromResource(this, R.array.field_filter, R.layout.spinner_layout);
        fieldAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        field.setPrompt("Field of Study");
        field.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        fieldAdapter,
                        R.layout.spinner_row_nothing_selected_field_of_study,
                        this));

        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(this, R.array.semester_filter, R.layout.spinner_layout);
        semesterAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        semester.setPrompt("Select Semester");
        semester.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        semesterAdapter,
                        R.layout.spinner_row_nothing_selected_semester,
                        this));

        ArrayAdapter<CharSequence> collegeTypeAdapter = ArrayAdapter.createFromResource(this, R.array.college_type_filter, R.layout.spinner_layout);
        collegeTypeAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        collegeType.setPrompt("College Type");
        collegeType.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        collegeTypeAdapter,
                        R.layout.spinner_row_nothing_selected_college_type,
                        this));

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.state_filter, R.layout.spinner_layout);
        stateAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        state.setPrompt("Select State");
        state.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        stateAdapter,
                        R.layout.spinner_row_nothing_selected_state,
                        this));

        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter.createFromResource(this, R.array.difficulty_filter, R.layout.spinner_layout);
        difficultyAdapter.setDropDownViewResource(R.layout.spinner_center_item);
        difficulty.setPrompt("Admission Difficulty");
        difficulty.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        difficultyAdapter,
                        R.layout.spinner_row_nothing_selected_difficulty,
                        this));

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Criteria criteria = new Criteria();

                criteria.setAdmission(admission.getSelectedItem() != null ? admission.getSelectedItem().toString() : null);
                criteria.setField(field.getSelectedItem() != null ? field.getSelectedItem()
                        .toString() : null);
                criteria.setSemester(semester.getSelectedItem() != null ? semester.getSelectedItem().toString(): null);
                criteria.setCollegeType(collegeType.getSelectedItem() != null ? collegeType.getSelectedItem().toString() : null);
                criteria.setState(state.getSelectedItem() != null ? state.getSelectedItem().toString() : null);
                criteria.setDifficultyLevel(difficulty.getSelectedItem() != null ? difficulty.getSelectedItem().toString() : null);

                Intent intent = new Intent(getBaseContext(),
                        MainTabActivity.class);

				 intent.putExtra("criteria", criteria);

                startActivity(intent);

            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
