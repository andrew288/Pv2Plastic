package com.example.myfirstapplication.db;

import android.media.audiofx.AudioEffect;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
    public int id;

    @NonNull
    public int idCategory;

    @NonNull
    public String name;
    public String description;
    public float sizeSGrams;
    public int maxMeasureS;
    public float sizeMGrams;
    public int maxMeasureM;
    public float sizeLGrams;
    public int maxMeasureL;
    public float sizeXLGrams;
    public int maxMeasureXL;
    public String measure;
    public byte[] image;

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
