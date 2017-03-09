package com.example.nagarnai.thelastmile.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nagarnai.thelastmile.R;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.mera_button) ;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });
    }

    private void createNotification() {

        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        intent.putExtra("action","call");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent , PendingIntent.FLAG_CANCEL_CURRENT);

        Intent intent2 = new Intent(this, NotificationReceiverActivity.class);
        intent2.putExtra("action","more");
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent2 = PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent intent3 = new Intent(this, NotificationReceiverActivity.class);
        intent3.putExtra("action","else");
        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent3 = PendingIntent.getActivity(this, 0, intent3, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Last mile")
                .setContentText("Bata yaar kya karna hai")
                .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                .setStyle(new Notification.BigTextStyle().bigText("sfsdfd"))
                .addAction(R.mipmap.ic_launcher, "Call", contentIntent)
                .addAction(R.mipmap.ic_launcher, "More", contentIntent2)
                .addAction(R.mipmap.ic_launcher, "Else", contentIntent3)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, noti);
    }

}
