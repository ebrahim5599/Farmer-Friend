package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.IOTModels.Control;
import com.graduation.farmerfriend.IOTModels.IOTRoot;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentIotControlBinding;
import com.graduation.farmerfriend.repos.IOTRepo;

public class IOTControlFragment extends Fragment {

    private IOTViewModel mViewModel;
    FragmentIotControlBinding binding;
    String buttonText;
    boolean isWaterPumpON = false;
    boolean isFertilizerPumpON = false;
    private boolean isAuto;
    private boolean waterSwitch;
    private boolean ferSwitch;
    private IOTRoot iotModel;
    IOTRepo iotRepo;

    public static IOTControlFragment newInstance() {
        return new IOTControlFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotControlBinding.inflate(inflater, container, false);
        iotRepo = IOTRepo.getInstance();
        mViewModel = new ViewModelProvider(requireActivity()).get(IOTViewModel.class);
        mViewModel.init();
        mViewModel.getIOTControlLiveData().observe(getViewLifecycleOwner(), new Observer<Control>() {
            @Override
            public void onChanged(Control control) {

                putIOT(control);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void putIOT(Control control) {
        Log.d("TAG", "putIOT: "+control.waterSwitch);

        if (control.isAuto) {
            binding.fregmentIotControlButtonManualOrAutomatic.setText(R.string.manual);
            binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.WHITE);
            binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);
            binding.fregmentIotControlImageviewFertilizerpumpPower.setEnabled(false);
            binding.fregmentIotControlImageviewWaterpumpPower.setEnabled(false);
        } else {
            binding.fregmentIotControlButtonManualOrAutomatic.setText(R.string.automatic);
            binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.RED);
            binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.WHITE);
            binding.fregmentIotControlImageviewFertilizerpumpPower.setEnabled(true);
            binding.fregmentIotControlImageviewWaterpumpPower.setEnabled(true);
        }


        binding.fregmentIotControlButtonManualOrAutomatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonText = (String) binding.fregmentIotControlButtonManualOrAutomatic.getText();

                if (buttonText.equals(getString(R.string.automatic))) {
                    iotRepo.WriteDataAuto(true);
                    binding.fregmentIotControlButtonManualOrAutomatic.setText(R.string.manual);
                    binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);

                    binding.fregmentIotControlImageviewFertilizerpumpPower.setEnabled(false);
                    binding.fregmentIotControlImageviewWaterpumpPower.setEnabled(false);
                } else {
                    iotRepo.WriteDataAuto(false);
                    binding.fregmentIotControlButtonManualOrAutomatic.setText(R.string.automatic);
                    binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.WHITE);

                    binding.fregmentIotControlImageviewFertilizerpumpPower.setEnabled(true);
                    binding.fregmentIotControlImageviewWaterpumpPower.setEnabled(true);
                }
            }
        });


        if (control.fertSwitch) {
            isFertilizerPumpON = true;
            binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.RED);
            binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.CYAN);
            binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);
        } else {
            binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.WHITE);
            binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.rgb(76, 85, 100));
            binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
        }


        binding.fregmentIotControlImageviewFertilizerpumpPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFertilizerPumpON) {
                    iotRepo.WriteDataFertilizer(false);
                    binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.rgb(76, 85, 100));
                    binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
                    isFertilizerPumpON = false;
                } else {
                    iotRepo.WriteDataFertilizer(true);
                    binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.CYAN);
                    binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);
                    isFertilizerPumpON = true;
                }
            }
        });

        if (control.waterSwitch) {
            isWaterPumpON = true;
            binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.RED);
            binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.CYAN);
            binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);

        } else {
            binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.WHITE);
            binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.rgb(76, 85, 100));
            binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
        }


        binding.fregmentIotControlImageviewWaterpumpPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isWaterPumpON) {
                    iotRepo.WriteDataWater(false);
                    binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.rgb(76, 85, 100));
                    binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
                    isWaterPumpON = false;
                } else {
                    iotRepo.WriteDataWater(true);
                    binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.CYAN);
                    binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);
                    isWaterPumpON = true;
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getControlData();
    }
}