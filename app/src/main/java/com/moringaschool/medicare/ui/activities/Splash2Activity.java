package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.medicare.MainActivity;
import com.moringaschool.medicare.R;

public class Splash2Activity extends AppCompatActivity {
    Handler h = new Handler();
    TextView skip;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash2Activity.this, Splash3Activity.class);
                startActivity(intent);
                finish();
            }
        },2500);
        skip = findViewById(R.id.skp);
        next= findViewById(R.id.nxt);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}