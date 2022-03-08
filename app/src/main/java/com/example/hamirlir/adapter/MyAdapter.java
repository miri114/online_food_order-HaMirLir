package com.example.hamirlir.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hamirlir.MainActivity;
import com.example.hamirlir.MenuActivity;
import com.example.hamirlir.R;
import com.example.hamirlir.models.Category;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Category> myArrayList;

    public MyAdapter(Context context, ArrayList<Category> myArrayList) {
        this.context = context;
        this.myArrayList = myArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nje_kategori,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category mykat = myArrayList.get(position);

        holder.id.setText(String.valueOf(mykat.getId()));
        holder.name.setText(mykat.getName());

        Glide.with(context)
                .load(mykat.getImgUrl())
                .centerCrop()
                .into(holder.imgUrl);

        holder.imgUrl.setOnClickListener(v ->
                Toast.makeText(context, "You clicked on image " + mykat.getName() + "with id: " + mykat.getId(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return myArrayList.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  id, name;
        ImageView imgUrl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.kategori_id);
            name = (TextView) itemView.findViewById(R.id.kategori_emer);
            imgUrl = (ImageView) itemView.findViewById(R.id.kategori_foto);
        }
    }
}
