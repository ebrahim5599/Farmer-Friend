package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.graduation.farmerfriend.databinding.ActivityCartBinding;
import com.graduation.farmerfriend.e_commerce.ui.User_Data;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        CartItemsAdapter adapter = new CartItemsAdapter(this);
        binding.cartRecyclerView.setAdapter(adapter);
        binding.cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Drawable mDivider = ContextCompat.getDrawable(requireContext(), R.drawable.ic_divider);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable();
        binding.cartRecyclerView.addItemDecoration(itemDecoration);

        binding.cartButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, User_Data.class);
                startActivity(intent);
            }
        });


    }
}