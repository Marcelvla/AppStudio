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

    // Sets the main menu interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, makeFriends());
        GridView grid = findViewById(R.id.friendGrid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new GridItemClickListener());
    }

    // Makes the list of friends that the interface uses to display the friends in the grid.
    private ArrayList<Friend> makeFriends() {
        ArrayList<Friend> friendList = new ArrayList<>();

        String[] names = {"arya", "cersei", "daenerys", "jaime", "jon", "jorah", "margaery", "melisandre", "sansa", "tyrion"};
        String[] bios = getResources().getStringArray(R.array.bios);


        for (int i = 0; i < names.length; i++) {
            Friend friend = new Friend(names[i], bios[i], getResources().getIdentifier(names[i], "drawable", getPackageName()) );
            friendList.add(friend);
        }

        return friendList;
    }

    // Starts the activitity for the profile of the character that has been clicked.
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
