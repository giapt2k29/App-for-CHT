package com.example.cht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class TeacherDAO extends SQLiteOpenHelper {

    public static final String TB_TEACHER = "TEACHER";
    public static final String TB_TEACHER_NAME = "name";
    public static final String TB_TEACHER_BIRTH = "birth";
    public static final String TB_TEACHER_PHONE = "phonenumber";
    public static final String TB_TEACHER_EMAIL = "email";

    public TeacherDAO(Context context, String name, int version) {
        super(context, TB_TEACHER, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<StringBuffer> check(SQLiteDatabase db, String name) {
        List<StringBuffer> stringBufferList = new ArrayList<>();

        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TB_TEACHER + " WHERE " +
                TB_TEACHER_NAME + " LIKE '%" + name + "'";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Họ và tên:" + cursor.getString(1) + "\n");
            stringBuffer.append("Môn dạy: " + cursor.getString(2) + "\n");
            stringBuffer.append("Ngày sinh: " + cursor.getString(3) + "\n");
            stringBuffer.append("Số điện thoại: " + cursor.getString(4) + "\n");
            stringBuffer.append("Email: " + cursor.getString(5) + "\n");
            stringBufferList.add(stringBuffer);
        }
        cursor.close();
        db.close();
        return stringBufferList;
    }
    public List<StringBuffer> checkimage(SQLiteDatabase db, String name) {
        List<StringBuffer> stringBufferList = new ArrayList<>();

        db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TB_TEACHER + " WHERE " +
                TB_TEACHER_NAME + " LIKE '%" + name + "'";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cursor.getString(6));
            stringBufferList.add(stringBuffer);
        }
        cursor.close();
        db.close();
        return stringBufferList;
    }
}
