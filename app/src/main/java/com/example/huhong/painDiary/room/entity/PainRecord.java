package com.example.huhong.painDiary.room.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.HashMap;

// 疼痛记录实体类

@Entity
public class PainRecord {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String username;
    // key : 日期(天)  value : 记录
    private HashMap<String, Record> map;

    @Ignore
    public PainRecord(String username) {
        this.username = username;
        map = new HashMap<>();
    }

    @Override
    public String toString() {
        return "PainRecord{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", map=" + map +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String, Record> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Record> map) {
        this.map = map;
    }

    public PainRecord() {
    }

    @Ignore
    public PainRecord(Integer id, String username, HashMap<String, Record> map) {
        this.id = id;
        this.username = username;
        this.map = map;
    }
}
