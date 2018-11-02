package com.example.magnetification.mrpotatohead;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkClicked(View v) {
        CheckBox checkbox = (CheckBox) v;
        String boxname = checkbox.getText().toString();

        Log.d("potato", boxname);
        ImageView image = findImage(boxname);

        if (image.getVisibility() == View.INVISIBLE) {
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.INVISIBLE);
        }
    }

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
