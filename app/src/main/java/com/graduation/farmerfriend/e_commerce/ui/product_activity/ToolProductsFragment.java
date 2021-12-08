package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentToolProductsBinding;

public class ToolProductsFragment extends Fragment {

    FragmentToolProductsBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentToolProductsBinding.inflate(inflater, container, false);

        ProductItemAdapter adapter = new ProductItemAdapter();
        binding.fragmentToolProductsRecyclerView.setAdapter(adapter);
        binding.fragmentToolProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));

        return binding.getRoot();
    }
}