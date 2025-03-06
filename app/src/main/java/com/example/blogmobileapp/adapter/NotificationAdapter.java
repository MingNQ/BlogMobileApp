package com.example.blogmobileapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogmobileapp.R;
import com.example.blogmobileapp.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<NotificationModel> notifications;

    public NotificationAdapter(List<NotificationModel> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        NotificationModel notification = notifications.get(position);
        holder.userAvatar.setImageResource(notification.getAvatarResId());
        holder.notificationIcon.setImageResource(notification.getNotificationIconResId());
        holder.userName.setText(notification.getUserName());
        holder.notificationContent.setText(notification.getNotificationContent());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ImageView userAvatar, notificationIcon;
        TextView userName, notificationContent;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.userAvatar);
            notificationIcon = itemView.findViewById(R.id.notificationIcon);
            userName = itemView.findViewById(R.id.userName);
            notificationContent = itemView.findViewById(R.id.notificationContent);
        }
    }
}
