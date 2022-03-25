package com.graduation.farmerfriend.control;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.control.iot_fragments.IotIntroFragment;
import com.graduation.farmerfriend.databinding.FragmentRegistrationBinding;

public class RegistrationFragment extends Fragment {

    private FragmentRegistrationBinding viewBinding;
    private IotIntroFragment iotIntroFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewBinding = FragmentRegistrationBinding.inflate(inflater, container, false);

        iotIntroFragment = new IotIntroFragment();

//        viewBinding.registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        viewBinding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null) {
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.registration_fragment_container, iotIntroFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        return viewBinding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("testFragmentLifeCycle", "RegistrationFragmentOnPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("testFragmentLifeCycle", "RegistrationFragmentOnStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("testFragmentLifeCycle", "RegistrationFragmentOnDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("testFragmentLifeCycle", "RegistrationFragmentOnDestroy()");
    }
}