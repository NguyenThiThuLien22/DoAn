package com.example.doan.Domain;

public class Foods {





    private double Price;
    private int resourceId;

    private double Star;

    private int TimeValue;
    private String Title;


    public Foods() {
    }

    public Foods(double price, int resourceId, double star, int timeValue, String title) {
        Price = price;
        this.resourceId = resourceId;
        Star = star;
        TimeValue = timeValue;
        Title = title;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public double getStar() {
        return Star;
    }

    public void setStar(double star) {
        Star = star;
    }

    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        TimeValue = timeValue;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
