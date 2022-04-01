package com.graduation.farmerfriend.e_commerce.ui;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.databinding.FragmentItemDescriptionBinding;
import com.graduation.farmerfriend.ecommerce_models.Product;


public class ItemDescriptionFragment extends Fragment {
    FragmentItemDescriptionBinding binding;
    private ItemDescViewModel viewModel;
    private static final String TAG = "ItemDescriptionFragment";

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
        viewModel = new ViewModelProvider(this).get(ItemDescViewModel.class);
        viewModel.init();
        viewModel.getProductById(ItemDescriptionFragmentArgs.fromBundle(getArguments()).getId());
        Log.d(TAG, "onCreateView: " + ItemDescriptionFragmentArgs.fromBundle(getArguments()).getId());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getProductLiveData().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                binding.itemDescriptionTextviewDescription.setText(String.valueOf(product.description));
                binding.itemDescriptionTextviewName.setText(String.valueOf(product.productName));
                binding.itemDescriptionTextviewPrice.setText(String.valueOf(product.price));
                if (product.productImage != null) {
                    String[] imageName = product.productImage.split("s/");
                    Glide.with(ItemDescriptionFragment.this).load("http://teamweb2022-001-site1.itempurl.com/productImages/" + imageName[1]).into(binding.itemDescriptionImageviewProduct);
                }
            }
        });
    }
}