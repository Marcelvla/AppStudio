package com.example.magnetification.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Locale;

public class MenuItemActivity extends AppCompatActivity {

    // Sets the details on the layout for the clicked menu item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem dish = (MenuItem) intent.getSerializableExtra("clicked_dish");

        ImageView photo = findViewById(R.id.dishPhoto);
        Picasso.get().load(dish.getImageUrl()).into(photo);

        TextView name = findViewById(R.id.dishName);
        name.setText(dish.getName());

        TextView price = findViewById(R.id.dishPrice);
        double p = dish.getPrice();
        String pr = "€" + String.format(Locale.getDefault(), "%.2f", p);
        price.setText(pr);

        TextView description = findViewById(R.id.dishDescription);
        description.setText(dish.getDescription());
    }
}
