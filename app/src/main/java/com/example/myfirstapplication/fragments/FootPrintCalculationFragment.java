package com.example.myfirstapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfirstapplication.FragmentStartCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;

public class FootPrintCalculationFragment extends Fragment implements FragmentStartCallbacks {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private EditText editTextNumber3;
    private EditText editTextNumber4;
    private EditText[] editTexts;
    private Button btCalculate;
    private StartCallbacks startCallbacks;

    public FootPrintCalculationFragment() {
        // Required empty public constructor
    }

    public static FootPrintCalculationFragment newInstance(String param1, String param2) {
        FootPrintCalculationFragment fragment = new FootPrintCalculationFragment();
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
        View view = inflater.inflate(R.layout.fragment_foot_print_calculation, container, false);
        editTextNumber1 = view.findViewById(R.id.fpc_et_number1);
        editTextNumber2 = view.findViewById(R.id.fpc_et_number2);
        editTextNumber3 = view.findViewById(R.id.fpc_et_number3);
        editTextNumber4 = view.findViewById(R.id.fpc_et_number4);
        editTexts = new EditText[]{editTextNumber1, editTextNumber2, editTextNumber3, editTextNumber4};
        btCalculate = view.findViewById(R.id.fpc_bt_calculate);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = calcular(editTexts);
                startCallbacks.onMsgFromFragmentToStart("footprintCalculate",String.valueOf(val));
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

    public int calcular(EditText[] edits){
        int suma = 0;
        String cad;
        for(int val = 0; val < 4; val++){
            cad = edits[val].getText().toString();
            if (!TextUtils.isEmpty(cad))
                suma += Integer.parseInt(cad);
        }
        if(suma<10)
            return 1;
        else if (suma<15)
            return 2;
        else if(suma<20)
            return 3;
        else return 4;
    }

    @Override
    public void onMsgFromStartToFragment(String value) {

    }
}