package com.example.ppl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    ArrayList<Datamodel> list;

    public MyAdapter(Context context, ArrayList<Datamodel> list) {
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Datamodel> getList() {
        return list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_card_view_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Datamodel user = list.get(position);
        holder.problem.setText(user.getProblem());
        holder.desc.setText(user.getDescription());
        holder.address.setText(user.getCom_address());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView problem, desc, address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            problem = itemView.findViewById(R.id.problem_fetch);
            desc = itemView.findViewById(R.id.description_fetch);
            address = itemView.findViewById(R.id.address_fetch);
        }
    }
}
