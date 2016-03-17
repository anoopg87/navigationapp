package com.inteliment.navigationapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.inteliment.navigationapp.model.SampleJsonModel;


// Mapfragment to display the location
public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    SampleJsonModel sampleJsonModel;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview_layout);
        toolbar=((Toolbar)findViewById(R.id.toolbar));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        toolbar.setTitle(getResources().getString(R.string.mapview));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        setSupportActionBar(toolbar);
        // get the selected location information from the main class
        sampleJsonModel=getIntent().getParcelableExtra("OBJ");
             SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // add pin in map view with the information of the selected location
        mMap = googleMap;
        if(null!=sampleJsonModel){
            LatLng sydney = new LatLng(Double.valueOf(sampleJsonModel.getLocation().getLatitude()), Double.valueOf(sampleJsonModel.getLocation().getLongitude()));
            mMap.addMarker(new MarkerOptions().position(sydney).title(sampleJsonModel.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(sampleJsonModel.getLocation().getLatitude()), Double.valueOf(sampleJsonModel.getLocation().getLongitude())), 12.0f));
        }

    }
}
