package com.graduation.farmerfriend.e_commerce.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.graduation.farmerfriend.databinding.ActivityCartBinding;
import com.graduation.farmerfriend.databinding.ActivityUserDataBinding;

import java.util.ArrayList;
import java.util.List;

public class User_Data extends AppCompatActivity {
    ActivityUserDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> gov = new ArrayList<String>();
        gov.add("Sharqia");
        gov.add("Cairo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gov);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.UserDataSpinnerGovernorate.setAdapter(adapter);

    }
}