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

public class ListWithIDAdapter extends RecyclerView.Adapter<ListWithIDAdapter.ViewHolder>{

    private ArrayList<String> hsptNames = new ArrayList<>();
    private ArrayList<Integer> hsptID = new ArrayList<>();
    private Context context;
    private OnHsptListener onHsptListener;

    public ListWithIDAdapter(Context context, ArrayList<Integer> hsptID, ArrayList<String> hsptNames, OnHsptListener onHsptListener) {
        this.hsptNames = hsptNames;
        this.hsptID = hsptID;
        this.context = context;
        this.onHsptListener = onHsptListener;
    }

    public int getClickedHsptID(int position) {
        return hsptID.get(position);
    }

    //below method same of almost any recyclerViews, just change the layout depending on use
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view, onHsptListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //to bind hospital name to the textview
        holder.hsptName.setText(hsptNames.get(position));


        //get hospital id when clicked on it
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, String.valueOf(hsptID.get(position)), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        //if this is not correct then the List wont appear
        //can use any Array to get the size
        return hsptNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hsptName;
        LinearLayout parentLayout;
        OnHsptListener onHsptListener;

        public ViewHolder(@NonNull View itemView, OnHsptListener onHsptListener) {
            super(itemView);
            hsptName = itemView.findViewById(R.id.hsptName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            this.onHsptListener = onHsptListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onHsptListener.OnHsptClickListener(getAdapterPosition());
        }
    }

    public interface OnHsptListener{
        void OnHsptClickListener(int position);
    }
}
