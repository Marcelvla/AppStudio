package com.example.magnetification.trivia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class HighScoreList implements Serializable {

    private ArrayList<HighScore> scorelist;

    public HighScoreList(JSONArray response, HighScoreHelper.Callback ac) {
        try {
            scorelist = new ArrayList<>(response.length() + 1);
            for (int i = 0; i < response.length(); i++) {
                JSONObject item = response.getJSONObject(i);
                String score = item.getString("score");
                String name = item.getString("name");
                HighScore hs = new HighScore(Integer.parseInt(score), name);

                scorelist.add(hs);
            }
        } catch (JSONException e) {
            ac.gotHighScoresError(e.getMessage());
        }
    }

    public ArrayList<HighScore> getHighScorelist() {
        return scorelist;
    }
}
