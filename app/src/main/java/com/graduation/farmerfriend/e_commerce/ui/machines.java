package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.Data;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;

public class machines extends Fragment {

    RecyclerView recyclerView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machines, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_machines);

        Data[] data = {
                new Data(R.drawable.image1,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image2,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image3,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image4,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image5,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image6,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image7,"jdbvhdv","100$","0%"),
                new Data(R.drawable.image8,"jdbvhdv","100$","0%")

        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ViewRecycleProductsAdapter recycleViewAdapter = new ViewRecycleProductsAdapter(getContext(),data);
        recyclerView.setAdapter(recycleViewAdapter);

        return view ;

    }
}