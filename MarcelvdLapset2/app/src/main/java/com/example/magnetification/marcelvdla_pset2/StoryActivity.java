package com.example.magnetification.marcelvdla_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class StoryActivity extends AppCompatActivity {

    // Global variables.
    private ArrayList<String> storyArrayList = new ArrayList<String>();
    private Story story;
    private TextView text;
    private TextView left;
    private TextView used;
    private TextView field;
    private Button confirm;

    // On saved instance state return to the current position in the story, otherwise lets you
    // choose the story you want to fill
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        if (savedInstanceState != null) {
            if (savedInstanceState.getSerializable("story") != null) {
                story = (Story) savedInstanceState.getSerializable("story");
                setGUI();
                String input = savedInstanceState.getString(("input"));
                field.setText(input);
            } else {
                makeStories();

                StoryTimeAdapter adapter = new StoryTimeAdapter(this, R.layout.list_item, storyArrayList);
                ListView list = findViewById(R.id.stories);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new ListItemClickListener());
            }
        } else {
            makeStories();

            StoryTimeAdapter adapter = new StoryTimeAdapter(this, R.layout.list_item, storyArrayList);
            ListView list = findViewById(R.id.stories);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new ListItemClickListener());
        }
    }

    // saves story to the outstate
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("story", story);
        String input = field.getText().toString();
        outState.putString("input", input);
    }

    // listitem listener to check what story was selected
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String clickedStory = (String) adapterView.getItemAtPosition(i);
            Toast.makeText(getApplicationContext(), "You chose the: " + clickedStory + " story", Toast.LENGTH_SHORT).show();
            fillStory(clickedStory, i);
        }
    }

    // add the different available stories to an array so it can be viewed in the list
    private void makeStories() {
        String[] storyList = {"simple", "tarzan", "university", "clothes", "dance"};
        storyArrayList.addAll(Arrays.asList(storyList));
    }

    // Start filling in the selected story
    private void fillStory(String name, int position) {
        String id = "madlib" + position + "_" + name;
        InputStream is = getResources().openRawResource(getResources().getIdentifier(id, "raw", getPackageName()));
        story = new Story(is);
        setGUI();
    }

    // Remove listview and add the views for filling in a story
    private void setGUI() {
        left = findViewById(R.id.wordsLeft);
        used = findViewById((R.id.wordsUsed));
        field = findViewById(R.id.wordField);
        confirm = findViewById(R.id.confirmWord);
        text = findViewById(R.id.choose);
        text.setText("Fill in the words to complete the story!");
        ListView list = findViewById(R.id.stories);

        list.setVisibility(View.INVISIBLE);
        left.setVisibility(View.VISIBLE);
        used.setVisibility(View.VISIBLE);
        field.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);

        String leftText = story.getPlaceholderRemainingCount() + " words of " + story.getPlaceholderCount() + " remaining";
        left.setText(leftText);
        String holder = story.getNextPlaceholder();
        used.setText("Fill in a/an " + holder);
    }

    // onclick function while filling the story. if the story is finished it shows the finished story
    // else it goes to next placeholder to be filled in.
    public void updateGUI(View v) {
        if (story.isFilledIn()) {
            text.setText("Here is your own madlibs story:");
            left.setVisibility(View.INVISIBLE);
            used.setText(story.toString());
            field.setVisibility(View.INVISIBLE);
            confirm.setVisibility(View.INVISIBLE);
            Button reset = findViewById(R.id.reset);
            reset.setVisibility(View.VISIBLE);
        } else {
            String input = field.getText().toString();
            if (input.equals("")) {
                Toast.makeText(getApplicationContext(), "You didn't submit a word", Toast.LENGTH_SHORT).show();
            } else {
                story.fillInPlaceholder(input);
                if (story.isFilledIn()) {
                    updateGUI(confirm);
                } else {
                    String holder = story.getNextPlaceholder();
                    field.setText("");
                    used.setText("Fill in a/an " + holder);
                    String leftText = story.getPlaceholderRemainingCount() + " words of " + story.getPlaceholderCount() + " remaining";
                    left.setText(leftText);
                }
            }
        }
    }

    // onlick function for the back button. Returns the user to the main activity
    public void returnMain(View v) {
        story.clear();
        Intent intent = new Intent(StoryActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
