package com.graduation.farmerfriend.control;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.IOT.IOT_Activity;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentControlBinding;

public class ControlFragment extends Fragment {

    FragmentControlBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentControlBinding.inflate(getLayoutInflater(),container,false);
        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), IOT_Activity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}