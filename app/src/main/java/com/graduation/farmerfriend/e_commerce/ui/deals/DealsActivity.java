package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


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
        View view = binding.getRoot();
        setContentView(view);



        Intent n = getIntent();
        if (n.getStringExtra("BEST_SELLER").equals("true")) {
            if (savedInstanceState == null) {
                bestSellersFragment = new BestSellerFragment();
                FragmentManager fragmentMng = getSupportFragmentManager();
                FragmentTransaction trn = fragmentMng.beginTransaction();
                trn.replace(R.id.activity_deals_fragmentContainer, bestSellersFragment);
                trn.commit();
            }
        } else {
            if (savedInstanceState == null) {
                hotDealsFragment = new HotDealsFragment();
                FragmentManager fragmentMng = getSupportFragmentManager();
                FragmentTransaction trn = fragmentMng.beginTransaction();
                trn.replace(R.id.activity_deals_fragmentContainer, hotDealsFragment);
                trn.commit();
            }
        }
    }
}