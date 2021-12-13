package com.graduation.farmerfriend.e_commerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.ActivityItemDescriptionBinding;

public class ItemDescriptionActivity extends AppCompatActivity {
    ActivityItemDescriptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityItemDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.itemDescriptionTextviewDescription.setText("corn is a heat_loving with varieties for eating fresh or drying to make popcorn" +
                "or maize. Botantically, sweet corn is actually an impressively tall grass , and the kernels are " +
                "mature seeds. Plant sweet corn in blocks to achieve even pollination.");
    }
}