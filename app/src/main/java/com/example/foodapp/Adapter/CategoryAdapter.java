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
import com.example.foodapp.Activity.ListFoodsActivity2;
import com.example.foodapp.Domain.Categories;
import com.example.foodapp.Domain.Food;
import com.example.foodapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {

    ArrayList<Categories> items;
    Context context;

    public CategoryAdapter(ArrayList<Categories> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new viewholder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
      holder.textTitle.setText(items.get(position).getName());

      switch (position){
          case 0:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_0_background);
              break;
          }
          case 1:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_1_background);
              break;
          }
          case 2:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_2_background);
              break;
          }
          case 3:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_3_background);
              break;
          }
          case 4:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_4_background);
              break;
          }
          case 5:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_5_background);
              break;
          }
          case 6:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_6_background);
              break;
          }
          case 7:{
              holder.imgViewPic.setBackgroundResource(R.drawable.cat_7_background);
              break;
          }
      }

      int drawableResourceId=context.getResources().getIdentifier(items.get(position).getImagePath(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.imgViewPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , ListFoodsActivity2.class);
                intent.putExtra("CategoryId" , items.get(position).getId());
                intent.putExtra("CategoryName" , items.get(position).getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView textTitle;
        ImageView imgViewPic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.catNameTxt);
            imgViewPic = itemView.findViewById(R.id.imgCat);
        }
    }
}
