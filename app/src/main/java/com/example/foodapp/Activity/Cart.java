package com.example.foodapp.Activity;

import com.example.foodapp.Activity.ListData;
import java.util.ArrayList;

public class Cart {
    private static Cart instance;
    private ArrayList<ListData> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(ListData item) {
        cartItems.add(item);
    }

    public ArrayList<ListData> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
