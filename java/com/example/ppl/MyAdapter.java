package com.example.ppl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    FirebaseDatabase  rootNode ;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;
    helperclass hc;

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

        int Count= user.getCount();
        String phone_num = user.getPhone_num();
        int Id = user.getId();
        String person = user.getPerson();
        String description = user.getDescription();
        double lat = user.getLatitude_value();
        double lon =user.getLongitude_value();
        String problem = user.getProblem();
        String status = user.getStatus();
        int Fake = user.getFake();
        double com_lat = user.getCom_lat();
        double com_lon = user.getCom_lon();
        String com_address = user.getCom_address();
        String com_city = user.getCom_city();
        String com_postal = user.getCom_postal();




        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode= FirebaseDatabase.getInstance();
                reference= rootNode.getReference("Complaint");
                int count = Count + 1;
                helperclass hc =new helperclass(Id, phone_num,person, description,lat,lon,problem, status,count,Fake, com_lat, com_lon, com_address, com_city, com_postal);
                reference.child(String.valueOf(Id)).setValue(hc);

                holder.yes.setVisibility(View.INVISIBLE);
                holder.no.setVisibility(View.INVISIBLE);
                holder.not_sure.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "THANK YOU FOR YOUR SUPPORT", Toast.LENGTH_SHORT).show();


            }
        });
        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode= FirebaseDatabase.getInstance();
                reference= rootNode.getReference("Complaint");
                int fake = Fake + 1;
                helperclass hc =new helperclass(Id, phone_num,person, description,lat,lon,problem, status,Count,fake, com_lat, com_lon, com_address, com_city, com_postal);
                reference.child(String.valueOf(Id)).setValue(hc);
                holder.yes.setVisibility(View.INVISIBLE);
                holder.no.setVisibility(View.INVISIBLE);
                holder.not_sure.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "THANK YOU FOR YOUR SUPPORT", Toast.LENGTH_SHORT).show();

            }
        });
        holder.not_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.yes.setVisibility(View.INVISIBLE);
                holder.no.setVisibility(View.INVISIBLE);
                holder.not_sure.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "THANK YOU FOR YOUR SUPPORT", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView problem, desc, address;
        Button yes,not_sure,no;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            problem = itemView.findViewById(R.id.problem_fetch);
            desc = itemView.findViewById(R.id.description_fetch);
            address = itemView.findViewById(R.id.address_fetch);
            yes=itemView.findViewById(R.id.yes);
            not_sure=itemView.findViewById(R.id.not_sure);
            no=itemView.findViewById(R.id.no);

        }
    }

}

