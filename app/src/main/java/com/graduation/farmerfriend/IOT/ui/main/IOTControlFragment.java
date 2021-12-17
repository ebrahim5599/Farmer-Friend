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
import com.graduation.farmerfriend.databinding.FragmentIotControlBinding;

public class IOTControlFragment extends Fragment {

    private IOTControlViewModel mViewModel;
    FragmentIotControlBinding binding;

    public static IOTControlFragment newInstance() {
        return new IOTControlFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotControlBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(IOTControlViewModel.class);
        return binding.getRoot();
    }


}