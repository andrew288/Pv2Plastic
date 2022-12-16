package com.example.myfirstapplication.fragments;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Product;
import com.example.myfirstapplication.db.daos.DaoProduct;
import com.example.myfirstapplication.widgets.CheckboxWithEditText;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class CreateElementFragment extends Fragment {

    //MEASURES
    private CheckboxWithEditText sizeS;
    private CheckboxWithEditText sizeM;
    private CheckboxWithEditText sizeL;
    private CheckboxWithEditText sizeXL;
    private Button createElement;

    //CAMERA AND IMAGE
    private FloatingActionButton bFloatingImageProduct;
    private ImageView imageProduct;
    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    private Uri image_uri;

    //FEATURES PRODUCT
    private TextInputEditText textInputName;
    private TextInputEditText textInputDescription;
    private AutoCompleteTextView checkBoxSelectedCategory;
    private AutoCompleteTextView checkBoxSelectedMeasure;
    private HashMap<String, Float> measures;
    private int itemSelectedAdapterCategory = 0;
    private String itemSelectAdapterMeasure="None";
    private String categoriesArray[] = {"Tereftalato de polietileno (PET)",
                                        "Polietileno de alta densidad (PEAD)",
                                        "Polietileno de baja densidad (LDPE)",
                                        "Polipropileno (PP)",
                                        "Poliestireno (PS)",
                                        "Otros"};
    private String measuresArray[] = {"Millimeters (ml)",
                                        "Centimeters (cm)",
                                        "Grams (g)"};

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

        // REFERENCIA AL FRAGMENT
        View root = inflater.inflate(R.layout.fragment_create_element, container, false);

        // INSTANCIA DE LA BASE DE DATOS
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoProduct productDao = db.daoProduct();

        sizeS = root.findViewById(R.id.create_element_size_s);
        sizeM = root.findViewById(R.id.create_element_size_m);
        sizeL = root.findViewById(R.id.create_element_size_l);
        sizeXL = root.findViewById(R.id.create_element_size_xl);
        createElement = root.findViewById(R.id.create_element_bCreate);
        textInputName = root.findViewById(R.id.create_element_name);
        textInputDescription = root.findViewById(R.id.create_element_description);
        checkBoxSelectedCategory = root.findViewById(R.id.create_element_category);
        checkBoxSelectedMeasure = root.findViewById(R.id.create_element_measure);
        bFloatingImageProduct = root.findViewById(R.id.create_element_floating_action);
        imageProduct = root.findViewById(R.id.create_element_image);
        measures = new HashMap<>();

        //LLENAMOS LOS DROPDOWNS
        ArrayAdapter<String> adapterCategories = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_category,categoriesArray);

        ArrayAdapter<String> adapterMeasures = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_category,measuresArray);

        checkBoxSelectedCategory.setAdapter(adapterCategories);
        checkBoxSelectedMeasure.setAdapter(adapterMeasures);

        //VALUE CHECKBOX
        checkBoxSelectedCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelectedAdapterCategory = (i+1);
                Log.d("ELEMENT","ID CATEGORY: " + itemSelectAdapterMeasure);
            }
        });

        checkBoxSelectedMeasure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelectAdapterMeasure = adapterView.getItemAtPosition(i).toString();
                Log.d("ELEMENT",itemSelectAdapterMeasure);
            }
        });

        //ADD IMAGE
        bFloatingImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) ==
                                    PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();
                    }
                } else{
                    openCamera();
                }
            }
        });

        createElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameElement = textInputName.getText().toString();
                String descriptionElement = textInputDescription.getText().toString();
                Product product = new Product();

                Log.d("ELEMENT","NAME: " + nameElement);
                Log.d("ELEMENT","DESCRIPTION: " + descriptionElement);
                Log.d("ELEMENT","CATEGORIA: " + itemSelectedAdapterCategory);
                Log.d("ELEMENT","MEASURE: " + itemSelectAdapterMeasure);

                //VALIDATION
                if(!nameElement.isEmpty() && itemSelectedAdapterCategory!=0 && !itemSelectAdapterMeasure.equals("None")){
                    product.setName(nameElement);
                    product.setDescription(descriptionElement);
                    product.setMeasure(itemSelectAdapterMeasure);
                    product.setIdCategory(itemSelectedAdapterCategory);

                    if(sizeS.itemSelected()){
                        Log.d("ELEMENT","SIZE S: " + sizeS.getValueSizeElement().getText().toString());
                        measures.put("S", Float.parseFloat(sizeS.getValueSizeElement().getText().toString()));
                        product.setMaxMeasureS(Integer.parseInt(sizeS.getRangeSizeElement().getText().toString()));
                        product.setSizeSGrams(Float.parseFloat(sizeS.getValueSizeElement().getText().toString()));
                    }
                    if(sizeM.itemSelected()){
                        Log.d("ELEMENT","SIZE M: " + sizeM.getValueSizeElement().getText().toString());
                        measures.put("M", Float.parseFloat(sizeM.getValueSizeElement().getText().toString()));
                        product.setMaxMeasureS(Integer.parseInt(sizeM.getRangeSizeElement().getText().toString()));
                        product.setSizeSGrams(Float.parseFloat(sizeM.getValueSizeElement().getText().toString()));
                    }
                    if(sizeL.itemSelected()){
                        Log.d("ELEMENT","SIZE L: " + sizeL.getValueSizeElement().getText().toString());
                        measures.put("L", Float.parseFloat(sizeL.getValueSizeElement().getText().toString()));
                        product.setMaxMeasureS(Integer.parseInt(sizeL.getRangeSizeElement().getText().toString()));
                        product.setSizeSGrams(Float.parseFloat(sizeL.getValueSizeElement().getText().toString()));
                    }
                    if(sizeXL.itemSelected()){
                        Log.d("ELEMENT","SIZE XL: " + sizeXL.getValueSizeElement().getText().toString());
                        measures.put("XL", Float.parseFloat(sizeXL.getValueSizeElement().getText().toString()));
                        product.setMaxMeasureS(Integer.parseInt(sizeXL.getRangeSizeElement().getText().toString()));
                        product.setSizeSGrams(Float.parseFloat(sizeXL.getValueSizeElement().getText().toString()));
                    }

                    productDao.insertProduct(product);

                }

            }
        });
        // Inflate the layout for this fragment
        return root;
    }

    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the camera");
//        image_uri = getContentResolver()
        Intent camintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camintent, CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_CODE:
                if(grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    openCamera();
                } else{
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("ELEMENT", String.valueOf(resultCode));
        if(resultCode == getActivity().RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBitMap = (Bitmap) extras.get("data");
            imageProduct.setImageBitmap(imgBitMap);
        }
    }
}