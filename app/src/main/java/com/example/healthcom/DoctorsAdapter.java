package com.example.healthcom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder>{
    private Context context;
    private ArrayList<String> dId;
    private ArrayList<String> dName;
    private ArrayList<String> qualifations;
    private ArrayList<String> phone;
    private ArrayList<String> email;

    public DoctorsAdapter(Context context, ArrayList<String> dId, ArrayList<String> dName, ArrayList<String> qualifations, ArrayList<String> phone, ArrayList<String> email) {
        this.context = context;
        this.dId = dId;
        this.dName = dName;
        this.qualifations = qualifations;
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_container_doctors, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDName.setText(dName.get(position));
        holder.tvQualifations.setText(qualifations.get(position));
        holder.tvPhone.setText(phone.get(position));
        holder.tvEmail.setText(email.get(position));
    }

    @Override
    public int getItemCount() {
        return dName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDName;
        TextView tvQualifations;
        TextView tvPhone;
        TextView tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDName = itemView.findViewById(R.id.tvDName);
            tvQualifations = itemView.findViewById(R.id.tvQualifications);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }


}
