package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class StoreActivity extends AppCompatActivity implements OnMapReadyCallback{

    public static final String EXTRA_CATEGORY_ID = "categoryId";
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 23;
    private GoogleMap mMap;
    private Integer resFlag = 0, categoryId = 0;
    Button findBtn;
    Spinner spinnerBtn;
    private String[] storeName = {
            "EzyFoody Alsut",
            "EzyFoody Kemanggisan"
    };
    LatLng myLoc;
    TextView distanceTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        init();
        categoryId = (Integer)getIntent().getExtras().get(EXTRA_CATEGORY_ID);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, storeName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBtn.setAdapter(adapter);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myLoc != null){
                    if (spinnerBtn.getSelectedItem().toString().equals("EzyFoody Alsut")) {
                        resFlag = 1;
                    }
                    else if (spinnerBtn.getSelectedItem().toString().equals("EzyFoody Kemanggisan")) {
                        resFlag = 2;
                    }

                    moveZoom( spinnerBtn.getSelectedItem().toString() );
                }
            }
        });
        checkPermission();
    }

    private void init() {
        findBtn = findViewById(R.id.findBtn);
        spinnerBtn = (Spinner)findViewById(R.id.storeSpinner);
        distanceTxt = findViewById(R.id.distanceTxt);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                } else {
                    finish();
                }
                return;
            }
        }
    }

    private void moveZoom(String address) {
        switch (address){
            case "EzyFoody Kemanggisan":{
                LatLng jktBinus = new LatLng(-6.200626786640842, 106.78510782104438);
                final MarkerOptions a = new MarkerOptions();
                a.position(jktBinus);
                a.title("EzyFoody Kemanggisan");
                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(jktBinus, 15);
                mMap.animateCamera(cameraUpdate);
                double hasil = CalculationByDistance(jktBinus, myLoc);
                hasil = Math.round(hasil * 100.0) / 100.0;
                String hy = hasil + " Km";
                distanceTxt.setText(hy);
                break;
            }
            case "EzyFoody Alsut":{
                LatLng alsBinus = new LatLng(-6.2232004023710585, 106.6488380704966);
                final MarkerOptions b = new MarkerOptions();
                b.position(alsBinus);
                b.title("EzyFoody Alsut");
                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(alsBinus, 15);
                mMap.animateCamera(cameraUpdate);
                double hasil = CalculationByDistance(alsBinus, myLoc);
                hasil = Math.round(hasil * 100.0) / 100.0;
                String hy = hasil + " Km";
                distanceTxt.setText(hy);
                break;
            }
        }
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng jktBinus = new LatLng(-6.200626786640842, 106.78510782104438);
        final MarkerOptions a = new MarkerOptions();
        a.position(jktBinus);
        a.title("EzyFoody Kemanggisan");
        LatLng alsBinus = new LatLng(-6.2232004023710585, 106.6488380704966);
        final MarkerOptions b = new MarkerOptions();
        b.position(alsBinus);
        b.title("EzyFoody Alsut");
        CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(jktBinus, 15);
        mMap.animateCamera(cameraUpdate);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLoc = new LatLng(location.getLatitude(),location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(myLoc);
                markerOptions.title("Your Location");
                mMap.clear();
                mMap.addMarker(markerOptions);
                mMap.addMarker(a);
                mMap.addMarker(b);
            }
        });
    }

    public void onClickProceed(View view) {
        Intent proceed = new Intent(this, DrinksActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("catId", categoryId);
        bundle.putInt("resId", resFlag);
        proceed.putExtra("passedBundle", bundle);
        startActivity(proceed);

    }
}