package com.sheygam.masa_2018_g1_26_12_18;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyAdapterClickListner listner;
    private ArrayList<String> titles;
    public MyAdapter(){
        titles = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            titles.add("Title " + i);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleTxt.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public void setListener(MyAdapterClickListner listener) {
        this.listner = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null){
                        listner.onRowClick(titles.get(getAdapterPosition()));
                    }
                }
            });

        }
    }

    interface MyAdapterClickListner{
        void onRowClick(String title);
    }

}
