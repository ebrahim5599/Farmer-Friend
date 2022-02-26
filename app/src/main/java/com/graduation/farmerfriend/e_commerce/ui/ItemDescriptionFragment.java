package com.graduation.farmerfriend.e_commerce.ui;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentItemDescriptionBinding;


public class ItemDescriptionFragment extends Fragment {
    FragmentItemDescriptionBinding binding;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        // This callback will only be called when MyFragment is at least Started.
//        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
//            @Override
//            public void handleOnBackPressed() {
//                Navigation.findNavController(requireView()).navigate(R.id.ECommerceFragment);
//            }
//        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
//
//        // The callback can be enabled or disabled here or in handleOnBackPressed()
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentItemDescriptionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.itemDescriptionTextviewDescription.setText("corn is a heat_loving with varieties for eating fresh or drying to make popcorn" +
                "or maize. Botantically, sweet corn is actually an impressively tall grass , and the kernels are " +
                "mature seeds. Plant sweet corn in blocks to achieve even pollination.");
    }
}