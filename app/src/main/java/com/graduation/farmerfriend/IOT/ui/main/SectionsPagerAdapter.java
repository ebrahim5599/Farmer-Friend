package com.graduation.farmerfriend.IOT.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return IOTWeatherFragment.newInstance(position + 1);
            case 1:
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