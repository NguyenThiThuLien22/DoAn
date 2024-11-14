package com.example.doan;



public class ActivityItem {
    private String description;
    private String time;
    private String amount;

    public ActivityItem(String description, String time, String amount) {
        this.description = description;
        this.time = time;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }
}
