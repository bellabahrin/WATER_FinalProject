package com.example.waterappmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;


public class helplineactivity extends AppCompatActivity {

    ClipboardManager clipboardManager;
    ClipData clipData;
    MaterialCardView copy1, copy2, copy3;
    TextView text1, text2, text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);

        //hooks

        copy1 = findViewById(R.id.copyText1);
        copy2 = findViewById(R.id.copyText2);
        copy3 = findViewById(R.id.copyText3);
        text1 = findViewById(R.id.call_txt);
        text2 = findViewById(R.id.emailus_text);

        copy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = text1.getText().toString();

                clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();
            }
        });

        copy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                String text;
                text = text2.getText().toString();

                clipData = ClipData.newPlainText("text", text);
                clipboardManager.setPrimaryClip(clipData);

                Toast.makeText(getApplicationContext(), "Text Copied",Toast.LENGTH_SHORT).show();
            }
        });


    }





    public void BackContact (View view){
        startActivity(new Intent(getApplicationContext(), homepageactivity.class));
        finish();
    }

    public void InboxContact (View view){
        startActivity(new Intent(getApplicationContext(), inboxactivity.class));
        finish();
    }

}