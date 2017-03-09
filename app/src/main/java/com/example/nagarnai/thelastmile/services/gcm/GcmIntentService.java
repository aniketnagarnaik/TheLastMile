package com.example.nagarnai.thelastmile.services.gcm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.nagarnai.thelastmile.activity.NotificationReceiver;
import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.example.nagarnai.thelastmile.R;
import com.example.nagarnai.thelastmile.services.gps.GPSTracker;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tangbang on 3/25/2015.
 */
public class GcmIntentService extends GcmListenerService {

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        //Getting the message from the bundle
        String message = data.getString("message");
        Log.d("MessageReceived", message);
        //Displaying a notiffication with the message
        createDeliveryNotification();
    }

    private void createDeliveryNotification() {

        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.setAction("address");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getService(this, 0, intent , PendingIntent.FLAG_CANCEL_CURRENT);

        Intent intent2 = new Intent(this, NotificationReceiver.class);
        intent2.setAction("mylocation");
        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent2 = PendingIntent.getService(this, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);

        Intent intent3 = new Intent(this, NotificationReceiver.class);
        intent3.setAction("tomorrow");
        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent3 = PendingIntent.getService(this, 0, intent3, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Last mile")
                .setContentText("Bata yaar kya karna hai")
                .setSmallIcon(R.drawable.cast_ic_notification_small_icon)
                .setStyle(new Notification.BigTextStyle().bigText("sfsdfd"))
                .addAction(R.mipmap.ic_launcher, "Deliver to Me", contentIntent)
                .addAction(R.mipmap.ic_launcher, "Deliver tomorrow", contentIntent2)
                .addAction(R.mipmap.ic_launcher, "Do nothing", contentIntent3)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, noti);
    }
}