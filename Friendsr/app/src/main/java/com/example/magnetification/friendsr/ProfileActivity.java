package com.example.magnetification.friendsr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    // Global Variables
    private Friend retrievedFriend;
    private SharedPreferences prefs;

    // Sets the image, name, rating and bio for the clicked friend. 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView profile = findViewById(R.id.profilePic);
        profile.setImageDrawable(getDrawable(retrievedFriend.getDrawableId()));

        TextView name = findViewById(R.id.profileName);
        name.setText(retrievedFriend.getName());
        name.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/GOT.ttf"));

        TextView bio = findViewById(R.id.bio);
        bio.setText(retrievedFriend.getBio());

        RatingBar ratingBar = findViewById(R.id.ratingBar);

        prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        float rating = prefs.getFloat(retrievedFriend.getName(), 0);
        if (rating != 0) {
            ratingBar.setRating(rating);
        }

        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener());
    }

    // Sets the rating given to the friend and saves it.
    private class OnRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
            if (b) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat(retrievedFriend.getName(), rating);
                editor.apply();
            }
        }
    }
}
