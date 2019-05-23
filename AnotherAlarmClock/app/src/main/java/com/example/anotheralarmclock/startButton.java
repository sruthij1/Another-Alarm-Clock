package com.example.anotheralarmclock;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class startButton extends AppCompatActivity {

    TimePicker alarmTime;
    TextClock currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_button);
        alarmTime = findViewById(R.id.timePicker);
        currentTime = findViewById(R.id.textClock0);
        final Ringtone sound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            public void run(){
                if (currentTime.getText().toString().equals(AlarmTime())){
                    sound.play();
                } else{
                    sound.stop();
                }

            }

        } ,0, 1000);
    }

    public String AlarmTime(){
        String time;
        String minutes = "";
        Integer hour = alarmTime.getHour();
        Integer min = alarmTime.getMinute();

        Log.d(hour.toString(), "hour: ");
        Log.d(min.toString(), "min: ");

        if (10>min){
            minutes.concat("0").concat(min.toString());
        } else {
            minutes.concat(min.toString());
        }

        if (hour >12){
            Log.d(hour.toString(), "heyy1: ");
            hour = hour-12;
            time = hour.toString().concat(":").concat(minutes).concat(" PM");
        } else {
            Log.d(hour.toString(), "heyy: ");
            time = hour.toString().concat(":").concat(minutes).concat(" AM");
        }

        Log.d(time, "time: ");

        return time;
    }


    public void backToStart(View view){
        Intent intent = new Intent(startButton.this, MainActivity.class);
        startActivity(intent);
    }
}
