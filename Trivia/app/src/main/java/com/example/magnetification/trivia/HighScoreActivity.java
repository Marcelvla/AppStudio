package com.example.magnetification.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

public class HighScoreActivity extends AppCompatActivity implements HighScoreHelper.Callback {

    private HighScoreHelper help;
    private HighScoreList highScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        help = new HighScoreHelper(this);
        help.getScores(this, "https://ide50-magnetification.cs50.io:8080/scores");


        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void gotHighScores(JSONArray scoreList, HighScoreHelper.Callback ac) {
        highScores = new HighScoreList(scoreList, ac);

        HighScoreAdapter adapter = new HighScoreAdapter(this, R.layout.score_item, highScores.getHighScorelist());
        ListView list = findViewById(R.id.highscoreList);
        list.setAdapter(adapter);
    }

    @Override
    public void gotHighScoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
