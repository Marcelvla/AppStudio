package com.example.magnetification.friendsr;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name, bio;
    private int drawableId;
    private float rating;

    public Friend(String aName, String aBio, int aDrawableId) {
        name = aName;
        bio = aBio;
        drawableId = aDrawableId;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
