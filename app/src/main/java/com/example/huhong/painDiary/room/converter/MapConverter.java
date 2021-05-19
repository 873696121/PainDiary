package com.example.huhong.painDiary.room.converter;

import androidx.room.TypeConverter;

import com.example.huhong.painDiary.room.entity.Record;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MapConverter {
    @TypeConverter
    public static HashMap<String, Record> fromString(String value){
        Type type = new TypeToken<HashMap<String, Record>>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromWeather(HashMap<String, Record> recordHashMap){
        return new Gson().toJson(recordHashMap);
    }
}
