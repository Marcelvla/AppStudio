package com.example.magnetification.friendsr;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.magnetification.friendsr.Friend;
import com.example.magnetification.friendsr.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends ArrayAdapter<Friend> {

    private ArrayList<Friend> friends;

    public FriendsAdapter(Context context, int resource, ArrayList<Friend> friendList) {
        super(context, resource, friendList);
        this.friends = friendList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        de.hdodenhof.circleimageview.CircleImageView friendIm = convertView.findViewById(R.id.friendImage);
        friendIm.setImageDrawable(getContext().getDrawable(friends.get(position).getDrawableId()));

        TextView friendText = convertView.findViewById(R.id.friendName);
        friendText.setText(friends.get(position).getName());
        friendText.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/GOT.ttf"));

        return convertView;
    }
}
