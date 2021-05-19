package com.example.huhong.painDiary.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.huhong.painDiary.room.converter.DateConverter;
import com.example.huhong.painDiary.room.converter.MapConverter;
import com.example.huhong.painDiary.room.converter.WeatherConverter;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.room.entity.PainRecord;

// 生产dao对象的工具类

@Database(entities = {PainRecord.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class, WeatherConverter.class, MapConverter.class})
public abstract class PainRecordDatabase extends RoomDatabase {
    public abstract PainRecordDao getPainRecordDao();
}
