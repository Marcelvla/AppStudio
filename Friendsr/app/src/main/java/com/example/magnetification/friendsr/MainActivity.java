package com.example.magnetification.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        friends = makeFriends();
        System.out.println(friends.get(0).getName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView grid = findViewById(R.id.friendGrid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new GridItemClickListener());
    }

    private ArrayList<Friend> makeFriends() {
        ArrayList<Friend> friendList = new ArrayList<>();

        String[] names = {"arya", "cersei", "daenerys", "jaime", "jon", "jorah", "margaery", "melisandre", "sansa", "tyrion"};
//        String[] bios = {''};

        for (String name:names) {
            Friend friend = new Friend(name, "bio", getResources().getIdentifier(name, "drawable", getPackageName()) );
            friendList.add(friend);
        }

        return friendList;
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Friend clickedFriend = (Friend) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }
}
