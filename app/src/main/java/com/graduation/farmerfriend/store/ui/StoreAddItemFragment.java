package com.graduation.farmerfriend.store.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentStoreAddItemBinding;
import com.graduation.farmerfriend.store.data.StoreDatabase;
import com.graduation.farmerfriend.store.pojo.StoreItems;

import java.util.ArrayList;

public class StoreAddItemFragment extends Fragment {

    FragmentStoreAddItemBinding binding;
    private StoreDatabase myDatabase;
    private static final String TAG = "StoreAddItemFragment";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreAddItemBinding.inflate(inflater,container,false);
        myDatabase = new StoreDatabase(getContext());

        binding.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.itemNameEditText.getText().toString();
                String details = binding.itemDetailsEditText.getText().toString();
                String amount = binding.itemAmountEditText.getText().toString();

                if (name.isEmpty())
                    binding.itemNameEditText.setError("Enter Item Name!");
                else if (amount.isEmpty())
                    binding.itemAmountEditText.setError("Enter Item Amount!");
                else{
                    int no_of_items = Integer.parseInt(amount);
                    boolean result = myDatabase.insertNewItem(new StoreItems(name, details, no_of_items));
                    if (result)
                        Toast.makeText(getContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getContext(), "An error has occurred, try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Will be added soon.", Toast.LENGTH_SHORT).show();
                ArrayList<StoreItems> items = myDatabase.getAllItems();
//                ArrayList<StoreItems> items = myDatabase.getAnItem("orn");
                for (StoreItems item : items ) {
                    Log.d(TAG, "ID "+item.getItemID()+" | Name: "+ item.getItemName());
                }
                Toast.makeText(getContext(), "#"+ items.size(), Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}