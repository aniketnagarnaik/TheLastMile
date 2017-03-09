package com.example.nagarnai.thelastmile.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nagarnai.thelastmile.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DALocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double longitude;
    private double latitude;
    private BroadcastReceiver br;
    private Marker associate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);
        longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);
        latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.i("Received", intent.getAction());
                longitude = intent.getExtras().getDouble("LONGITUDE");
                latitude = intent.getExtras().getDouble("LATITUDE");
                Log.i("GotLocation", "location: "+latitude + " "+ longitude);
                final LatLng latlng = new LatLng(latitude, longitude);
                final MarkerOptions markerOptions = new MarkerOptions().position(latlng).title("DA's address");
                if(associate!=null) {
                    associate.remove();
                }
                associate = mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14.0f));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(br,
                new IntentFilter("location"));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        final LatLng latlng = new LatLng(latitude, longitude);
        final MarkerOptions markerOptions = new MarkerOptions().position(latlng).title("DA's address");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 14.0f));
    }
}
