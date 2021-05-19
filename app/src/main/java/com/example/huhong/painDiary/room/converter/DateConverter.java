package com.example.huhong.painDiary.room.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;

// converter下的包用于序列化功能， java对象和json对象之间相互转化

public class DateConverter {
    @TypeConverter
    public static Date fromString(String value){
        Type type = new TypeToken<Date>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromDate(Date date){
        return new Gson().toJson(date);
    }

}
