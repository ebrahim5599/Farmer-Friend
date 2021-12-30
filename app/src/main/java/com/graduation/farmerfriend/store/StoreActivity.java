package com.graduation.farmerfriend.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityStoreBinding;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    ActivityStoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //get the drawable
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable myFabSrc = getResources().getDrawable(R.drawable.ic_add);
        //copy it in a new one
        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        //set the color filter, you can use also Mode.SRC_ATOP
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to your fab button initialized before
        binding.fab.setImageDrawable(willBeWhite);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreActivity.this, AddToStoreActivity.class));
            }
        });


        ArrayList<StoreItems> storeArrayList = new ArrayList<>();
        storeArrayList.add(new StoreItems("Mint", "mint gamed gedan", 5, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "mint gamed gedan", 20, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "mint gamed gedan", 5, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "mint gamed gedan", 5, R.drawable.will_smith));


        binding.activityStoreRecycleViewStore.setLayoutManager(new LinearLayoutManager(this));
        binding.activityStoreRecycleViewStore.setHasFixedSize(true);
        StoreItemsAdapter storeItemsAdapter = new StoreItemsAdapter(this, storeArrayList);
        binding.activityStoreRecycleViewStore.setAdapter(storeItemsAdapter);

    }
}