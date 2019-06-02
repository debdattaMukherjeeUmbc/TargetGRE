package collegeek.example.collegeek.scholoship.fragment;
 
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import collegeek.example.collegeek.scholoship.adapter.CollegeCustomAdapter;
import collegeek.example.collegeek.scholoship.interfaceScholorship.Communicator;
import collegeek.example.collegeek.scholoship.object.CollegeResponse;
import collegeek.example.collegeek.scholoship.object.Criteria;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import com.collegeek.scholoship.R;

public class Tab2 extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Criteria criteria;
    private FragmentActivity activity;
    private Properties mProperties;
    private Communicator comm;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_2,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.college_recycler_view);
        Set<Integer> ids = new HashSet<Integer>();

        activity = getActivity();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        CopyOnWriteArrayList<CollegeResponse>  responses= new CopyOnWriteArrayList<CollegeResponse>();
        Properties properties = new Properties();

        mAdapter = new CollegeCustomAdapter(responses, properties);
        mRecyclerView.setAdapter(mAdapter);



        return v;
    }

public void changeData(List<CollegeResponse> responses, Properties properties)
{
    mProperties = properties;
    List<CollegeResponse> displayList = new ArrayList<CollegeResponse>();

    try {

        File file = new File(android.os.Environment.getExternalStorageDirectory(),"data.properties");
        mProperties.load(new FileInputStream(file));

        for (CollegeResponse collegeResponse : responses) {
            if(collegeResponse.isFavoriteSelected())
            {
                displayList.add(collegeResponse);
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    // use a linear layout manager
    mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
    mRecyclerView.setLayoutManager(mLayoutManager);

    mAdapter = new CollegeCustomAdapter(displayList, mProperties, comm);
    mRecyclerView.setAdapter(mAdapter);
}


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        comm = (Communicator)activity;
    }


}