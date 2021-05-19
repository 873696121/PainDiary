package com.example.huhong.painDiary.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.huhong.painDiary.room.entity.PainRecord;

import java.util.Date;
import java.util.List;

// 数据库访问对象

@Dao
public interface PainRecordDao {

    @Insert
    void insertRecords(PainRecord... painRecords);

    @Update
    void updateRecords(PainRecord... painRecords);

    @Query("select * from painrecord")
    List<PainRecord> getAllPainRecords();

    @Query("select * from painrecord where username = :username")
    PainRecord getPainRecord(String username);
}
