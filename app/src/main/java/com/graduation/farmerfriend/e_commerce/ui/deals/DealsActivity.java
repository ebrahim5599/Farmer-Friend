package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityDealsBinding;

public class DealsActivity extends AppCompatActivity {

    private ActivityDealsBinding binding;
    private BestSellerFragment bestSellersFragment;
    private HotDealsFragment hotDealsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDealsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent n = getIntent();
        if (n.getStringExtra("BEST_SELLER").equals("true")) {
            if (bestSellersFragment == null) {
                bestSellersFragment = new BestSellerFragment();
                Log.i("onCreate()", "bestSellerFragmentCreated");
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainerView2, bestSellersFragment);
            transaction.commit();
        } else {
            if (hotDealsFragment == null) {
                hotDealsFragment = new HotDealsFragment();
                Log.i("onCreate()", "hotDealsFragmentCreated");
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragmentContainerView2, hotDealsFragment);
            transaction.commit();
        }
    }
}