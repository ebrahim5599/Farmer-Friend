package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityDealsBinding;

public class DealsActivity extends AppCompatActivity {

    private ActivityDealsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDealsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        HotDealsFragment f = new HotDealsFragment();
        if (savedInstanceState == null) {
            FragmentManager fragmentMng = getSupportFragmentManager();
            FragmentTransaction trn = fragmentMng.beginTransaction();
            trn.add(R.id.fragmentContainerView2, f);
            trn.commit();
        }



    }
}