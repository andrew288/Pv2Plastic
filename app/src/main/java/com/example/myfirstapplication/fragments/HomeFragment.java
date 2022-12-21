package com.example.myfirstapplication.fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myfirstapplication.MainCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;

    MainCallbacks mainCallbacks;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
       View root = inflater.inflate(R.layout.fragment_home, container, false);

       image1 = root.findViewById(R.id.home_iv_pet);
       image2 = root.findViewById(R.id.home_iv_pead);
       image3 = root.findViewById(R.id.home_iv_ldpe);
       image4 = root.findViewById(R.id.home_iv_pp);
       image5 = root.findViewById(R.id.home_iv_ps);
       image6 = root.findViewById(R.id.home_iv_otros);

       image1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                mainCallbacks.onMsgFromFragmentToMain("category",1);
           }
       });

       image2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mainCallbacks.onMsgFromFragmentToMain("category",2);
           }
       });

       image3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mainCallbacks.onMsgFromFragmentToMain("category",3);
           }
       });

       image4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mainCallbacks.onMsgFromFragmentToMain("category",4);
           }
       });

       image5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mainCallbacks.onMsgFromFragmentToMain("category",5);
           }
       });

       image6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mainCallbacks.onMsgFromFragmentToMain("category",6);
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