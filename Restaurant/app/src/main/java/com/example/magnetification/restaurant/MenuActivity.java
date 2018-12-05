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

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menuItems);
        ListView list = findViewById(R.id.menuList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // ArrayAdapter for the listview in MenuActivity
    public class MenuAdapter extends ArrayAdapter<MenuItem> {

        private ArrayList<MenuItem> menu;

        public MenuAdapter(Context context, int resource, ArrayList<MenuItem> menuList) {
            super(context, resource, menuList);
            this.menu = menuList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
            }

            MenuItem dish = menu.get(position);

            ImageView photo = convertView.findViewById(R.id.dishPhoto);
            Picasso.get().load(dish.getImageUrl()).into(photo);

            TextView name = convertView.findViewById(R.id.dishName);
            name.setText(dish.getName());

            TextView price = convertView.findViewById(R.id.price);
            String pr = "€" + dish.getPrice();
            price.setText(pr);

            return convertView;
        }
    }

    // OnItemClickListener for the categories list
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
