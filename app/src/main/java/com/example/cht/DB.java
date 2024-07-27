package com.example.cht;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DB extends SQLiteOpenHelper{

    Context context;
        public static String DB_NAME = "TEACHER";
    public DB(Context context) {
        super(context, DB_NAME, null, 1);
    }
    public SQLiteDatabase openDatabase() {
        File dbFile = context.getDatabasePath(DB_NAME);
        try{
            SQLiteDatabase db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
            db.close();
            copyDatabase(dbFile);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void copyDatabase(File dbFile) {
        try {
            InputStream openDB = context.getAssets().open("Teacher.db");
            FileOutputStream outputStream = new FileOutputStream(dbFile);
            int Length;
            byte[] buffer = new byte[1024];
            while((Length = openDB.read(buffer)) > 0) {
                outputStream.write(buffer, 0, Length);
                Log.d("DB", "writing");
            }
            outputStream.flush();
            outputStream.close();
            openDB.close();
            Log.d("DB", "completedDB");
        }catch (Exception e) {

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
