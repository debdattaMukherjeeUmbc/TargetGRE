package collegeek.example.collegeek.scholoship.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.collegeek.scholoship.R;

import java.util.List;
import java.util.Properties;

import collegeek.example.collegeek.scholoship.adapter.ViewPagerAdapter;
import collegeek.example.collegeek.scholoship.fragment.Tab1;
import collegeek.example.collegeek.scholoship.fragment.Tab2;
import collegeek.example.collegeek.scholoship.interfaceScholorship.Communicator;
import collegeek.example.collegeek.scholoship.object.CollegeResponse;
import collegeek.example.collegeek.scholoship.tab.SlidingTabLayout;

public class MainTabActivity extends ActionBarActivity implements Communicator {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Colleges"};
    int Numboftabs = 1;
    Intent intent;

    @Override
    public void respond(List<CollegeResponse> response, Properties properties) {

        int pagerIndex = 0;
        pager = (ViewPager) findViewById(R.id.pager);

        if (pager.getCurrentItem() == 0) {
            pagerIndex = 1;

            android.support.v4.app.Fragment fg2 = adapter.getRegisteredFragment(pagerIndex);

            if (fg2 instanceof Tab2) {
                Tab2 tab2 = (Tab2) fg2;
                tab2.changeData(response, properties);
            }
        } else {

            android.support.v4.app.Fragment fg1 = adapter.getRegisteredFragment(0);
            if (fg1 instanceof Tab1) {
                Tab1 tab1 = (Tab1) fg1;
                tab1.changeData(response, properties);
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
		
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        //setting indicator and divider color
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.white);    //define any color in xml resources and set it here, I have used white
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tab, menu);
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
		
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
		
    }

    }
