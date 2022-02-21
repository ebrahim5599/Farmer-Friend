package com.graduation.farmerfriend.e_commerce.ui.cart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.databinding.FragmentUserDataBinding;
import com.graduation.farmerfriend.location.AddressCallBack;
import com.graduation.farmerfriend.location.Location;

public class UserDataFragment extends Fragment implements AddressCallBack {

    FragmentUserDataBinding binding;
    private Location location;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
////        location.getLocation();
//
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentUserDataBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.UserDataButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = new Location(requireActivity(), 1001, UserDataFragment.this);
                location.getLocation();
                binding.UserDataActivityTextViewLocationAddress.setVisibility(View.VISIBLE);
            }
        });
    }
    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == location.getLOCATION_REQUEST_CODE()) {
//            // If request is cancelled, the result arrays are empty.
//            location.getLocation();
//        }
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == location.getLOCATION_REQUEST_CODE()) {
//            if (requestCode == location.getLOCATION_REQUEST_CODE()) {
//                // If request is cancelled, the result arrays are empty.
//                location.getLocation();
//            }
//
//        }
//    }
    @Override
    public void getAddress(String address) {
        binding.UserDataActivityTextViewLocationAddress.setText(address);
        binding.UserDataEdittextCity.setText(location.getCity());
        binding.UserDataEdittextGovernorate.setText(location.getGovernorate());
        location.destroy();
    }
}