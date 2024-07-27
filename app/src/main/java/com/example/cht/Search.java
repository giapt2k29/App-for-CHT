package com.example.cht;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Search extends AppCompatActivity {
    String dbName = "TEACHER";
    String dbPath = "/databases/";
    public static final String TB_TEACHER = "Teacher";
    public static final String TB_TEACHER_NAME = "name";
    SQLiteDatabase db = null;
    DB databases = new DB(this);
    TeacherDAO teacherDAO = new TeacherDAO(this, "teacher.db", 0);
    EditText editText;
    RadioGroup Radio;
    RadioButton HS, GV;

    ImageButton btn;
    TextView resultText1, resultText2, resultText3;
    CardView resultCard1, resultCard2, resultCard3;
    ImageView imageView1, imageView2, imageView3;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        try {
            copyDatabase(getDatabasePath(dbName));
        } catch (Exception e) {
            Log.d("Loi khi copy data", "loi", e);
        }

        // Khởi tạo các View
        editText = findViewById(R.id.edittext);
        HS = findViewById(R.id.HS);
        GV = findViewById(R.id.GV);
        Radio = findViewById(R.id.radio1);
        btn = findViewById(R.id.search_button);

        resultText1 = findViewById(R.id.result_text1); // Tham chiếu tới TextView kết quả
        resultText2 = findViewById(R.id.result_text2);
        resultText3 = findViewById(R.id.result_text3);
        resultCard1 = findViewById(R.id.result_card1); // Tham chiếu tới CardView
        resultCard2 = findViewById(R.id.result_card2);
        resultCard3 = findViewById(R.id.result_card3);
        imageView1 = findViewById(R.id.test1);
        imageView2 = findViewById(R.id.test2);
        imageView3 = findViewById(R.id.test3);

        Radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.HS) {
                    res = "STUDENT";
                }
                else if(checkedId == R.id.GV) {
                    res = "TEACHER";
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Count = "1";

                    db = openOrCreateDatabase(res, MODE_PRIVATE, null);

                    String name = editText.getText().toString();

                    List<StringBuffer> stringBuffer = teacherDAO.check(db, name);

                    List<StringBuffer> stringBuffer1 = teacherDAO.checkimage(db, name);

                    resultText1.setText("");
                    resultCard1.setVisibility(View.GONE);
                    resultText2.setText("");
                    resultCard2.setVisibility(View.GONE);
                    resultText3.setText("");
                    resultCard3.setVisibility(View.GONE);
                    imageView2.setVisibility(View.GONE);
                    imageView3.setVisibility(View.GONE);
                    imageView1.setVisibility(View.GONE);

                    if(stringBuffer.size() == 1) {
                        imageView1.setVisibility(View.VISIBLE);
                        resultText1.setText(stringBuffer.get(0).toString());
                        resultCard1.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(0).toString())
                                .into(imageView1);
                    }
                    if(stringBuffer.size() == 2) {
                        imageView1.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.VISIBLE);
                        resultText1.setText(stringBuffer.get(0).toString());
                        resultCard1.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(0).toString())
                                .into(imageView1);
                        resultText2.setText(stringBuffer.get(1).toString());
                        resultCard2.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(1).toString())
                                .into(imageView2);
                    }
                    if(stringBuffer.size() == 3) {
                        imageView1.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.VISIBLE);
                        imageView3.setVisibility(View.VISIBLE);
                        resultText1.setText(stringBuffer.get(0).toString());
                        resultCard1.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(0).toString())
                                .into(imageView1);
                        resultText2.setText(stringBuffer.get(1).toString());
                        resultCard2.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(1).toString())
                                .into(imageView2);
                        resultText3.setText(stringBuffer.get(2).toString());
                        resultCard3.setVisibility(View.VISIBLE);
                        Glide.with(Search.this)
                                .load(stringBuffer1.get(2).toString())
                                .into(imageView3);
                    }
                }
                catch (Exception e) {
                    Log.d("Loi khi truy vấn","Loi khi truy vấn", e);

                    String msg = "Lỗi khi truy vấn";

                    Toast.makeText(Search.this, msg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void copyDatabase(File dbFile) {
        try {
            InputStream openDB = getAssets().open("Teacher.db");
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
        } catch (Exception e) {
            Log.e("DB", "Error copying database", e);
        }
    }
}
