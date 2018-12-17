package com.example.magnetification.trivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighScoreAdapter extends ArrayAdapter<HighScore> {

    private ArrayList<HighScore> scorelist;

    public HighScoreAdapter(Context context, int resource, ArrayList<HighScore> aScores) {
        super(context, resource, aScores);
        this.scorelist = aScores;
    }

    // Sets highscores in the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }

        HighScore hs = scorelist.get(position);

        TextView nameItem = convertView.findViewById(R.id.nameView);
        TextView scoreItem = convertView.findViewById(R.id.scoreView);
        String name = "Name: " + hs.getName();
        String score = "Score: " + String.valueOf(hs.getScore());
        nameItem.setText(name);
        scoreItem.setText(score);

        return convertView;
    }
}