package com.example.nagarnai.thelastmile.activity;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationReceiver extends IntentService {

    public NotificationReceiver() {
        super("NotificationReceiver");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent.getAction().equals("address")) {
            return;
        } else if(intent.getAction().equals("mylocation")) {
            // TODO: pass longitude and latitude.
            Intent newIntent = new Intent(getBaseContext(), DALocationActivity.class);
            newIntent.putExtra("LONGITUDE", 77.688716);
            newIntent.putExtra("LATITUDE", 12.982653);
            startActivity(newIntent);
        } else if(intent.getAction().equals("tomorrow")) {
            // ashruthi
        }
    }
}