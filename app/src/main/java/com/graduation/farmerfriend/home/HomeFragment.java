package com.graduation.farmerfriend.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.graduation.farmerfriend.databinding.FragmentHomeBinding;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HomeFragment#newInstance} factory method to
// * create an instance of this fragment.
// *
// */
public class HomeFragment extends Fragment {

    FragmentHomeBinding fragmentHomeBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = fragmentHomeBinding.getRoot();
        EcommerceAdapter ecommerceAdapter = new EcommerceAdapter();
        fragmentHomeBinding.recyclerViewEcommerce.setAdapter(ecommerceAdapter);

        NewsAdapter newsAdapter = new NewsAdapter();
        fragmentHomeBinding.recyclerViewNews.setAdapter(newsAdapter);
        return view;
    }
}