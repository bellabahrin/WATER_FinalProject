package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class waterinfoactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_water_info);


    }

    public void WaterList (View view){
    }

    public void WaterNews (View view){
        startActivity(new Intent(getApplicationContext(), waternews.class));
        finish();
    }

    public void WaterMaintenance (View view){
        startActivity(new Intent(getApplicationContext(), watermaintenance.class));
        finish();
    }

    public void Back (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void guide_card (View view){
        startActivity(new Intent(getApplicationContext(), helpcenteractivity.class));
        finish();
    }

    public void Inbox (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

}