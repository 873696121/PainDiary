package com.example.huhong.painDiary.room.entity;

import com.example.huhong.painDiary.room.entity.javaBeanTemp.Weather;
import com.example.huhong.painDiary.utils.DatetimeUtil;

import java.util.Date;

// 记录
public class Record {
    private Date lastModified;
    private Integer painIntensityLevel;
    private String painLocation;
    private Integer moodLevel;
    private Integer stepsTaken;
    private Weather weather;
    private Integer spinnerPosition;
    private Integer goalStep;


    public Record(Date lastModified) {
        this.lastModified = lastModified;
    }



    public Integer getSpinnerPosition() {
        return spinnerPosition;
    }

    public void setSpinnerPosition(Integer spinnerPosition) {
        this.spinnerPosition = spinnerPosition;
    }

    @Override
    public String toString() {
        if(lastModified == null) lastModified = new Date();
        return "Date: " + DatetimeUtil.getTheTimeInSeconds(getLastModified())
                + "\npainIntensityLevel: " + getPainIntensityLevel()
                + "\npainLocation: " + getPainLocation()
                + "\nmoodLevel: " + getMoodLevel()
                + "\nstepsTaken: " + getStepsTaken()
                + "\nweather: " + getWeather();
    }

    public Record(Date lastModified, Integer painIntensityLevel, String painLocation, Integer moodLevel, Integer stepsTaken, Weather weather, Integer spinnerPosition, Integer goalStep) {
        this.lastModified = lastModified;
        this.painIntensityLevel = painIntensityLevel;
        this.painLocation = painLocation;
        this.moodLevel = moodLevel;
        this.stepsTaken = stepsTaken;
        this.weather = weather;
        this.spinnerPosition = spinnerPosition;
        this.goalStep = goalStep;
    }

    public Integer getGoalStep() {
        return goalStep;
    }

    public void setGoalStep(Integer goalStep) {
        this.goalStep = goalStep;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getPainIntensityLevel() {
        return painIntensityLevel;
    }

    public void setPainIntensityLevel(Integer painIntensityLevel) {
        this.painIntensityLevel = painIntensityLevel;
    }

    public String getPainLocation() {
        return painLocation;
    }

    public void setPainLocation(String painLocation) {
        this.painLocation = painLocation;
    }

    public Integer getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(Integer moodLevel) {
        this.moodLevel = moodLevel;
    }

    public Integer getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(Integer stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
