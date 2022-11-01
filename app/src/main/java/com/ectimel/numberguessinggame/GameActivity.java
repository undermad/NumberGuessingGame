package com.ectimel.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
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

    ArrayList<String> guesses = new ArrayList<>();


    public static String print(boolean bol) {
        if (bol) {
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

        if (twoDigits) {
            randomInt = r.nextInt(90) + 10;
        } else if (threeDigits) {
            randomInt = r.nextInt(900) + 100;
        } else if (fourDigits) {
            randomInt = r.nextInt(9000) + 1000;
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    inputInt = Integer.valueOf(input.getText().toString());
                    guesses.add(input.getText().toString());
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Congratulations. You successful choose correct number!" +
                                "\n\nYour guess is: " + randomInt +
                                "\n\nAttempts: " + (10 - reamingAttemptsInt) +
                                "\n\nYour guesses: " + (guesses) +
                                "\n\nWould you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        builder.show();


                    }

                    if (reamingAttemptsInt == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("Game Over!" +
                                "\nYour guesses: " + guesses +
                                "\nWould you like to play again?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                        builder.show();
                    }

                } catch (NumberFormatException e) {
                    answerResult.setText("Please, use integers!");
                }
            }
        });
    }
}