package com.example.myfirstapplication.db.converters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ConverterBitMap {
    @TypeConverter
    public static byte[] getStringFromBitMap(Bitmap bitmapPicture) {
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 0, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        return b;
    }

    @TypeConverter
    public static Bitmap getBitMapFromStr(byte[] arr){
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);
    }
}
