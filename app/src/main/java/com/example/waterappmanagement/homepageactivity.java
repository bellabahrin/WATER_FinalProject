package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class homepageactivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void Logout (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), signinactivity.class));
        finish();
    }

    public void waterInfo_card (View view){
        startActivity(new Intent(getApplicationContext(), waterinfoactivity.class));
        finish();
    }

    public void bill_card (View view){
        startActivity(new Intent(getApplicationContext(), billactivity.class));
        finish();
    }

    public void helpCenter_card (View view){
        startActivity(new Intent(getApplicationContext(), helpcenteractivity.class));
        finish();
    }

    public void eAduan_card (View view){
        startActivity(new Intent(getApplicationContext(), eaduanactivity.class));
        finish();
    }

    public void myAcc_card (View view){
        startActivity(new Intent(getApplicationContext(), myaccountactivity.class));
        finish();
    }

    public void contactus_card (View view){
        startActivity(new Intent(getApplicationContext(), helplineactivity.class));
        finish();
    }

    public void guide_card (View view){
        startActivity(new Intent(getApplicationContext(), helpcenteractivity.class));
        finish();
    }

    public void InboxHomepage (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

    public void Announcement1 (View view){
        startActivity(new Intent(getApplicationContext(), waternews.class));
        finish();
    }

    public void Announcement2 (View view){
        startActivity(new Intent(getApplicationContext(), watermaintenance.class));
        finish();
    }
}