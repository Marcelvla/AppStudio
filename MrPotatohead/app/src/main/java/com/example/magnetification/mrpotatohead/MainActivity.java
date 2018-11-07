package com.example.magnetification.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    String[] pics = {"Shoes", "Eyes", "Arms", "Ears", "Nose", "Glasses", "Mustache", "Mouth", "Eyebrows", "Hat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            for (int i = 0; i<pics.length; i++) {
                int state = savedInstanceState.getInt(pics[i]);
                System.out.println(state);
                findImage(pics[i]). setVisibility(state);
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i=0; i<pics.length; i++) {
            outState.putInt(pics[i], findImage(pics[i]).getVisibility());
        }
    }

    // Main function that's called when a button is clicked
    public void checkClicked(View v) {
        // Get checkbox and its id, use that id to find the right image
        CheckBox checkbox = (CheckBox) v;
        String boxname = checkbox.getText().toString();
        ImageView image = findImage(boxname);

        // Set the image to visible if its currently invisible and vice versa
        if (image.getVisibility() == View.INVISIBLE) {
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.INVISIBLE);
        }
    }

    // Function that returns the right imageView object with the given checkbox id
    private ImageView findImage(String boxname) {
        if (boxname.equals("Shoes")) {
            return findViewById(R.id.shoes);
        } else if (boxname.equals("Eyes")) {
            return findViewById(R.id.eyes);
        } else if (boxname.equals("Arms")) {
            return findViewById(R.id.arms);
        } else if (boxname.equals("Ears")) {
            return findViewById(R.id.ears);
        } else if (boxname.equals("Nose")) {
            return findViewById(R.id.nose);
        } else if (boxname.equals("Glasses")) {
            return findViewById(R.id.glasses);
        } else if (boxname.equals("Mustache")) {
            return findViewById(R.id.mustache);
        } else if (boxname.equals("Mouth")) {
            return findViewById(R.id.mouth);
        } else if (boxname.equals("Eyebrows")) {
            return findViewById(R.id.eyebrows);
        } else {
            return findViewById(R.id.hat);
        }
    }
}
