package com.example.elliotstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private LottieAnimationView animationViewClock;
    private TextView textViewApplicationName, textViewApplicationDescription;
    private Button buttonGetStarted;
    Animation atg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //insert function
        castingViews();

        //set animation
        animationViewClock.animate().alpha(1).setDuration(2000);
        textViewApplicationName.animate().alpha(1).setDuration(2000);
        atg = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        buttonGetStarted.startAnimation(atg);

        //set font
        Typeface typefaceName = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface typefaceDescription = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface typefaceGetStart = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");
        textViewApplicationName.setTypeface(typefaceName);
        textViewApplicationDescription.setTypeface(typefaceDescription);
        buttonGetStarted.setTypeface(typefaceGetStart);

        //onClick
        buttonGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set animation on click button
                final Animation buttonAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha_anim2);
                buttonGetStarted.startAnimation(buttonAnimation);

                //set sound on button
                MediaPlayer sound = MediaPlayer.create(MainActivity.this,R.raw.newsound);
                sound.start();

                Intent intentHome = new Intent(MainActivity.this, Home_Activity.class);
                startActivity(intentHome);
                finish();
            }
        });
    }

    void castingViews() {
        animationViewClock = (LottieAnimationView) findViewById(R.id.animationViewClock);
        textViewApplicationName = (TextView) findViewById(R.id.txtApplicationName);
        textViewApplicationDescription = (TextView) findViewById(R.id.txtApplicationDescription);
        buttonGetStarted = (Button) findViewById(R.id.btnGetStarted);
    }
}