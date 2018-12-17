package com.example.magnetification.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    // sets the layout to show the contents of the clicked journal entry
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String title = (String) intent.getSerializableExtra("title");
        String text = (String) intent.getSerializableExtra("text");
        int mood = (int) intent.getSerializableExtra("mood");
        String time = (String) intent.getSerializableExtra("time");

        ImageView moodView = findViewById(R.id.moodImage);
        switchMood(mood, moodView);

        TextView titleView = findViewById(R.id.entryTitle);
        titleView.setText(title);

        TextView contentView = findViewById(R.id.journalEntry);
        contentView.setText(text);

        TextView timestamp = findViewById(R.id.timeStamp);
        timestamp.setText(time);

    }

    // switches the mood image for the current entry
    public void switchMood(int moodid, View v) {
        ImageView moodIm = v.findViewById(R.id.moodImage);
        switch (moodid) {
            case 1:
                moodIm.setImageResource(R.drawable.afraid);
                break;
            case 2:
                moodIm.setImageResource(R.drawable.angry);
                break;
            case 3:
                moodIm.setImageResource(R.drawable.embarrased);
                break;
            case 4:
                moodIm.setImageResource(R.drawable.happy);
                break;
            case 5:
                moodIm.setImageResource(R.drawable.joyous);
                break;
            case 6:
                moodIm.setImageResource(R.drawable.proud);
                break;
            case 7:
                moodIm.setImageResource(R.drawable.sad);
                break;
            case 8:
                moodIm.setImageResource(R.drawable.upset);
                break;
            case 9:
                moodIm.setImageResource(R.drawable.worried);
                break;
        }
    }
}
