package com.example.anotheralarmclock;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText chooseStartTime, chooseEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startTime = (Button) findViewById(R.id.startButton);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewActivity = new Intent(MainActivity.this, startButton.class);
                startActivity(startNewActivity);
            }
        });

        final Button endTime = (Button) findViewById(R.id.EndButton);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewActivity = new Intent(MainActivity.this, endButton.class);
                startActivity(startNewActivity);
            }
        });

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
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseStartTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
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
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        chooseEndTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });
    }

    public void alarms(View view){
        Intent intent = new Intent(MainActivity.this, alarm_times.class);
        startActivity(intent);
    }
}
