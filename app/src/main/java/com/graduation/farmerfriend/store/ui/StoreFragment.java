package com.graduation.farmerfriend.store.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentStoreBinding;
import com.graduation.farmerfriend.store.data.StoreDatabase;
import com.graduation.farmerfriend.store.pojo.StoreItems;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    private FragmentStoreBinding binding;
    private StoreDatabase myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater,container,false);
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
                Navigation.findNavController(view).navigate(R.id.storeAddItemFragment);
            }
        });

//        myDatabase = new StoreDatabase(getContext());
        ArrayList<StoreItems> storeArrayList = new ArrayList<>();

//        ArrayList<StoreItems> items = myDatabase.getAllItems();
//        for (StoreItems item : items ) {
//            items.add(new StoreItems(item.getItemName(), item.getItemDetails(), item.getNumberOfItems(), R.drawable.no_product));
//        }
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.no_product));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 20, R.drawable.no_product));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.no_product));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.no_product));


        binding.activityStoreRecycleViewStore.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.activityStoreRecycleViewStore.setHasFixedSize(true);
        StoreItemsAdapter storeItemsAdapter = new StoreItemsAdapter(requireContext(), storeArrayList);
        binding.activityStoreRecycleViewStore.setAdapter(storeItemsAdapter);

        return binding.getRoot();
    }
}