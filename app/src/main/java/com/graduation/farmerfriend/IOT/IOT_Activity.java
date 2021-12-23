package com.graduation.farmerfriend.IOT;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;
import android.widget.Toast;

import com.graduation.farmerfriend.IOT.ui.main.SectionsPagerAdapter;
import com.graduation.farmerfriend.databinding.ActivityIotBinding;

public class IOT_Activity extends AppCompatActivity {

    private ActivityIotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        ViewPager2 viewPager = binding.activityIotViewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        binding.activityIotTextviewPageTitle.setText(setPageTitle(viewPager.getCurrentItem()));
        binding.activityIotButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 2) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
        binding.activityIotButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageScrolled(int position,
                                       float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                binding.activityIotTextviewPageTitle.setText(setPageTitle(position));

            }
        });
    }

    public String setPageTitle(int pageNumber) {
        switch (pageNumber) {
            case 0:
                return "Weather Sensors";
            case 1:
                return "Soil Sensors";
            default:
                return "Control";
        }
    }
}