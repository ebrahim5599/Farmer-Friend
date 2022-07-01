package com.graduation.farmerfriend.IOT.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.IOTModels.Sensors;
import com.graduation.farmerfriend.databinding.FragmentIotWeatherBinding;

import java.text.MessageFormat;

/**
 * A placeholder fragment containing a simple view.
 */
public class IOTWeatherFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private com.graduation.farmerfriend.IOT.ui.main.IOTViewModel IOTViewModel;
    private FragmentIotWeatherBinding binding;
    private IOTViewModel mViewModel;

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
        IOTViewModel = new ViewModelProvider(this).get(com.graduation.farmerfriend.IOT.ui.main.IOTViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentIotWeatherBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ProgressBar progressBar = binding.fragmentWeatherProgressBar;
        TextView textViewTemp = binding.fragmentIotWeatherTextViewTemp;
        mViewModel = new ViewModelProvider(requireActivity()).get(IOTViewModel.class);
        mViewModel.init(requireContext());
        mViewModel.getIOTSensorsLiveData().observe(getViewLifecycleOwner(), new Observer<Sensors>() {
            @Override
            public void onChanged(Sensors sensors) {
//                Log.d("TAG", "onChanged: "+sensors.soilTemp);
                    progressBar.setProgress(sensors.airTemp);
                    binding.fragmentIotWeatherTextViewHumidity.setText(MessageFormat.format("{0} %", sensors.humidity));
                    binding.fragmentIotWeatherTextViewTemp.setText(MessageFormat.format("{0} C", sensors.airTemp));
                    binding.fragmentIotWeatherTextViewLuminousIntensity.setText(MessageFormat.format("{0} lx", sensors.luminous));
                    binding.fragmentIotWeatherTextViewPressure.setText(MessageFormat.format("{0} mb", sensors.pressure));
                }

        });

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            int i = 0;
//            @Override
//            public void run() {
//                // set the limitations for the numeric
//                // text under the progress bar
//                if (i <= 100) {
//                    textViewTemp.setText("" + i);
//                    progressBar.setProgress((int) Math.ceil(i*.77));
//                    i++;
//                    handler.postDelayed(this, 200);
//                } else {
//                    handler.removeCallbacks(this);
//                }
//            }
//        }, 200);

//        final TextView textView = binding.sectionLabel;
//        IOTWeatherViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                textView.setText(s);
////            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}