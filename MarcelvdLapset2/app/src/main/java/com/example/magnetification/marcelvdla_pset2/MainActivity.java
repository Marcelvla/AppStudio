package com.example.magnetification.marcelvdla_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // Sets the onclicklistener for the startbutton
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent  = new Intent(MainActivity.this, StoryActivity.class);
                 startActivity(intent);
             }
         });
    }
}
