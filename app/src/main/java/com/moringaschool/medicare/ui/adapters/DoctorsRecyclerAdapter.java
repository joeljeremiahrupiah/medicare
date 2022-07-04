package com.moringaschool.medicare.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.Doctor;

import java.util.ArrayList;

public class DoctorsRecyclerAdapter extends RecyclerView.Adapter<DoctorsRecyclerAdapter.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Doctor> doctorsList;

    public DoctorsRecyclerAdapter(RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<Doctor> doctorsList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.doctorsList = doctorsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.doctorName.setText(doctorsList.get(position).getName());
//        holder.practice.setText(doctorsList.get(position).getPractice());
//        holder.rating.setText(doctorsList.get(position).getRating());
//        holder.hours.setText(doctorsList.get(position).getHours());

    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView doctorName;
        TextView practice;
        TextView rating;
        TextView hours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.drName);
            practice = itemView.findViewById(R.id.practice);
            rating = itemView.findViewById(R.id.rating);
            hours = itemView.findViewById(R.id.hours);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
