package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfirstapplication.db.User;

import java.util.List;

@Dao
public interface DaoUser {
    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE id= :id")
    User getUser(int id);

    @Query("SELECT * FROM users WHERE username= :username AND password= :password")
    User getUserByUsername(String username, String password);

    @Insert
    void insertUser(User...users);

    @Query("UPDATE users SET full_name= :fullname, email= :email WHERE id= :id")
    void updateUser(int id, String fullname, String email);

    @Query("DELETE FROM users WHERE id= :id")
    void deleteUser(int id);
}
