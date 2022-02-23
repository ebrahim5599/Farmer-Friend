package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.R;
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
        mViewModel.getIOTModelLiveData().observe(getViewLifecycleOwner(), new Observer<IOTRoot>() {
            @Override
            public void onChanged(IOTRoot iotRoot) {
                binding.fragmentIOTSoilTextViewTemp.setText(MessageFormat.format("{0} C", iotRoot.sensors.stemp));
                binding.fragmentIOTSoilTextViewMoisture.setText(MessageFormat.format("{0} %", iotRoot.sensors.smoisture));
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
        mViewModel.getIOTData();
    }
}