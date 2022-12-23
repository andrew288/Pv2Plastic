package com.example.myfirstapplication.db;

import android.graphics.BitmapFactory;
import android.media.audiofx.AudioEffect;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.db.converters.ConverterBitMap;

import org.w3c.dom.Text;

@Entity(
        tableName = "products",
        foreignKeys = {
                @ForeignKey(entity = Category.class,
                        parentColumns = "id",
                        childColumns = "idCategory")
        })
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private int idCategory;

    @NonNull
    private String name;
    private String description;
    private float sizeSGrams;
    private int maxMeasureS;
    private float sizeMGrams;
    private int maxMeasureM;
    private float sizeLGrams;
    private int maxMeasureL;
    private float sizeXLGrams;
    private int maxMeasureXL;
    private String measure;
    private byte[] image;

    public Product(){

    };

    public Product(String name,
                   String description,
                   int idCategory,
                   String measure,
                   int maxMeasureS, float sizeSGrams,
                   int maxMeasureM, float sizeMGrams,
                   int maxMeasureL, float sizeLGrams,
                   int maxMeasureXL, float sizeXLGrams,
                   byte[] image){
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.maxMeasureS = maxMeasureS;
        this.sizeSGrams = sizeSGrams;
        this.maxMeasureM = maxMeasureM;
        this.sizeMGrams = sizeMGrams;
        this.maxMeasureL = maxMeasureL;
        this.sizeLGrams = sizeLGrams;
        this.maxMeasureXL = maxMeasureXL;
        this.sizeXLGrams = sizeXLGrams;
        this.measure = measure;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSizeSGrams() {
        return sizeSGrams;
    }

    public void setSizeSGrams(float sizeSGrams) {
        this.sizeSGrams = sizeSGrams;
    }

    public int getMaxMeasureS() {
        return maxMeasureS;
    }

    public void setMaxMeasureS(int maxMeasureS) {
        this.maxMeasureS = maxMeasureS;
    }

    public float getSizeMGrams() {
        return sizeMGrams;
    }

    public void setSizeMGrams(float sizeMGrams) {
        this.sizeMGrams = sizeMGrams;
    }

    public int getMaxMeasureM() {
        return maxMeasureM;
    }

    public void setMaxMeasureM(int maxMeasureM) {
        this.maxMeasureM = maxMeasureM;
    }

    public float getSizeLGrams() {
        return sizeLGrams;
    }

    public void setSizeLGrams(float sizeLGrams) {
        this.sizeLGrams = sizeLGrams;
    }

    public int getMaxMeasureL() {
        return maxMeasureL;
    }

    public void setMaxMeasureL(int maxMeasureL) {
        this.maxMeasureL = maxMeasureL;
    }

    public float getSizeXLGrams() {
        return sizeXLGrams;
    }

    public void setSizeXLGrams(float sizeXLGrams) {
        this.sizeXLGrams = sizeXLGrams;
    }

    public int getMaxMeasureXL() {
        return maxMeasureXL;
    }

    public void setMaxMeasureXL(int maxMeasureXL) {
        this.maxMeasureXL = maxMeasureXL;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
