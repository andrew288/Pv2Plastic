package com.example.myfirstapplication.adapters;

import static com.example.myfirstapplication.R.color.green_normal;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Category;
import com.example.myfirstapplication.db.Product;
import com.example.myfirstapplication.db.Record;
import com.example.myfirstapplication.db.RecordItem;
import com.example.myfirstapplication.db.User;
import com.example.myfirstapplication.db.converters.ConverterBitMap;
import com.example.myfirstapplication.db.daos.DaoRecord;
import com.example.myfirstapplication.db.daos.DaoRecordItem;
import com.example.myfirstapplication.db.daos.DaoUser;
import com.example.myfirstapplication.fragments.CategoryFragment;

import java.util.Calendar;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    List<Product> products;
    Context mContext;
    String buttonSelected = "";

    public AdapterProduct(Context mContext, List<Product> products){
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_single_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product productSelected = products.get(position);
        holder.imageCardCategory.setImageBitmap(ConverterBitMap.getBitMapFromStr(productSelected.getImage()));
        holder.tvCardCategory.setText(productSelected.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = AppDatabase.getInstance(mContext);
                DaoRecordItem daoRecordItem = db.daoRecordItem();
                DaoRecord daoRecord = db.daoRecord();
                DaoUser daoUser = db.daoUser();

                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.product_dialog);

                TextView tvName = dialog.findViewById(R.id.dialog_product_name);
                TextView tvMeasure = dialog.findViewById(R.id.dialog_product_tv_measure);
                Button btSizeS = dialog.findViewById(R.id.dialog_product_bt_s);
                Button btSizeM = dialog.findViewById(R.id.dialog_product_bt_m);
                Button btSizeL = dialog.findViewById(R.id.dialog_product_bt_l);
                Button btSizeXL = dialog.findViewById(R.id.dialog_product_bt_xl);
                TextView tvSizeS = dialog.findViewById(R.id.dialog_product_tv_s);
                TextView tvSizeM = dialog.findViewById(R.id.dialog_product_tv_m);
                TextView tvSizeL = dialog.findViewById(R.id.dialog_product_tv_l);
                TextView tvSizeXL = dialog.findViewById(R.id.dialog_product_tv_xl);
                EditText etPlasticGrams = dialog.findViewById(R.id.dialog_product_et_score);
                EditText etQuantity = dialog.findViewById(R.id.dialog_product_et_quantity);
                TextView tvPlasticScore = dialog.findViewById(R.id.dialog_product_tv_plastic_score);
                ImageView btCancel = dialog.findViewById(R.id.dialog_product_bt_cancel);
                ImageView btSave = dialog.findViewById(R.id.dialog_product_bt_save);
                LinearLayout llSizeS = dialog.findViewById(R.id.dialog_product_ll_s);
                LinearLayout llSizeM = dialog.findViewById(R.id.dialog_product_ll_m);
                LinearLayout llSizeL = dialog.findViewById(R.id.dialog_product_ll_l);
                LinearLayout llSizeXL = dialog.findViewById(R.id.dialog_product_ll_xl);
                ImageButton btPlusQuantity = dialog.findViewById(R.id.dialog_product_bt_plus);
                ImageButton btLessQuantity = dialog.findViewById(R.id.dialog_product_bt_less);

                // VALORES POR DEFECTO
                etPlasticGrams.setEnabled(false);
                etQuantity.setEnabled(false);
                etPlasticGrams.setText("0");
                tvPlasticScore.setText("0");
                etQuantity.setText("0");

                // AUMENTAR O DISMINUIR CANTIDAD
                btPlusQuantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int valueQuantity = Integer.parseInt(etQuantity.getText().toString()) + 1;
                        etQuantity.setText(String.valueOf(valueQuantity));
                        float gramsProduct = 0;
                        if(!buttonSelected.isEmpty()){
                            switch (buttonSelected){
                                case "S": gramsProduct = productSelected.getSizeSGrams();
                                    break;
                                case "M": gramsProduct = productSelected.getSizeMGrams();
                                    break;
                                case "L": gramsProduct = productSelected.getSizeLGrams();
                                    break;
                                case "XL": gramsProduct = productSelected.getSizeXLGrams();
                                    break;

                            }
                            float totalGrams = valueQuantity * gramsProduct;
                            etPlasticGrams.setText(String.valueOf(totalGrams));
                            tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                        }
                    }
                });

                btLessQuantity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int valueQuantity = Integer.parseInt(etQuantity.getText().toString());
                        if(valueQuantity > 0){
                            valueQuantity = valueQuantity -1;
                            etQuantity.setText(String.valueOf(valueQuantity));
                            float gramsProduct = 0;
                            if(!buttonSelected.isEmpty()){
                                switch (buttonSelected){
                                    case "S": gramsProduct = productSelected.getSizeSGrams();
                                        break;
                                    case "M": gramsProduct = productSelected.getSizeMGrams();
                                        break;
                                    case "L": gramsProduct = productSelected.getSizeLGrams();
                                        break;
                                    case "XL": gramsProduct = productSelected.getSizeXLGrams();
                                        break;

                                }
                                float totalGrams = valueQuantity * gramsProduct;
                                etPlasticGrams.setText(String.valueOf(totalGrams));
                                tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                            }
                        }
                    }
                });

                // ESTABLECEMOS VALORES GENERALES
                tvName.setText(productSelected.getName());
                tvMeasure.setText("Measure: " + productSelected.getMeasure());

                // BUTTON S LOGICA
                if(productSelected.getMaxMeasureS()!=0){
                    btSizeS.setText("S");
                    tvSizeS.setText("0 - " + productSelected.getMaxMeasureS());

                    btSizeS.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // SELECCIONAR BUTTON
                            if(!buttonSelected.isEmpty()){
                                switch (buttonSelected){
                                    case "S": btSizeS.setTextColor(Color.WHITE);
                                        break;
                                    case "M": btSizeM.setTextColor(Color.WHITE);
                                        break;
                                    case "L": btSizeL.setTextColor(Color.WHITE);
                                        break;
                                    case "XL": btSizeXL.setTextColor(Color.WHITE);
                                        break;

                                }
                            }
                            btSizeS.setTextColor(Color.parseColor("#ffff00"));
                            buttonSelected = "S";

                            // CAMBIAR SCORE Y TOTAL GRAM
                            int totalQuantity = Integer.parseInt(etQuantity.getText().toString());
                            float gramsProduct = productSelected.getSizeSGrams();
                            float totalGrams = totalQuantity * gramsProduct;
                            etPlasticGrams.setText(String.valueOf(totalGrams));
                            tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                        }
                    });
                } else{
                    ViewGroup container = (ViewGroup) llSizeS.getParent();
                    container.removeView(llSizeS);
                }

                // BUTTON M LOGICA
                if(productSelected.getMaxMeasureM()!=0){
                    btSizeM.setText("M");
                    tvSizeM.setText(productSelected.getMaxMeasureS() +
                                    " - " + productSelected.getMaxMeasureM());

                    btSizeM.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // SELECCIONAR BUTTON
                            if(!buttonSelected.isEmpty()){
                                switch (buttonSelected){
                                    case "S": btSizeS.setTextColor(Color.WHITE);
                                        break;
                                    case "M": btSizeM.setTextColor(Color.WHITE);
                                        break;
                                    case "L": btSizeL.setTextColor(Color.WHITE);
                                        break;
                                    case "XL": btSizeXL.setTextColor(Color.WHITE);
                                        break;

                                }
                            }
                            btSizeM.setTextColor(Color.parseColor("#ffff00"));
                            buttonSelected = "M";
                            int totalQuantity = Integer.parseInt(etQuantity.getText().toString());
                            float gramsProduct = productSelected.getSizeMGrams();
                            float totalGrams = totalQuantity * gramsProduct;
                            etPlasticGrams.setText(String.valueOf(totalGrams));
                            tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                        }
                    });
                } else{
                    ViewGroup container = (ViewGroup) llSizeM.getParent();
                    container.removeView(llSizeM);
                }

                // BUTTON L LOGICA
                if(productSelected.getMaxMeasureM()!=0){
                    btSizeL.setText("L");
                    tvSizeL.setText(productSelected.getMaxMeasureM() +
                            " - " + productSelected.getMaxMeasureL());

                    btSizeL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // SELECCIONAR BUTTON
                            if(!buttonSelected.isEmpty()){
                                switch (buttonSelected){
                                    case "S": btSizeS.setTextColor(Color.WHITE);
                                        break;
                                    case "M": btSizeM.setTextColor(Color.WHITE);
                                        break;
                                    case "L": btSizeL.setTextColor(Color.WHITE);
                                        break;
                                    case "XL": btSizeXL.setTextColor(Color.WHITE);
                                        break;

                                }
                            }
                            btSizeL.setTextColor(Color.parseColor("#ffff00"));
                            buttonSelected = "L";
                            int totalQuantity = Integer.parseInt(etQuantity.getText().toString());
                            float gramsProduct = productSelected.getSizeLGrams();
                            float totalGrams = totalQuantity * gramsProduct;
                            etPlasticGrams.setText(String.valueOf(totalGrams));
                            tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                        }
                    });
                } else{
                    ViewGroup container = (ViewGroup) llSizeL.getParent();
                    container.removeView(llSizeL);
                }

                // BUTTON XL LOGICA
                if(productSelected.getMaxMeasureXL()!=0){
                    btSizeXL.setText("XL");
                    tvSizeXL.setText(productSelected.getMaxMeasureL() +
                            " - " + productSelected.getMaxMeasureXL()+" +");

                    btSizeXL.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // SELECCIONAR BUTTON
                            if(!buttonSelected.isEmpty()){
                                switch (buttonSelected){
                                    case "S": btSizeS.setTextColor(Color.WHITE);
                                        break;
                                    case "M": btSizeM.setTextColor(Color.WHITE);
                                        break;
                                    case "L": btSizeL.setTextColor(Color.WHITE);
                                        break;
                                    case "XL": btSizeXL.setTextColor(Color.WHITE);
                                        break;

                                }
                            }
                            btSizeXL.setTextColor(Color.parseColor("#ffff00"));
                            buttonSelected = "XL";
                            int totalQuantity = Integer.parseInt(etQuantity.getText().toString());
                            float gramsProduct = productSelected.getSizeXLGrams();
                            float totalGrams = totalQuantity * gramsProduct;
                            etPlasticGrams.setText(String.valueOf(totalGrams));
                            tvPlasticScore.setText(String.valueOf((int)totalGrams/5));
                        }
                    });

                } else{
                    ViewGroup container = (ViewGroup) llSizeXL.getParent();
                    container.removeView(llSizeXL);
                }

                // ADD RECOR ITEM
                btSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Float.parseFloat(etPlasticGrams.getText().toString())>0){
                            SharedPreferences pref = mContext.getSharedPreferences("Authentication",0);
                            Calendar c = Calendar.getInstance();
                            RecordItem newRecordItem = new RecordItem(0,
                                    productSelected.getName(),
                                    productSelected.getIdCategory(),
                                    c.get(Calendar.HOUR),
                                    c.get(Calendar.MINUTE),
                                    c.get(Calendar.SECOND),
                                    Float.parseFloat(etPlasticGrams.getText().toString()),
                                    Integer.parseInt(tvPlasticScore.getText().toString()));

                            Record record = daoRecord.getRecordByDate(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
                            if(record!= null){
                                newRecordItem.setIdRecord(record.getId());
                                record.setTotalGrams(record.getTotalGrams() + Float.parseFloat(etPlasticGrams.getText().toString()));
                                record.setTotalScore(record.getTotalScore() + Integer.parseInt(tvPlasticScore.getText().toString()));
                                daoRecord.updateRecord(record);
                            }
                            else{
                                Record newRecord = new Record(pref.getInt("ID_USER",-1),
                                                                c.get(Calendar.DAY_OF_MONTH),
                                                                c.get(Calendar.MONTH),
                                                                c.get(Calendar.YEAR),
                                                                Float.parseFloat(etPlasticGrams.getText().toString()),
                                                                Integer.parseInt(tvPlasticScore.getText().toString()));

                                daoRecord.insertRecord(newRecord);
                                Record instanceRecord =  daoRecord.getRecordByDate(c.get(Calendar.DAY_OF_MONTH),
                                        c.get(Calendar.MONTH),
                                        c.get(Calendar.YEAR));
                                newRecordItem.setId(instanceRecord.getId());
                            }
                            daoRecordItem.insertRecordItem(newRecordItem);
                            User user = daoUser.getUserById(pref.getInt("ID_USER",-1));
                            user.setScore(user.getScore() + Integer.parseInt(tvPlasticScore.getText().toString()));
                            daoUser.updateUser(user);
                            buttonSelected = "";
                            dialog.dismiss();
                        } else{
                            Log.d("DIALOG PRODUCT","AGREGUE MEDIDAS");
                        }
                    }
                });

                // CANCEL RECORD
                btCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonSelected = "";
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageCardCategory;
        TextView tvCardCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCardCategory = (ImageView) itemView.findViewById(R.id.product_recycler_image);
            tvCardCategory = (TextView) itemView.findViewById(R.id.product_recycler_text);
        }
    }
}
