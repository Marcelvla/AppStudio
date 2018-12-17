package com.example.magnetification.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context ct;
    private Callback ac;
    private String cat;

    public MenuRequest(Context context) {
        this.ct = context;
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        ac.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray menu = response.getJSONArray("items");
            ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

            for (int i = 0; i < menu.length(); i++) {
                JSONObject item = menu.getJSONObject(i);
                if (item.get("category").equals(cat)) {
                    menuList.add(new MenuItem(  (String) item.get("name"),
                                                (String) item.get("description"),
                                                (String) item.get("image_url"),
                                                (double) item.get("price"),
                                                (String) item.get("category")));
                }
            }

            ac.gotMenu(menuList);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMenu(Callback activity, String category) {
        ac = activity;
        cat = category;

        RequestQueue queue = Volley.newRequestQueue(ct);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);
    }
}
