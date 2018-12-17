package com.example.magnetification.marcelvdla_pset2;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoryTimeAdapter extends ArrayAdapter<String> {

    private ArrayList<String> story;

    public StoryTimeAdapter (Context context, int resource, ArrayList<String> storyList) {
        super(context, resource, storyList);
        this.story = storyList;
    }

    // Adapter for the listview for different stories.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView storyItem = convertView.findViewById(R.id.story);
        storyItem.setText(story.get(position));

        return convertView;
    }
}
