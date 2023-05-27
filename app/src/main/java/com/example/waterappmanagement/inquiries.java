package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class inquiries extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseDatabase db;
    EditText username, email, subject, message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiries);

        username= findViewById(R.id.username_inquiries);
        email = findViewById(R.id.email_inquiries);
        subject = findViewById(R.id.subject_inquiries);
        message = findViewById(R.id.message_inquiries);
        send = findViewById(R.id.inquiries_btn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app");
                ref = db.getReference("inquiries");

                //get values
                String usernameinput = username.getText().toString();
                String emailinput = email.getText().toString();
                String subjectinput = subject.getText().toString();
                String messageinput = message.getText().toString();

                InquiriesHelperClass helperClass = new InquiriesHelperClass(usernameinput, emailinput, subjectinput, messageinput);
                ref.child(usernameinput).setValue(helperClass);

                Toast.makeText(inquiries.this, "Inquiries have been submitted", Toast.LENGTH_SHORT).show();
                username.getText().clear();
                email.getText().clear();
                subject.getText().clear();
                message.getText().clear();

            }

        });
    }

    public void BackInquiries(View view) {
        startActivity(new Intent(getApplicationContext(), helpcenteractivity.class));
        finish();
    }

    public void InboxInquiries(View view) {
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }
}