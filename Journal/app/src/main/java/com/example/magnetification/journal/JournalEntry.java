package com.example.magnetification.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable  {

    private String title;
    private String content;
    private int mood;
    private String timestamp;

    public JournalEntry(String aTitle, String aContent, int aMood, String aTimestamp) {
        title = aTitle;
        content = aContent;
        mood = aMood;
        timestamp = aTimestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getMood() {
        return mood;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
