package com.example.coronatravel;

import android.graphics.drawable.Drawable;

public class weatherListViewItem {
    private Drawable iconDrawble;
    private String date;
    private String temperature;

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
