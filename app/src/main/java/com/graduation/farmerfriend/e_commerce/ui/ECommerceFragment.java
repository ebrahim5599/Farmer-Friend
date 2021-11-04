package com.graduation.farmerfriend.e_commerce.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.graduation.farmerfriend.R;
import com.graduation.farmerfriend.e_commerce.Categorie;
import com.graduation.farmerfriend.e_commerce.Data;
import com.graduation.farmerfriend.e_commerce.ViewRecycleCategoriesAdapter;
import com.graduation.farmerfriend.e_commerce.ViewRecycleProductsAdapter;


public class ECommerceFragment extends Fragment {

    RecyclerView recycler_seeds,recycler_tools ,recycler_machines ,recycler_fertilizers , recycler_categorie ;
    ImageView cart ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e_commerce, container, false);

        cart = view.findViewById(R.id.Shopping_Car);
        recycler_categorie = view.findViewById(R.id.categorie_recycle);
        recycler_seeds = view.findViewById(R.id.recycleview_seeds);
        recycler_fertilizers = view.findViewById(R.id.recycleview_fertilizers);
        recycler_tools = view.findViewById(R.id.recycleview_tools);
        recycler_machines = view.findViewById(R.id.recycleview_machines);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView,new Cart()).addToBackStack(null).commit();
            }
        });

        Categorie[] categorie ={
                new Categorie("Seeds"),
                new Categorie("Fertilizers"),
                new Categorie("Tools"),
                new Categorie("Machine Shops")
        };

        LinearLayoutManager linearc = new LinearLayoutManager(getContext());
        recycler_categorie.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleCategoriesAdapter recycleViewAdapterc = new ViewRecycleCategoriesAdapter(getContext(),categorie);
        recycler_categorie.setAdapter(recycleViewAdapterc);


        Data[] data_seeds = {
            new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        LinearLayoutManager linears = new LinearLayoutManager(getContext());
        recycler_seeds.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapters = new ViewRecycleProductsAdapter(getContext(), data_seeds);
        recycler_seeds.setAdapter(recycleViewAdapters);

        Data[] data_fertilize = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        LinearLayoutManager linearf = new LinearLayoutManager(getContext());
        recycler_fertilizers.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterf = new ViewRecycleProductsAdapter(getContext(),data_fertilize);
        recycler_fertilizers.setAdapter(recycleViewAdapterf);

        Data[] data_tools = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        LinearLayoutManager lineart = new LinearLayoutManager(getContext());
        recycler_tools.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdaptert = new ViewRecycleProductsAdapter(getContext(),data_tools);
        recycler_tools.setAdapter(recycleViewAdaptert);

        Data[] data_machines = {
                new Data(R.drawable.image1, "rice", "50$", "no"),
                new Data(R.drawable.image2, "fyuegif", "100$", "50%"),
                new Data(R.drawable.image3,"rice","50$","no"),
                new Data(R.drawable.image4,"fyuegif","100$","50%"),
                new Data(R.drawable.image5,"rice","50$","no"),
                new Data(R.drawable.image6,"fyuegif","100$","50%"),
                new Data(R.drawable.image7,"rice","50$","no"),
                new Data(R.drawable.image8,"fyuegif","100$","50%")
        };

        LinearLayoutManager linearm = new LinearLayoutManager(getContext());
        recycler_machines.setLayoutManager((new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)));
        ViewRecycleProductsAdapter recycleViewAdapterm = new ViewRecycleProductsAdapter(getContext(),data_fertilize);
        recycler_machines.setAdapter(recycleViewAdapterm);

        return view;
    }
}