package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.Category;

import java.util.List;

@Dao
public interface DaoCategory {
    @Query("SELECT * FROM categories")
    List<Category> getCategories();

    @Query("SELECT * FROM categories WHERE id= :id")
    Category getCategoryById(int id);

    @Insert
    void insertCategory(Category...categories);

    @Update
    void updateCategory(Category...categories);

    @Delete
    void deleteCategory(Category...categories);
}
