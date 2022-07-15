package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.moringaschool.medicare.MainActivity;
import com.moringaschool.medicare.R;
import com.moringaschool.medicare.ui.fragments.ConsultationFragment;
import com.moringaschool.medicare.ui.fragments.DeliveryFragment;

public class SplashActivity extends AppCompatActivity {
    TextView skip;
    TextView next;
    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        skip = findViewById(R.id.skp);
        next= findViewById(R.id.nxt);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Splash2Activity.class);
                startActivity(intent);
                finish();
            }
        },2500);

    }

    private void replaceFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
//        fragmentTransaction.commit();
    }
}