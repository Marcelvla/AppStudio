package com.example.magnetification.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {

    // Simple class for a menuitem, couple of global variables, getters and setters
    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    public MenuItem(String aName, String aDescription, String aURL, double aPrice, String cat) {
        name = aName;
        description = aDescription;
        imageUrl = aURL;
        price = aPrice;
        category = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
