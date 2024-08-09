package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodapp.Domain.Food;
import com.example.foodapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends BaseActivity {

    private static final String TAG = "OrderActivity";
    Button btnConfirm, btnCancel;
    private ListData selectedItem;
    private DatabaseReference databaseReference;
    private String orderKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("orders");

        // Retrieve the selected item from the intent
        selectedItem = (ListData) getIntent().getSerializableExtra("selectedItem");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(v);
            }
        });
    }

    public void confirm(View view) {
        // Store the selected item in the "adminOrders" node in Firebase Realtime Database
        DatabaseReference newOrderRef = FirebaseDatabase.getInstance().getReference("adminOrders").push();
        newOrderRef.setValue(selectedItem)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        orderKey = newOrderRef.getKey(); // Save the key of the new order
                        Toast.makeText(this, "Order Confirmed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }

           
                });
    }

    public void cancel(View view) {
        if (orderKey != null) {
            // Remove the selected item from the "adminOrders" node in Firebase Realtime Database
            databaseReference.child(orderKey).removeValue()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                            orderKey = null; // Reset the order key
                        } else {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No order to cancel", Toast.LENGTH_SHORT).show();
        }
    }
}
