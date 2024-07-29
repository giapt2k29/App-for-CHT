package com.example.cht;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class NotificationData extends SQLiteOpenHelper {

    public NotificationData(Context context) {
        super(context, "notification.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int Insert(SQLiteDatabase db, String title, String body) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("body", body);

        if(db.insert("notification", null, values) == -1)
        {
            db.close();
            return -1;
        }
        else {
            db.close();
            return 1;
        }
    }

    public ArrayList<StringBuffer> selectAll(SQLiteDatabase db) {
        ArrayList<StringBuffer> list = new ArrayList<StringBuffer>();
        db = this.getReadableDatabase();
        String query = "SELECT * FROM notification";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            StringBuffer result = new StringBuffer();
            result.append(cursor.getString(0) + "\n");
            result.append(cursor.getString(1));
            list.add(result);
        }
        cursor.close();
        db.close();
        return list;
    }
}
