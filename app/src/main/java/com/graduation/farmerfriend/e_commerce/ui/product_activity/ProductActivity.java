package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityProductBinding;
import com.graduation.farmerfriend.e_commerce.ui.Search.Search;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding ;

    FertilizerProductsFragment fertilizerProductsFragment;
    SeedProductsFragment seedProductsFragment;
    ToolProductsFragment toolProductsFragment;
    String chooseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        chooseFragment = intent.getStringExtra("FRAGMENT_NO");
        if (chooseFragment.equals("2")) {
            if (fertilizerProductsFragment == null) {
                fertilizerProductsFragment = new FertilizerProductsFragment();
//                Log.i("onCreate()","FertilizersFragment created");
            }
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_product_fragment, fertilizerProductsFragment);
                fragmentTransaction.commit();
            }

        } else if (chooseFragment.equals("3")) {
            if (toolProductsFragment == null) {
                toolProductsFragment = new ToolProductsFragment();
//                Log.i("onCreate()","ToolsFragment created");
            }
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_product_fragment, toolProductsFragment);
                fragmentTransaction.commit();
            }
        }


        binding.activityProductsSeedsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seedProductsFragment == null) {
                    seedProductsFragment = new SeedProductsFragment();
//                    Log.i("onCreate()","SeedsFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_product_fragment, seedProductsFragment);
                fragmentTransaction.commit();

            }
        });


        binding.activityProductsFertilizersLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fertilizerProductsFragment == null) {
                    fertilizerProductsFragment = new FertilizerProductsFragment();
//                    Log.i("onCreate()","FertilizersFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_product_fragment, fertilizerProductsFragment);
                fragmentTransaction.commit();
            }
        });


        binding.activityProductsToolsLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toolProductsFragment == null) {
                    toolProductsFragment = new ToolProductsFragment();
//                    Log.i("onCreate()", "ToolsFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_product_fragment, toolProductsFragment);
                fragmentTransaction.commit();
            }
        });

        binding.activityProductTextViewWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), WishlistActivity.class);
                startActivity(intent);
            }
        });

        binding.activityProductTextViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        binding.activityProductEditTextSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Search.class);
                    startActivity(intent);

            }
        });

    }



}