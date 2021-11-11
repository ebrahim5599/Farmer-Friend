package com.graduation.farmerfriend.e_commerce.ui.product_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;

public class ToolProductsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tool_products, container, false);

        RecyclerView tools_recycler = view.findViewById(R.id.tools_recycler_view);

        ProductItemAdapter adapter = new ProductItemAdapter();
        tools_recycler.setAdapter(adapter);
        tools_recycler.setLayoutManager(new GridLayoutManager(requireContext(),3));

        return view;
    }
}