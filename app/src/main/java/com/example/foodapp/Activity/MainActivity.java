package com.example.foodapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.foodapp.Adapter.BestFoodAdapter;
import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Domain.Categories;
import com.example.foodapp.Domain.Food;
import com.example.foodapp.Domain.Location;
import com.example.foodapp.Domain.Price;
import com.example.foodapp.Domain.Time;
import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityMainBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        initLocation();
//        initTime();
//        initPrice();
        initBestFood();
        initCategory();
        setVariable();

    }

    private void setVariable() {

        binding.logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this , LoginActivity.class));
        });

        binding.searchBtn.setOnClickListener(v -> {
            String text = binding.editTextSearchEdt.getText().toString();
            if (!text.isEmpty()){

                Intent intent = new Intent(MainActivity.this , ListFoodsActivity2.class);
                intent.putExtra("text" ,text);
                intent.putExtra("isSearch" , true);
                startActivity(intent);

            }
        });
    }

    private void initBestFood() {

        DatabaseReference myRef = database.getReference("Foods");
        binding.progBarBestFood.setVisibility(View.VISIBLE);
        Query query = myRef.orderByChild("BestFood").equalTo(true);
        ArrayList<Food> list = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot issue: snapshot.getChildren()){

                        list.add(issue.getValue(Food.class));
                    }
                    if (list.size()>0){
                        binding.recyclerBestFoodView.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.HORIZONTAL, false));
                        RecyclerView.Adapter adapter = new BestFoodAdapter(list);
                        binding.recyclerBestFoodView.setAdapter(adapter);
                    }
                    binding.progBarBestFood.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCategory() {


        DatabaseReference myRef = database.getReference("Category");
        binding.progBarCategory.setVisibility(View.VISIBLE);

        ArrayList<Categories> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){


                    for (DataSnapshot issue: snapshot.getChildren()){

                        list.add(issue.getValue(Categories.class));
                    }
                    if (list.size()>0){
                        binding.recyclerCategoryView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                        RecyclerView.Adapter adapter = new CategoryAdapter(list);
                        binding.recyclerCategoryView.setAdapter(adapter);
                    }
                    binding.progBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*private void initLocation() {

        DatabaseReference dbR = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();

        dbR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Location.class));

                    }

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initTime() {

        DatabaseReference dbR = database.getReference("Time");
        ArrayList<Time> list = new ArrayList<>();

        dbR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Time.class));

                    }

                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initPrice() {

        DatabaseReference dbR = database.getReference("Price");
        ArrayList<Price> list = new ArrayList<>();

        dbR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(Price.class));

                    }

                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSpinner.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    } */
}