package com.example.huhong.painDiary.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.huhong.painDiary.room.entity.User;

@Dao
public interface UserDao {
    @Insert
    void insertUsers(User... users);

    @Update
    void updateUsers(User... users);

    @Delete
    void deleteUsers(User... users);

    @Query("select * from user where username = :username and password = :password")
    User getUser(String username, String password);
}
