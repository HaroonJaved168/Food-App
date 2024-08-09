package com.example.foodapp.Activity;

import java.io.Serializable;

public class ListData implements Serializable {

    public String title;
    public double price;
    public String image;

    public ListData(String title, double price, String image) {
        this.title = title;
        this.price = price;
        this.image = image;
    }
}
