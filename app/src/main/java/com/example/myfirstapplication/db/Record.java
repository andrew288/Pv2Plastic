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
    public int id;

    @NonNull
    public int idUser;
    public Date date;
    public float totalGrams;
    public int totalScore;

    public Record(int id, int idUser, Date date, float totalGrams, int totalScore){
        this.id = id;
        this.idUser = idUser;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
