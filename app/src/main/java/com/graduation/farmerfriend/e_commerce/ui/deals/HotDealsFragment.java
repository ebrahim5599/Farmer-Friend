package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;

import com.graduation.farmerfriend.databinding.FragmentBestSelersBinding;
import com.graduation.farmerfriend.databinding.FragmentHotDealsBinding;

public class HotDealsFragment extends Fragment {
    FragmentHotDealsBinding fragmentHotDealsBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHotDealsBinding = FragmentHotDealsBinding.inflate(inflater,container,false);
        View view = fragmentHotDealsBinding.getRoot();
        BestSellerAdapter adapter = new BestSellerAdapter();
        fragmentHotDealsBinding.hotDealsRecyclerView.setAdapter(adapter);
        fragmentHotDealsBinding.hotDealsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        return view;

    }
}