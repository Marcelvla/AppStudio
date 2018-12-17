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

    private EntryDatabase db;

    // shows all joural
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

        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cs = db.selectAll();

        EntryAdapter adapter = new EntryAdapter(this, cs);
        ListView list = findViewById(R.id.entryList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ListItemClickListener());
        list.setOnItemLongClickListener(new ListItemLongClickListener());
    }

    // listitem click listener, starts the activitiy with the right contents of the journal
    // since journalentry implements serializable this could have been done passing the class instance
    // for the clicked entry, but in this case I didn't because youre working with cursors
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

    // Longclickhandler, deletes the journal entry which has been longclicked
    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor clickedEntry = (Cursor) adapterView.getItemAtPosition(i);
            long id = clickedEntry.getLong(0);
            db.deleteEntry(id);
            finish();
            startActivity(getIntent());
            return true;
        }
    }
}
