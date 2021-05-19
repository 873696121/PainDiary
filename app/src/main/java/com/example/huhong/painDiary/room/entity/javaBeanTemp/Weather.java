package com.example.huhong.painDiary.room.entity.javaBeanTemp;

import java.io.Serializable;

// 天气
public class Weather implements Serializable {
    private Double temperature;
    private Integer humidity;
    private Integer pressure;

    public Weather(Double temperature, Integer humidity, Integer pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Weather(Weather weather) {
        this.temperature = weather.temperature;
        this.humidity = weather.humidity;
        this.pressure = weather.pressure;
    }

    public Weather() {
        this.pressure = 0;
        this.humidity = 0;
        this.temperature = 0.0;
    }

    @Override
    public String toString() {
        return "Today's weather \ntemperature: " + temperature + "\nhumidity: " + humidity + "\npressure: " + pressure;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }
}
