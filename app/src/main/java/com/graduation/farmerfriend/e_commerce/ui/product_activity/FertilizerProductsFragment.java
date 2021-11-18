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

public class FertilizerProductsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fertilizer_products, container, false);

        RecyclerView fertilizers_recycler = view.findViewById(R.id.fertilizer_recycler_view);

        ProductItemAdapter adapter = new ProductItemAdapter();
        fertilizers_recycler.setAdapter(adapter);
        fertilizers_recycler.setLayoutManager(new GridLayoutManager(requireContext(),3));
        Log.i("Fragment","fertilizers fragment oncreateview");

        return view;
    }
}