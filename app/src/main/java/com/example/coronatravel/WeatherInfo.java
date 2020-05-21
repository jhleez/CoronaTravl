package com.example.coronatravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronatravel.detail.detailCommon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeatherInfo extends AppCompatActivity {
    TextView test;
    private RecyclerView weatherListview;
    private myWeatherAdapter weatherAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<weatherListViewItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        weatherListview = findViewById(R.id.weatherListview);

        getdata();
        init();
    }

    public void init() {

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        weatherListview.setLayoutManager(layoutManager);

        itemList = new ArrayList<>();

        for (int i = 0; i < MainActivity.ShortWeather_ArrayList.size(); i++) {
            String rainState = MainActivity.ShortWeather_ArrayList.get(i).getPTY();
            String skyState = MainActivity.ShortWeather_ArrayList.get(i).getSKY();
            String month = MainActivity.ShortWeather_ArrayList.get(i).getFcstDate().substring(4, 6);
            String date = MainActivity.ShortWeather_ArrayList.get(i).getFcstDate().substring(6);
            Drawable skyIcon = null;
            if (rainState.equals("1") || rainState.equals("4")) //Rain or shower
                skyIcon = ContextCompat.getDrawable(this, R.drawable.rainicon);
            else if (rainState.equals("2")) //Rain or Snow
                skyIcon = ContextCompat.getDrawable(this, R.drawable.rainsnowicon);
            else if (rainState.equals("3")) //Snow
                skyIcon = ContextCompat.getDrawable(this, R.drawable.snowicon);
                //else if(rainState.equals("4")) //Shower
                //skyIcon = ContextCompat.getDrawable(this,R.drawable.showericon);
            else if (rainState.equals("0")) { //nothing
                if (skyState.equals("1")) //sunny
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.sunnyicon);
                else if (skyState.equals("3")) //lots of cloud
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.lotsofcloudicon);
                else if (skyState.equals("4")) //cloudy
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.cloudyicon);
                else Toast.makeText(this, "skyState is " + skyState, Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(WeatherInfo.this, "rainState is " + rainState, Toast.LENGTH_SHORT).show();

            itemList.add(new weatherListViewItem(skyIcon, month + "월 " + date + "일",
                    MainActivity.ShortWeather_ArrayList.get(i).getTMN() + "/" + MainActivity.ShortWeather_ArrayList.get(i).getTMX()));
        }

        weatherAdapter = new myWeatherAdapter(this, itemList);
        weatherListview.setAdapter(weatherAdapter);

        myWeatherListviewDecoration decoration = new myWeatherListviewDecoration();
        weatherListview.addItemDecoration(decoration);
    }

    public void getdata() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

        String today = year + month + day;

        String pagenumber = "1";
        String ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=" + pagenumber +
                "&dataType=JSON" +
                "&base_date=" + today +
                "&base_time=0500" +
                "&nx=55&ny=127";


        String JSONFromShortWeatherURL = "a";
        try {
            JSONFromShortWeatherURL = new HttpReqTask().execute(ShortWeatherURL).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        String totalCount = ShortWeather.JSONParsing(JSONFromShortWeatherURL);
        int totalPage = (Integer.parseInt(totalCount) / 50) + 1;
        pagenumber = String.valueOf(Integer.parseInt(pagenumber) + 1);

        while (Integer.parseInt(pagenumber) <= totalPage) {
            ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                    "&numOfRows=50" +
                    "&pageNo=" + pagenumber +
                    "&dataType=JSON" +
                    "&base_date=" + today +
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
                "&pageNo=" + "1" +
                "&dataType=JSON" +
                "&base_date=" + today +
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
        String test1 = "";
        for (int i = 0; i < MainActivity.ShortWeather_ArrayList.size(); i++) {
            test1 = test1 + "날짜 : " + MainActivity.ShortWeather_ArrayList.get(i).getFcstDate()
                    + "\n 최저기온 : " + MainActivity.ShortWeather_ArrayList.get(i).getTMN() +
                    "\n 최저기온 : " + MainActivity.ShortWeather_ArrayList.get(i).getTMX() + "\n";

        }
    }


}
