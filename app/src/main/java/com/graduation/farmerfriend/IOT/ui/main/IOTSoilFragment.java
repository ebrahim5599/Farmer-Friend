package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.databinding.FragmentIotSoilBinding;

import java.text.MessageFormat;

public class IOTSoilFragment extends Fragment {


    FragmentIotSoilBinding binding;
    private IOTViewModel mViewModel;

    public static IOTSoilFragment newInstance() {
        return new IOTSoilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotSoilBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(IOTViewModel.class);
        mViewModel.init();
        mViewModel.getIOTSensorsLiveData().observe(getViewLifecycleOwner(), new Observer<Sensors>() {
            @Override
            public void onChanged(Sensors sensors) {
                Log.d("TAG", "onChanged: "+sensors.soilTemp);
                binding.fragmentIOTSoilTextViewTemp.setText(MessageFormat.format("{0} C", sensors.soilTemp));
                binding.fragmentIOTSoilTextViewMoisture.setText(MessageFormat.format("{0} %", sensors.moisture));
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getSensorsData();
    }
}