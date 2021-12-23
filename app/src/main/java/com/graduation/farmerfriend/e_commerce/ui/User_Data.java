package com.graduation.farmerfriend.e_commerce.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.graduation.farmerfriend.databinding.ActivityCartBinding;
import com.graduation.farmerfriend.databinding.ActivityUserData10Binding;
import com.graduation.farmerfriend.databinding.ActivityUserDataBinding;
import com.graduation.farmerfriend.location.Location;

import java.util.ArrayList;
import java.util.List;

public class User_Data extends AppCompatActivity {
    ActivityUserData10Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserData10Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<String> gov = new ArrayList<String>();
        gov.add("Sharqia");
        gov.add("Cairo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gov);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.UserDataSpinnerGovernorate.setAdapter(adapter);


    }


}