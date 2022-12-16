package com.example.myfirstapplication.db.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myfirstapplication.db.Product;

import java.util.List;

@Dao
public interface DaoProduct {
    @Query("SELECT * FROM products")
    List<Product> getProducts();

    @Query("SELECT * FROM products WHERE idCategory= :idCategory")
    Product getProduct(int idCategory);

    @Insert
    void insertProduct(Product...products);

    @Update
    public void updateProduct(Product...products);

    @Delete
    public void deleteProduct(Product...products);
}
