package com.example.magnetification.journal;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.DropBoxManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Spinner dropdown = findViewById(R.id.moodSpin);
        final String[] items = new String[]{"select", "Afraid", "Angry", "Embarrased", "Happy", "Joyous", "Proud", "Sad", "Upset", "Worried"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Button confirmEntry = findViewById(R.id.confirmEntry);
        confirmEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (addEntry(items) == 1) {
                    Intent startEntry = new Intent(v.getContext(), MainActivity.class);
                    startActivity(startEntry);
                }
            }
        });
    }

    private int addEntry(String[] items) {
        TextView titleField = findViewById(R.id.title);
        String title = titleField.getText().toString();

        TextView entryField = findViewById(R.id.entry);
        String text = entryField.getText().toString();

        Spinner moodSpin = findViewById(R.id.moodSpin);
        String mood = moodSpin.getSelectedItem().toString();
        int index = Arrays.asList(items).indexOf(mood);

        if (mood.equals("select")) {
            Toast.makeText(getApplicationContext(), "Select a mood!", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (title.equals("")) {
            Toast.makeText(getApplicationContext(), "Enter a title!", Toast.LENGTH_SHORT).show();
            return 0;
        } else if (text.equals("")) {
            Toast.makeText(getApplicationContext(), "You didn't write anything!", Toast.LENGTH_SHORT).show();
            return 0;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
        String timestamp = sdf.format(new Date());

        JournalEntry entry = new JournalEntry(title, text, index, timestamp);
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        db.insert(entry);
        return 1;
    }


}
