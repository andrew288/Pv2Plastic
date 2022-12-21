package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.Award;
import com.example.myfirstapplication.db.Product;

import java.util.List;

@Dao
public interface DaoAward {
    @Query("SELECT * FROM awards")
    List<Award> getAwards();

    @Query("SELECT * FROM awards WHERE id= :id")
    Award getAwardById(int id);

    @Insert
    void insertAward(Award...awards);

    @Update
    public void updateAward(Award...awards);

    @Delete
    public void deleteAward(Award...awards);
}
