package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduation.farmerfriend.R;

public class Item_description extends Fragment {

    TextView description ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_description, container, false);

        description = view.findViewById(R.id.description);
        description.setText("corn is a heat_loving with varieties for eating fresh or drying to make popcorn" +
                "or maize. Botantically, sweet corn is actually an impressively tall grass , and the kernels are " +
                "mature seeds. Plant sweet corn in blocks to achieve even pollination.");
        return view ;
    }

}