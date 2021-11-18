package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentItemDescriptionBinding;

public class Item_description extends Fragment {

    FragmentItemDescriptionBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentItemDescriptionBinding.inflate(inflater, container, false);


        binding.itemDescriptionTextviewDescription.setText("corn is a heat_loving with varieties for eating fresh or drying to make popcorn" +
                "or maize. Botantically, sweet corn is actually an impressively tall grass , and the kernels are " +
                "mature seeds. Plant sweet corn in blocks to achieve even pollination.");
        return binding.getRoot() ;
    }

}