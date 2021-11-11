package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.Search_Item;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;
import com.graduation.farmerfriend.e_commerce.ViewRecycleSearchAdapter;

public class Search extends Fragment {

    RecyclerView recyclerView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recycle_search);

        Search_Item [] search ={
                new Search_Item(R.drawable.corn,"Corn","100$"),
                new Search_Item(R.drawable.corn,"Corn","100$"),
                new Search_Item(R.drawable.corn,"Corn","100$"),
                new Search_Item(R.drawable.corn,"Corn","100$")
        };


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewRecycleSearchAdapter recycleViewAdapter = new ViewRecycleSearchAdapter(getContext(),search);
        recyclerView.setAdapter(recycleViewAdapter);
        return view ;
    }
}