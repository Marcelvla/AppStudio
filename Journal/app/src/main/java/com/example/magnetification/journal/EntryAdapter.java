package com.example.magnetification.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

public class EntryAdapter extends ResourceCursorAdapter {

    private Cursor cs;

    public EntryAdapter(Context context, Cursor cursor) {
        super(context, R.layout.entry_row, cursor);
        this.cs = cursor;
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {
        String titleString = cs.getString(1);
        String textString = cs.getString(2).substring(0,2) + "...";
        int moodid = cs.getInt(3);
        String timeString = cs.getString(4);

        switchMood(moodid, v);

        TextView titleView = v.findViewById(R.id.title);
        titleView.setText(titleString);

        TextView dateView = v.findViewById(R.id.date);
        dateView.setText(timeString);

        TextView preView = v.findViewById(R.id.preview);
        preView.setText(textString);
    }


    public void switchMood(int moodid, View v) {
        ImageView moodIm = v.findViewById(R.id.moodImage);
        switch (moodid) {
            case 1:
                moodIm.setImageResource(R.drawable.afraid);
                break;
            case 2:
                moodIm.setImageResource(R.drawable.angry);
                break;
            case 3:
                moodIm.setImageResource(R.drawable.embarrased);
                break;
            case 4:
                moodIm.setImageResource(R.drawable.happy);
                break;
            case 5:
                moodIm.setImageResource(R.drawable.joyous);
                break;
            case 6:
                moodIm.setImageResource(R.drawable.proud);
                break;
            case 7:
                moodIm.setImageResource(R.drawable.sad);
                break;
            case 8:
                moodIm.setImageResource(R.drawable.upset);
                break;
            case 9:
                moodIm.setImageResource(R.drawable.worried);
                break;
        }
    }
}
