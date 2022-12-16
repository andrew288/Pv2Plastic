package com.example.myfirstapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "record_items",
        foreignKeys = {@ForeignKey(
                entity = Record.class,
                parentColumns = "id",
                childColumns = "idRecord"
        )})
public class RecordItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int idRecord;
    @NonNull
    public String name;
    @NonNull
    public String category;
    public float grams;
    public int score;
    public Date hour;

    public RecordItem(int id, int idRecord, String name, String category, float grams, int score, Date hour){
        this.id = id;
        this.idRecord = idRecord;
        this.name = name;
        this.category = category;
        this.grams = grams;
        this.score = score;
        this.hour = hour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(int idRecord) {
        this.idRecord = idRecord;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    public float getGrams() {
        return grams;
    }

    public void setGrams(float grams) {
        this.grams = grams;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }
}
