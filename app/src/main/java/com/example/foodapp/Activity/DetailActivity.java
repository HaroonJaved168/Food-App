package com.example.foodapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.bumptech.glide.Glide;
import com.example.foodapp.Activity.Cart;
import com.example.foodapp.Domain.Food;
import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    private Food objet;
    private int num = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(getResources().getColor(R.color.black));

        getIntentExtra();
        setVariable();

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
                order();
            }
        });
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());

        Glide.with(DetailActivity.this)
                .load(objet.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("$" + objet.getPrice());
        binding.titleTxt.setText(objet.getTitle());
        binding.descriptionTxt.setText(objet.getDescription());
        binding.totalTxt.setText(num * objet.getPrice() + "$");

        binding.plusBtn.setOnClickListener(v -> {
            num = num + 1;
            binding.numTxt.setText(num + " ");
            binding.totalTxt.setText("$" + (num * objet.getPrice()));
        });

        binding.minusBtn.setOnClickListener(v -> {
            if (num > 1) {
                num = num - 1;
                binding.numTxt.setText(num + "");
                binding.totalTxt.setText("$" + (num * objet.getPrice()));
            }
        });
    }

    private void getIntentExtra() {
        objet = (Food) getIntent().getSerializableExtra("object");
    }

    private void addToCart() {
        double totalPrice = num * objet.getPrice();
        ListData listData = new ListData(objet.getTitle(), totalPrice, objet.getImagePath());
        Cart.getInstance().addItem(listData);
    }

    public void order() {
        Intent intent = new Intent(DetailActivity.this, ListViewActivity.class);
        startActivity(intent);
    }
}
