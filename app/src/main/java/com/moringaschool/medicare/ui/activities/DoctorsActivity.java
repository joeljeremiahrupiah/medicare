package com.moringaschool.medicare.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.Doctor;
import com.moringaschool.medicare.network.clients.DoctorClient;
import com.moringaschool.medicare.network.interfaces.DoctorApi;
import com.moringaschool.medicare.ui.adapters.DoctorsRecyclerAdapter;
import com.moringaschool.medicare.ui.adapters.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorsActivity extends AppCompatActivity implements RecyclerViewInterface  {
    RecyclerView recyclerView;
    RecyclerViewInterface recyclerViewInterface;
    DoctorsRecyclerAdapter doctorsRecyclerAdapter;
    List<Doctor> doctorList = new ArrayList<>();
    Doctor doctor;
    DoctorApi client = DoctorClient.getClient();
    DoctorClient doctorClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        ButterKnife.bind(this);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuOrders:
                        Intent intent = new Intent(DoctorsActivity.this, MedicineActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        recyclerView = findViewById(R.id.doctorsRecycler);
        initRecycler();
        Call<List<Doctor>> call = client.getResults();
        call.enqueue(new Callback<List<Doctor>> () {
            @Override
            public void onResponse(Call<List<Doctor>>  call, Response<List<Doctor>>  response) {
                 doctorList= response.body();
                 init();
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Something went wrong. Please check your Internet connection and try again later"+t.getMessage(),
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    private  void initRecycler(){
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(DoctorsActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }
    public void init(){
        doctorsRecyclerAdapter = new DoctorsRecyclerAdapter(this, DoctorsActivity.this, doctorList);
        recyclerView.setAdapter(doctorsRecyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DoctorDetailActivity.class);
        intent.putExtra("details",doctorList.get(position));
        startActivity(intent);
    }
}