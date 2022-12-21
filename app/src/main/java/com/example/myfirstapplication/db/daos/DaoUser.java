package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.User;

import java.util.List;

@Dao
public interface DaoUser {
    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE id= :id")
    User getUserById(int id);

    @Query("SELECT * FROM users WHERE username= :username AND password= :password")
    User getUserByUsername(String username, String password);

    @Insert
    void insertUser(User...users);

    @Update
    void updateUser(User...user);

    @Delete
    void deleteUser(User...user);
}
