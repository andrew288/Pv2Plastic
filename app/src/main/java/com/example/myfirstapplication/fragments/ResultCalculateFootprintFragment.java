package com.example.myfirstapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapplication.FragmentStartCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;
import com.example.myfirstapplication.views.GraficoResultados;

public class ResultCalculateFootprintFragment extends Fragment implements FragmentStartCallbacks {
    private StartCallbacks startCallbacks;
    private GraficoResultados graficoResultados;
    private Button btNextHome;

    public ResultCalculateFootprintFragment() {
        // Required empty public constructor
    }

    public static ResultCalculateFootprintFragment newInstance(String param1, String param2) {
        ResultCalculateFootprintFragment fragment = new ResultCalculateFootprintFragment();
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
        View view = inflater.inflate(R.layout.fragment_result_calculate_footprint, container, false);
        Bundle bundle = this.getArguments();
        int data = bundle.getInt("TOTAL");

        graficoResultados =  view.findViewById(R.id.rcf_gr_imageGrafico);
        graficoResultados.setValue(data);

        btNextHome = view.findViewById(R.id.rcf_bt_next);

        btNextHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCallbacks.onMsgFromFragmentToStart("result","foo");
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StartCallbacks){
            startCallbacks = (StartCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }

    @Override
    public void onMsgFromStartToFragment(String value) {

    }
}