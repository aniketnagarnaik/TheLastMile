package com.example.nagarnai.thelastmile.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nagarnai.thelastmile.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        final double longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);
        final double latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        Log.i("longitude", " " + longitude);
        Log.i("latitude", " " + latitude);
        Geocoder geocoder;
        List<Address> addresses = new ArrayList<Address>(1);
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (Exception e) {

        }
        final String address1 = addresses.get(0).getAddressLine(0);
        final String address2 = addresses.get(0).getAddressLine(1);
        final String address3 = addresses.get(0).getAddressLine(2); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        final String city = addresses.get(0).getLocality();
        final String state = addresses.get(0).getAdminArea();
        final String country = addresses.get(0).getCountryName();
        final String postalCode = addresses.get(0).getPostalCode();
        final String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        Log.i("address1", " " + address1);
        Log.i("address2", " " + address2);
        Log.i("address3", " " + address3);
        Log.i("city", " " + city);
        Log.i("state", " " + state);
        Log.i("country", " " + country);
        Log.i("postalCode", " " + postalCode);
        Log.i("knownName", " " + knownName);

        EditText address1EditTest = (EditText) findViewById(R.id.address1);
        EditText address2EditTest = (EditText) findViewById(R.id.address2);
        EditText address3EditTest = (EditText) findViewById(R.id.address3);
        TextView longitudeEditTest = (TextView) findViewById(R.id.longitude);
        TextView latitudeEditTest = (TextView) findViewById(R.id.latitude);

        address1EditTest.setText(address1);
        address2EditTest.setText(address2);
        address3EditTest.setText(address3);
        longitudeEditTest.setText(Double.toString(longitude));
        latitudeEditTest.setText(Double.toString(latitude));

        Button submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), DALocationActivity.class);
                intent.putExtra("ADDRESS1", address1);
                intent.putExtra("ADDRESS2", address2);
                intent.putExtra("ADDRESS2", address3);
                intent.putExtra("LONGITUDE", longitude);
                intent.putExtra("LATITUDE", latitude);
                startActivity(intent);
            }
        });
    }
}
