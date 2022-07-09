package com.moringaschool.medicare.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.Category;
import com.moringaschool.medicare.ui.adapters.CategoryRecyclerAdapter;

import java.util.ArrayList;

public class MedicineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Category> catList;
    private CategoryRecyclerAdapter adapter;

    private int[] myImageList = new int[]{R.drawable.consult, R.drawable.medicine,R.drawable.tst, R.drawable.ref,R.drawable.hh};
    private String[] myImageNameList = new String[]{"Consultation","Medicines" ,"Lab Tests","Refill","Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView2);

       catList = setCategory();
        adapter = new CategoryRecyclerAdapter(this, catList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private ArrayList<Category> setCategory() {
        ArrayList<Category> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Category category = new Category();
            category.setName(myImageNameList[i]);
            category.setImage_drawable(myImageList[i]);
            list.add(category);
        }

        return list;
    }
}