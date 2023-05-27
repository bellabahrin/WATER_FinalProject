package com.example.waterappmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.waterappmanagement.databinding.ActivityWatermaintenanceBinding;
import com.example.waterappmanagement.databinding.ActivityWaternewsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class waternews extends AppCompatActivity {

    ActivityWaternewsBinding binding;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityWaternewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = binding.newsTxt.getText().toString();
                if (!date.isEmpty()){

                    searchData(date);
                }else{
                    Toast.makeText(waternews.this, "Please enter date of news",Toast.LENGTH_SHORT).show();



                }
            }

            private void searchData(String date) {

                ref = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("News");
                ref.child(date).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if (task.isSuccessful()) {

                            if (task.getResult().exists()) {

                                Toast.makeText(waternews.this, "News found!", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot =  task.getResult();
                                String newsTitle = String.valueOf(dataSnapshot.child("NewsTitle").getValue());
                                String newsDate = String.valueOf(dataSnapshot.child("NewsDate").getValue());
                                String newsSource = String.valueOf(dataSnapshot.child("NewsSource").getValue());
                                String newsSummary = String.valueOf(dataSnapshot.child("NewsSummary").getValue());

                                binding.newsTitle.setText(newsTitle);
                                binding.dateTitle.setText(newsDate);
                                binding.sourceTitle.setText(newsSource);
                                binding.summaryTitle.setText(newsSummary);




                            } else {

                                Toast.makeText(waternews.this, "News doesn't exist", Toast.LENGTH_SHORT).show();
                                binding.newsTitle.setText("");
                                binding.dateTitle.setText("");
                                binding.sourceTitle.setText("");
                                binding.summaryTitle.setText("");
                            }


                        } else {

                            Toast.makeText(waternews.this, "Failed to search the news", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });

    }

    public void BackNews(View view) {
        startActivity(new Intent(getApplicationContext(), waterinfoactivity.class));
        finish();
    }

    public void InboxNews(View view) {
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }
}