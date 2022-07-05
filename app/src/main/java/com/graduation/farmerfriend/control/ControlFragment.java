package com.graduation.farmerfriend.control;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.control.iot_fragments.IOTAdapter;
import com.graduation.farmerfriend.databinding.FragmentControlBinding;
import com.graduation.farmerfriend.databinding.FragmentRegistrationBinding;

public class ControlFragment extends Fragment {

    FragmentControlBinding binding;
    FragmentRegistrationBinding fragmentRegistrationBinding;

//    FragmentIotIntroBinding b;
//    FragmentIotWaitingCodeBinding c;

    ViewPager2 viewPager2;
    boolean isRegistered = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if(isRegistered){
//            b = FragmentIotIntroBinding.inflate(getLayoutInflater(), container, false);
//
//            return b.getRoot();
//        }else{
//            c = FragmentIotWaitingCodeBinding.inflate(getLayoutInflater(), container, false);
//
//            return c.getRoot();
//        }

        binding = FragmentControlBinding.inflate(inflater, container, false);
        //TODO:**************************************************************************************************
//        RegistrationFragment registrationFragment = new RegistrationFragment();
//        if (isRegistered) {
//            binding.linearVisibility.setVisibility(View.GONE);
//            if (savedInstanceState == null) {
//                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.registration_fragment_container, registrationFragment);
//                fragmentTransaction.commit();
//            }
//        } else {
//            binding.registrationFragmentContainer.setVisibility(View.GONE);
//        }
        binding.registrationFragmentContainer.setVisibility(View.GONE);

        //TODO:**************************************************************************************************
//        binding.fragmentControlButtonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(requireContext(), IOT_Activity.class);
//                startActivity(intent);
//            }
//        });

//        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                // Handle the back button event
//                Navigation.findNavController(requireView()).navigate(R.id.controlContainerFragment);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


        IOTAdapter iotAdapter = new IOTAdapter(this);
        viewPager2 = binding.viewPager2;
        viewPager2.setAdapter(iotAdapter);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new TabLayoutMediator(binding.tabs, viewPager2,
                (tab, position) -> tab.setText(setTabTitle(position))
        ).attach();
        binding.tabs.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#00796B"));
        binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#00796B"));

    }


    public String setTabTitle(int pageNumber) {
        switch (pageNumber) {
            case 0:
                return getString(R.string.weather);
            case 1:
                return getString(R.string.soil);
            default:
                return getString(R.string.control);
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("testFragmentLifeCycle", "ControlFragmentOnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("testFragmentLifeCycle", "ControlFragmentOnStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("testFragmentLifeCycle", "ControlFragmentOnDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("testFragmentLifeCycle", "ControlFragmentOnDestroy()");
    }
}