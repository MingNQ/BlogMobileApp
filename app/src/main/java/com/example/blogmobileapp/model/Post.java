package com.example.blogmobileapp.model;

public class Post {
    private String title;
    private String authorName;
    private String date;
    private String timeReading;
    private int thumbnailResId;
    private int authorAvtResId;
    private int reactCount;
    private int commentCount;

    public Post(String title, String authorName, String date, String timeReading, int thumbnailResId, int authorAvtResId, int reactCount, int commentCount) {
        this.title = title;
        this.authorName = authorName;
        this.date = date;
        this.timeReading = timeReading;
        this.thumbnailResId = thumbnailResId;
        this.authorAvtResId = authorAvtResId;
        this.reactCount = reactCount;
        this.commentCount = commentCount;
    }

    public String getTitle() {
        return title;
    }

    public int getReactCount() {
        return reactCount;
    }

    public void setReactCount(int reactCount) {
        this.reactCount = reactCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeReading() {
        return timeReading;
    }

    public void setTimeReading(String timeReading) {
        this.timeReading = timeReading;
    }

    public int getThumbnailResId() {
        return thumbnailResId;
    }

    public void setThumbnailResId(int thumbnailResId) {
        this.thumbnailResId = thumbnailResId;
    }

    public int getAuthorAvtResId() {
        return authorAvtResId;
    }

    public void setAuthorAvtResId(int authorAvtResId) {
        this.authorAvtResId = authorAvtResId;
    }
}
