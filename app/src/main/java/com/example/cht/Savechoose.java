package com.example.cht;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Savechoose extends SQLiteOpenHelper {
    public Savechoose(Context context, String name, int version) {
        super(context, "chooseClass.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String checkClass(SQLiteDatabase db) {
        String result = "";
        db = this.getReadableDatabase();
        String query = "SELECT * FROM tbclass";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToLast()) {
            result = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return result;
    }
    public int Update(SQLiteDatabase db, String Class, String Update) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Class", Update);

        int n = db.update("tbclass", values, "Class = " + "'" + Class + "'", null);
        db.close();
        return n;
    }

    public int Insert(SQLiteDatabase db, String name) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("class", name);
        if(db.insert("tbclass", null, values) == -1)
        {
            db.close();
            return -1;
        }
        else {
            db.close();
            return 1;
        }
    }
    public int deleteAll(SQLiteDatabase db) {
        db = getWritableDatabase();
        int rowsAffected = db.delete("tbclass", null, null);
        db.close();
        return rowsAffected;
    }
    public boolean isTableEmpty(SQLiteDatabase db, String tableName) {
        db = getWritableDatabase();
        boolean isEmpty = true;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
            if (cursor != null && cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    isEmpty = false;
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        db.close();
        return isEmpty;
    }

}
