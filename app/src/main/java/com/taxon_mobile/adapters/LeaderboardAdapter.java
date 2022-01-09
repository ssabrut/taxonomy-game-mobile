package com.taxon_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taxon_mobile.R;
import com.taxon_mobile.models.Leaderboard;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {
    private Context context;
    public static List<Leaderboard.Leaderboards> listLeaderboard;

    public LeaderboardAdapter(Context context) {
        this.context = context;
    }

    private List<Leaderboard.Leaderboards> getListLeaderboard() {
        return listLeaderboard;
    }

    public void setListLeaderboard(List<Leaderboard.Leaderboards> listLeaderboard) {
        this.listLeaderboard = listLeaderboard;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_leaderboard, parent, false);
        return new LeaderboardAdapter.LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        final Leaderboard.Leaderboards result = getListLeaderboard().get(position);
        holder.leaderboard_name.setText(result.getUser().getName());
        holder.leaderboard_evo.setText(String.valueOf(result.getEvo()));
        holder.leaderboard_power.setText(String.valueOf(result.getPower()));
        holder.leaderboard_point.setText(String.valueOf(result.getPoint()));
        if (position == 0) {
            holder.leaderboard_position.setTextColor(context.getResources().getColor(R.color.gold));
            holder.leaderboard_position.setText(String.valueOf(position + 1));
        } else if (position == 1) {
            holder.leaderboard_position.setTextColor(context.getResources().getColor(R.color.silver));
            holder.leaderboard_position.setText(String.valueOf(position + 1));
        } else if (position == 2) {
            holder.leaderboard_position.setTextColor(context.getResources().getColor(R.color.bronze));
            holder.leaderboard_position.setText(String.valueOf(position + 1));
        } else {
            holder.leaderboard_position.setText(String.valueOf(position + 1));
        }
    }

    @Override
    public int getItemCount() {
        return listLeaderboard.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {
        TextView leaderboard_name, leaderboard_position, leaderboard_evo, leaderboard_power, leaderboard_point;

        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            leaderboard_name = itemView.findViewById(R.id.leaderboard_name);
            leaderboard_position = itemView.findViewById(R.id.leaderboard_position);
            leaderboard_evo = itemView.findViewById(R.id.leaderboard_evo);
            leaderboard_power = itemView.findViewById(R.id.leaderboard_power);
            leaderboard_point = itemView.findViewById(R.id.leaderboard_point);
        }
    }
}
