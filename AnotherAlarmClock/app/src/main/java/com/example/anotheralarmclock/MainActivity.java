package com.example.anotheralarmclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }
}
