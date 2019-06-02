package collegeek.example.collegeek.scholoship.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.collegeek.scholoship.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import collegeek.example.collegeek.scholoship.adapter.CollegeCustomAdapter;
import collegeek.example.collegeek.scholoship.helper.AlphanumComparator;
import collegeek.example.collegeek.scholoship.helper.DataBaseHelper;
import collegeek.example.collegeek.scholoship.interfaceScholorship.Communicator;
import collegeek.example.collegeek.scholoship.object.CollegeResponse;
import collegeek.example.collegeek.scholoship.object.Criteria;
import collegeek.example.collegeek.scholoship.object.Query;
import collegeek.example.collegeek.scholoship.translator.CriteriaToQuery;

public class Tab1 extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Criteria criteria;
    private FragmentActivity activity;
    private static String sort= "Sort by name";
    private List<CollegeResponse> mResponses;
    private boolean flag=false;
    private ImageView imgFavorite;
    private Communicator comm;
    private Properties mProperties = new Properties();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);


        activity = getActivity();
        mResponses = new ArrayList<>();
        Intent intent = activity.getIntent();
        criteria = intent
                .getParcelableExtra("criteria");
        ActionBar actionBar = ((ActionBarActivity)activity).getSupportActionBar();


        DataBaseHelper dataBaseHelper = new DataBaseHelper(
                activity.getApplicationContext());

        try {
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();

            CriteriaToQuery criteriaToQuery = new CriteriaToQuery();
            Query query = criteriaToQuery
                    .translateCollegeCriteriaToQuery(criteria);

            mResponses = dataBaseHelper.getCollegeData(query);

            // no response found
            if(mResponses.isEmpty())
            {
                Toast.makeText(activity.getApplicationContext(), "No result found. Try different criteria.",
                        Toast.LENGTH_LONG).show();
            }

            dataBaseHelper.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);




        // Adapter
        SpinnerAdapter spinnerAdapter =   ArrayAdapter.createFromResource(activity.getApplicationContext(), R.array.sort_actions,
                R.layout.action_spinner_layout);


// Callback
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {

            String[] items = getResources().getStringArray(R.array.sort_actions); // List items from res

            @Override
            public boolean onNavigationItemSelected(int position, long id) {

                sort = items[position];
                Log.d("NavigationItemSelected", items[position]); // Debug

                if(sort.equalsIgnoreCase("Sort by name"))
                {
                    Collections.sort(mResponses);
                    mAdapter = new CollegeCustomAdapter(mResponses,mProperties,comm);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else  if(sort.equalsIgnoreCase("Sort by in-state tuition"))
                {
                    Collections.sort(mResponses, CollegeResponse.InStateTuitionComparator);
                    mAdapter = new CollegeCustomAdapter(mResponses, mProperties,comm);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else  if(sort.equalsIgnoreCase("Sort by out-state tuition"))
                {
                    Collections.sort(mResponses, CollegeResponse.OutStateTuitionComparator);
                    mAdapter = new CollegeCustomAdapter(mResponses, mProperties,comm);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else  if(sort.equalsIgnoreCase("Sort by R&D expenditure"))
                {
                    Collections.sort(mResponses, CollegeResponse.ExpenditureComparator);
                    mAdapter = new CollegeCustomAdapter(mResponses, mProperties,comm);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }else  if(sort.equalsIgnoreCase("Sort by deadline"))
                {
                    Collections.sort(mResponses, new AlphanumComparator());
                    mAdapter = new CollegeCustomAdapter(mResponses, mProperties,comm);
                    mRecyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }

                return true;

            }

        };

        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setListNavigationCallbacks(spinnerAdapter,  callback);
         return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                activity.onBackPressed();
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        comm = (Communicator)activity;
    }

    public void changeData(List<CollegeResponse> responses, Properties properties)
    {
        mAdapter = new CollegeCustomAdapter(mResponses,properties,comm);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}