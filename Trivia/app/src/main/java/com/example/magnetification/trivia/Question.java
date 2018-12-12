package com.example.magnetification.trivia;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question implements Serializable {

    private String question;
    private ArrayList<String> falseAnswers;
    private String correctAnswer;
    private String category;
    private String difficulty;
    private String type;

    public Question(String aQuestion, ArrayList<String> aFalseAnswers, String aCorrectAnswer, String cat, String diff, String aType) {
        question = aQuestion;
        falseAnswers = aFalseAnswers;
        correctAnswer = aCorrectAnswer;
        category = cat;
        difficulty = diff;
        type = aType;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getFalseAnswers() {
        return falseAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }
}


