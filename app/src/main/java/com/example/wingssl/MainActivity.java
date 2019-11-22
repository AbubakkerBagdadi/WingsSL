package com.example.wingssl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private ImageView imageView;
    private Button nextButton;

    private Spinner vcondition;


    private EditText vamount;

    private Spinner vtype;
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
        setContentView(R.layout.activity_main);

        init(); //Initialising Variables

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toast.makeText(MainActivity.this,"Firebase Connected",Toast.LENGTH_LONG).show();
        selecetImage();
        goToLocation();

    }

    //next button
    private void goToLocation() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Vehicle_details");
                String pid = db.push().getKey();
                SharePid.pid = pid;
                Vehicle_Details vehicle_details = new Vehicle_Details();
                Spinner spinner = (Spinner) findViewById(R.id.vtype);
                String st =spinner.getSelectedItem().toString();
                int pos =spinner.getSelectedItemPosition();
                if(pos!=0)
                {
                    vehicle_details.setVtype(vtype.getSelectedItem().toString());
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Please Select the Vehicle type !!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                Spinner spinne = (Spinner) findViewById(R.id.vcondition);
                String s =spinne.getSelectedItem().toString();
                int poss =spinne.getSelectedItemPosition();
                if(poss!=0)
                {
                    vehicle_details.setVcondition(vcondition.getSelectedItem().toString());
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Please Select Condition !!", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                System.out.println(vamount.getText());
                vehicle_details.setVamount(vamount.getText().toString());

                if(vamount.length()==0)

                {
                    vamount.requestFocus();
                    vamount.setError("FIELD CANNOT BE EMPTY");
                    return;
                }


                vehicle_details.setPushId(pid);
                db.child(pid).setValue(vehicle_details);
                Intent intent = new Intent(MainActivity.this , Main2Activity.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
                finish();


            }
        });
    }



    private void selecetImage() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });
    }

    private void init() {
        mTextMessage = findViewById(R.id.message);
        imageView = findViewById(R.id.vehicleImageView);
        nextButton = findViewById(R.id.nextButton);
        vtype = findViewById(R.id.vtype);
        vcondition = findViewById(R.id.vcondition);
        vamount = (EditText) findViewById(R.id.vamount);

    }


    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 177);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
        {
            if (requestCode == 177)
            {
                // Get the url from data
                Uri selectedImageUri = data.getData();


                if (null != selectedImageUri)
                {
                    // Set the image in ImageView
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }



}