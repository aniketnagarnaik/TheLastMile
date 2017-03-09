package com.example.nagarnai.thelastmile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nagarnai.thelastmile.R;

public class NotificationReceiverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        Log.d("notification clic","alsdnlas") ;

        onNewIntent(getIntent());
    }

    protected void onNewIntent(Intent intent) {
        Toast toast = Toast.makeText(getApplicationContext(), intent.getStringExtra("action"), Toast.LENGTH_SHORT);
        toast.show();
    }
}