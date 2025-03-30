package com.example.blogmobileapp.model;

public class NotificationModel {
    private int avatarResId;
    private String userName;
    private String notificationContent;
    private int notificationIconResId;

    public NotificationModel(int avatarResId, String userName, String notificationContent, int notificationIconResId) {
        this.avatarResId = avatarResId;
        this.userName = userName;
        this.notificationContent = notificationContent;
        this.notificationIconResId = notificationIconResId;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public String getUserName() {
        return userName;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public int getNotificationIconResId() {
        return notificationIconResId;
    }
}
