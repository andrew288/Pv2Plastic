package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.AwardsUser;

import java.util.List;

@Dao
public interface DaoAwardUser {
    @Query("SELECT * FROM awards_user")
    List<AwardsUser> getAwardsUsers();

    @Query("SELECT * FROM awards_user WHERE id= :id")
    AwardsUser getAwardUserById(int id);

    @Query("SELECT * FROM awards_user WHERE idUser= :idUser")
    List<AwardsUser> getAwardUserByUser(int idUser);

    @Insert
    void insertAwardsUser(AwardsUser...awardsUsers);

    @Update
    public void updateAwardsUser(AwardsUser...awardsUsers);

    @Delete
    public void deleteAwardsUser(AwardsUser...awardsUsers);
}
