package com.graduation.farmerfriend.e_commerce.ui;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.ActivityShopBinding;

public class ShopActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityShopBinding binding;
    private String chooseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShopBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.fragmentShopView);
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.seedProductsFragment, R.id.toolProductsFragment,
                R.id.fertilizerProductsFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.ecommerceBottomNavView,navController);

        Intent intent = getIntent();
        chooseFragment = intent.getStringExtra(Constants.NAVIGATION_FRAGMENT);
        if (chooseFragment.equals(Constants.FRAGMENT_SEEDS)) {
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.seedProductsFragment, true)
//                    .build();
            navController.navigate(R.id.seedProductsFragment, null);
        } else if (chooseFragment.equals(Constants.FRAGMENT_FERTILIZERS)) {
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.seedProductsFragment, true)
//                    .build();
            navController.navigate(R.id.fertilizerProductsFragment, null);
        } else if (chooseFragment.equals(Constants.FRAGMENT_TOOLS)) {
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.seedProductsFragment, true)
//                    .build();
            navController.navigate(R.id.toolProductsFragment, null);
        }
        else if (chooseFragment.equals(Constants.FRAGMENT_WISHLIST)) {
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.seedProductsFragment, true)
//                    .build();
            navController.navigate(R.id.wishlistFragment, null);
        } else if (chooseFragment.equals(Constants.FRAGMENT_CART)) {
//            NavOptions navOptions = new NavOptions.Builder()
//                    .setPopUpTo(R.id.seedProductsFragment, true)
//                    .build();
            navController.navigate(R.id.cartFragment, null);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentShopView);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item,Navigation.findNavController(this,R.id.fragmentShopView))||super.onOptionsItemSelected(item);
    }
}