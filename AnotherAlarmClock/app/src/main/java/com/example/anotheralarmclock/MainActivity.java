package com.example.anotheralarmclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.support.v7.widget.Toolbar;


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

    //creates main appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void alarms(View view){
        Intent intent = new Intent(MainActivity.this, alarm_times.class);
        startActivity(intent);
    }
}
