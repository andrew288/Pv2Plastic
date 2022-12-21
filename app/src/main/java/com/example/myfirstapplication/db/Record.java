package com.example.myfirstapplication.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "records",
        foreignKeys = {
            @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "idUser")
        })
public class Record {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private int idUser;
    private int day;
    private int month;
    private int year;
    private float totalGrams;
    private int totalScore;

    public Record(int idUser, int day, int month, int year, float totalGrams, int totalScore){
        this.idUser = idUser;
        this.day = day;
        this.month = month;
        this.year = year;
        this.totalGrams = totalGrams;
        this.totalScore = totalScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getTotalGrams() {
        return totalGrams;
    }

    public void setTotalGrams(float totalGrams) {
        this.totalGrams = totalGrams;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
