package com.example.huhong.painDiary.room.converter;

import androidx.room.TypeConverter;

import com.example.huhong.painDiary.room.entity.javaBeanTemp.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WeatherConverter {

    @TypeConverter
    public static Weather fromString(String value){
        Type type = new TypeToken<Weather>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromWeather(Weather weather){
        return new Gson().toJson(weather);
    }

}
