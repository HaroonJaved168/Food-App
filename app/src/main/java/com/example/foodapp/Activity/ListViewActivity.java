package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.foodapp.Adapter.ListAdapter;
import com.example.foodapp.Activity.Cart;
import com.example.foodapp.databinding.ActivityListViewBinding;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    ActivityListViewBinding binding;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the cart items
        ArrayList<ListData> cartItems = Cart.getInstance().getCartItems();

        // Create and set the adapter
        listAdapter = new ListAdapter(ListViewActivity.this, cartItems);
        binding.listView.setAdapter(listAdapter);

        // Set item click listener
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListData selectedItem = cartItems.get(position);
                Intent intent = new Intent(ListViewActivity.this, OrderActivity.class);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });
    }
}
