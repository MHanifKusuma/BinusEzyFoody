package com.example.midtermproject_binusezyfoody;

import java.util.ArrayList;

public class Drinks {

    private String name;
    private int price;
    private int imageResourceId;
    private int stock;


    public Drinks() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
