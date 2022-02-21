package com.graduation.farmerfriend.control.iot_fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.graduation.farmerfriend.IOT.ui.main.IOTControlFragment;
import com.graduation.farmerfriend.IOT.ui.main.IOTSoilFragment;
import com.graduation.farmerfriend.IOT.ui.main.IOTWeatherFragment;

public class IOTAdapter extends FragmentStateAdapter {

    public IOTAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return IOTWeatherFragment.newInstance(position + 1);
            case 2:
                return IOTControlFragment.newInstance();
            default:
                return IOTSoilFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
