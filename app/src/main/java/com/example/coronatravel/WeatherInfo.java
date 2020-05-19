package com.example.coronatravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.coronatravel.detail.detailCommon;

import java.util.ArrayList;

public class WeatherInfo extends AppCompatActivity {
    TextView test;
    private RecyclerView weatherListview;
    private myWeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);




        String pagenumber="1";
        String ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=" + pagenumber+
                "&dataType=JSON" +
                "&base_date=20200519" +
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
                    "&base_date=20200519" +
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


        String ShortWeatherURL2 = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=" +"1"+
                "&dataType=JSON" +
                "&base_date=20200519" +
                "&base_time=0200" +
                "&nx=55&ny=127";


        String JSONFromShortWeatherURL2 = "";
        try {
            JSONFromShortWeatherURL2 = new HttpReqTask().execute(ShortWeatherURL2).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        String a = ShortWeather.JSONParsing2(JSONFromShortWeatherURL2);
        MainActivity.ShortWeather_ArrayList.get(0).setTMN(a);
        String test1="";
        for(int i = 0; i< MainActivity.ShortWeather_ArrayList.size();i++){
            test1 = test1 + "날짜 : " +MainActivity.ShortWeather_ArrayList.get(i).getFcstDate()
                    +"\n 최저기온 : " +MainActivity.ShortWeather_ArrayList.get(i).getTMN()+
                    "\n 최저기온 : " + MainActivity.ShortWeather_ArrayList.get(i).getTMX()+"\n";
        }
        test.setText(test1);

       // init();
    }

    private void init() {
        weatherListview = findViewById(R.id.weatherListview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        weatherListview.setLayoutManager(layoutManager);


        ArrayList<weatherListViewItem> itemList = new ArrayList<>();

        weatherListViewItem e = new weatherListViewItem();
        e.setTemperature("5/20도");
        e.setDate("5월 18일");
        e.setIconDrawble(ContextCompat.getDrawable(this,R.drawable.ic_menu_camera));
        itemList.add(e);
        itemList.add(e);
        itemList.add(e);

        weatherAdapter = new myWeatherAdapter(this,itemList);
        weatherListview.setAdapter(weatherAdapter);

        myWeatherListviewDecoration decoration = new myWeatherListviewDecoration();
        weatherListview.addItemDecoration(decoration);
    }
}
