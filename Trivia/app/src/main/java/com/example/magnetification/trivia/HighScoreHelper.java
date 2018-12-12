package com.example.magnetification.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighScoreHelper implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context ct;
    private Callback ac;

    public HighScoreHelper(Context context) {
        this.ct = context;
    }

    public interface Callback {
        void gotHighScores(JSONArray scoreList, Callback ac);
        void gotHighScoresError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        ac.gotHighScoresError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        ac.gotHighScores(response, ac);
    }

    public void getScores(Callback activity, String url) {
        ac = activity;
        RequestQueue queue = Volley.newRequestQueue(ct);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }
}
