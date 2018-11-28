package com.example.magnetification.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton newEntry = findViewById(R.id.newEntry);
        newEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startEntry = new Intent(v.getContext(), InputActivity.class);
                startActivity(startEntry);
            }
        });

        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cs = db.selectAll();

        EntryAdapter adapter = new EntryAdapter(this, cs);
        ListView list = findViewById(R.id.entryList);
        list.setAdapter(adapter);
//        list.setOnItemClickListener(new ListItemClickListener());
    }
}
