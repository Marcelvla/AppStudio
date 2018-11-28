package com.example.magnetification.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable  {

    private int id;
    private float title;
    private float content;
    private int mood;
    private int timestamp;

    public JournalEntry(float title, float content, int mood, int timestamp) {

    }

    public int getId() {
        return id;
    }

    public float getTitle() {
        return title;
    }

    public float getContent() {
        return content;
    }

    public int getMood() {
        return mood;
    }

    public int getTimestamp() {
        return timestamp;
    }

}
