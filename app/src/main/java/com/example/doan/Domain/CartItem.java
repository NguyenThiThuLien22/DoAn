package com.example.doan.Domain;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String title;
    private double price;
    private int quantity;
    private int imageResourceId;
    private double totalPrice;

    public CartItem(String title, double price, int quantity, int imageResourceId, double totalPrice) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.imageResourceId = imageResourceId;
        this.totalPrice = totalPrice; // Khởi tạo tổng tiền
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public double getTotalPrice() {
        return price * quantity; // Tính tổng giá trị
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
