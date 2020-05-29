package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_25 {
    detailInfo_25(){};
    detailInfo_25(String distance, String infocentertourcourse,
                  String schedule, String taketime, String theme){
        this.distance=distance;
        this.infocentertourcourse=infocentertourcourse;
        this.schedule=schedule;
        this.taketime=taketime;
        this.theme=theme;
    }
    private String distance; // 코스 총거리
    private String infocentertourcourse; //문의 및 안내
    private String schedule; // 코스 일정
    private String taketime; // 코스 총 소요시간
    private String theme; // 코스 테마

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getInfocentertourcourse() {
        return infocentertourcourse;
    }

    public void setInfocentertourcourse(String infocentertourcourse) {
        this.infocentertourcourse = infocentertourcourse;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTaketime() {
        return taketime;
    }

    public void setTaketime(String taketime) {
        this.taketime = taketime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public detailInfo_25 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_25 subclass = null;
        String distance; // 코스 총거리
        String infocentertourcourse; //문의 및 안내
        String schedule; // 코스 일정
        String taketime; // 코스 총 소요시간
        String theme; // 코스 테마
        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);
            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);
            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonObject_item = new JSONObject(item);

            try {
                distance = jsonObject_item.getString("distance");
            } catch (JSONException e) {
                distance = "";
            }
            try {
                infocentertourcourse = jsonObject_item.getString("infocentertourcourse");
            } catch (JSONException e) {
                infocentertourcourse = "";
            }
            try {
                schedule = jsonObject_item.getString("schedule");
            } catch (JSONException e) {
                schedule = "";
            }
            try {
                taketime = jsonObject_item.getString("taketime");
            } catch (JSONException e) {
                taketime = "";
            }
            try {
                theme = jsonObject_item.getString("theme");
            } catch (JSONException e) {
                theme = "";
            }

            subclass = new detailInfo_25(distance, infocentertourcourse, schedule, taketime, theme);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_25 parsing error");
        }
        return subclass;
    }
}

