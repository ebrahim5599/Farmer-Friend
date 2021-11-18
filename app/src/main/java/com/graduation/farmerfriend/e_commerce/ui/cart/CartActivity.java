package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityCartBinding;

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


    }
}