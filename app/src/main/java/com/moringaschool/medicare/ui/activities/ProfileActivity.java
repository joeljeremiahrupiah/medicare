package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.medicare.R;

public class ProfileActivity extends AppCompatActivity {
    Button edit;
    Button sub;
    CardView card;
    EditText edtBirth;
    EditText edtGen;
    EditText edtHei;
    EditText edtWei;
    TextView birth;
    TextView gend;
    TextView usrHeight;
    TextView usrWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edit = findViewById(R.id.edit);
        card =findViewById(R.id.edtCard);
        sub =findViewById(R.id.sub);
        edtBirth =findViewById(R.id.edtBday);
        edtGen =findViewById(R.id.edGender);
        edtHei =findViewById(R.id.edHeight);
        edtWei =findViewById(R.id.edWeight);
        birth = findViewById(R.id.bithday);
        gend = findViewById(R.id.gender);
        usrHeight = findViewById(R.id.height);
        usrWeight = findViewById(R.id.weight);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bd =edtBirth.getText().toString();
                String bg =edtGen.getText().toString();
                String bh = edtHei.getText().toString();
                String bw =edtWei.getText().toString();
                birth.setText(bd);
                gend.setText(bg);
                usrHeight.setText(bh);
                usrWeight.setText(bw);
                card.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);

            }
        });
    }
}