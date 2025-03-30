package com.example.blogmobileapp.model;

public class PostModel {
    private String title;
    private String author;
    private String timeReading;
    private String content;
    private String category;
    private String thumbnailResId;
    private String authorAvtResId;
    private int likes;
    private int commentCount;
    private long timestamp;

    public PostModel() {
    }

    public PostModel(String author, String category, String content, int likes, long timestamp, String title) {
        this.author = author;
        this.category = category;
        this.content = content;
        this.likes = likes;
        this.timestamp = timestamp;
        this.title = title;
    }

    public PostModel(String title, String authorName, String date, String timeReading, int thumbnailResId, int authorAvtResId, int reactCount, int commentCount) {
        this.title = title;
        this.author = authorName;
        this.timeReading = timeReading;
        this.likes = reactCount;
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTimeReading() {
        return timeReading;
    }

    public void setTimeReading(String timeReading) {
        this.timeReading = timeReading;
    }

    public String getThumbnailResId() {
        return thumbnailResId;
    }

    public void setThumbnailResId(String thumbnailResId) {
        this.thumbnailResId = thumbnailResId;
    }

    public String getAuthorAvtResId() {
        return authorAvtResId;
    }

    public void setAuthorAvtResId(String authorAvtResId) {
        this.authorAvtResId = authorAvtResId;
    }
}
