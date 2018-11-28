package com.example.magnetification.journal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        list.setOnItemClickListener(new ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clickedEntry = (Cursor) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", clickedEntry.getString(1));
            intent.putExtra("text", clickedEntry.getString(2));
            intent.putExtra("mood", clickedEntry.getInt(3));
            intent.putExtra("time", clickedEntry.getString(4));
            startActivity(intent);
        }
    }
}
