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
    private int id;

    @NonNull
    private int idRecord;
    @NonNull
    private String name;
    @NonNull
    private int category;
    private float grams;
    private int score;
    private int hour;
    private int minute;
    private int second;

    public RecordItem(int idRecord, String name, int category, int hour, int minute, int second, float grams, int score){
        this.idRecord = idRecord;
        this.name = name;
        this.category = category;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.grams = grams;
        this.score = score;
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
    public int getCategory() {
        return category;
    }

    public void setCategory(@NonNull int category) {
        this.category = category;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
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
}
