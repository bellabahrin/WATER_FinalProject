package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class helpcenteractivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
    }

    public void BackHelpCenter (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void InboxHelpCenter (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

    public void emergencyHelpCenter (View view){
        startActivity(new Intent(getApplicationContext(), helplineactivity.class));
        finish();
    }

    public void inquiriesCard(View view) {
        startActivity(new Intent(getApplicationContext(), inquiries.class));
        finish();
    }

    public void faqsCard(View view) {
        startActivity(new Intent(getApplicationContext(), faqs.class));
        finish();
    }
}