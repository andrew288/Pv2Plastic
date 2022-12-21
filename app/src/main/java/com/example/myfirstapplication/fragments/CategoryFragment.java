package com.example.myfirstapplication.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapplication.MainCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.adapters.AdapterProduct;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Product;
import com.example.myfirstapplication.db.daos.DaoProduct;
import com.example.myfirstapplication.db.daos.DaoUser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CategoryFragment extends Fragment {

    private int IdCategory;
    RecyclerView recview;
    FloatingActionButton fabAddProduct;

    MainCallbacks mainCallbacks;

    public CategoryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           IdCategory = getArguments().getInt("ID_CATEGORY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        fabAddProduct = root.findViewById(R.id.category_floating);
        Context contextFragment = this.getContext();

        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoProduct productDao = db.daoProduct();

        recview = root.findViewById(R.id.category_recycle_view);
        recview.setLayoutManager(new GridLayoutManager(root.getContext(),2));

        List<Product> products = productDao.getProductsByCategory(IdCategory);
        AdapterProduct adapterProduct = new AdapterProduct(this.getContext(),products);
        recview.setAdapter(adapterProduct);

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainCallbacks.onMsgFromFragmentToMain("create-element",0);
            }
        });

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCallbacks){
            mainCallbacks = (MainCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }
}