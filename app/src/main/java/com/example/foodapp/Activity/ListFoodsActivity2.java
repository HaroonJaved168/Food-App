package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.foodapp.Adapter.FoodListAdapter;
import com.example.foodapp.Domain.Food;
import com.example.foodapp.databinding.ActivityListFoods2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity2 extends BaseActivity {

    ActivityListFoods2Binding binding;

    private RecyclerView.Adapter adapterLisFood;
    private int categoryId;
    private String categoryName;
    private String searchText;
    private boolean isSearch;
    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoods2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        getIntentExtra();
        initList();
        setVariable();
    }

    private void setVariable() {
    }

    private void initList() {

        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarListFood.setVisibility(View.VISIBLE);
        ArrayList<Food> list = new ArrayList<>();

        Query query;

        if (isSearch){
            query=myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query=myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    for (DataSnapshot issue : snapshot.getChildren()){

                        list.add(issue.getValue(Food.class));
                    }
                    if (list.size() > 0 ){
                        binding.recyclerFoodListView.setLayoutManager(new GridLayoutManager(ListFoodsActivity2.this,2));
                        adapterLisFood=new FoodListAdapter(list);
                        binding.recyclerFoodListView.setAdapter(adapterLisFood);

                    }

                    binding.progressBarListFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIntentExtra() {

        categoryId=getIntent().getIntExtra("CategoryId" , 0);
        categoryName=getIntent().getStringExtra("CategoryName");
        searchText=getIntent().getStringExtra("text");
        isSearch=getIntent().getBooleanExtra("isSearch" , false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());

    }
}