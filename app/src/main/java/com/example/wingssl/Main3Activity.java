package com.example.wingssl;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Main3Activity extends AppCompatActivity {
    private TextView mTextMessage;
    public  String pid;
    Button done;
    private EditText mtype;
    private EditText mcondition;
    private EditText mamount;
    Button update;
    Button delete;


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
        setContentView(R.layout.activity_main3);
        //Intent intent = getIntent();
        //pid = intent.getStringExtra("pid");
        //System.out.println(pid);

        mtype = (EditText) findViewById(R.id.textView5);
        mcondition = (EditText) findViewById(R.id.textView6);
        mamount = (EditText) findViewById(R.id.textView7);
        update = (Button) findViewById(R.id.button_update);
        delete = (Button) findViewById(R.id.button_delete);
        done = (Button) findViewById(R.id.button_done);

        System.out.println(SharePid.pid);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Vehicle_details");
        db.child(SharePid.pid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Vehicle_Details vehicle_details = dataSnapshot.getValue(Vehicle_Details.class);
                    mtype.setText(vehicle_details.getVtype());
                    mcondition.setText(vehicle_details.getVcondition());
                    mamount.setText(vehicle_details.getVamount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main3Activity.this, Main4Activity.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
                finish();
            }});

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = mtype.getText().toString();
                String condition = mcondition.getText().toString();
                String amount = mamount.getText().toString();
                HashMap<String,Object> result = new HashMap<>();
                result.put("vtype",type);
                result.put("vcondition",condition);
                result.put("vamount",amount);
                FirebaseDatabase.getInstance().getReference().child("Vehicle_details")
                        .child(SharePid.pid).updateChildren(result);
                Toast.makeText(Main3Activity.this, "Updated Successfuly", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Vehicle_details")
                        .child(SharePid.pid).setValue(null);
                Toast.makeText(Main3Activity.this, "Deleted Successfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main3Activity.this , MainActivity.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
                finish();
            }
        });


    }



}
