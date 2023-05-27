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

public class eaduanactivity extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseDatabase db;
    EditText username, aduan;
    Button eaduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_aduan);

        username= findViewById(R.id.username_aduan);
        aduan = findViewById(R.id.aduan);
        eaduan = findViewById(R.id.eaduan_btn);

        eaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app");
                ref = db.getReference("eaduan");

                //get values
                String usernameinput = username.getText().toString();
                String aduaninput = aduan.getText().toString();

                AduanHelperClass helperClass = new AduanHelperClass(usernameinput, aduaninput);
                ref.child(usernameinput).setValue(helperClass);

                    Toast.makeText(eaduanactivity.this, "Aduan have been submitted", Toast.LENGTH_SHORT).show();
                    username.getText().clear();
                    aduan.getText().clear();

            }
        });

    }

    /*public void eaduan(View view) {

        db = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app");
        ref = db.getReference("eaduan");

        //get values
        String usernameinput = username.getText().toString();
        String aduaninput = aduan.getText().toString();

        AduanHelperClass helperClass = new AduanHelperClass(usernameinput, aduaninput);
        ref.child(usernameinput).setValue(helperClass);
    }*/

    public void BackEAduan (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void InboxEAduan (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

    public void EmergencyEAduan (View view){
        startActivity(new Intent(getApplicationContext(), helplineactivity.class));
        finish();
    }


}