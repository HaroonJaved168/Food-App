package com.example.foodapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapp.Activity.DetailActivity;
import com.example.foodapp.Domain.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class  BestFoodAdapter extends RecyclerView.Adapter<BestFoodAdapter.viewholder> {

    ArrayList<Food> items;
    Context context;

    public BestFoodAdapter(ArrayList<Food> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestFoodAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal,parent,false);
        return new viewholder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BestFoodAdapter.viewholder holder, int position) {

        holder.textTitle.setText(items.get(position).getTitle());
        holder.textPrice.setText("$"+items.get(position).getPrice());
//        holder.textTime.setText(items.get(position).getTimeValue()+"min");
//        holder.textStar.setText(""+items.get(position).getStar());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.imgViewPic);


        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context , DetailActivity.class);
            intent.putExtra("object" , items.get(position));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView textTitle , textPrice , textStar , textTime;
        ImageView imgViewPic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textViewTitle);
            textPrice = itemView.findViewById(R.id.textViewPrice);
//            textStar = itemView.findViewById(R.id.textViewStar);
//            textTime = itemView.findViewById(R.id.textViewTime);
            imgViewPic = itemView.findViewById(R.id.imgViewpic);
        }
    }
}
