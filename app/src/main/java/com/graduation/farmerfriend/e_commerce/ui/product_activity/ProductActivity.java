package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.ui.deals.BestSellerFragment;

public class ProductActivity extends AppCompatActivity {

    FertilizerProductsFragment fertilizerProductsFragment;
    SeedProductsFragment seedProductsFragment;
    ToolProductsFragment toolProductsFragment;
    String chooseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        TextView farmer_friend = findViewById(R.id.textView_farmer_friend);
        farmer_friend.setText("Farmer\nFriend");

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
                fragmentTransaction.replace(R.id.fragmentContainerView3, fertilizerProductsFragment);
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
                fragmentTransaction.replace(R.id.fragmentContainerView3, toolProductsFragment);
                fragmentTransaction.commit();
            }
        }

        LinearLayout seeds_linear = findViewById(R.id.seeds_linear);
        seeds_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seedProductsFragment == null) {
                    seedProductsFragment = new SeedProductsFragment();
//                    Log.i("onCreate()","SeedsFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView3, seedProductsFragment);
                fragmentTransaction.commit();

            }
        });


        LinearLayout fertilizers_linear = findViewById(R.id.fertilizers_linear);
        fertilizers_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fertilizerProductsFragment == null) {
                    fertilizerProductsFragment = new FertilizerProductsFragment();
//                    Log.i("onCreate()","FertilizersFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView3, fertilizerProductsFragment);
                fragmentTransaction.commit();
            }
        });


        LinearLayout tools_linear = findViewById(R.id.tools_linear);
        tools_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toolProductsFragment == null) {
                    toolProductsFragment = new ToolProductsFragment();
                    Log.i("onCreate()", "ToolsFragment created");
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView3, toolProductsFragment);
                fragmentTransaction.commit();
            }
        });
    }
}