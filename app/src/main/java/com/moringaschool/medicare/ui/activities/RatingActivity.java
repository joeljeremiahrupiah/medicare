package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.medicare.R;

public class RatingActivity extends AppCompatActivity {
    View dislike;
    View like;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        dislike = findViewById(R.id.animationView);
        like = findViewById(R.id.animationView2);
        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingActivity.this, "Thank you for your feedback.What can we do to improve your experience?", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onClick: like" );
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingActivity.this, "Thank you for your feedback..", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onClick: like" );
            }
        });
    }
}