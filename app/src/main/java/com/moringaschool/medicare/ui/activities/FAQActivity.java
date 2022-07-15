package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
Button post;
EditText question;
ImageView send;
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
        question = findViewById(R.id.ques);
        post = findViewById(R.id.post);
        send = findViewById(R.id.send);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setVisibility(View.VISIBLE);
                send.setVisibility(View.VISIBLE);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setVisibility(View.GONE);
                send.setVisibility(View.GONE);
                Toast.makeText(FAQActivity.this, "Your message has been send.You will get feedback soon.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}