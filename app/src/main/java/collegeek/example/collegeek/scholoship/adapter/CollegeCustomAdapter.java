package collegeek.example.collegeek.scholoship.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.collegeek.scholoship.R;

import collegeek.example.collegeek.scholoship.activity.CollegeDetailActivity;
import collegeek.example.collegeek.scholoship.interfaceScholorship.Communicator;
import collegeek.example.collegeek.scholoship.object.CollegeResponse;

import java.util.List;
import java.util.Properties;


public class CollegeCustomAdapter extends RecyclerView.Adapter<CollegeCustomAdapter.ViewHolder>  {


    public List<CollegeResponse> mResponse;
    private Context mContext;
    private Properties mProperties;
    private Communicator mComm;
    private static final String MY_FILE_NAME = "myfile";

    public CollegeCustomAdapter(List<CollegeResponse> response, Properties properties) {
        mResponse = response;
        mProperties = properties;
    }

    public CollegeCustomAdapter(List<CollegeResponse> response, Properties properties, Communicator comm) {
        mResponse = response;
        mProperties = properties;
        mComm = comm;
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView txtName;
        public TextView txtLocation;
        public TextView txtTuition;
        public TextView txtDeadline;
        public ImageView imgFavorite;
        public TextView txtResearchExp;

        public ViewHolder(View v, Context context) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.collegeName);
            txtTuition = (TextView) v.findViewById(R.id.collegeTuition);
            txtDeadline = (TextView) v.findViewById(R.id.collegeDeadline);
            txtLocation = (TextView) v.findViewById(R.id.locationPlace);
            txtResearchExp = (TextView) v.findViewById(R.id.rDExpenditure);

            mContext = context;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
			
            Intent intent = new Intent(mContext, CollegeDetailActivity.class);
            intent.putExtra("response", (android.os.Parcelable) mResponse.get(getPosition()));
            mContext.startActivity(intent);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v,parent.getContext() );
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtName.setText( mResponse.get(position).getCollegeName());
        holder.txtTuition.setText(mResponse.get(position).getCollegeTuition());
        holder.txtDeadline.setText(mResponse.get(position).getDeadline());
        holder.txtLocation.setText(mResponse.get(position).getLocationState());
        holder.txtResearchExp.setText(mResponse.get(position).getRdExpenditure());

        boolean bFlag = mResponse.get(position).isFavoriteSelected();

        if(bFlag)
        {
            holder.imgFavorite.setImageResource(R.mipmap.ic_favorite_selected);
        }
    }

    @Override
    public int getItemCount() {
        return mResponse.size();
    }




}
