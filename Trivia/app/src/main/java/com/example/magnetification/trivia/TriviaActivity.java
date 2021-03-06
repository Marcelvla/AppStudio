package com.example.magnetification.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TriviaActivity extends AppCompatActivity implements TriviaHelper.Callback {

    private TriviaHelper help;
    private int score;
    private String name;

    // if you already started the quiz loads the current question, else requests the questions
    // with the url previously provided
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            String url = (String) intent.getSerializableExtra("url");
            name = (String) intent.getSerializableExtra("name");
            score = 0;

            help = new TriviaHelper(this);
            help.getQuestions(this, url);
        } else {
            help = (TriviaHelper) savedInstanceState.getSerializable("trivia_help");
            fillQuestion();
        }
    }

    // saves current progress in the quiz
    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putSerializable("trivia_help", help);
    }

    // when the app received the questions it fills in the first, let the quiz begin!
    @Override
    public void gotQuestions(ArrayList<Question> lst) {
        fillQuestion();
    }

    // shows error messsage when someting went wrong getting the questions.
    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // onclick handler, immediately shows if you answered the question correctly, updates the score
    // accordingly, checks if you were at the last question and posts your score if you're done. Else
    // the next question is loaded.
    public void checkClicked(View v) {
        Button b = (Button) v;
        if (b.getText().equals(help.getCurrentQuestion().getCorrectAnswer())) {
            Toast.makeText(this, "You answered correctly!", Toast.LENGTH_SHORT).show();
            updateScore(help.getCurrentQuestion().getDifficulty());
        } else {
            String msg = "No, the correct answer was: " + help.getCurrentQuestion().getCorrectAnswer();
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
        }

        if (help.getQuestionNumber() == help.getMax()) {
            postScore();
        } else {
            help.nextQuestion();
            fillQuestion();
        }
    }

    // Gets current question and sets the values of all attributes in the layout
    public void fillQuestion() {
        Question q = help.getCurrentQuestion();
        int number = help.getQuestionNumber();

        TextView head = findViewById(R.id.questionHeader);
        String qnumber = "Question " + Integer.toString(number + 1);
        head.setText(qnumber);

        Button answerA = findViewById(R.id.answerOne);
        Button answerB = findViewById(R.id.answerTwo);
        Button answerC = findViewById(R.id.answerThree);
        Button answerD = findViewById(R.id.answerFour);
        TextView quest = findViewById(R.id.question);
        TextView cat = findViewById(R.id.putCat);
        TextView diff = findViewById(R.id.putDiff);

        quest.setText(q.getQuestion());
        cat.setText(q.getCategory());
        diff.setText(q.getDifficulty());

        if (q.getType().equals("multiple")) {
            if (answerC.getVisibility() == View.INVISIBLE) {
                answerC.setVisibility(View.VISIBLE);
                answerD.setVisibility(View.VISIBLE);
            }

            // randomize the place where the correct answer is
            Random rand = new Random();
            int n = rand.nextInt(4);
            ArrayList<String> falseAnswers = q.getFalseAnswers();

            switch (n) {
                case 0:
                    answerA.setText(q.getCorrectAnswer());
                    answerB.setText(falseAnswers.get(0));
                    answerC.setText(falseAnswers.get(1));
                    answerD.setText(falseAnswers.get(2));
                    break;
                case 1:
                    answerB.setText(q.getCorrectAnswer());
                    answerA.setText(falseAnswers.get(0));
                    answerC.setText(falseAnswers.get(1));
                    answerD.setText(falseAnswers.get(2));
                    break;
                case 2:
                    answerC.setText(q.getCorrectAnswer());
                    answerA.setText(falseAnswers.get(0));
                    answerB.setText(falseAnswers.get(1));
                    answerD.setText(falseAnswers.get(2));
                    break;
                case 3:
                    answerD.setText(q.getCorrectAnswer());
                    answerA.setText(falseAnswers.get(0));
                    answerB.setText(falseAnswers.get(1));
                    answerC.setText(falseAnswers.get(2));
                    break;
            }
        } else {
            // for true false questions make 2 possibilities invisible
            answerA.setText("False");
            answerB.setText("True");
            answerC.setVisibility(View.INVISIBLE);
            answerD.setVisibility(View.INVISIBLE);
        }
    }

    // calculate score after answering, easy questions get 1 point, medium 3 pojnts and hard 5 points.
    private void updateScore(String diff) {
        switch (diff) {
            case "easy":
                score += 1;
                break;
            case "medium":
                score += 3;
                break;
            case "hard":
                score += 5;
                break;
        }
    }

    // Sends the request to the server to post the score of your quiz.
    private void postScore() {
        String url = "https://ide50-magnetification.cs50.io:8080/scores";
        RequestQueue queue = Volley.newRequestQueue(this);
        PostRequest request = new PostRequest(Request.Method.POST, url, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                Intent intent = new Intent(TriviaActivity.this, HighScoreActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TriviaActivity.this, "Something went wrong posting your score :(", Toast.LENGTH_SHORT).show();
            }
        }, name, score);
        queue.add(request);
    }
}
