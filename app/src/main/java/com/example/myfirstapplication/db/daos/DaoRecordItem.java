package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.RecordItem;

import java.util.List;

@Dao
public interface DaoRecordItem {
    @Query("SELECT * FROM record_items")
    List<RecordItem> getRecordItems();

    @Query("SELECT * FROM record_items WHERE id= :id")
    RecordItem getRecordItemById(int id);

    @Query("SELECT * FROM record_items WHERE idRecord= :idRecord")
    List<RecordItem> getRecordsItemByRecord(int idRecord);

    @Insert
    void insertRecordItem(RecordItem...recordItems);

    @Update
    void updateRecordItem(RecordItem...recordItems);

    @Delete
    void deleteRecordItem(RecordItem...recordItems);
}
