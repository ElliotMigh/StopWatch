package com.example.elliotstopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Locale;

public class Home_Activity extends AppCompatActivity {

    private LottieAnimationView animationViewTimer;
    private TextView textViewShowTime;
    private Button buttonReset, buttonPlay, buttonStop, buttonSetAlarm;
    private int seconds;
    private boolean running;
    private boolean warRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //insert function
        initViews();
        setAnimationOnButtonAndAnimationView();

        if (savedInstanceState != null) {
            savedInstanceState.getInt("seconds");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        //insert runTime function
        runTimer();

        //onClick set alarm button
        buttonSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sound
                MediaPlayer playerSetAlarmButton = MediaPlayer.create(Home_Activity.this, R.raw.newsound);
                //animation
                Animation animationSetAlarmButton = AnimationUtils.loadAnimation(Home_Activity.this, R.anim.alpha_anim2);

                //set animation
                buttonSetAlarm.startAnimation(animationSetAlarmButton);
                //set sound
                playerSetAlarmButton.start();

                startActivity(new Intent(Home_Activity.this, Activity_Alarm.class));
            }
        });
    }

    void initViews() {
        animationViewTimer = (LottieAnimationView) findViewById(R.id.animationTimer);
        textViewShowTime = (TextView) findViewById(R.id.txtShowTime);
        buttonReset = (Button) findViewById(R.id.btnReset);
        buttonPlay = (Button) findViewById(R.id.btnPlay);
        buttonStop = (Button) findViewById(R.id.btnStop);
        buttonSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
    }

    public void onStart(View view) {
        //animation
        Animation animationStart = AnimationUtils.loadAnimation(this, R.anim.alpha_anim2);
        buttonPlay.startAnimation(animationStart);
        //sound
        MediaPlayer playerStart = MediaPlayer.create(this, R.raw.newsound);
        playerStart.start();

        running = true;
    }

    public void onStop(View view) {
        //animation
        Animation animationStop = AnimationUtils.loadAnimation(this, R.anim.alpha_anim2);
        buttonStop.startAnimation(animationStop);
        //sound
        MediaPlayer playerStop = MediaPlayer.create(this, R.raw.newsound);
        playerStop.start();
        running = false;
    }

    public void onReset(View view) {
        //animation
        Animation animationReset = AnimationUtils.loadAnimation(this, R.anim.alpha_anim2);
        buttonReset.startAnimation(animationReset);
        //sound
        MediaPlayer playerReset = MediaPlayer.create(this, R.raw.newsound);
        playerReset.start();

        running = false;
        seconds = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        warRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (warRunning) {
            running = true;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", warRunning);
    }

    private void setAnimationOnButtonAndAnimationView() {

        //for button
        Animation animationForBtn = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        buttonSetAlarm.startAnimation(animationForBtn);
        buttonPlay.startAnimation(animationForBtn);
        buttonReset.startAnimation(animationForBtn);
        buttonStop.startAnimation(animationForBtn);

        //for animationView
        animationViewTimer.animate().alpha(1).setDuration(2000);
    }

    private void runTimer() {
        Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",
                        hours, minutes, secs);

                textViewShowTime.setText(time);

                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}