package com.example.huhong.painDiary.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.huhong.painDiary.room.dao.UserDao;
import com.example.huhong.painDiary.room.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
