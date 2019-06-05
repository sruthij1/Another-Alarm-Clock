package com.example.anotheralarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

public class Settings extends AppCompatActivity {

    public static boolean night = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Settings.night == true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_settings);
    }

    public void setLightMode(View view) {
        night = false;
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);

    }


    public void setNightMode(View view) {
        night = true;
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }


    public void whatTime(View view) {
        Intent intent = new Intent(Settings.this, Notification.class);
        startActivity(intent);
    }
}
