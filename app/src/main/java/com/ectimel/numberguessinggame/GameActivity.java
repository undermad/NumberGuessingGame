package com.ectimel.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView lastGuess, reamingAttempts, answerResult;
    EditText input;
    Button check;

    boolean twoDigits, threeDigits, fourDigits;
    int randomInt;
    Random r = new Random();

    int reamingAttemptsInt = 10;
    int inputInt;


    public static String print(boolean bol){
        if (bol){
            return "true";
        } else
            return "false";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        lastGuess = findViewById(R.id.textViewLastGuess);
        reamingAttempts = findViewById(R.id.textViewReamingAttempts);
        answerResult = findViewById(R.id.textViewAnswerResult);
        input = findViewById(R.id.editTextInput);
        check = findViewById(R.id.buttonCheck);

        twoDigits = getIntent().getBooleanExtra("two", false);
        threeDigits = getIntent().getBooleanExtra("three", false);
        fourDigits = getIntent().getBooleanExtra("four", false);

        if(twoDigits){
            randomInt = r.nextInt(90) + 10;
        } else if(threeDigits){
            randomInt = r.nextInt(900) + 100;
        } else if (fourDigits){
            randomInt = r.nextInt(9000) +1000;
        }


        Intent i = new Intent(GameActivity.this, PlayAgainActivity.class);
        i.putExtra("random", randomInt);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputInt = Integer.parseInt(input.getText().toString());
                if (randomInt > inputInt) {
                    answerResult.setText("Increase you guess!");
                    reamingAttemptsInt--;
                    reamingAttempts.setText("Your reaming rights: " + reamingAttemptsInt);
                    lastGuess.setText("Your last guess: " + inputInt);
                    input.setText("");
                } else if (randomInt < inputInt) {
                    answerResult.setText("Decrease you guess!");
                    reamingAttemptsInt--;
                    reamingAttempts.setText("Your reaming rights: " + reamingAttemptsInt);
                    lastGuess.setText("Your last guess: " + inputInt);
                    input.setText("");
                } else {

                    i.putExtra("attempts", reamingAttemptsInt);
                    startActivity(i);
                }

                if (reamingAttemptsInt == 0) {
                    i.putExtra("attempts", reamingAttemptsInt);
                    startActivity(i);
                }

            }
        });


    }
}