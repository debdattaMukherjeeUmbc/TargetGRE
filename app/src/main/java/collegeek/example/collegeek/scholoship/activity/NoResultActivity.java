package collegeek.example.collegeek.scholoship.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.collegeek.scholoship.R;

import collegeek.example.collegeek.scholoship.adapter.ViewPagerAdapter;
import collegeek.example.collegeek.scholoship.tab.SlidingTabLayout;

public class NoResultActivity extends ActionBarActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Colleges"};
    int Numboftabs = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_result);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_detail);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:

                super.onBackPressed();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
