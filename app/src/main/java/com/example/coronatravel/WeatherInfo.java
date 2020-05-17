package com.example.coronatravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.coronatravel.detail.detailCommon;

public class WeatherInfo extends AppCompatActivity {
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        String pagenumber="1";
        String ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=" + pagenumber+
                "&dataType=JSON" +
                "&base_date=20200517" +
                "&base_time=0500" +
                "&nx=55&ny=127";


        String JSONFromShortWeatherURL = "a";
        try {
            JSONFromShortWeatherURL = new HttpReqTask().execute(ShortWeatherURL).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        test = (TextView)findViewById(R.id.test);

        String totalCount = ShortWeather.JSONParsing(JSONFromShortWeatherURL);
        int totalPage = (Integer.parseInt(totalCount) / 50) + 1;
        pagenumber = String.valueOf(Integer.parseInt(pagenumber) + 1);

        while (Integer.parseInt(pagenumber) <= totalPage){
            ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                    "&numOfRows=50" +
                    "&pageNo=" + pagenumber+
                    "&dataType=JSON" +
                    "&base_date=20200517" +
                    "&base_time=0500" +
                    "&nx=55&ny=127";
            try {
                JSONFromShortWeatherURL = new HttpReqTask().execute(ShortWeatherURL).get();
            } catch (Exception e) {
                Log.d("TAG", "jsonparsing error");
            }
            ShortWeather.JSONParsing(JSONFromShortWeatherURL);
            pagenumber = String.valueOf(Integer.parseInt(pagenumber) + 1);
        }

        test.setText(MainActivity.ShortWeather_ArrayList.get(8).getFcstTime());
    }
}
