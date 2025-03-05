package com.example.blogmobileapp.model;

public class User {
    private String username;
    private String fullname;
    private int avatarResId;

    public User(String username, String fullname, int avatarResId) {
        this.username = username;
        this.fullname = fullname;
        this.avatarResId = avatarResId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }
}
