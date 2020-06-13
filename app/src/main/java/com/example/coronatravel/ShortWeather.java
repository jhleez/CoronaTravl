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
        TMN = "";
        this.fcstTime = "";
        this.fcstDate = "";
    }

    public ShortWeather(String POP, String PTY, String r06, String SKY, String TMN, String TMX, String fcstTime, String fcstDate) {
        this.POP = POP;
        this.PTY = PTY;
        R06 = r06;
        this.SKY = SKY;
        this.TMN = TMN;
        this.TMX = TMX;
        this.fcstTime = fcstTime;
        this.fcstDate = fcstDate;
    }

    private String POP; // 강수확률
    private String PTY; // 강수형태    없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)   여기서 비/눈은 비와 눈이 섞여 오는 것을 의미 (진눈개비)
    private String R06; // 6시간 강수량

//            0mm 또는 없음 0
//            1mm 미만 1
//            1~4mm	5
//            5~9mm 	10
//            10~19mm	20
//            20~39mm	40
//            40~69mm	70
//            70mm 이상	100

    private String SKY; // 하늘 상태 하늘상태(SKY) 코드 : 맑음(1), 구름많음(3), 흐림(4)
    private String TMN; // 최저 기온
    private String TMX; // 최고 기온
    private String fcstTime; // 조회 시간
    private String fcstDate; // 조회 날짜

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

    public String getTMN() {
        return TMN;
    }

    public void setTMN(String TMN) {
        this.TMN = TMN;
    }

    public String getTMX() {
        return TMX;
    }

    public void setTMX(String TMX) {
        this.TMX = TMX;
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
        String TMN="";
        String TMX="";
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
                if(!(time.equals("1500") || time.equals("0600"))){
                    continue;
                }
                if(!(category.equals("POP") ||category.equals("PTY") ||category.equals("R06") ||category.equals("SKY") ||category.equals("TMN") || category.equals("TMX"))){
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
                else if(subJsonObject.getString("category").equals("TMN")) {
                    TMN = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setTMN(TMN);
                }
                else if(subJsonObject.getString("category").equals("TMX")) {
                    TMX = subJsonObject.getString("fcstValue");
                    MainActivity.sub_shortweather.setTMX(TMX);
                    MainActivity.ShortWeather_ArrayList.add(MainActivity.sub_shortweather);
                    MainActivity.sub_shortweather = new ShortWeather();
                    if(MainActivity.ShortWeather_ArrayList.size() == 2){
                        return "";
                    }
                }
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        return totalCount;
    }

    public static String JSONParsing2(String JSONFromLocationBasedListaddr) {
        String TMN="A";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONArray jsonArray_item = new JSONArray(item);
            for (int i = 0; i < jsonArray_item.length(); i++) {
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                String time = subJsonObject.getString("fcstTime");
                String category = subJsonObject.getString("category");
                if(!(time.equals("1500") || time.equals("0600"))){
                    continue;
                }
                if(!(category.equals("POP") ||category.equals("PTY") ||category.equals("R06") ||category.equals("SKY") ||category.equals("TMN") || category.equals("TMX"))){
                    continue;
                }

                if(subJsonObject.getString("category").equals("TMN")) {
                    TMN = subJsonObject.getString("fcstValue");
                    return TMN;
                }
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        return TMN;
    }
}
