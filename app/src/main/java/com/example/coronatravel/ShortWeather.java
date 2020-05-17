package com.example.coronatravel;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShortWeather {
    ShortWeather(){
        this.POP = "";
        this.PTY = "";
        R06 = "";
        this.SKY = "";
        T3H = "";
        this.fcstTime = "";
        this.fcstDate = "";
    }
    public ShortWeather(String POP, String PTY, String r06, String SKY, String t3H, String fcstTime, String fcstDate) {
        this.POP = POP;
        this.PTY = PTY;
        R06 = r06;
        this.SKY = SKY;
        T3H = t3H;
        this.fcstTime = fcstTime;
        this.fcstDate = fcstDate;
    }

    private String POP;
    private String PTY;
    private String R06;
    private String SKY;
    private String T3H;
    private String fcstTime;
    private String fcstDate;

    public String getPOP() {
        return POP;
    }

    public void setPOP(String POP) {
        this.POP = POP;
    }

    public String getPTY() {
        return PTY;
    }

    public void setPTY(String PTY) {
        this.PTY = PTY;
    }

    public String getR06() {
        return R06;
    }

    public void setR06(String r06) {
        R06 = r06;
    }

    public String getSKY() {
        return SKY;
    }

    public void setSKY(String SKY) {
        this.SKY = SKY;
    }

    public String getT3H() {
        return T3H;
    }

    public void setT3H(String t3H) {
        T3H = t3H;
    }

    public String getFcstTime() {
        return fcstTime;
    }

    public void setFcstTime(String fcstTime) {
        this.fcstTime = fcstTime;
    }

    public String getFcstDate() {
        return fcstDate;
    }

    public void setFcstDate(String fcstDate) {
        this.fcstDate = fcstDate;
    }

    public static String JSONParsing(String JSONFromLocationBasedListaddr) {
        String totalCount ="";
        String fcstTime="";
        String fcstDate="";
        String POP="";
        String PTY="";
        String R06="";
        String SKY="";
        String T3H="";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalCount = jsonObject_body.getString("totalCount");

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONArray jsonArray_item = new JSONArray(item);
            for (int i = 0; i < jsonArray_item.length(); i++) {
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                String time = subJsonObject.getString("fcstTime");
                String category = subJsonObject.getString("category");
                if(!(time.equals("0900") || time.equals("1200") || time.equals("1500") || time.equals("1800") || time.equals("2100"))){
                    continue;
                }
                if(!(category.equals("POP") ||category.equals("PTY") ||category.equals("R06") ||category.equals("SKY") ||category.equals("T3H"))){
                    continue;
                }

                if(subJsonObject.getString("category").equals("POP")){
                    POP = subJsonObject.getString("fcstValue");
                    fcstTime = subJsonObject.getString("fcstTime");
                    fcstDate = subJsonObject.getString("fcstDate");
                    MainActivity.sub_shortweather.setPOP(POP);
                    MainActivity.sub_shortweather.setFcstTime(fcstTime);
                    MainActivity.sub_shortweather.setFcstDate(fcstDate);
                }
                else if(subJsonObject.getString("category").equals("PTY")) {
                    PTY = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setPTY(PTY);
                }
                else if(subJsonObject.getString("category").equals("R06")) {
                    R06 = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setR06(R06);
                }
                else if(subJsonObject.getString("category").equals("SKY")) {
                    SKY = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setSKY(SKY);
                }
                else if(subJsonObject.getString("category").equals("T3H")) {
                    T3H = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setT3H(T3H);
                    MainActivity.ShortWeather_ArrayList.add(MainActivity.sub_shortweather);
                    MainActivity.sub_shortweather = new ShortWeather();
                }

            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        return totalCount;
    }
}
