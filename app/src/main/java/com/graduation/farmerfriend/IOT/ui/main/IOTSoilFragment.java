package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentIotSoilBinding;

public class IOTSoilFragment extends Fragment {


    FragmentIotSoilBinding binding;

    public static IOTSoilFragment newInstance() {
        return new IOTSoilFragment();
    }

    private IOTSoilViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotSoilBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(IOTSoilViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}