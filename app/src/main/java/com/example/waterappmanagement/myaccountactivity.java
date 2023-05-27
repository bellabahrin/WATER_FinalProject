package com.example.waterappmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class myaccountactivity extends AppCompatActivity {

    TextInputLayout fullName, username, idNo, email, phoneNo, address, password;
    TextView nameLabel;
    FirebaseDatabase db;
    DatabaseReference ref;
    String usernameinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);



        //hooks

        fullName = findViewById(R.id.fullname_acc);
        username = findViewById(R.id.username_acc);
        idNo = findViewById(R.id.idNo_acc);
        email = findViewById(R.id.email_acc);
        phoneNo = findViewById(R.id.phoneNo_acc);
        address = findViewById(R.id.address_acc);
        password = findViewById(R.id.password_acc);
        nameLabel = findViewById(R.id.name_label);

        final String userEnterUsername = username.getEditText().getText().toString().trim();
        final String userEnterPassword = password.getEditText().getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");
        Query checkUser = ref.orderByChild("username").equalTo(userEnterUsername);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.child("username").getValue().equals(usernameinput)) {
                   fullName.getEditText().setText(snapshot.child("fullName").getValue(String.class));
                   username.getEditText().setText(snapshot.child("username").getValue(String.class));
                   idNo.getEditText().setText(snapshot.child("idNo").getValue(String.class));
                   email.getEditText().setText(snapshot.child("email").getValue(String.class));
                   phoneNo.getEditText().setText(snapshot.child("phoneNo").getValue(String.class));
                   address.getEditText().setText(snapshot.child("address").getValue(String.class));
                   password.getEditText().setText(snapshot.child("password").getValue(String.class));


               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //showalldata
        //showAllUserData();

    }

  /*  private void showAllUserData() {

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_fullname = intent.getStringExtra("fullName");
        String user_idno = intent.getStringExtra("idNo");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_address = intent.getStringExtra("address");
        String user_password = intent.getStringExtra("password");


        nameLabel.setText(user_fullname);
        fullName.getEditText().setText(user_fullname);
        username.getEditText().setText(user_username);
        idNo.getEditText().setText(user_idno);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);
        address.getEditText().setText(user_address);
        password.getEditText().setText(user_password);

    }*/

    public void logoutMyAcc (View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), signinactivity.class));
        finish();
    }

    public void BackMyAcc (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void InboxMyAcc (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }
}