package com.example.cht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String FIRST_TIME_KEY = "firstTime";
    ImageButton canlendar, Class, news, study, Search, Home, Profile, notification;
    Savechoose savechoose = new Savechoose(this, "chooseClass.db", 0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isFirstTime = settings.getBoolean(FIRST_TIME_KEY, true);
        try {
            SQLiteDatabase db1 = null;
            db1 = openOrCreateDatabase("chooseClass.db", MODE_PRIVATE, null);
            String sql = "CREATE TABLE IF NOT EXISTS tbclass (class TEXT PRIMARY KEY)";
            db1.execSQL(sql);
            if(savechoose.isTableEmpty(db1, "tbclass")) {
                savechoose.Insert(db1, "12T2");
            }
            db1.close();
        } catch (Exception e) {
            Log.d("Loi", "data da ton tai", e);
        }

//        if(isFirstTime) {
//            SharedPreferences.Editor editor = settings.edit();
//            editor.putBoolean(FIRST_TIME_KEY, false);
//            editor.apply();
//        }

        setContentView(R.layout.activity_main);

        canlendar = findViewById(R.id.imageView);
        Class = findViewById(R.id.imageView2);
        news = findViewById(R.id.imageView3);
        study = findViewById(R.id.imageView4);
        Search = findViewById(R.id.imageView6);
        Home = findViewById(R.id.imageView5);
        Profile = findViewById(R.id.imageView7);
        notification = findViewById(R.id.notificationbtn);

        canlendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendarclass.class);
                startActivity(intent);
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, News.class);
                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Tính năng đang phát triển";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        Class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Tính năng đang phát triển";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cht.Search.class);
                startActivity(intent);
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Tính năng đang phát triển";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.cht.Notification.class);
                startActivity(intent);
            }
        });
    }
}