package com.example.midtermproject_binusezyfoody;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyOrder {
    private String name;
    private Integer price;
    private Integer imageResourceId;
    private Integer quantity;
    private Integer grandTotal;

    public static final MyOrder[] orderList = {
        new MyOrder("Air Mineral", 123, R.drawable.mineral_icon, 10),
        new MyOrder("Jus Alpukat", 123, R.drawable.avocado_icon, 4),
        new MyOrder("Jus Apel", 123, R.drawable.apple_icon, 5),
        new MyOrder("Air Mineral", 123, R.drawable.mineral_icon, 3),
        new MyOrder("Jus Mangga", 123, R.drawable.manggo_icon, 4),
        new MyOrder("Jus Apel", 123, R.drawable.apple_icon, 15),
        new MyOrder("Jus Alpukat", 123, R.drawable.avocado_icon, 2),
        new MyOrder("Air Mineral", 123, R.drawable.mineral_icon, 30),
        new MyOrder("Jus Mangga", 123, R.drawable.manggo_icon, 6),
        new MyOrder("Jus Apel", 123, R.drawable.apple_icon, 8)
    };

    public MyOrder(String name, Integer price, Integer imageResourceId, Integer quantity) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(Integer imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getGrandTotal() {
        int items = orderList.length;
        int count = 0;
        int subtotal;

        while(count < items) {
            subtotal = 0;
            subtotal = orderList[count].getPrice() * orderList[count].getQuantity();
            grandTotal = grandTotal + subtotal;

            count++;
        }

        return grandTotal;
    }

    public static MyOrder[] getOrderList() {
        return orderList;
    }
}
