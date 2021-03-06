package com.example.magnetification.trivia;

import java.io.Serializable;

public class HighScore implements Serializable {

    // Simple highscore class with score value and name
    private int score;
    private String name;

    public HighScore(int aScore, String aName) {
        score = aScore;
        name = aName;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
