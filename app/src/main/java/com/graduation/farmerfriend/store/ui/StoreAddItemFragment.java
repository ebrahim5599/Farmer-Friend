package com.graduation.farmerfriend.store.ui;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class StoreAddItemFragment extends Fragment {

    FragmentStoreAddItemBinding binding;
    private StoreDatabase myDatabase;
    private static final String TAG = "StoreAddItemFragment";
    byte[] image;

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
                else if (amount.isEmpty() || Integer.parseInt(amount) == 0)
                    binding.itemAmountEditText.setError("Enter Item Amount!");
                else{
                    int no_of_items = Integer.parseInt(amount);
                    boolean result = myDatabase.insertNewItem(new StoreItems(name, details, no_of_items, image));
                    if (result) {
                        Toast.makeText(getContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
                        requireActivity().onBackPressed();
                    }else
                        Toast.makeText(getContext(), "An error has occurred, try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 99);

//                ArrayList<StoreItems> items = myDatabase.getAllItems();
//                for (StoreItems item : items ) {
//                    Log.d(TAG, "ID "+item.getItemID()+" | Name: "+ item.getNumberOfItems());
//                }
//                Toast.makeText(getContext(), "#"+ items.size(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.removeUploadedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = null;
                binding.uploadedImageRelativeLayout.setVisibility(View.GONE);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 99 && resultCode == RESULT_OK){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                binding.uploadedImageRelativeLayout.setVisibility(View.VISIBLE);
                binding.uploadedImage.setImageBitmap(bitmap);
                image = getBytes(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}