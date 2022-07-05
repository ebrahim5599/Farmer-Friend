package com.graduation.farmerfriend.e_commerce.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.constants.Constants;
import com.graduation.farmerfriend.databinding.FragmentItemDescriptionBinding;
import com.graduation.farmerfriend.ecommerce_models.PostCart;
import com.graduation.farmerfriend.ecommerce_models.Product;
import com.graduation.farmerfriend.sharedPreferences.SharedPref;


public class ItemDescriptionFragment extends Fragment {
    FragmentItemDescriptionBinding binding;
    ItemDescViewModel viewModel;
    private static final String TAG = "ItemDescriptionFragment";
    SharedPref sharedPref;
    private Boolean internet ;

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
        sharedPref = new SharedPref(requireContext(),Constants.MAIN_SHARED_PREFERENCES);
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
                    Glide.with(ItemDescriptionFragment.this).load("http://teamweb992022-001-site1.htempurl.com/productImages/" + imageName[1]).into((ImageView) binding.itemDescriptionImageviewProduct);
                }

                if (product.description == null){
                    binding.itemDescriptionTextviewDescription.setText(R.string.no_description_for_this_product);
                }

                onclick(product);
            }
        });

        if(getConnectivityStatus(getContext())){

//            binding.description.setVisibility(View.VISIBLE);
            binding.itemDescriptionButtonAddToCart.setVisibility(View.VISIBLE);
            binding.mainActivityNoInternetConnection.setVisibility(View.GONE);
        }else {
//            binding.description.setVisibility(View.GONE);
            binding.itemDescriptionButtonAddToCart.setVisibility(View.GONE);
            binding.mainActivityNoInternetConnection.setVisibility(View.VISIBLE);
        }

    }












    private void onclick(Product product) {
        binding.itemDescriptionButtonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sharedPref.getStringPref(Constants.USER_ID, "").isEmpty() && sharedPref.getStringPref(Constants.USER_ID, "") != null) {
                    PostCart postCart = new PostCart(product.productId,sharedPref.getStringPref(Constants.USER_ID,""),1);
                    viewModel.addToCart(postCart);
                    Toast.makeText(requireContext(),"تمت اضافة "+ product.productName +" لشنطة التسوق",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "يرجى تسجيل الدخول حتى تستطيع الاضافة الى العربة ",Toast.LENGTH_SHORT).show();
                    //TODO navigate to sign in fragment
                }
            }
        });
    }
    public Boolean getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                internet = true ;
                return internet;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                internet = true ;
                return internet;
            }
        } else {
            internet = false ;
            return internet;
        }
        return internet;
    }
}