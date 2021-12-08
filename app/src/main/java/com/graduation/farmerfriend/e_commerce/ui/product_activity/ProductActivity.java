package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityProductBinding;

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

        binding.activityProductTextViewFarmerFriend.setText("Farmer\nFriend");

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



    }



}