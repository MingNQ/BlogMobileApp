package com.example.blogmobileapp.model;

public class CommentModel {
    private int avatarResId;
    private String userName;
    private String createDate;
    private String commentContent;

    public CommentModel(int avatarResId, String userName, String createDate, String commentContent) {
        this.avatarResId = avatarResId;
        this.userName = userName;
        this.createDate = createDate;
        this.commentContent = commentContent;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCommentContent() {
        return commentContent;
    }
}
