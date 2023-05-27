package com.example.waterappmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.waterappmanagement.databinding.ActivityWatermaintenanceBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class watermaintenance extends AppCompatActivity {

    ActivityWatermaintenanceBinding binding;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityWatermaintenanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String region = binding.searchTxt.getText().toString();
                if (!region.isEmpty()){

                    searchData(region);
                }else{
                    Toast.makeText(watermaintenance.this, "Please enter region",Toast.LENGTH_SHORT).show();



                }
            }

            private void searchData(String region) {

                ref = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Maintenance");
                ref.child(region).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult().exists()) {

                                Toast.makeText(watermaintenance.this, "Region found!", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot =  task.getResult();
                                String activity = String.valueOf(dataSnapshot.child("Activity").getValue());
                                String start = String.valueOf(dataSnapshot.child("Start").getValue());
                                String completion = String.valueOf(dataSnapshot.child("Completion").getValue());
                                String region = String.valueOf(dataSnapshot.child("Region").getValue());
                                String areaAffected = String.valueOf(dataSnapshot.child("AreaAffected").getValue());

                                binding.activityMaintenance.setText(activity);
                                binding.startMaintenance.setText(start);
                                binding.endMaintenance.setText(completion);
                                binding.regionMaintenance.setText(region);
                                binding.areaMaintenance.setText(areaAffected);




                            } else {

                                Toast.makeText(watermaintenance.this, "Region doesn't exist", Toast.LENGTH_SHORT).show();
                                binding.activityMaintenance.setText("");
                                binding.startMaintenance.setText("");
                                binding.endMaintenance.setText("");
                                binding.regionMaintenance.setText("");
                                binding.areaMaintenance.setText("");
                            }


                        } else {

                            Toast.makeText(watermaintenance.this, "Failed to search", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });



    }

    public void InboxMaintenance(View view) {
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

    public void BackMaintenance(View view) {
        startActivity(new Intent(getApplicationContext(), waterinfoactivity.class));
        finish();
    }
}