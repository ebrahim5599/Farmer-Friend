package com.graduation.farmerfriend;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.farmerfriend.databinding.FragmentStoreBinding;
import com.graduation.farmerfriend.store.StoreItems;
import com.graduation.farmerfriend.store.StoreItemsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentStoreBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(inflater,container,false);
        //get the drawable
        @SuppressLint("UseCompatLoadingForDrawables")
        Drawable myFabSrc = getResources().getDrawable(R.drawable.ic_add);
        //copy it in a new one
        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        //set the color filter, you can use also Mode.SRC_ATOP
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to your fab button initialized before
        binding.fab.setImageDrawable(willBeWhite);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.storeAddItemFragment);
            }
        });


        ArrayList<StoreItems> storeArrayList = new ArrayList<>();
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 20, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.will_smith));
        storeArrayList.add(new StoreItems("Mint", "details for mint", 5, R.drawable.will_smith));


        binding.activityStoreRecycleViewStore.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.activityStoreRecycleViewStore.setHasFixedSize(true);
        StoreItemsAdapter storeItemsAdapter = new StoreItemsAdapter(requireContext(), storeArrayList);
        binding.activityStoreRecycleViewStore.setAdapter(storeItemsAdapter);
        return binding.getRoot();
    }
}