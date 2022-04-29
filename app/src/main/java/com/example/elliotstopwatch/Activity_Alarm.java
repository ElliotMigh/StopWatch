package com.example.elliotstopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Calendar;
import java.util.Date;

public class Activity_Alarm extends AppCompatActivity {
    private LottieAnimationView animationViewAlarm;
    private EditText editTextHour, editTextMinute;
    private Button buttonSetTime, buttonAlarmSet;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    Animation firstAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //insert function
        casting();
        setAnimation();

        //onClick
        buttonSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set animation on click button
                final Animation animationClick = AnimationUtils.loadAnimation(Activity_Alarm.this, R.anim.alpha_anim2);
                buttonSetTime.startAnimation(animationClick);

                //sound
                MediaPlayer playerSetTimeButton = MediaPlayer.create(Activity_Alarm.this, R.raw.newsound);
                playerSetTimeButton.start();

                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(Activity_Alarm.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        editTextHour.setText(String.format("%02d", hourOfDay));
                        editTextMinute.setText(String.format("%02d", minute));
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        buttonAlarmSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set animation on click button
                final Animation animationClick = AnimationUtils.loadAnimation(Activity_Alarm.this, R.anim.alpha_anim2);
                buttonAlarmSet.startAnimation(animationClick);

                //sound
                MediaPlayer playerAlarmSetButton = MediaPlayer.create(Activity_Alarm.this, R.raw.newsound);
                playerAlarmSetButton.start();

                if (!editTextHour.getText().toString().isEmpty() && !editTextMinute.getText().toString().isEmpty()) {
                    Intent intentAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                    intentAlarm.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(editTextHour.getText().toString()));
                    intentAlarm.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(editTextMinute.getText().toString()));
                    intentAlarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Elliot Alarm is One!");
                    startActivity(intentAlarm);
                } else {
                    Toast.makeText(Activity_Alarm.this, "Please Choose a Time!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void casting() {
        animationViewAlarm = (LottieAnimationView) findViewById(R.id.animationAlarm);
        editTextHour = (EditText) findViewById(R.id.etHour);
        editTextMinute = (EditText) findViewById(R.id.etMinute);
        buttonSetTime = (Button) findViewById(R.id.btnSetTime);
        buttonAlarmSet = (Button) findViewById(R.id.btnAlarmSet);
    }

    private void setAnimation() {
        animationViewAlarm.animate().alpha(1).setDuration(2000);
        firstAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        buttonSetTime.startAnimation(firstAnimation);
        buttonAlarmSet.startAnimation(firstAnimation);
    }
}