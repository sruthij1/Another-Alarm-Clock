package com.example.anotheralarmclock;

import android.app.TimePickerDialog;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText chooseStartTime, chooseEndTime;
    TextClock nowTime;

    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDB(this, MyDB.DB_NAME, null, 1);
        //nowTime from the TextClock
        nowTime = findViewById(R.id.nowTime);

        // Starting new time picker code
        chooseStartTime = findViewById(R.id.etChooseTime);
        chooseStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String start = "";
                        String amPm;
                        String min =Integer.toString(minutes);

                        if (hourOfDay == 0){
                            hourOfDay = 12;
                            amPm = " AM";
                        } else if (hourOfDay == 12){
                            amPm = " PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay = hourOfDay - 12;
                            amPm = " PM";
                        } else {
                            amPm = " AM";
                        }

                        if (Integer.valueOf(min) < 10){
                            start = Integer.toString(hourOfDay).concat(":").concat("0" + min).concat(amPm);
                        } else {
                            start = Integer.toString(hourOfDay).concat(":").concat(min).concat(amPm);
                        }

                        //start = String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                        chooseStartTime.setText(start);
                        db.insert(start);
                        final boolean check = nowTime.getText().toString().equals(start);
                        final String a = nowTime.getText().toString();

                        final Ringtone sound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

                        Timer t = new Timer();
                        t.scheduleAtFixedRate(new TimerTask() {

                            public void run(){
                                if (check == true){
                                    sound.play();
                                    if (a != nowTime.getText().toString()){
                                        sound.stop();
                                    }
                                }
                            }
                        } ,0, 1000);
                    }
                },  currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        chooseEndTime = findViewById(R.id.etChooseTime2);
        chooseEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currenthour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentminute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                            String end = "";
                            String amPm;
                            String min =Integer.toString(minutes);

                            if (hourOfDay == 0){
                                hourOfDay = 12;
                                amPm = " AM";
                            } else if (hourOfDay == 12){
                                amPm = " PM";
                            } else if (hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                                amPm = " PM";
                            } else {
                                amPm = " AM";
                            }

                            if (Integer.valueOf(min) < 10){
                                end = Integer.toString(hourOfDay).concat(":").concat("0" + min).concat(amPm);
                            } else {
                                end = Integer.toString(hourOfDay).concat(":").concat(min).concat(amPm);
                            }

                            //start = String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                            chooseEndTime.setText(end);
                            db.insert(end);
                            final boolean check = nowTime.getText().toString().equals(end);
                            final String a = nowTime.getText().toString();

                            final Ringtone sound = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

                            Timer t = new Timer();
                            t.scheduleAtFixedRate(new TimerTask() {

                                public void run(){
                                    if (check == true){
                                        sound.play();
                                        if (a != nowTime.getText().toString()){
                                            sound.stop();
                                        }
                                    }
                                }
                            } ,0, 1000);
                        }
                }, currenthour, currentminute, false);
                timePickerDialog.show();
            }
        });
    }

    public void ringingTimes(View view){
        //Intent intent = new Intent(MainActivity.this, alarm_times.class);
        //startActivity(intent);

        db.view();
    }
}