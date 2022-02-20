package com.graduation.farmerfriend.control;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.graduation.farmerfriend.IOT.IOT_Activity;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentControlBinding;

public class ControlFragment extends Fragment {

    FragmentControlBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentControlBinding.inflate(getLayoutInflater(),container,false);

        binding.fragmentControlButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), IOT_Activity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
    @Override
    public void onPause() {
        super.onPause();

        Toast.makeText(requireContext(),"onPause() IOTControl ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(requireContext(),"onDestroy() IOTControl ",Toast.LENGTH_LONG).show();
    }
}