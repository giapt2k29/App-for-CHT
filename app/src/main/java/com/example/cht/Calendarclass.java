package com.example.cht;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.text.SpannableStringBuilder;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Calendarclass extends AppCompatActivity {
    String Change = "";
    String Class = "";
    String T1 = null;
    String T2 = null;
    String T3 = null;
    String T4 = null;
    String T5 = null;
    String res = "";
    TextView txt1, txt2, txt3, txt4, txt5;
    CardView t1, t2, t3, t4, t5;

    CalendarView calendar;
    ImageButton imageButton;
    String Dateset = "2";
    SQLiteDatabase db1;
    Savechoose savechoose = new Savechoose(Calendarclass.this, "chooseClass.db", 0);
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Class = savechoose.checkClass(db1);
        //txt.setText(Class);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.calendar);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference();

        String path = "/" + Class + "/T2";
        txt1 = findViewById(R.id.T1_text);
        txt2 = findViewById(R.id.T2_text);
        txt3 = findViewById(R.id.T3_text);
        txt4 = findViewById(R.id.T4_text);
        txt5 = findViewById(R.id.T5_text);
        calendar = findViewById(R.id.calendar1);
        txt = findViewById(R.id.textview);

        t1 = findViewById(R.id.T1);
        t2 = findViewById(R.id.T2);
        t3 = findViewById(R.id.T3);
        t4 = findViewById(R.id.T4);
        t5 = findViewById(R.id.T5);
        imageButton = findViewById(R.id.choose);

        String Classsetting = "";
        switch (Class) {
            case "12T1":
                Classsetting = "12 Toán 1";
                break;
            case "12T2":
                Classsetting = "12 Toán 2";
                break;
            case "12L":
                Classsetting = "12 Lý";
                break;
            case "12H":
                Classsetting = "12 Hoá";
                break;
            case "12S":
                Classsetting = "12 Sinh";
                break;
            case "12TIN":
                Classsetting = "12 Tin";
                break;
            case "12V":
                Classsetting = "12 Văn";
                break;
            case "12A1":
                Classsetting = "12 Anh 1";
                break;
            case "12A2":
                Classsetting = "12 Anh 2";
                break;
            case "12P":
                Classsetting = "12 Pháp";
                break;
            case "12SD":
                Classsetting = "12 Sử - Địa";
                break;
            case "11T1":
                Classsetting = "11 Toán 1";
                break;
            case "11T2":
                Classsetting = "11 Toán 2";
                break;
            case "11L":
                Classsetting = "11 Lý";
                break;
            case "11H":
                Classsetting = "11 Hoá";
                break;
            case "11SINH":
                Classsetting = "11 Sinh";
                break;
            case "11TIN":
                Classsetting = "11 Tin";
                break;
            case "11V":
                Classsetting = "11 Văn";
                break;
            case "11A1":
                Classsetting = "11 Anh 1";
                break;
            case "11A2":
                Classsetting = "11 Anh 2";
                break;
            case "11P":
                Classsetting = "11 Pháp";
                break;
            case "11SU":
                Classsetting = "11 Sử";
                break;
            case "11DIA":
                Classsetting = "11 Địa";
                break;
            case "10T1":
                Classsetting = "10 Toán 1";
                break;
            case "10T2":
                Classsetting = "10 Toán 2";
                break;
            case "10L":
                Classsetting = "10 Lý";
                break;
            case "10H":
                Classsetting = "10 Hoá";
                break;
            case "10SINH":
                Classsetting = "10 Sinh";
                break;
            case "10TIN":
                Classsetting = "10 Tin";
                break;
            case "10V":
                Classsetting = "10 Văn";
                break;
            case "10A1":
                Classsetting = "10 Anh 1";
                break;
            case "10A2":
                Classsetting = "10 Anh 2";
                break;
            case "10P":
                Classsetting = "10 Pháp";
                break;
            case "10SU":
                Classsetting = "10 Sử";
                break;
            case "10DIA":
                Classsetting = "10 Địa";
                break;
            case "10TRUNG":
                Classsetting = "10 Trung";
                break;

        }

        Calendar today = Calendar.getInstance();
        int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
        txt.setText("Bạn đang xem thời khoá biểu lớp " + Classsetting);
        String finalPath = path;
        String finalDateset = Dateset;

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class = savechoose.checkClass(db1);
                Toast.makeText(Calendarclass.this, "Lớp hiện tại đang chọn: " + Class, Toast.LENGTH_LONG).show();
                try {
                    showDialog();
                }catch (Exception e) {
                    Log.d("Loi", "Loi", e);
                }
            }
        });

        switch (dayOfWeek) {
            case Calendar.MONDAY: // 2
                path = "/" + Class + "/T2";
                Dateset = "2";
                break;
            case Calendar.TUESDAY: // 3
                path = "/" + Class + "/T3";
                Dateset = "3";
                break;
            case Calendar.WEDNESDAY: // 4
                path = "/" + Class + "/T4";
                Dateset = "4";
                break;
            case Calendar.THURSDAY: // 5
                path = "/" + Class + "/T5";
                Dateset = "5";
                break;
            case Calendar.FRIDAY: // 6
                path = "/" + Class + "/T6";
                Dateset = "6";
                break;
            case Calendar.SATURDAY: // 7
                path = "/" + Class + "/T7";
                Dateset = "7";
                break;
            case Calendar.SUNDAY:
                path = "/" + Class + "/CN";
                Dateset = "1";
                break;
        }

        fetchDataAndUpdateUI(databaseReference, path, Dateset);

        // Set the listener for calendar view for future date changes
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                int dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK);

                // Determine the correct path based on the day of the week
                String path = "/12T2/T2";
                String Dateset = "2";
                switch (dayOfWeek) {
                    case Calendar.MONDAY: // 2
                        path = "/" + Class + "/T2";
                        Dateset = "2";
                        break;
                    case Calendar.TUESDAY: // 3
                        path = "/" + Class + "/T3";
                        Dateset = "3";
                        break;
                    case Calendar.WEDNESDAY: // 4
                        path = "/" + Class + "/T4";
                        Dateset = "4";
                        break;
                    case Calendar.THURSDAY: // 5
                        path = "/" + Class + "/T5";
                        Dateset = "5";
                        break;
                    case Calendar.FRIDAY: // 6
                        path = "/" + Class + "/T6";
                        Dateset = "6";
                        break;
                    case Calendar.SATURDAY: // 7
                        path = "/" + Class + "/T7";
                        Dateset = "7";
                        break;
                    case Calendar.SUNDAY:
                        path = "/" + Class + "/CN";
                        Dateset = "1";
                        break;
                }
                fetchDataAndUpdateUI(databaseReference, path, Dateset);
            }
        });
    }

    private void fetchDataAndUpdateUI(DatabaseReference databaseReference, String path, String Dateset) {
        databaseReference.child(path).addValueEventListener(new ValueEventListener() {
            StringBuffer stringBuffer = new StringBuffer();
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
            SpannableStringBuilder stringBuilder1 = new SpannableStringBuilder();
            SpannableStringBuilder stringBuilder2 = new SpannableStringBuilder();
            SpannableStringBuilder stringBuilder3 = new SpannableStringBuilder();
            SpannableStringBuilder stringBuilder4 = new SpannableStringBuilder();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (Dateset != "1") {
                    txt1.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    txt3.setVisibility(View.VISIBLE);
                    txt4.setVisibility(View.VISIBLE);
                    txt5.setVisibility(View.VISIBLE);
                    String stringValue = snapshot.getValue(String.class);
                    res = "";
                    T1 = T2 = T3 = T4 = T5 = null; // Reset the values

                    for (int i = 0; i < stringValue.length(); i++) {
                        if (stringValue.charAt(i) != ',') {
                            res += stringValue.charAt(i);
                        } else {
                            if (T1 == null) {
                                T1 = res;
                            } else if (T2 == null) {
                                T2 = res;
                            } else if (T3 == null) {
                                T3 = res;
                            } else if (T4 == null) {
                                T4 = res;
                            } else if (T5 == null) {
                                T5 = res;
                            }
                            res = "";
                        }
                    }
                    if (!res.isEmpty()) {
                        if (T1 == null) {
                            T1 = res;
                        } else if (T2 == null) {
                            T2 = res;
                        } else if (T3 == null) {
                            T3 = res;
                        } else if (T4 == null) {
                            T4 = res;
                        } else if (T5 == null) {
                            T5 = res;
                        }
                    }

                    addBulletPoint(stringBuilder, "06:45      ", false);
                    addBulletPoint(stringBuilder, "Môn học: " + T1 + "\n", true);
                    addBulletPoint(stringBuilder, "\n", false);
                    addBulletPoint(stringBuilder, "     |           ", false);
                    addBulletPoint(stringBuilder, "Sáng thứ " + Dateset + ", tiết 1" + "\n", true);
                    addBulletPoint(stringBuilder, "\n", false);
                    addBulletPoint(stringBuilder, "07:30      ", false);
                    addBulletPoint(stringBuilder, "Giáo viên: null", false);
                    txt1.setText(stringBuilder);

                    addBulletPoint(stringBuilder1, "07:35      ", false);
                    addBulletPoint(stringBuilder1, "Môn học: " + T2 + "\n", true);
                    addBulletPoint(stringBuilder1, "\n", false);
                    addBulletPoint(stringBuilder1, "     |           ", false);
                    addBulletPoint(stringBuilder1, "Sáng thứ " + Dateset + ", tiết 2" + "\n", true);
                    addBulletPoint(stringBuilder1, "\n", false);
                    addBulletPoint(stringBuilder1, "08:20      ", false);
                    addBulletPoint(stringBuilder1, "Giáo viên: null", false);
                    txt2.setText(stringBuilder1);

                    addBulletPoint(stringBuilder2, "08:35      ", false);
                    addBulletPoint(stringBuilder2, "Môn học: " + T3 + "\n", true);
                    addBulletPoint(stringBuilder2, "\n", false);
                    addBulletPoint(stringBuilder2, "     |           ", false);
                    addBulletPoint(stringBuilder2, "Sáng thứ " + Dateset + ", tiết 3" + "\n", true);
                    addBulletPoint(stringBuilder2, "\n", false);
                    addBulletPoint(stringBuilder2, "09:15      ", false);
                    addBulletPoint(stringBuilder2, "Giáo viên: null", false);
                    txt3.setText(stringBuilder2);

                    addBulletPoint(stringBuilder3, "09:15      ", false);
                    addBulletPoint(stringBuilder3, "Môn học: " + T4 + "\n", true);
                    addBulletPoint(stringBuilder3, "\n", false);
                    addBulletPoint(stringBuilder3, "     |           ", false);
                    addBulletPoint(stringBuilder3, "Sáng thứ " + Dateset + ", tiết 4" + "\n", true);
                    addBulletPoint(stringBuilder3, "\n", false);
                    addBulletPoint(stringBuilder3, "10:05      ", false);
                    addBulletPoint(stringBuilder3, "Giáo viên: null", false);
                    txt4.setText(stringBuilder3);

                    if (T5 != null) {
                        addBulletPoint(stringBuilder4, "10:20      ", false);
                        addBulletPoint(stringBuilder4, "Môn học: " + T5 + "\n", true);
                        addBulletPoint(stringBuilder4, "\n", false);
                        addBulletPoint(stringBuilder4, "     |           ", false);
                        addBulletPoint(stringBuilder4, "Sáng thứ " + Dateset + ", tiết 5" + "\n", true);
                        addBulletPoint(stringBuilder4, "\n", false);
                        addBulletPoint(stringBuilder4, "11:05      ", false);
                        addBulletPoint(stringBuilder4, "Giáo viên: null", false);
                        txt5.setText(stringBuilder4);
                    }
                    t1.setVisibility(T1 != null ? View.VISIBLE : View.GONE);
                    t2.setVisibility(T2 != null ? View.VISIBLE : View.GONE);
                    t3.setVisibility(T3 != null ? View.VISIBLE : View.GONE);
                    t4.setVisibility(T4 != null ? View.VISIBLE : View.GONE);
                    t5.setVisibility(T5 != null ? View.VISIBLE : View.GONE);
                }
                else {
                    txt1.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
                    txt3.setVisibility(View.GONE);
                    txt4.setVisibility(View.GONE);
                    txt5.setVisibility(View.GONE);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
    private void addBulletPoint(SpannableStringBuilder builder, String text, boolean addBullet) {
        int start = builder.length();
        builder.append(text);
        int end = builder.length();
        if (addBullet) {
            builder.setSpan(new BulletSpan(15, Color.BLUE), start, end, 0);
        }
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chooseclass);

        LinearLayout t1_12 = dialog.findViewById(R.id.t1_12);
        LinearLayout t2_12 = dialog.findViewById(R.id.t2_12);
        LinearLayout h_12 = dialog.findViewById(R.id.h_12);
        LinearLayout l_12 = dialog.findViewById(R.id.l_12);
        LinearLayout sinh_12 = dialog.findViewById(R.id.sinh_12);
        LinearLayout tin_12 = dialog.findViewById(R.id.tin_12);
        LinearLayout v_12 = dialog.findViewById(R.id.v_12);
        LinearLayout a1_12 = dialog.findViewById(R.id.a1_12);
        LinearLayout a2_12 = dialog.findViewById(R.id.a2_12);
        LinearLayout p_12 = dialog.findViewById(R.id.p_12);
        LinearLayout sd_12 = dialog.findViewById(R.id.sd_12);

        LinearLayout t1_11 = dialog.findViewById(R.id.t1_11);
        LinearLayout t2_11 = dialog.findViewById(R.id.t2_11);
        LinearLayout l_11 = dialog.findViewById(R.id.l_11);
        LinearLayout h_11 = dialog.findViewById(R.id.h_11);
        LinearLayout sinh_11 = dialog.findViewById(R.id.sinh_11);
        LinearLayout tin_11 = dialog.findViewById(R.id.tin_11);
        LinearLayout v_11 = dialog.findViewById(R.id.v_11);
        LinearLayout a1_11 = dialog.findViewById(R.id.a1_11);
        LinearLayout a2_11 = dialog.findViewById(R.id.a2_11);
        LinearLayout p_11 = dialog.findViewById(R.id.p_11);
        LinearLayout su_11 = dialog.findViewById(R.id.su_11);
        LinearLayout dia_11 = dialog.findViewById(R.id.dia_11);

        LinearLayout t1_10 = dialog.findViewById(R.id.t1_10);
        LinearLayout t2_10 = dialog.findViewById(R.id.t2_10);
        LinearLayout l_10 = dialog.findViewById(R.id.l_10);
        LinearLayout h_10 = dialog.findViewById(R.id.h_10);
        LinearLayout sinh_10 = dialog.findViewById(R.id.sinh_10);
        LinearLayout tin_10 = dialog.findViewById(R.id.tin_10);
        LinearLayout v_10 = dialog.findViewById(R.id.v_10);
        LinearLayout a1_10 = dialog.findViewById(R.id.a1_10);
        LinearLayout a2_10 = dialog.findViewById(R.id.a2_10);
        LinearLayout p_10 = dialog.findViewById(R.id.p_10);
        LinearLayout su_10 = dialog.findViewById(R.id.su_10);
        LinearLayout dia_10 = dialog.findViewById(R.id.dia_10);
        LinearLayout trung_10 = dialog.findViewById(R.id.trung_10);

        t1_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12T1";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Toán 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        t2_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12T2";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Toán 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        h_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12H";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Hoá";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        l_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12L";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Lý";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        sinh_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12SINH";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Sinh";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        tin_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12TIN";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Tin";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        v_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12V";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Văn";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a1_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12A1";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Anh 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a2_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12A2";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Anh 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        p_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12P";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Pháp";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        sd_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "12SD";
                String msg = "Bạn đang xem thời khoá biểu lớp 12 Sử - Địa";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        t1_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11T1";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Toán 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        t2_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11T2";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Toán 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        l_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11L";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Lý";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        h_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11H";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Hoá";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        sinh_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11SINH";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Sinh";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        tin_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11TIN";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Tin";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        v_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11V";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Văn";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a1_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11A1";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Anh 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a2_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11A2";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Anh 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        p_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11P";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Pháp";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        su_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11SU";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Sử";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        dia_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "11DIA";
                String msg = "Bạn đang xem thời khoá biểu lớp 11 Địa";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        t1_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10T1";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Toán 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        t2_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10T2";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Toán 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        l_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10L";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Lý";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        h_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10H";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Hoá";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        sinh_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10SINH";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Sinh";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        tin_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10TIN";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Tin";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        v_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10V";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Văn";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a1_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10A1";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Anh 1";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        a2_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10A2";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Anh 2";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        p_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10P";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Pháp";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        dia_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10DIA";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Địa";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        su_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10SU";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Sử";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });

        trung_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Change = "10TRUNG";
                String msg = "Bạn đang xem thời khoá biểu lớp 10 Trung";
                try {
                    savechoose.deleteAll(db1);
                    savechoose.Insert(db1, Change);
                    txt.setText(msg);
                    recreate();
                }catch (Exception e) {
                    Log.d("Loi update", "Loi", e);
                }
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialoAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
}
