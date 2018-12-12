package com.example.magnetification.trivia;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends StringRequest {

    private String name;
    private int score;

    // Constructor
    public PostRequest(int method, String url, Response.Listener listener, Response.ErrorListener errorListener, String aName, int aScore) {
        super(method, url, listener, errorListener);
        name = aName;
        score = aScore;
    }

    // Method to supply parameters to the request
    @Override
    protected Map<String, String> getParams() {

        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("score", String.valueOf(score));
        return params;
    }
}