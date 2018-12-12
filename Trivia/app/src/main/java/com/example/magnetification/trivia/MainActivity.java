package com.example.magnetification.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.confirm);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getURL();

                Intent intent = new Intent(MainActivity.this, TriviaActivity.class);
                intent.putExtra("url", url);
                EditText n = findViewById(R.id.nameview);
                intent.putExtra("name", n.getText().toString());
                startActivity(intent);
            }
        });
    }

    private String getURL() {
        String url = "https://opentdb.com/api.php?amount=";
        Spinner numSpin = findViewById(R.id.numSpinner);
        String number = numSpin.getSelectedItem().toString();
        url += number;

        Spinner catSpinner = findViewById(R.id.catSpinner);
        String cat = catSpinner.getSelectedItem().toString();
        if (!cat.equals("Any Category")) {
            int index = Arrays.asList(getResources().getStringArray(R.array.cat)).indexOf(cat) + 8;
            url += "&category=" + String.valueOf(index);
        }

        Spinner diffSpin = findViewById(R.id.diffSpinner);
        String diff = diffSpin.getSelectedItem().toString();
        if (!diff.equals("Any Difficulty")) {
            url += "&difficulty=" + diff;
        }

        Spinner typeSpin = findViewById(R.id.typeSpinner);
        String type = typeSpin.getSelectedItem().toString();
        if (type.equals("Multiple Choice")) {
            url += "&type=multiple";
        } else if (type.equals(("True / False"))) {
            url += "&type=boolean";
        }

        return url;
    }
}
