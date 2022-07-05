package com.moringaschool.medicare.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.medicare.R;

public class AppointmentActivity extends AppCompatActivity {
    private CalendarView mCalendarView;
    private Button appointmentTime;
    private Button appointmentTime2;
    private Button appointmentTime3;
    private Button appointmentTime4;
    private Button appointmentTime5;
    private Button booking;
    String date;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        mCalendarView = findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 date = dayOfMonth+"/"+ (month+1) + "/"+year;
                Log.e("TAG", "onSelectedDayChange: "+ date);
            }
        });
        appointmentTime = findViewById(R.id.am10);
        appointmentTime2 = findViewById(R.id.am11);
        appointmentTime3 = findViewById(R.id.am1);
        appointmentTime4 = findViewById(R.id.am2);
        appointmentTime5 = findViewById(R.id.am3);


        appointmentTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentTime.setBackgroundColor(Color.parseColor("#21D0B2"));
                 time = (String) appointmentTime.getText();
                Log.e("TAG", "Time"+time);
            }
        });
        appointmentTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentTime2.setBackgroundColor(Color.parseColor("#21D0B2"));
                time = (String) appointmentTime2.getText();
                Log.e("TAG", "Time"+time);
            }
        });
        appointmentTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentTime3.setBackgroundColor(Color.parseColor("#21D0B2"));
                time = (String) appointmentTime3.getText();
                Log.e("TAG", "Time"+time);
            }
        });
        appointmentTime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentTime4.setBackgroundColor(Color.parseColor("#21D0B2"));
                time = (String) appointmentTime4.getText();
                Log.e("TAG", "Time"+time);
            }
        });
        appointmentTime5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointmentTime5.setBackgroundColor(Color.parseColor("#21D0B2"));
                time = (String) appointmentTime5.getText();
                Log.e("TAG", "Time"+time);
            }
        });
        booking = findViewById(R.id.book);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppointmentActivity.this, "Booked Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AppointmentActivity.this, BookingsActivity.class);
                intent.putExtra("dateBooked",date);
                intent.putExtra("timeBooked",time);
                FirebaseDatabase.getInstance().getReference("Appointments").push().child("Date").setValue(date + " "+ time);
                startActivity(intent);
            }
        });
    }
}