package com.rammus.covidtravel;

import android.graphics.drawable.Drawable;

public class weatherListViewItem {
    private Drawable iconDrawble;
    private String date, weatherString;
    private String temperature;

    public weatherListViewItem(Drawable icon, String date, String weatherString, String temperature){
        this.iconDrawble = icon;
        this.date = date;
        this.weatherString = weatherString;
        this.temperature = temperature;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
    }

    public String getWeatherString() {
        return this.weatherString;
    }

    public Drawable getIconDrawble() {
        return iconDrawble;
    }

    public void setIconDrawble(Drawable iconDrawble) {
        this.iconDrawble = iconDrawble;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
