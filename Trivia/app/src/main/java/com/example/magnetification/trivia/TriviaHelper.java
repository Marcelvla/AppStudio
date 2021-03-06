package com.example.magnetification.trivia;

import android.app.AuthenticationRequiredException;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TriviaHelper implements Response.Listener<JSONObject>, Response.ErrorListener, Serializable {

    private ArrayList<Question> questions = new ArrayList<>();
    private int current;
    private Context ct;
    private Callback ac;
    private int max;

    public TriviaHelper(Context context) {
        this.ct = context;
        current = 0;
    }

    // Callback function for retrieving the questions
    public interface Callback {
        void gotQuestions(ArrayList<Question> lst);
        void gotQuestionsError(String message);
    }

    // passes the error to function gotQuestonsError
    @Override
    public void onErrorResponse(VolleyError error) {
        ac.gotQuestionsError(error.getMessage());
    }

    // When the questions are retrieved makes an arraylist with all the questions in it and passes it to
    // the function gotQuestions.
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray quest = response.getJSONArray("results");
            max = quest.length() - 1;

            for (int i = 0; i <= max; i++) {
                JSONObject questArr = quest.getJSONObject(i);

                String cat = questArr.getString("category");
                String type = questArr.getString("type");
                String diff = questArr.getString("difficulty");
                String question = String.valueOf(Html.fromHtml(questArr.getString("question")));
                String correctAns = String.valueOf(Html.fromHtml(questArr.getString("correct_answer")));
                JSONArray wrongAnsJSON =  questArr.getJSONArray("incorrect_answers");

                ArrayList<String> wrongAns = new ArrayList<>();
                for (int k = 0; k < wrongAnsJSON.length(); k++) {
                    wrongAns.add(String.valueOf(Html.fromHtml(wrongAnsJSON.getString(k))));
                }

                Question q = new Question(question, wrongAns, correctAns, cat, diff, type);
                questions.add(q);

            }

            ac.gotQuestions(questions);
        } catch (JSONException e) {
            ac.gotQuestionsError(e.getMessage());
        }
    }

    // Function that makes the API request with given URL for the questions.
    public void getQuestions(Callback activity, String url) {
        ac = activity;
        RequestQueue queue = Volley.newRequestQueue(ct);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // Returns the first question that has to be answered
    public Question getCurrentQuestion() {
        return questions.get(current);
    }

    // returns the number of the first question that has to be answered
    public int getQuestionNumber() {
        return current;
    }

    // increase current by one to get the next question
    public void nextQuestion() {
        current += 1;
    }

    // returns the number of quesions in the array. 
    public int getMax() {
        return max;
    }
}
