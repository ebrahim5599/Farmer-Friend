package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentBestSelersBinding;

public class BestSellerFragment extends Fragment {
    FragmentBestSelersBinding fragmentBestSelersBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentBestSelersBinding = FragmentBestSelersBinding.inflate(inflater,container,false);
        View view = fragmentBestSelersBinding.getRoot();
        BestSellerAdapter adapter = new BestSellerAdapter();
        fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setAdapter(adapter);
        fragmentBestSelersBinding.fragmentBestSellersRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));

        Log.i("Fragment","Best sellers oncreateView");
        return view;
    }

}