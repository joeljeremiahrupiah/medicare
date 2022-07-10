package com.moringaschool.medicare.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.DrugList;
import com.moringaschool.medicare.models.FormAndDosage;
import com.moringaschool.medicare.network.clients.DrugClient;
import com.moringaschool.medicare.network.interfaces.DrugApi;
import com.moringaschool.medicare.ui.adapters.DrugAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrugsActivity extends AppCompatActivity {
    @BindView(R.id.drugRecycler) RecyclerView mRecyclerView;
    @BindView(R.id.drugTxt)
    EditText drugTxt;
    List<FormAndDosage> form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs);
        ButterKnife.bind(this);

        drugTxt.setText("Xanax");
        String slug = drugTxt.getText().toString();

        DrugApi client = DrugClient.getClient();
        initRecycler();
        Call<DrugList> call = client.getInfo(slug);

        call.enqueue(new Callback<DrugList>(){

            @Override
            public void onResponse(@NonNull Call<DrugList> call,  Response<DrugList> response) {
                if(response.isSuccessful()){
                    if (response!= null) {
                        Log.e("TAG", call.request().url().toString());
                        Log.e("TAG", "res" + response.body().toString());
                        form = response.body().getData().getBrand().getFormAndDosages();
                        init();

                    }else
                        Toast.makeText(DrugsActivity.this, "No such drug", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getBaseContext(), "Drug does not exist", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<DrugList> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecycler() {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(DrugsActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);;
    }
    private void init(){
        DrugAdapter drugAdapter = new DrugAdapter(mRecyclerView.getContext(), form);
        mRecyclerView.setAdapter(drugAdapter);
    }
}