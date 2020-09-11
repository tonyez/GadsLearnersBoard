package com.example.gadslearnersboard.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gadslearnersboard.R;
import com.example.gadslearnersboard.models.TopLearner;

import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.ViewHolder> {
    private Context mContext;
    private List<TopLearner> mTopLearnerList;

    public LearnerAdapter(Context context, List<TopLearner> topLearnerList) {
        this.mContext = context;
        this.mTopLearnerList = topLearnerList;
    }

    @NonNull
    @Override
    public LearnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerAdapter.ViewHolder holder, int position) {
        TopLearner topLearner = mTopLearnerList.get(position);
        String name = topLearner.getName();
        String scoreDetails = topLearner.getHours() + " learning hours, " + topLearner.getCountry();
        String badgeUrl = topLearner.getBadgeUrl();

        holder.tv_name.setText(name);
        holder.tv_details.setText(scoreDetails);
        loadBadge(holder, badgeUrl);
    }

    private void loadBadge(ViewHolder holder, String url) {
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.top_learner)
                .into(holder.topLearnerBadge);
    }

    @Override
    public int getItemCount() {
        return mTopLearnerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView topLearnerBadge;
        TextView tv_name;
        TextView tv_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            topLearnerBadge = itemView.findViewById(R.id.badge);
            tv_name = itemView.findViewById(R.id.student_name);
            tv_details = itemView.findViewById(R.id.learning_details);
        }
    }
}
