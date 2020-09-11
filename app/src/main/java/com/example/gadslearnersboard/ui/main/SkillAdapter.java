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
import com.example.gadslearnersboard.models.SkillLeader;

import java.util.List;

class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {
    private Context mContext;
    private List<SkillLeader> mSkillLeaderList;

    public SkillAdapter(Context context, List<SkillLeader> skillLeaderList) {
        this.mContext = context;
        this.mSkillLeaderList = skillLeaderList;
    }
    @NonNull
    @Override
    public SkillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.learning_list, parent, false);
        return new SkillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillAdapter.ViewHolder holder, int position) {
        SkillLeader skillLeader = mSkillLeaderList.get(position);
        String name = skillLeader.getName();
        String scoreDetails = skillLeader.getScore() + " Skill IQ Score, " + skillLeader.getCountry();
        String badgeUrl = skillLeader.getBadgeUrl();

        holder.tv_name.setText(name);
        holder.tv_details.setText(scoreDetails);
        loadBadge(holder, badgeUrl);
    }

    private void loadBadge(SkillAdapter.ViewHolder holder, String url) {
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.top_learner)
                .into(holder.skillBadge);
    }

    @Override
    public int getItemCount() {
        return mSkillLeaderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView skillBadge;
        TextView tv_name;
        TextView tv_details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skillBadge = itemView.findViewById(R.id.badge);
            tv_name = itemView.findViewById(R.id.student_name);
            tv_details = itemView.findViewById(R.id.learning_details);
        }
    }
}
