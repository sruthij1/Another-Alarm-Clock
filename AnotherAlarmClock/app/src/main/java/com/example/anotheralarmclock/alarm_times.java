package com.example.anotheralarmclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class alarm_times extends AppCompatActivity {
    MyDB dbHelper;
    private ListView listV;
    static ArrayList<String> alarmListData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Settings.night == true)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_alarm_times);
        // Create pointers for Dbhelper and List View
        listV = (ListView) findViewById(R.id.alarmListView);
        dbHelper = new MyDB(this, MyDB.DB_NAME, null, 1);

        Cursor alarmTime = dbHelper.returnCurs();
        alarmListData = new ArrayList<>();
        while (alarmTime.moveToNext()) {
            alarmListData.add(alarmTime.getString(0));
        }

        // create adapter for ListView
        ListAdapter alarmAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alarmListData);
        listV.setAdapter(alarmAdapter);
    }


}
