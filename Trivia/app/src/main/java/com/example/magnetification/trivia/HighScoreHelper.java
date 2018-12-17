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

    // Callback functions for when the highscores are retrieved.
    public interface Callback {
        void gotHighScores(JSONArray scoreList, Callback ac);
        void gotHighScoresError(String message);
    }

    // Passes error message to gotHighScoresError when something went wrong retrieving the highscores.
    @Override
    public void onErrorResponse(VolleyError error) {
        ac.gotHighScoresError(error.getMessage());
    }

    // Very lazily passes the response to gotHighScores function, Hooray for not optimizing things
    @Override
    public void onResponse(JSONArray response) {
        ac.gotHighScores(response, ac);
    }

    // Function that makes request for the highscores.
    public void getScores(Callback activity, String url) {
        ac = activity;
        RequestQueue queue = Volley.newRequestQueue(ct);

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(url, this, this);
        queue.add(jsonArrayRequest);
    }
}
