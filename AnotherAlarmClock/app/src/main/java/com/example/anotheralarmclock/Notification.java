package com.example.anotheralarmclock;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Notification extends AppCompatActivity {

    EditText chooseTime = findViewById(R.id.etChooseTime3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Settings.night == true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_notification);



    }

    public void toMain(View view) {
        Intent intent = new Intent(Notification.this, MainActivity.class);
        startActivity(intent);
        //Toast.makeText(Notification.this, "Daily alarm set for 9:00 AM", Toast.LENGTH_SHORT).show();
    }


}
