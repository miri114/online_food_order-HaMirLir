package com.example.hamirlir.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hamirlir.R;
import com.example.hamirlir.models.Dishes;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PreferredAdapter extends RecyclerView.Adapter<PreferredAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Dishes> dishesArrayList;

    public PreferredAdapter(Context context, ArrayList<Dishes> dishesArrayList) {
        this.context = context;
        this.dishesArrayList = dishesArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.preferred_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Dishes myDishes = dishesArrayList.get(position);

        holder.price.setText(String.valueOf(myDishes.getPrice()));

        holder.dish_name.setText(myDishes.getDish_name());

        Glide.with(context)
                .load(myDishes.getDish_imgUrl())
                .centerCrop()
                .into(holder.dish_image);
    }

    @Override
    public int getItemCount() {
        return dishesArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  price, dish_name;
        ImageView dish_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.pricePreferred);
            dish_name = (TextView) itemView.findViewById(R.id.txtPreferred);
            dish_image =(CircleImageView) itemView.findViewById(R.id.profile_image);
        }
    }

}

