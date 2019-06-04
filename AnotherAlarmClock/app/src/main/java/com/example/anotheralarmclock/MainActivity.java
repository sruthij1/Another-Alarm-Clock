package com.example.anotheralarmclock;

import android.app.TimePickerDialog;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {

    EditText chooseStartTime, chooseEndTime, timeInt;
    TextClock nowTime;
    MyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDB(this, MyDB.DB_NAME, null, 1);
        //nowTime from the TextClock
        nowTime = findViewById(R.id.nowTime);
        timeInt = findViewById(R.id.editText);

        // Starting new time picker code
            chooseStartTime = findViewById(R.id.etChooseTime);
            chooseStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currenthour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentminute = calendar.get(Calendar.MINUTE);
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

                        chooseStartTime.setText(start);
                    }
                }, currenthour, currentminute, false);
                timePickerDialog.show();
            }
        });


            chooseEndTime = findViewById(R.id.etChooseTime2);
            chooseEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String end= "";
                        String amPm;
                        String min =Integer.toString(minutes);
                        Log.d(chooseStartTime.getText().toString(), "hello");

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

                        chooseEndTime.setText(end);

                    }
                },  currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

    }

    public void ringingTimes(View view){
        //Intent intent = new Intent(MainActivity.this, alarm_times.class);
        //startActivity(intent);

        // Code to get all interval times and make alarm ring at these interval times
        String end = chooseEndTime.getText().toString();
        String change = chooseStartTime.getText().toString();
        String timeInter = timeInt.getText().toString();
        int timeInterval = Integer.valueOf(timeInter);

        while (!change.equals(end)){

            // Insert the start time, and then add the interval times
            if (change.equals(chooseStartTime.getText().toString())){
                db.insert(change);
            }

            int hour;
            int min;
            String amPm;
            if (change.substring(2,3).equals(":")){
                hour = Integer.parseInt(change.substring(0,2));
                min = Integer.parseInt(change.substring(3,5));
                amPm = change.substring(5, 8);
            } else {
                hour = Integer.parseInt(change.substring(0,1));
                Log.d(Integer.toString(hour), "INSIDE");
                min = Integer.parseInt(change.substring(2,4));
                amPm = change.substring(4, 7);
            }

            min = min + timeInterval;

            if (min == 60){
                min = 0;
                hour = hour + 1;
                if (hour == 13){
                    hour = 1;
                    if (amPm.equals(" AM")){
                        amPm = " PM";
                    } else {
                        amPm = " AM";
                    }
                }
            }

            if (min < 10){
                change = Integer.toString(hour).concat(":").concat("0" + String.valueOf(min)).concat(amPm);
                db.insert(change);
            } else {
                change = Integer.toString(hour).concat(":").concat(String.valueOf(min)).concat(amPm);
                db.insert(change);
            }

            if (change.equals(end)){
                break;
            }
        }
        db.view();
    }

    public void ring(View view){

        Cursor c = db.returnCurs();
        while(c.moveToNext()){

            String time = c.getString(0);

            if (time.equals(nowTime.getText().toString())){

                final boolean check1 = nowTime.getText().toString().equals(time);

                final String s = nowTime.getText().toString();

                final Ringtone sound1 = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

                Timer t1 = new Timer();
                t1.scheduleAtFixedRate(new TimerTask() {

                    public void run() {
                        if (check1 == true) {
                            sound1.play();
                            if (s != nowTime.getText().toString()) {
                                sound1.stop();
                                Log.d("stop", "stopping");
                            }
                        }
                    }
                }, 0, 1000);

                Log.d("next", "next");
                continue;
            }
        }
    }
}
