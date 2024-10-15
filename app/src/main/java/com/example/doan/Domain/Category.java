package com.example.doan.Domain;

public class Category {

    private int resourceId;
    private String title; // Đổi từ Title sang title

    public Category(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title; // Đổi từ Title sang title
    }

    public void setTitle(String title) {
        this.title = title; // Đổi từ Title sang title
    }
}
