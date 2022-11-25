package com.example.myfirstapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.myfirstapplication.widgets.CheckboxWithEditText;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class CreateElementFragment extends Fragment {

    private CheckboxWithEditText sizeS;
    private CheckboxWithEditText sizeM;
    private CheckboxWithEditText sizeL;
    private CheckboxWithEditText sizeXL;
    private Button createElement;
    private TextInputEditText textInputName;
    private TextInputEditText textInputDescription;
    private AutoCompleteTextView checkBoxSelected;
    private HashMap<String, Float> medidas;
    private String itemSelectedAdapterCategory="None";
    private String categories[] = {"Drinks","Bottles", "Cupboard and Hygiene","Bags and film", "Pots, tubs, trays", "Other"};

    public CreateElementFragment() {
        // Required empty public constructor
    }

    public static CreateElementFragment newInstance(String param1, String param2) {
        CreateElementFragment fragment = new CreateElementFragment();
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

        View root = inflater.inflate(R.layout.fragment_create_element, container, false);

        sizeS = root.findViewById(R.id.create_element_size_s);
        sizeM = root.findViewById(R.id.create_element_size_m);
        sizeL = root.findViewById(R.id.create_element_size_l);
        sizeXL = root.findViewById(R.id.create_element_size_xl);
        createElement = root.findViewById(R.id.create_element_bCreate);
        textInputName = root.findViewById(R.id.create_element_name);
        textInputDescription = root.findViewById(R.id.create_element_description);
        checkBoxSelected = root.findViewById(R.id.create_element_category);
        medidas = new HashMap<>();
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_category,categories);
        checkBoxSelected.setAdapter(adapterCategories);
        checkBoxSelected.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelectedAdapterCategory = adapterView.getItemAtPosition(i).toString();
                Log.d("ELEMENT",itemSelectedAdapterCategory);
            }
        });

        createElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameElement = textInputName.getText().toString();
                String descriptionElement = textInputDescription.getText().toString();
                Log.d("ELEMENT","NAME: " + nameElement);
                Log.d("ELEMENT","DESCRIPTION: " + descriptionElement);
                Log.d("ELEMENT","CATEGORIA: " + itemSelectedAdapterCategory);
                if(sizeS.itemSelected()){
                    Log.d("ELEMENT","SIZE S: " + sizeS.getValueSizeElement().getText().toString());
                    medidas.put("S", Float.parseFloat(sizeS.getValueSizeElement().getText().toString()));
                }
                if(sizeM.itemSelected()){
                    Log.d("ELEMENT","SIZE M: " + sizeM.getValueSizeElement().getText().toString());
                    medidas.put("M", Float.parseFloat(sizeM.getValueSizeElement().getText().toString()));
                }
                if(sizeL.itemSelected()){
                    Log.d("ELEMENT","SIZE L: " + sizeL.getValueSizeElement().getText().toString());
                    medidas.put("L", Float.parseFloat(sizeL.getValueSizeElement().getText().toString()));
                }
                if(sizeXL.itemSelected()){
                    Log.d("ELEMENT","SIZE XL: " + sizeXL.getValueSizeElement().getText().toString());
                    medidas.put("XL", Float.parseFloat(sizeXL.getValueSizeElement().getText().toString()));
                }
            }
        });
        // Inflate the layout for this fragment
        return root;
    }

}