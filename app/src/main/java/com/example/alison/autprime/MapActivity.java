package com.example.alison.autprime;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, GoogleMap.OnInfoWindowClickListener {
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap mMap;

    private static final LatLng AUTSouthCampus = new LatLng(-36.984360,174.879210);
    private Marker SouthCampus;

    private static final LatLng AUTCityCampus = new LatLng(-36.852631,174.766785);
    private Marker CityCampus;

    private static final LatLng AUTNorthCampus = new LatLng(-36.792930,174.747960);
    private Marker NorthCampus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
    }
    private void fetchLastLocation(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                    supportMapFragment.getMapAsync(MapActivity.this);
                }
            }
        });
    }
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;

        //Adds campus markers to map
        addMarkersToMap();

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener((GoogleMap.OnMyLocationClickListener) this);
        mMap.setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) this);

        //Adding a marker on current location and camera zooms is for current location
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Current location");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        googleMap.addMarker(markerOptions);
    }

    private void addMarkersToMap(){
        //Add a marker in 3 different AUT campuses
        SouthCampus = mMap.addMarker(new MarkerOptions()
        .position(AUTSouthCampus)
        .title("AUT south campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AUTSouthCampus));

        CityCampus = mMap.addMarker(new MarkerOptions()
                .position(AUTCityCampus)
                .title("AUT city campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AUTCityCampus));

        NorthCampus = mMap.addMarker(new MarkerOptions()
                .position(AUTNorthCampus)
                .title("AUT north campus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AUTNorthCampus));
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

    public boolean onMyLocationButtonClick(){
        Toast.makeText(this,"Current location button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void onMyLocationClick(@NonNull Location location){
        Toast.makeText(this,"Current location", Toast.LENGTH_SHORT).show();
    }

    public void onInfoWindowClick(Marker marker){
        Toast.makeText(this,"Info window clicked",Toast.LENGTH_SHORT).show();
    }
}
