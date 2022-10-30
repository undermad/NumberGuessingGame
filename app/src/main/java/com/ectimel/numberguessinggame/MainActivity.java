package com.ectimel.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView info;
    RadioGroup radioGroup;
    RadioButton radio1, radio2, radio3;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = findViewById(R.id.textViewInfo);
        radioGroup = findViewById(R.id.radioGroup);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        start = findViewById(R.id.buttonStart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);

                if (!radio1.isChecked() && !radio2.isChecked() && !radio3.isChecked()) {
                    Snackbar.make(view, "Please, select mode", Snackbar.LENGTH_SHORT).show();
                } else if (radio2.isChecked()) {
                    i.putExtra("two", true);
                    startActivity(i);

                } else if (radio1.isChecked()) {
                    i.putExtra("one", true);
                    startActivity(i);

                } else if (radio3.isChecked()) {
                    i.putExtra("three", true);
                    startActivity(i);
                }

            }

        });


    }
}