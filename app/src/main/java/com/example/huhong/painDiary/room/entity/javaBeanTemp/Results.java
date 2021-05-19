/**
  * Copyright 2021 json.cn 
  */
package com.example.huhong.painDiary.room.entity.javaBeanTemp;

import java.util.Date;

/**
 * Auto-generated: 2021-05-12 1:32:31
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Results {

    private Location location;
    private Now now;
    private Date last_update;
    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setNow(Now now) {
         this.now = now;
     }
     public Now getNow() {
         return now;
     }

    public void setLast_update(Date last_update) {
         this.last_update = last_update;
     }
     public Date getLast_update() {
         return last_update;
     }

}