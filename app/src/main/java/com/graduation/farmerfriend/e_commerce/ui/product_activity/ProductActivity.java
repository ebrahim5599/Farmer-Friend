package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.graduation.farmerfriend.R;

public class ProductActivity extends AppCompatActivity {

    FertilizerProductsFragment fertilizerProductsFragment;
    SeedProductsFragment seedProductsFragment;
    ToolProductsFragment toolProductsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        TextView farmer_friend = findViewById(R.id.textView_farmer_friend);
        farmer_friend.setText("Farmer\nFriend");

        LinearLayout seeds_linear = findViewById(R.id.seeds_linear);
        seeds_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Seeds Fragment", Toast.LENGTH_SHORT).show();
                seedProductsFragment = new SeedProductsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView2, seedProductsFragment);
                fragmentTransaction.commit();

            }
        });


        LinearLayout fertilizers_linear = findViewById(R.id.fertilizers_linear);
        fertilizers_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Fertilizers Fragment", Toast.LENGTH_SHORT).show();
                fertilizerProductsFragment = new FertilizerProductsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView2, fertilizerProductsFragment);
                fragmentTransaction.commit();
            }
        });


        LinearLayout tools_linear = findViewById(R.id.tools_linear);
        tools_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Tools Fragment", Toast.LENGTH_SHORT).show();
                toolProductsFragment = new ToolProductsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView2, toolProductsFragment);
                fragmentTransaction.commit();
            }
        });

//        // Instantiate a ViewPager2 and a PagerAdapter.
//        ViewPager2 viewPager = findViewById(R.id.fragment_viewPager);
//        ProductFragmentAdapter productAdapter = new ProductFragmentAdapter(this);
//        viewPager.setAdapter(productAdapter);
    }

//    private class ProductFragmentAdapter extends FragmentStateAdapter {
//
//        public ProductFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
//            super(fragmentActivity);
//        }
//
//        @NonNull
//        @Override
//        public Fragment createFragment(int position) {
//            return new ProductsFragment();
//        }
//
//        @Override
//        public int getItemCount() {
//            return 3;
//        }
//    }
}