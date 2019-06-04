package com.example.anotheralarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import android.database.Cursor;

public class MyDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Context ctx;
    static String DB_NAME = "NAME_DB";
    static String TABLE_NAME = "NAME_TABLE";
    static int VERSION = 1;

    public MyDB (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        ctx = context;
        VERSION = version;
        DB_NAME = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + " (_id INTEGER PRIMARY KEY, ALARM_TIME STRING);");
        Toast.makeText(ctx, "TABLE IS CREATED", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (VERSION == oldVersion){
            VERSION = newVersion;
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            onCreate(db);
        }
    }

    public void count() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        Toast.makeText(ctx, String.valueOf(c.getCount()), Toast.LENGTH_LONG).show();
    }

    public void insert(String alarmtime) throws SQLiteException {
        db = getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("ALARM_TIME", alarmtime);
        db.insert(TABLE_NAME, null, cv);
        Toast.makeText(ctx, "INSERTION IS SUCCESSFUL", Toast.LENGTH_LONG).show();
    }

    public Cursor returnCurs() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT ALARM_TIME FROM " + TABLE_NAME+";", null);
        return c;
    }

    public void view() {
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT ALARM_TIME FROM " + TABLE_NAME+";", null);
        StringBuilder time = new StringBuilder();
        while(c.moveToNext()){
            time.append(c.getString(0) + "\n");
        }
        Toast.makeText(ctx, time, Toast.LENGTH_LONG).show();
    }


}