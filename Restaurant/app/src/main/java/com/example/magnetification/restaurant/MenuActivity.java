package com.example.magnetification.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    // Does the request for retrieving the menu items
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String retrievedCat = (String) intent.getSerializableExtra("clicked_category");

        TextView menuCat = findViewById(R.id.menuCategory);
        menuCat.setText(retrievedCat);

        MenuRequest x = new MenuRequest(this);
        x.getMenu(this, retrievedCat);
    }

    // sets the menu items for the selected category
    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);
        ListView list = findViewById(R.id.menuList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ListItemClickListener());
    }

    // shows error message when something went wrong retrieving the menu items.
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // OnItemClickListener for the categories list, starts the detail view for a menu item.
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MenuItem clickedDish = (MenuItem) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clicked_dish", clickedDish);
            startActivity(intent);
        }
    }
}
