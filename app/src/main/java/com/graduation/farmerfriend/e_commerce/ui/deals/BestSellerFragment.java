package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.databinding.FragmentBestSelersBinding;

public class BestSellerFragment extends Fragment {
    FragmentBestSelersBinding fragmentBestSelersBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentBestSelersBinding = FragmentBestSelersBinding.inflate(inflater,container,false);
        View view = fragmentBestSelersBinding.getRoot();
        BestSellerAdapter adapter = new BestSellerAdapter();
        fragmentBestSelersBinding.bestSellersRV.setAdapter(adapter);
        fragmentBestSelersBinding.bestSellersRV.setLayoutManager(new GridLayoutManager(requireContext(),3));

        Log.i("Fragment","Best sellers oncreateView");
        return view;
    }
}