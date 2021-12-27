package com.graduation.farmerfriend.e_commerce.ui.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivitySearchBinding;
import com.graduation.farmerfriend.e_commerce.ui.cart.CartActivity;
import com.graduation.farmerfriend.e_commerce.ui.wishlist.WishlistActivity;

public class Search extends AppCompatActivity {

    ActivitySearchBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.activitySearchTextViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        binding.activitySearchTextViewWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
                startActivity(intent);
            }
        });

        Search_Item[] search ={
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating")
        };


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.activitySearchRecycleviewSearchItem.setLayoutManager(linearLayoutManager);
        ViewRecycleSearchAdapter recycleViewAdapter = new ViewRecycleSearchAdapter(getApplicationContext(),search);
        binding.activitySearchRecycleviewSearchItem.setAdapter(recycleViewAdapter);

    }
}

