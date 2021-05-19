package com.example.huhong.painDiary.retrofit;

import com.example.huhong.painDiary.room.entity.javaBeanTemp.JsonRootBean;
import com.example.huhong.painDiary.room.entity.javaBeanTemp.Now;
import com.example.huhong.painDiary.room.entity.javaBeanTemp.Weather;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 调用天气API接口获取json数据，然后再转成Weather对象的工具类, 调用getWeather方法可以得到当天苏州的天气

public class RetrofitUtil {

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private final String location = "suzhou";
    private final String key = "SHhxKMDSBX7aVLYhu";
    private final String url = "http://api.seniverse.com/";

    public static Weather weather;

    public Weather getWeather() {

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<JsonRootBean> response = apiService.queryWeather(key, location).execute();
                    JsonRootBean body = response.body();
                    if(body != null){
                        weather = jsonRootBeanToWeather(body);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return weather;
    }

    public static Weather jsonRootBeanToWeather(JsonRootBean jsonRootBean){
        Now now = jsonRootBean.getResults().get(0).getNow();
        return new Weather(now.getTemperature(), now.getHumidity(), now.getPressure());
    }
}
