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
import com.graduation.farmerfriend.databinding.FragmentFertilizerProductsBinding;

public class FertilizerProductsFragment extends Fragment {

    FragmentFertilizerProductsBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFertilizerProductsBinding.inflate(inflater, container, false);

        ProductItemAdapter adapter = new ProductItemAdapter();
        binding.fragmentFertilizerProductsRecyclerView.setAdapter(adapter);
        binding.fragmentFertilizerProductsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        Log.i("Fragment","fertilizers fragment oncreateview");

        return binding.getRoot();
    }
}