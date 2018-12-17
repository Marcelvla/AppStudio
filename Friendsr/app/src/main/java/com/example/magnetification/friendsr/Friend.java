package com.example.magnetification.friendsr;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name, bio;
    private int drawableId;

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
}
