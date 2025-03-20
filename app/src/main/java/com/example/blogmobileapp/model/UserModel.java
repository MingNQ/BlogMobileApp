package com.example.blogmobileapp.model;

public class UserModel {
    private String userId;
    private String username;
    private String fullname;
    private String email;
    private String photoUrl;
    private int avatarResId;

    public UserModel(String username, String fullname, int avatarResId) {
        this.username = username;
        this.fullname = fullname;
        this.avatarResId = avatarResId;
    }

    public UserModel(String userId, String username, String fullname, String email, String photoUrl) {
        this.userId = userId;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
