package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myfirstapplication.db.Category;

import java.util.List;

@Dao
public interface DaoCategory {
    @Query("SELECT * FROM categories")
    List<Category> getCategories();

    @Query("SELECT * FROM categories WHERE id= :id")
    Category getCategory(int id);

    @Insert
    void insertCategory(Category...categories);

    @Query("UPDATE categories SET name= :name WHERE id= :id")
    void updateCategory(int id, String name);

    @Query("DELETE FROM categories WHERE id= :id")
    void deleteCategory(int id);
}
