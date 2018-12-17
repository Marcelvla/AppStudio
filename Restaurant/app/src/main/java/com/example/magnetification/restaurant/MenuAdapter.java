package com.example.magnetification.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

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
        double p = dish.getPrice();
        String pr = "€" + String.format(Locale.getDefault(), "%.2f", p);
        price.setText(pr);

        return convertView;
    }
}
