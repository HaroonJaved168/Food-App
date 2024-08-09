package com.example.foodapp.Domain;

public class FoodItemAdmin {
    private String title;
    private int categoryId;
    private String description;
    private int id;
    private double price;
    private boolean bestFood;
    private String imageUrl; // Add this field

    public FoodItemAdmin() {}

    public FoodItemAdmin(String title, int categoryId, String description, int id, double price, boolean bestFood, String imageUrl) {
        this.title = title;
        this.categoryId = categoryId;
        this.description = description;
        this.id = id;
        this.price = price;
        this.bestFood = bestFood;
        this.imageUrl = imageUrl; // Initialize this field
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBestFood() {
        return bestFood;
    }

    public void setBestFood(boolean bestFood) {
        this.bestFood = bestFood;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
