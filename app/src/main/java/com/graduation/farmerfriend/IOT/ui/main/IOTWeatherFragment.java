package com.graduation.farmerfriend.IOT.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.graduation.farmerfriend.databinding.FragmentIotWeatherBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class IOTWeatherFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private com.graduation.farmerfriend.IOT.ui.main.IOTWeatherViewModel IOTWeatherViewModel;
    private FragmentIotWeatherBinding binding;

    public static IOTWeatherFragment newInstance(int index) {
        IOTWeatherFragment fragment = new IOTWeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IOTWeatherViewModel = new ViewModelProvider(this).get(com.graduation.farmerfriend.IOT.ui.main.IOTWeatherViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        IOTWeatherViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentIotWeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.sectionLabel;
        IOTWeatherViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}