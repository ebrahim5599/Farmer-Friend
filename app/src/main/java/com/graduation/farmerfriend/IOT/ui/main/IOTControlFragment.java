package com.graduation.farmerfriend.IOT.ui.main;

import androidx.lifecycle.ViewModelProvider;
import android.graphics.Color;
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
    String buttonText;
    int countClickWaterPump = 0;
    int countClickFertilizerPump = 0;

    public static IOTControlFragment newInstance() {
        return new IOTControlFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIotControlBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(IOTControlViewModel.class);

        binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);
        binding.fregmentIotControlButtonManualOrAutomatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonText = (String) binding.fregmentIotControlButtonManualOrAutomatic.getText() ;
                if(buttonText.equals("AUTOMATIC")){
                    binding.fregmentIotControlButtonManualOrAutomatic.setText("MANUAL");
                    binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.RED);
                }else{
                    binding.fregmentIotControlButtonManualOrAutomatic.setText("AUTOMATIC");
                    binding.fregmentIotControlLambManual.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlLambAutomatic.setCardBackgroundColor(Color.WHITE);
                }
            }
        });

        binding.fregmentIotControlImageviewWaterpumpPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countClickWaterPump++;
                if(countClickWaterPump == 1) {
                    binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.CYAN);
                    binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);
                    countClickWaterPump = -1 ;
                }else {
                    binding.fregmentIotControlLambWaterpump.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlCardviewWaterpumpPower.setCardBackgroundColor(Color.rgb(76,85,100));
                    binding.fregmentIotControlImageviewWaterpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
                }
            }
        });

        binding.fregmentIotControlImageviewFertilizerpumpPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countClickFertilizerPump++;
                if (countClickFertilizerPump == 1) {
                    binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.RED);
                    binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.CYAN);
                    binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_on);
                    countClickFertilizerPump = -1;
                }else{
                    binding.fregmentIotControlLambFertilizerpump.setCardBackgroundColor(Color.WHITE);
                    binding.fregmentIotControlCardviewFertilizerpumpPower.setCardBackgroundColor(Color.rgb(76,85,100));
                    binding.fregmentIotControlImageviewFertilizerpumpPower.setImageResource(R.drawable.fregment_iot_control_power_off);
                }
            }
        });
        return binding.getRoot();
    }
}