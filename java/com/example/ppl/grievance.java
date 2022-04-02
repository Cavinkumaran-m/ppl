package com.example.ppl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.IOException;
import java.nio.Buffer;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class grievance extends AppCompatActivity {
    GoogleMap gmap;
    ImageView locator ,camera ;
    MaterialButton takepic,compalintbtn;
    TextInputLayout person_text,address_text,desc_text;
    FusedLocationProviderClient mFusedLocationClient;
    Spinner spinnerLanguage ;
    FirebaseAuthProvider firebaseAuthProvider;
    Geocoder geocoder;


    int PERMISSION_ID = 44;
    int Id ,Count=0,Fake=0;

    String status = "Just Initiated";
    Double lon, lat, com_lon, com_lat;
    boolean map_trig = false;
    boolean trigger = false;
    FirebaseDatabase  rootNode ;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    helperclass hc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grievance);
        //gps
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.gmap);
        supportMapFragment.getMapAsync(this::onMapReady);

        camera = (ImageView) findViewById(R.id.cameraimage);
        takepic = (MaterialButton) findViewById(R.id.takepic);
        compalintbtn=(MaterialButton) findViewById(R.id.filecomplaint) ;

        person_text =(TextInputLayout) findViewById(R.id.outlinebox3);
        desc_text = (TextInputLayout)findViewById(R.id.outlinebox5);
        spinnerLanguage = (Spinner) findViewById(R.id.spinner);



        geocoder = new Geocoder(this,Locale.getDefault());

        //longitude value and latitude value are already declared

        compalintbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String person=person_text.getEditText().getText().toString();
                String description=desc_text.getEditText().getText().toString();
                String problem = spinnerLanguage.getSelectedItem().toString();
                //String contact= .getSelectedItem().toString();

                Random r = new Random();
                Id = r.nextInt(100000);
                List<Address> com_location;

                com_location = null;
                try {
                    com_location = geocoder.getFromLocation(com_lat, com_lon, 1);
                } catch (IOException e) {

                }


                String com_address= com_location.get(0).getAddressLine(0);
                String com_city= com_location.get(0).getLocality();
                String com_postal= com_location.get(0).getPostalCode();


                SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);
                String phone_num = sh.getString("phone_num", "");
                rootNode= FirebaseDatabase.getInstance();
                reference= rootNode.getReference("Complaint");
                helperclass hc =new helperclass(Id, phone_num,person, description,lat,lon,problem, status,Count,Fake, com_lat, com_lon, com_address, com_city, com_postal);
                reference.child(String.valueOf(Id)).setValue(hc);
            } });




        if(ContextCompat.checkSelfPermission(grievance.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(grievance.this,new String[]{
                    Manifest.permission.CAMERA
            },100);
        }
        takepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });


        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguage.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            camera.setImageBitmap(captureImage);
        }

    }





    public void onMapReady(GoogleMap googleMap){
        gmap=googleMap ;


        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                googleMap.clear();
                MarkerOptions marker = new MarkerOptions()
                        .position(new LatLng(point.latitude, point.longitude))
                        .title("Complaint Location");
                //marker.
                googleMap.addMarker(marker);
                //Marker marker = gmap.addMarker(new MarkerOptions().position(new LatLng(point.latitude, point.longitude)).draggable(true));
                trigger = true;

                com_lat = point.latitude;
                com_lon = point.longitude;
            }
        });
    }

    //Location finder functions
    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                            map_trig = true;
                        } else {
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                            map_trig = true;
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }
}