package com.example.huhong.painDiary.retrofit;

import com.example.huhong.painDiary.room.entity.javaBeanTemp.JsonRootBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Http请求调用接口
public interface ApiService {
    @GET("v3/weather/now.json")
    Call<JsonRootBean> queryWeather(@Query("key") String key, @Query("location") String location);
}
