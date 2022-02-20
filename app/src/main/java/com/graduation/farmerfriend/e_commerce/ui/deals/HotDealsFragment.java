package com.graduation.farmerfriend.e_commerce.ui.deals;

import android.os.Bundle;


import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentHotDealsBinding;


public class HotDealsFragment extends Fragment {
    FragmentHotDealsBinding fragmentHotDealsBinding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHotDealsBinding = FragmentHotDealsBinding.inflate(inflater,container,false);
        View view = fragmentHotDealsBinding.getRoot();
        HotDealsAdapter adapter = new HotDealsAdapter();
        fragmentHotDealsBinding.fragmentHotDealsRecyclerView.setAdapter(adapter);
        fragmentHotDealsBinding.fragmentHotDealsRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        return view;


    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.ECommerceFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        // The callback can be enabled or disabled here or in handleOnBackPressed()
    }
}