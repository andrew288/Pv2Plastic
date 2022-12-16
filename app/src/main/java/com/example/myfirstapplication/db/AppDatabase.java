package com.example.myfirstapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myfirstapplication.db.converters.ConverterBitMap;
import com.example.myfirstapplication.db.converters.ConverterDate;
import com.example.myfirstapplication.db.daos.DaoCategory;
import com.example.myfirstapplication.db.daos.DaoProduct;
import com.example.myfirstapplication.db.daos.DaoRecord;
import com.example.myfirstapplication.db.daos.DaoRecordItem;
import com.example.myfirstapplication.db.daos.DaoUser;

@Database(
        version = 3,
        entities = {User.class,
                    Category.class,
                    Record.class,
                    RecordItem.class,
                    Product.class}
)

@TypeConverters({ConverterDate.class, ConverterBitMap.class})

public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract DaoUser daoUser();
    public abstract DaoCategory daoCategory();
    public abstract DaoProduct daoProduct();
    public abstract DaoRecord daoRecord();
    public abstract DaoRecordItem daoRecordItem();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "ecoplastic.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
