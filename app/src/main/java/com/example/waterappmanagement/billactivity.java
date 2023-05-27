package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class billactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
    }

    public void BackBill (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void guideCardBill (View view){
        startActivity(new Intent(getApplicationContext(), helpcenteractivity.class));
        finish();
    }

    public void InboxBill (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }
}