package com.example.myfirstapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myfirstapplication.db.converters.ConverterBitMap;
import com.example.myfirstapplication.db.converters.ConverterDate;
import com.example.myfirstapplication.db.daos.DaoAward;
import com.example.myfirstapplication.db.daos.DaoAwardUser;
import com.example.myfirstapplication.db.daos.DaoCategory;
import com.example.myfirstapplication.db.daos.DaoProduct;
import com.example.myfirstapplication.db.daos.DaoRecord;
import com.example.myfirstapplication.db.daos.DaoRecordItem;
import com.example.myfirstapplication.db.daos.DaoUser;

@Database(
        version = 4,
        entities = {User.class,
                    Category.class,
                    Record.class,
                    RecordItem.class,
                    Product.class,
                    Award.class,
                    AwardsUser.class}
)

@TypeConverters({ConverterBitMap.class})

public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract DaoUser daoUser();
    public abstract DaoCategory daoCategory();
    public abstract DaoProduct daoProduct();
    public abstract DaoRecord daoRecord();
    public abstract DaoRecordItem daoRecordItem();
    public abstract DaoAward daoAward();
    public abstract DaoAwardUser daoAwardUser();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "ecoplastic.db")
                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
                    .createFromAsset("ecoplastic.db")
                    .build();
        }
        return INSTANCE;
    }
}
