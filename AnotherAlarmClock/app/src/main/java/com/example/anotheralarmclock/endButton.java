package com.example.anotheralarmclock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class endButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_button);
    }

    public void backToStart(View view){
        Intent intent = new Intent(endButton.this, MainActivity.class);
        startActivity(intent);
    }


}
