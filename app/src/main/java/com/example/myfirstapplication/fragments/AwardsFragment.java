package com.example.myfirstapplication.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Category;
import com.example.myfirstapplication.db.Product;
import com.example.myfirstapplication.db.converters.ConverterBitMap;
import com.example.myfirstapplication.db.converters.ConverterDate;
import com.example.myfirstapplication.db.daos.DaoCategory;
import com.example.myfirstapplication.db.daos.DaoProduct;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AwardsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AwardsFragment extends Fragment {

    public AwardsFragment() {
        // Required empty public constructor
    }


    public static AwardsFragment newInstance() {
        AwardsFragment fragment = new AwardsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_awards, container, false);
        return root;
    }
}