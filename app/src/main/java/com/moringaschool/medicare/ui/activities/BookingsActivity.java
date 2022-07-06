package com.moringaschool.medicare.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.Bookings;
import com.moringaschool.medicare.ui.adapters.BookingAdapter;

import java.util.ArrayList;

public class BookingsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    BookingAdapter bookingAdapter;
    ArrayList<Bookings> bookingsList;
    ImageView gbck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        recyclerView = findViewById(R.id.bkRecycler);
        databaseReference= FirebaseDatabase.getInstance().getReference("Appointments");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookingsList= new ArrayList<>();
        bookingAdapter = new BookingAdapter(this,bookingsList);
        recyclerView.setAdapter(bookingAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    bookingsList.add(dataSnapshot.child("Booking").getValue(Bookings.class));
                }
                bookingAdapter.notifyItemInserted(bookingsList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        gbck= findViewById(R.id.gbck);
        gbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnds = new Intent(BookingsActivity.this,AppointmentActivity.class);
                startActivity(intnds);
            }
        });

    }
}