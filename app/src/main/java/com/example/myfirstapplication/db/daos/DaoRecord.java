package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.Record;

import java.util.Date;
import java.util.List;

@Dao
public interface DaoRecord {
    @Query("SELECT * FROM records")
    List<Record> getRecords();

    @Query("SELECT * FROM records WHERE day= :day AND month= :month AND year= :year")
    Record getRecordByDate(int day, int month, int year);

    @Query("SELECT * FROM records WHERE id= :id")
    Record getRecordById(int id);

    @Query("SELECT * FROM records WHERE idUser= :idUser")
    List<Record> getRecordsByUser(int idUser);

    @Insert
    void insertRecord(Record...records);

    @Update
    void updateRecord(Record...records);

    @Delete
    void deleteRecord(Record...records);
}
