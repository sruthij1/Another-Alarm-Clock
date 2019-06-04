package com.example.anotheralarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class startButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_button);
    }


    public void backToStart(View view){
        Intent intent = new Intent(startButton.this, MainActivity.class);
        startActivity(intent);
    }
}
