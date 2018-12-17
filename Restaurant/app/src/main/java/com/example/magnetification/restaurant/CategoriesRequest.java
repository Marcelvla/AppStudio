package com.example.magnetification.restaurant;

import android.content.Context;
import android.telecom.Call;
import android.view.KeyEvent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context ct;
    private Callback ac;

    public CategoriesRequest(Context context) {
        this.ct = context;
    }

    // Callback for the cateories request.
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);

    }

    // sends the error message back to function gotCategoriesError
    @Override
    public void onErrorResponse(VolleyError error) {
        ac.gotCategoriesError(error.getMessage());
    }

    // On receiving the categories it passes an arraylist with all categories to the function gotCategories
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray cats = response.getJSONArray("categories");
            ArrayList<String> cat = new ArrayList<>(cats.length());

            for (int i = 0; i < cats.length(); i++) {
                cat.add(cats.getString(i));
            }

            ac.gotCategories(cat);
        } catch (JSONException e) {
            ac.gotCategoriesError("Something went wrong retrieving categories");
        }
    }

    // makes the request for the categories
    public void getCategories(Callback activity) {
        ac = activity;
        RequestQueue queue = Volley.newRequestQueue(ct);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
