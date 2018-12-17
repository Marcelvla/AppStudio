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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    // makes the request for categories.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest x = new CategoriesRequest(this);
        x.getCategories(this);
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }

    // Shows categories when these have been found by the API
    @Override
    public void gotCategories(ArrayList<String> categories) {
        CatAdapter adapter = new CatAdapter(this, R.layout.category_item, categories);
        ListView list = findViewById(R.id.categories);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ListItemClickListener());
    }

    // Shows error message when something went wrong retrieving the categories
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    // OnItemClickListener for the categories list
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String clickedCategory = (String) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("clicked_category", clickedCategory);
            startActivity(intent);
        }
    }
}
