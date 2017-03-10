package com.example.nagarnai.thelastmile.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.nagarnai.thelastmile.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lastLongitude;
    private double lastLatitude;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        lastLongitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);
        lastLatitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_layout_map);
        float centreX=rl.getX() + rl.getWidth()  / 2;
        float centreY=rl.getY() + rl.getHeight() / 2;
        Button setLocationButton = (Button) findViewById(R.id.set_latlng);
        setLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HouseActivity.class);
                intent.putExtra("LONGITUDE", longitude);
                intent.putExtra("LATITUDE", latitude);
                startActivity(intent);
            }
        });

//        Canvas canvas = new Canvas();
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(centreX, centreY, 50, paint);
//        Bitmap bitmap=Bitmap.createBitmap(440,587,Bitmap.Config.ARGB_8888);
//        Canvas c=new Canvas(bitmap);
//        tcanvas.draw(bitmap);
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
        final LatLng latlng = new LatLng(lastLatitude, lastLongitude);
        final MarkerOptions markerOptions = new MarkerOptions().position(latlng).title("Buyer location").draggable(true);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15.0f));
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker markerDragStart) {
                Log.i("Marker drag", "start");
            }

            @Override
            public void onMarkerDragEnd(Marker markerDragEnd) {
                Log.i("Marker drag", "end");
                LatLng latLng = markerDragEnd.getPosition();
                longitude = latLng.longitude;
                latitude = latLng.latitude;
            }

            @Override
            public void onMarkerDrag(Marker markerDrag) {
                Log.i("Marker drag", "drag");
            }
        });
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                longitude = cameraPosition.target.longitude;
                latitude = cameraPosition.target.latitude;

                Log.i("Last geo", lastLatitude + " " + lastLongitude);
                Log.i("new geo", latitude + " " + longitude);
//                double distance = distFrom(latitude, longitude, lastLongitude, longitde, "K");
//
//                Log.i("distance", Double.toString(distance));
//                if(distance>5) {
//                    markerOptions.draggable(false);
//                } else {
//                    markerOptions.draggable(true);
//                }
            }
        });
    }

    private double distFrom(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equals("K")) {
            dist = dist * 1.609344;
        } else if (unit.equals("N")) {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
