package com.example.cht;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String FIRST_TIME_KEY = "firstTime";
    ImageButton canlendar, Class, news, study, Search, Home, Profile, notification;
    Savechoose savechoose = new Savechoose(this, "chooseClass.db", 0);
    TextView txt;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String Classsave = "";
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        Log.d("Permission", "Notification permission granted");
                    } else {
                        // Permission is denied
                        Log.d("Permission", "Notification permission denied");
                    }
                });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 or higher
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission", "Notification permission already granted");
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.POST_NOTIFICATIONS)) {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
            boolean isFirstRun = sharedPreferences.getBoolean("FirstRun", true);

            if (isFirstRun) {
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
                finish();
            } else {
                setContentView(R.layout.activity_main);
                txt = findViewById(R.id.text1);
                String fullName = sharedPreferences.getString("FullName", "");
                String className = sharedPreferences.getString("Class", "");
                Classsave = className;
                String user = fullName + " | " + className;
                txt.setText(user);

            }
        }catch (Exception e) {
            Log.d("Loi khoi dong", "Loi", e);
        }

        String save = "";
        for(int i = 0; i < Classsave.length(); i++) {
            if(Classsave.charAt(i) >= '0' && Classsave.charAt(i) < 'z') {
                save += Classsave.charAt(i);
            }
        }
        Log.d("className", save);
        FirebaseApp.initializeApp(this);

        FirebaseMessaging.getInstance().subscribeToTopic("News")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        try {

                        }catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Đọc thông báo lỗi.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        try {
            SQLiteDatabase db1 = null;
            db1 = openOrCreateDatabase("chooseClass.db", MODE_PRIVATE, null);
            String sql = "CREATE TABLE IF NOT EXISTS tbclass (class TEXT PRIMARY KEY)";
            db1.execSQL(sql);
            if (savechoose.isTableEmpty(db1, "tbclass")) {
                switch (save) {
                    case "12T1":
                        savechoose.Insert(db1, "12T1");
                        break;
                    case "12T2":
                        savechoose.Insert(db1, "12T2");
                        break;
                    case "12L":
                        savechoose.Insert(db1, "12L");
                        break;
                    case "12H":
                        savechoose.Insert(db1, "12H");
                        break;
                    case "12S":
                        savechoose.Insert(db1, "12SINH");
                        break;
                    case "12TIN":
                        savechoose.Insert(db1, "12TIN");
                        break;
                    case "12V":
                        savechoose.Insert(db1, "12V");
                        break;
                    case "12A1":
                        savechoose.Insert(db1, "12A1");
                        break;
                    case "12A2":
                        savechoose.Insert(db1, "12A2");
                        break;
                    case "12P":
                        savechoose.Insert(db1, "12P");
                        break;
                    case "12SD":
                        savechoose.Insert(db1, "12SD");
                        break;
                    case "11T1":
                        savechoose.Insert(db1, "11T1");
                        break;
                    case "11T2":
                        savechoose.Insert(db1, "11T2");
                        break;
                    case "11L":
                        savechoose.Insert(db1, "11L");
                        break;
                    case "11H":
                        savechoose.Insert(db1, "11H");
                        break;
                    case "11SINH":
                        savechoose.Insert(db1, "11SINH");
                        break;
                    case "11TIN":
                        savechoose.Insert(db1, "11TIN");
                        break;
                    case "11V":
                        savechoose.Insert(db1, "11V");
                        break;
                    case "11A1":
                        savechoose.Insert(db1, "11A1");
                        break;
                    case "11A2":
                        savechoose.Insert(db1, "11A2");
                        break;
                    case "11P":
                        savechoose.Insert(db1, "11P");
                        break;
                    case "11SU":
                        savechoose.Insert(db1, "11SU");
                        break;
                    case "11DIA":
                        savechoose.Insert(db1, "11DIA");
                        break;
                    case "10T1":
                        savechoose.Insert(db1, "10T1");
                        break;
                    case "10T2":
                        savechoose.Insert(db1, "10T2");
                        break;
                    case "10L":
                        savechoose.Insert(db1, "10L");
                        break;
                    case "10H":
                        savechoose.Insert(db1, "10H");
                        break;
                    case "10SINH":
                        savechoose.Insert(db1, "10SINH");
                        break;
                    case "10TIN":
                        savechoose.Insert(db1, "10TIN");
                        break;
                    case "10V":
                        savechoose.Insert(db1, "10V");
                        break;
                    case "10A1":
                        savechoose.Insert(db1, "10A1");
                        break;
                    case "10A2":
                        savechoose.Insert(db1, "10A2");
                        break;
                    case "10P":
                        savechoose.Insert(db1, "10P");
                        break;
                    case "10SU":
                        savechoose.Insert(db1, "10SU");
                        break;
                    case "10DIA":
                        savechoose.Insert(db1, "10DIA");
                        break;
                    case "10TRUNG":
                        savechoose.Insert(db1, "10TRUNG");
                        break;
                }
            }
            db1.close();
        } catch (Exception e) {
            Log.d("Loi", "data da ton tai", e);
        } finally {
            SQLiteDatabase db1 = null;
            db1 = openOrCreateDatabase("chooseClass.db", MODE_PRIVATE, null);
            if (savechoose.isTableEmpty(db1, "tbclass")) {
                switch (save) {
                    case "12T1":
                        savechoose.Insert(db1, "12T1");
                        break;
                    case "12T2":
                        savechoose.Insert(db1, "12T2");
                        break;
                    case "12L":
                        savechoose.Insert(db1, "12L");
                        break;
                    case "12H":
                        savechoose.Insert(db1, "12H");
                        break;
                    case "12S":
                        savechoose.Insert(db1, "12SINH");
                        break;
                    case "12TIN":
                        savechoose.Insert(db1, "12TIN");
                        break;
                    case "12V":
                        savechoose.Insert(db1, "12V");
                        break;
                    case "12A1":
                        savechoose.Insert(db1, "12A1");
                        break;
                    case "12A2":
                        savechoose.Insert(db1, "12A2");
                        break;
                    case "12P":
                        savechoose.Insert(db1, "12P");
                        break;
                    case "12SD":
                        savechoose.Insert(db1, "12SD");
                        break;
                    case "11T1":
                        savechoose.Insert(db1, "11T1");
                        break;
                    case "11T2":
                        savechoose.Insert(db1, "11T2");
                        break;
                    case "11L":
                        savechoose.Insert(db1, "11L");
                        break;
                    case "11H":
                        savechoose.Insert(db1, "11H");
                        break;
                    case "11SINH":
                        savechoose.Insert(db1, "11SINH");
                        break;
                    case "11TIN":
                        savechoose.Insert(db1, "11TIN");
                        break;
                    case "11V":
                        savechoose.Insert(db1, "11V");
                        break;
                    case "11A1":
                        savechoose.Insert(db1, "11A1");
                        break;
                    case "11A2":
                        savechoose.Insert(db1, "11A2");
                        break;
                    case "11P":
                        savechoose.Insert(db1, "11P");
                        break;
                    case "11SU":
                        savechoose.Insert(db1, "11SU");
                        break;
                    case "11DIA":
                        savechoose.Insert(db1, "11DIA");
                        break;
                    case "10T1":
                        savechoose.Insert(db1, "10T1");
                        break;
                    case "10T2":
                        savechoose.Insert(db1, "10T2");
                        break;
                    case "10L":
                        savechoose.Insert(db1, "10L");
                        break;
                    case "10H":
                        savechoose.Insert(db1, "10H");
                        break;
                    case "10SINH":
                        savechoose.Insert(db1, "10SINH");
                        break;
                    case "10TIN":
                        savechoose.Insert(db1, "10TIN");
                        break;
                    case "10V":
                        savechoose.Insert(db1, "10V");
                        break;
                    case "10A1":
                        savechoose.Insert(db1, "10A1");
                        break;
                    case "10A2":
                        savechoose.Insert(db1, "10A2");
                        break;
                    case "10P":
                        savechoose.Insert(db1, "10P");
                        break;
                    case "10SU":
                        savechoose.Insert(db1, "10SU");
                        break;
                    case "10DIA":
                        savechoose.Insert(db1, "10DIA");
                        break;
                    case "10TRUNG":
                        savechoose.Insert(db1, "10TRUNG");
                        break;
                    default:
                        savechoose.Insert(db1, "10T2");
                        break;
                }
                db1.close();
            }
        }

        try {
            SQLiteDatabase db1 = null;
            db1 = openOrCreateDatabase("notification.db", MODE_PRIVATE, null);
            String sql = "CREATE TABLE IF NOT EXISTS notification (title TEXT PRIMARY KEY, body TEXT)";
            db1.execSQL(sql);
            db1.close();
        }catch (Exception e) {
            Log.d("Loi", "Database tin nhan da ton tai", e);
        }

        canlendar = findViewById(R.id.imageView);
        Class = findViewById(R.id.imageView2);
        news = findViewById(R.id.imageView3);
        study = findViewById(R.id.imageView4);
        Search = findViewById(R.id.imageView6);
        Home = findViewById(R.id.imageView5);
        Profile = findViewById(R.id.imageView7);
        notification = findViewById(R.id.notificationbtn);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, Notification.class);
                    startActivity(intent);
                }catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Đọc thông báo lỗi.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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