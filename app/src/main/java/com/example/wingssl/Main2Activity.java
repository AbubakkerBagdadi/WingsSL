package com.example.wingssl;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main2Activity extends AppCompatActivity {
    private TextView mTextMessage;
    private GoogleMap mMap;
    private Marker mMarker;
    private Button button2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_vehicle:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_Booking:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_Inbox:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Intent intent = getIntent();
        //pid = intent.getStringExtra("pid");
        loadMap();
        init(); //Initialising Variables

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        goToinvoice();

    }
    private void goToinvoice() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this , Main3Activity.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
                finish();
            }
        });
    }
    private void init() {
        button2 = findViewById(R.id.button2);

    }


    private void loadMap() {
        SupportMapFragment mapFragment = new SupportMapFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.googleMapFrame, mapFragment,"map1");

        fragmentTransaction.commit();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
//
                final LatLng colombo = new LatLng(6.913281, 79.850681);
                googleMap.addMarker(new MarkerOptions().position(colombo).title("Marker"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(colombo));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

            }
        });
    }

}
