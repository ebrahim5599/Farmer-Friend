package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentSeedProductsBinding;

public class SeedProductsFragment extends Fragment {

    FragmentSeedProductsBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSeedProductsBinding.inflate(inflater, container, false);

        ProductItemAdapter adapter = new ProductItemAdapter();
        binding.fragmentSeedsProductsRecyclerView.setAdapter(adapter);
        binding.fragmentSeedsProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        Log.i("Fragment","seeds fragment oncreateview");

        return binding.getRoot();
    }
}