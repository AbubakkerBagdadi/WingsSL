package com.example.wingssl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Main4Activity extends AppCompatActivity {
    ImageButton vechile;
    ImageButton host;
    ImageButton settings;
    ImageButton help;
    ImageButton booking;
    ImageButton feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        vechile = (ImageButton) findViewById(R.id.Vehicle);
        host = (ImageButton) findViewById(R.id.imageButton6);
        settings = (ImageButton) findViewById(R.id.imageButton12);
        help = (ImageButton) findViewById(R.id.imageButton10);
        booking = (ImageButton) findViewById(R.id.imageButton7);
        feedback = (ImageButton) findViewById(R.id.imageButton9);


        vechile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                //intent.putExtra("pid",pid);
                startActivity(intent);
                finish();
            }});
    }


}
