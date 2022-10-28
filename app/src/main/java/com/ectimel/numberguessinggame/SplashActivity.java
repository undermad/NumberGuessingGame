package com.ectimel.numberguessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView earth;
    private TextView boring;

    Animation animationEarth, animationBoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        earth = findViewById(R.id.imageViewEarth);
        boring = findViewById(R.id.textViewBoring);

        animationEarth = AnimationUtils.loadAnimation(this, R.anim.earth_animation);
        animationBoring = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        earth.setAnimation(animationEarth);
        boring.setAnimation(animationBoring);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }.start();

    }
}