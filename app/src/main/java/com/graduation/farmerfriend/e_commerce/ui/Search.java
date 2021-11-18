package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentSearchBinding;
import com.graduation.farmerfriend.e_commerce.Search_Item;
import com.graduation.farmerfriend.e_commerce.ViewRecycleSearchAdapter;

public class Search extends Fragment {

    FragmentSearchBinding binding;

    RecyclerView recyclerView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        Search_Item [] search ={
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating"),
                new Search_Item(R.drawable.corn,"Corn","100$","corn is a heat_loving with varieties for eating")
        };


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.searchRecycleviewSearchItem.setLayoutManager(linearLayoutManager);
        ViewRecycleSearchAdapter recycleViewAdapter = new ViewRecycleSearchAdapter(getContext(),search);
        binding.searchRecycleviewSearchItem.setAdapter(recycleViewAdapter);
        return binding.getRoot() ;
    }
}