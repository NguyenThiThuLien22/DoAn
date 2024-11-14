package com.example.doan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<ActivityItem> activityList;

    public ActivityAdapter(List<ActivityItem> activityList) {
        this.activityList = activityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityItem item = activityList.get(position);
        holder.description.setText(item.getDescription());
        holder.time.setText(item.getTime());
        holder.amount.setText(item.getAmount());
        holder.icon.setImageResource(R.drawable.history);  // Đảm bảo có drawable "history"
    }

    @Override
    public int getItemCount() {
        return activityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView description, time, amount;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.activityDescription);
            time = itemView.findViewById(R.id.activityTime);
            amount = itemView.findViewById(R.id.activityAmount);
            icon = itemView.findViewById(R.id.activityIcon);
        }
    }
}
