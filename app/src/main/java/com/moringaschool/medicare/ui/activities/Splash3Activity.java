package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moringaschool.medicare.R;

public class Splash3Activity extends AppCompatActivity {
    Button strt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);
        strt = findViewById(R.id.start);
        strt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash3Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}