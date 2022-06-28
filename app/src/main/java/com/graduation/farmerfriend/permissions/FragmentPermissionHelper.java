package com.graduation.farmerfriend.permissions;

import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;



public class FragmentPermissionHelper {

    public void startPermissionRequest(@NonNull Fragment fragmentActivity, String requestPermissionType, FragmentPermissionInterface fragmentPermissionInterface) {
        ActivityResultLauncher<String> requestPermissionLauncher = fragmentActivity.registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    fragmentPermissionInterface.onGranted(isGranted);
                });
        requestPermissionLauncher.launch(requestPermissionType);
    }
}
