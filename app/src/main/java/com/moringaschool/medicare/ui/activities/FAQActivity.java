package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.medicare.R;

public class FAQActivity extends AppCompatActivity {
TextView que1;
TextView que2;
TextView que3;
TextView que4;
TextView ans1;
TextView ans2;
TextView ans3;
TextView ans4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);
        que1= findViewById(R.id.que1);
        que2= findViewById(R.id.que2);
        que3= findViewById(R.id.que3);
        que4= findViewById(R.id.que4);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        que1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans1.setVisibility(View.VISIBLE);
            }
        });
        que2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans2.setVisibility(View.VISIBLE);
            }
        });
        que3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans3.setVisibility(View.VISIBLE);
            }
        });
        que4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans4.setVisibility(View.VISIBLE);
            }
        });
    }
}