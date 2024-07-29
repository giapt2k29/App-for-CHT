package com.example.cht;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {
    MyFirebaseMessagingService myFirebaseMessagingService = new MyFirebaseMessagingService();
    LinearLayout linearLayout;

    NotificationData notificationData = new NotificationData(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.notification);

        linearLayout = findViewById(R.id.notificationzone);

        String title = MyFirebaseMessagingService.getNotificationTitle(this);

        String body = MyFirebaseMessagingService.getNotificationBody(this);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(title + "\n");

        stringBuffer.append(body);

        addCardView(stringBuffer);

        ArrayList<StringBuffer> notificationList;

        notificationList = notificationData.selectAll(db);

        for(StringBuffer s : notificationList) {
            addCardView(s);
        }
    }
    private void addCardView(StringBuffer title) {
        LayoutInflater inflater = LayoutInflater.from(this);

        View cardView = inflater.inflate(R.layout.cardview, linearLayout, false);

        TextView textViewTitle = cardView.findViewById(R.id.text_view_title);

        textViewTitle.setText(title.toString());

        linearLayout.addView(cardView);
    }

}
