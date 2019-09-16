package com.example.healthcom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacilitiesAdapter extends RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>{
    private Context context;
    private ArrayList<String> facilities;
    private ArrayList<Integer> cost;

    public FacilitiesAdapter(Context context, ArrayList<String> facilities, ArrayList<Integer> cost) {
        this.context = context;
        this.facilities = facilities;
        this.cost = cost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_container_facilities, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvFaciName.setText(facilities.get(position));
        holder.tvFaciCost.setText(String.valueOf(cost.get(position)));

    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFaciName;
        TextView tvFaciCost;
        LinearLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFaciName = itemView.findViewById(R.id.tvFaciName);
            tvFaciCost = itemView.findViewById(R.id.tvFaciCost);
            parentLayout = itemView.findViewById(R.id.parent_facility);
        }
    }
}