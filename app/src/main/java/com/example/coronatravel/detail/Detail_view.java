package com.example.coronatravel.detail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronatravel.Adapter.MaskSwipeAdapter;
import com.example.coronatravel.DbOpenHelper;
import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.Mask;
import com.example.coronatravel.R;
import com.example.coronatravel.ShortWeather;
import com.example.coronatravel.TypeId;
import com.example.coronatravel.detail.fragment.Data;
import com.example.coronatravel.detail.fragment.Detail_First_Fragment;
import com.example.coronatravel.detail.fragment.Detail_Fourth_Fragment;
import com.example.coronatravel.detail.fragment.Detail_Second_Fragment;
import com.example.coronatravel.detail.fragment.Detail_Third_Fragment;
import com.example.coronatravel.myWeatherAdapter;
import com.example.coronatravel.myWeatherListviewDecoration;
import com.example.coronatravel.weatherListViewItem;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Detail_view extends AppCompatActivity {

    //TextView testCommon, testInfo,testImage,testMask;
    CheckBox checkbox;
    DbOpenHelper mDbOpenHelper;
    String addr1, contentid, contenttypeid, firstimage, title;
    ConstraintLayout weatehr_expandlayout, mask_expandlayout, corona_expandlayout;
    FrameLayout frameLayout;
    Detail_First_Fragment detail_first_fragment ;
    Detail_Second_Fragment detail_second_fragment = new Detail_Second_Fragment();
    Detail_Third_Fragment detail_third_fragment = new Detail_Third_Fragment();
    Detail_Fourth_Fragment detail_fourth_fragment = new Detail_Fourth_Fragment();
    ChipNavigationBar chipNavigationBar;
    String ServiceKey;

    ViewPager viewPager_mask;
    MaskSwipeAdapter maskSwipeAdapter;

    private RecyclerView weatherListview;
    private myWeatherAdapter weatherAdapter;
    Button weatherexpendbt, maskexpandbt, coronaexpandbt;
    CardView weathercardview, maskcardview, coronacardview;
    LinearLayoutManager layoutManager;
    ArrayList<weatherListViewItem> itemList;


    private int addressCode = 0, addressIndex;
    private String cityName = null;
    private TextView totalP, citynameP, plusP, dischargedP, curedP, deathP;
    public detailCommon detail_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        totalP = (TextView) findViewById(R.id.AddressTotalTextview);
        citynameP = (TextView) findViewById(R.id.AddressCitynameTextview);
        plusP = (TextView) findViewById(R.id.AddressPlusTextview);
        dischargedP = (TextView) findViewById(R.id.AddressDischarged);
        curedP = (TextView) findViewById(R.id.AddressCured);
        deathP = (TextView) findViewById(R.id.AddressDeath);
        detailRepeat.repeat_array.clear();
        detailRepeat_32.repeat_array.clear();;
        detailRepeat_25.repeat_array.clear();
        detailImage.Images.clear();
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");
        contenttypeid = MainActivity.LocationBasedList_ArrayList.get(position).getContenttypeid();
        contentid = MainActivity.LocationBasedList_ArrayList.get(position).getContentid();
        addr1 = MainActivity.LocationBasedList_ArrayList.get(position).getAddr1();
        firstimage = MainActivity.LocationBasedList_ArrayList.get(position).getFirstimage();
        title = MainActivity.LocationBasedList_ArrayList.get(position).getTitle();
        ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";

//        testCommon = (TextView) findViewById(R.id.testCommon);
//        testInfo = (TextView) findViewById(R.id.testInfo);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
//        testMask =(TextView) findViewById(R.id.testMask);
        mDbOpenHelper = new DbOpenHelper(this);

        weatehr_expandlayout = findViewById(R.id.expend_layout_weather);
        mask_expandlayout = findViewById(R.id.expand_layout_mask);
        corona_expandlayout = findViewById(R.id.expand_layout_corona);

        weatherexpendbt = findViewById(R.id.weather_expend_bt);
        maskexpandbt = findViewById(R.id.mask_expend_bt);
        coronaexpandbt = findViewById(R.id.corona_expend_bt);

        weathercardview = findViewById(R.id.weather_cardview);
        maskcardview = findViewById(R.id.mask_cardview);
        coronacardview = findViewById(R.id.corona_cardview);

        viewPager_mask = findViewById(R.id.mask_viewpager);
        frameLayout = findViewById(R.id.detail_framlayout);

        final int firstnavigationclick[] ={0,0,0,0};
        chipNavigationBar = findViewById(R.id.chipnavigation);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (i == R.id.first_menu) {
                    fragmentTransaction.replace(R.id.detail_framlayout, detail_first_fragment);
                    fragmentTransaction.commit();
                }

                else if (i == R.id.second_menu) {
                    if(firstnavigationclick[1] == 0){

                        String detailInfoUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?" +
                                "ServiceKey=" + ServiceKey +
                                "&contentTypeId=" + contenttypeid +
                                "&contentId=" + contentid +
                                "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";

                        String JSONFromdetailInfoURL = "";
                        try {
                            JSONFromdetailInfoURL = new HttpReqTask().execute(detailInfoUrl).get();
                        } catch (Exception e) {
                        }

                        detailInfo_12 detail_I_12 = new detailInfo_12();
                        detailInfo_14 detail_I_14 = new detailInfo_14();
                        detailInfo_15 detail_I_15 = new detailInfo_15();
                        detailInfo_25 detail_I_25 = new detailInfo_25();
                        detailInfo_28 detail_I_28 = new detailInfo_28();
                        detailInfo_32 detail_I_32 = new detailInfo_32();
                        detailInfo_38 detail_I_38 = new detailInfo_38();
                        detailInfo_39 detail_I_39 = new detailInfo_39();
                        Data.contentid = contentid;
                        Data.contenttypeid = contenttypeid;

                        if (contenttypeid.equals("12")) {
                            detail_I_12 = detail_I_12.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("14")) {
                            detail_I_14 = detail_I_14.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("15")) {
                            detail_I_15 = detail_I_15.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("25")) {
                            detail_I_25 = detail_I_25.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("28")) {
                            detail_I_28 = detail_I_28.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("32")) {
                            detail_I_32 = detail_I_32.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("38")) {
                            detail_I_38 = detail_I_38.JSONParsing(JSONFromdetailInfoURL);
                        } else if (contenttypeid.equals("39")) {
                            detail_I_39 = detail_I_39.JSONParsing(JSONFromdetailInfoURL);
                        }

                        Data. detail_I_12 = detail_I_12;
                        Data. detail_I_14 = detail_I_14;
                        Data. detail_I_15 = detail_I_15;
                        Data. detail_I_25 = detail_I_25;
                        Data. detail_I_28 = detail_I_28;
                        Data. detail_I_32 = detail_I_32;
                        Data. detail_I_38 = detail_I_38;
                        Data. detail_I_39 = detail_I_39;

                        firstnavigationclick[1]++;
                    }

                    fragmentTransaction.replace(R.id.detail_framlayout, detail_second_fragment);
                    fragmentTransaction.commit();
                }

                else if (i == R.id.third_menu) {

                    String detailRepeatURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?" +
                            "ServiceKey="+ServiceKey+
                            "&contentTypeId="+contenttypeid+
                            "&contentId="+contentid+
                            "&MobileOS=AND&MobileApp=CoronaTravel&listYN=Y&_type=json";
                    String JSONFromdetailRepeat = "";
                    try {
                        JSONFromdetailRepeat = new HttpReqTask().execute(detailRepeatURL).get();
                    } catch (Exception e) {
                        ;
                    }
                    if(contenttypeid.equals("25")){
                        detailRepeat_25.JSONParsing(JSONFromdetailRepeat);
                    }
                    else if(contenttypeid.equals("32")){
                        detailRepeat_32.JSONParsing(JSONFromdetailRepeat);
                    }
                    else{
                        detailRepeat.JSONParsing(JSONFromdetailRepeat);
                    }
                    fragmentTransaction.replace(R.id.detail_framlayout, detail_third_fragment);
                    fragmentTransaction.commit();
                }

                else if (i == R.id.fourth_menu) {
                    Log.d("click",String.valueOf(firstnavigationclick[3]));
                    if(firstnavigationclick[3] == 0) {
                        String detailImageUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?" +
                                "ServiceKey=" + ServiceKey +
                                "&contentTypeId=" + contenttypeid +
                                "&MobileOS=AND&MobileApp=CoronaTravel" +
                                "&contentId=" + contentid +
                                "&imageYN=Y&_type=json";
                        String JSONFromdetailImageUrl = "";
                        String image;
                        try {
                            JSONFromdetailImageUrl = new HttpReqTask().execute(detailImageUrl).get();
                        } catch (Exception e) {
                            ;
                        }
                        detailImage.JSONParsing(JSONFromdetailImageUrl);
                        firstnavigationclick[3]++;
                    }

                    fragmentTransaction.replace(R.id.detail_framlayout, detail_fourth_fragment);
                    fragmentTransaction.commit();
                }
            }
        });


        weatherexpendbt.setOnClickListener(new View.OnClickListener() {
            int firstclick=0;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (weatehr_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(weathercardview, new AutoTransition());
                    weatehr_expandlayout.setVisibility(View.VISIBLE);
                    weatherexpendbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                    if(firstclick == 0){
                        init(addressCode);
                        firstclick++;
                    }


                } else {
                    TransitionManager.beginDelayedTransition(weathercardview, new AutoTransition());
                    weatehr_expandlayout.setVisibility(View.GONE);
                    weatherexpendbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });
        maskexpandbt.setOnClickListener(new View.OnClickListener() {
            int firstclick=0;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mask_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(maskcardview, new AutoTransition());
                    mask_expandlayout.setVisibility(View.VISIBLE);
                    maskexpandbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                   if(firstclick == 0) {
                       String dist = "10000";
                       String maskUrl = "";
                       maskUrl = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?" +
                               "lat=" + detail_C.getMapy() + "&" +
                               "lng=" + detail_C.getMapx() + "&" +
                               "m=" + dist;
                       String JSONFromTotalSearch = "";
                       try {
                           JSONFromTotalSearch = new HttpReqTask().execute(maskUrl).get();
                       } catch (Exception e) {
                       }
                       Mask.JSONParsing(JSONFromTotalSearch);
                       maskSwipeAdapter = new MaskSwipeAdapter(getSupportFragmentManager(), MainActivity.MASK_AraayList);
                       if (MainActivity.MASK_AraayList.size() != 0)
                           viewPager_mask.setAdapter(maskSwipeAdapter);
                       firstclick++;
                   }
                } else {
                    TransitionManager.beginDelayedTransition(maskcardview, new AutoTransition());
                    mask_expandlayout.setVisibility(View.GONE);
                    maskexpandbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });
        coronaexpandbt.setOnClickListener(new View.OnClickListener() {
            int firstclick;
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (corona_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(coronacardview, new AutoTransition());
                    corona_expandlayout.setVisibility(View.VISIBLE);
                    coronaexpandbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                    if(firstclick == 0){
                        init2();
                        firstclick++;
                    }

                } else {
                    TransitionManager.beginDelayedTransition(coronacardview, new AutoTransition());
                    corona_expandlayout.setVisibility(View.GONE);
                    coronaexpandbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });



        detail_C = new detailCommon();
        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                "ServiceKey=" + ServiceKey +
                "&contentTypeId=" + contenttypeid +
                "&contentId=" + contentid +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
                "&_type=json";

        String JSONFromdetailCommonUrl = "";
        try {
            JSONFromdetailCommonUrl = new HttpReqTask().execute(detailCommonUrl).get();
        } catch (Exception e) {
        }


        //체크박스 처리
        int i = 0;
        Cursor iCursor = mDbOpenHelper.selectColumns(contentid);
        while (iCursor.moveToNext()) {
            i++;
        }
        if (i == 0) {
            checkbox.setChecked(false);
        } else {
            checkbox.setChecked(true);
        }


        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbOpenHelper.open();
                mDbOpenHelper.create();

                if (((CheckBox) v).isChecked()) {
                    mDbOpenHelper.insertColumn(addr1, contentid, contenttypeid, firstimage, title);
                } else {
                    mDbOpenHelper.deleteColumn(contentid);
                }
            }
        });


        detail_C = detail_C.JSONParsing(JSONFromdetailCommonUrl);
        addressCode = Integer.parseInt(detail_C.getAreacode()); // 여기
        Data.detail_C = detail_C;
        detail_first_fragment=new Detail_First_Fragment();

        weatherListview = findViewById(R.id.weatherListview);
        //init(addressCode);
        if (addressCode == 1) {
            cityName = "서울특별시";
            addressIndex = 1;
        } else if (addressCode == 2) {
            cityName = "인천광역시";
            addressIndex = 4;
        } else if (addressCode == 3) {
            cityName = "대전광역시";
            addressIndex = 6;
        } else if (addressCode == 4) {
            cityName = "대구광역시";
            addressIndex = 3;
        } else if (addressCode == 5) {
            cityName = "광주광역시";
            addressIndex = 5;
        } else if (addressCode == 6) {
            cityName = "부산광역시";
            addressIndex = 2;
        } else if (addressCode == 7) {
            cityName = "울산광역시";
            addressIndex = 7;
        } else if (addressCode == 8) {
            cityName = "세종특별자치시";
            addressIndex = 8;
        } else if (addressCode == 31) {
            cityName = "경기도";
            addressIndex = 9;
        } else if (addressCode == 32) {
            cityName = "강원도";
            addressIndex = 10;
        } else if (addressCode == 33) {
            cityName = "충청북도";
            addressIndex = 11;
        } else if (addressCode == 34) {
            cityName = "충청남도";
            addressIndex = 12;
        } else if (addressCode == 35) {
            cityName = "경상북도";
            addressIndex = 15;
        } else if (addressCode == 36) {
            cityName = "경상남도";
            addressIndex = 16;
        } else if (addressCode == 37) {
            cityName = "전라북도";
            addressIndex = 13;
        } else if (addressCode == 38) {
            cityName = "전라남도";
            addressIndex = 14;
        } else if (addressCode == 39) {
            cityName = "제주특별자치도";
            addressIndex = 17;
        }
        citynameP.setText(cityName);
        //init2();

//        String detailInfoUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?" +
//                "ServiceKey=" + ServiceKey +
//                "&contentTypeId=" + contenttypeid +
//                "&contentId=" + contentid +
//                "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
//
//        String JSONFromdetailInfoURL = "";
//        try {
//            JSONFromdetailInfoURL = new HttpReqTask().execute(detailInfoUrl).get();
//        } catch (Exception e) {
//        }
//
//        detailInfo_12 detail_I_12 = new detailInfo_12();
//        detailInfo_14 detail_I_14 = new detailInfo_14();
//        detailInfo_15 detail_I_15 = new detailInfo_15();
//        detailInfo_25 detail_I_25 = new detailInfo_25();
//        detailInfo_28 detail_I_28 = new detailInfo_28();
//        detailInfo_32 detail_I_32 = new detailInfo_32();
//        detailInfo_38 detail_I_38 = new detailInfo_38();
//        detailInfo_39 detail_I_39 = new detailInfo_39();
//        Data.contentid = contentid;
//        Data.contenttypeid = contenttypeid;
//
//        if (contenttypeid.equals("12")) {
//            detail_I_12 = detail_I_12.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("14")) {
//            detail_I_14 = detail_I_14.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("15")) {
//            detail_I_15 = detail_I_15.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("25")) {
//            detail_I_25 = detail_I_25.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("28")) {
//            detail_I_28 = detail_I_28.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("32")) {
//            detail_I_32 = detail_I_32.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("38")) {
//            detail_I_38 = detail_I_38.JSONParsing(JSONFromdetailInfoURL);
//        } else if (contenttypeid.equals("39")) {
//            detail_I_39 = detail_I_39.JSONParsing(JSONFromdetailInfoURL);
//        }
//
//        Data. detail_I_12 = detail_I_12;
//        Data. detail_I_14 = detail_I_14;
//        Data. detail_I_15 = detail_I_15;
//        Data. detail_I_25 = detail_I_25;
//        Data. detail_I_28 = detail_I_28;
//        Data. detail_I_32 = detail_I_32;
//        Data. detail_I_38 = detail_I_38;
//        Data. detail_I_39 = detail_I_39;
//
//        String detailRepeatURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?" +
//                "ServiceKey="+ServiceKey+
//                "&contentTypeId="+contenttypeid+
//                "&contentId="+contentid+
//                "&MobileOS=AND&MobileApp=CoronaTravel&listYN=Y&_type=json";
//        String JSONFromdetailRepeat = "";
//        try {
//            JSONFromdetailRepeat = new HttpReqTask().execute(detailRepeatURL).get();
//        } catch (Exception e) {
//            ;
//        }
//        if(contenttypeid.equals("25")){
//            detailRepeat_25.JSONParsing(JSONFromdetailRepeat);
//        }
//        else if(contenttypeid.equals("32")){
//            detailRepeat_32.JSONParsing(JSONFromdetailRepeat);
//        }
//        else{
//            detailRepeat.JSONParsing(JSONFromdetailRepeat);
//        }


//        String detailImageUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?" +
//                "ServiceKey=" + ServiceKey +
//                "&contentTypeId=" + contenttypeid +
//                "&MobileOS=AND&MobileApp=CoronaTravel" +
//                "&contentId=" + contentid +
//                "&imageYN=Y&_type=json";
//        String JSONFromdetailImageUrl = "";
//        String image;
//        try {
//            JSONFromdetailImageUrl = new HttpReqTask().execute(detailImageUrl).get();
//        } catch (Exception e) {
//            ;
//        }
//        detailImage.JSONParsing(JSONFromdetailImageUrl);




//        String dist = "10000";
//        String maskUrl = "";
//        maskUrl = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?" +
//                "lat=" + detail_C.getMapy() + "&" +
//                "lng=" + detail_C.getMapx() + "&" +
//                "m=" + dist;
//        String JSONFromTotalSearch = "";
//        try {
//            JSONFromTotalSearch = new HttpReqTask().execute(maskUrl).get();
//        } catch (Exception e) {
//            Log.d("TAG", "jsonparsing error");
//        }
//        Mask.JSONParsing(JSONFromTotalSearch);
//
//        maskSwipeAdapter = new MaskSwipeAdapter(getSupportFragmentManager(), MainActivity.MASK_AraayList);
//        if (MainActivity.MASK_AraayList.size() != 0) viewPager_mask.setAdapter(maskSwipeAdapter);
    }
    public void init2() {
        String pathAddress = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13&ncvContSeq=&contSeq=&board_id=&gubun=";

        new getAddressDiagnosis().execute(pathAddress);
        new getAddressDischarged().execute(pathAddress);
        new getAddressCured().execute(pathAddress);
        new getAddressDeath().execute(pathAddress);
        new getAddressPlus().execute(pathAddress);
    }

    public void init(int addressCode) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);
        String month1 = monthFormat.format(currentTime);
        String day = dayFormat.format(currentTime);

        String today = year + month1 + day;

        String pagenumber = "1";
        String ShortWeatherURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst?serviceKey=LuQHzrmd0D8xz9tDN8srTETgDoVfSeUV%2FAvFrhKX%2BtTdNMG7GJINi%2B6INCB7yMFJXXIO%2FKb7JfNeFdA%2BNmEIqA%3D%3D" +
                "&numOfRows=50" +
                "&pageNo=" + pagenumber +
                "&dataType=JSON" +
                "&base_date=" + today +
                "&base_time=0500&" + TypeId.nxny(String.valueOf(addressCode));
        //"&nx=55&ny=127";

        Log.d("TAG", ShortWeatherURL);

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
                    "&base_time=0500&" + TypeId.nxny(String.valueOf(addressCode));
            //"&nx=55&ny=127";
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
                "&base_time=0200&" + TypeId.nxny(String.valueOf(addressCode));
        //"&nx=55&ny=127";


        String JSONFromShortWeatherURL2 = "";
        try {
            JSONFromShortWeatherURL2 = new HttpReqTask().execute(ShortWeatherURL2).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        String a = ShortWeather.JSONParsing2(JSONFromShortWeatherURL2);
        MainActivity.ShortWeather_ArrayList.get(0).setTMN(a);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        weatherListview.setLayoutManager(layoutManager);

        itemList = new ArrayList<>();

//        Toast.makeText(Detail_view.this,"size is " + MainActivity.ShortWeather_ArrayList.size(), Toast.LENGTH_SHORT).show();

        for (int i = 0; i < 3; i++) {
            final String rainState = MainActivity.ShortWeather_ArrayList.get(i).getPTY();
            final String skyState = MainActivity.ShortWeather_ArrayList.get(i).getSKY();
            final String month = MainActivity.ShortWeather_ArrayList.get(i).getFcstDate().substring(4, 6);
            final String date = MainActivity.ShortWeather_ArrayList.get(i).getFcstDate().substring(6);
            String weatherString = null;
            Drawable skyIcon = null;
            if (rainState.equals("1") || rainState.equals("4")) { //Rain or shower
                skyIcon = ContextCompat.getDrawable(this, R.drawable.rainicon);
                weatherString = "비";
            }
            else if (rainState.equals("2")) { //Rain or Snow
                skyIcon = ContextCompat.getDrawable(this, R.drawable.rainsnowicon);
                weatherString = "눈/비";
            }
            else if (rainState.equals("3")) { //Snow
                skyIcon = ContextCompat.getDrawable(this, R.drawable.snowicon);
                weatherString = "눈";
            }
            else if (rainState.equals("0")) { //nothing
                if (skyState.equals("1")) { //sunny
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.sunnyicon);
                    weatherString = "맑음";
                }
                else if (skyState.equals("3")) { //lots of cloud
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.lotsofcloudicon);
                    weatherString = "구름 많음";
                }
                else if (skyState.equals("4")) { //cloudy
                    skyIcon = ContextCompat.getDrawable(this, R.drawable.cloudyicon);
                    weatherString = "흐림";
                }
            } else
                Toast.makeText(this, "rainState is " + rainState, Toast.LENGTH_SHORT).show();

            itemList.add(new weatherListViewItem(skyIcon, month + "월 " + date + "일",
                    weatherString, MainActivity.ShortWeather_ArrayList.get(i).getTMN() + "/" + MainActivity.ShortWeather_ArrayList.get(i).getTMX()));
        }

        weatherAdapter = new myWeatherAdapter(this, itemList);
        weatherListview.setAdapter(weatherAdapter);

        myWeatherListviewDecoration decoration = new myWeatherListviewDecoration();
        weatherListview.addItemDecoration(decoration);
    }

    private class getAddressDischarged extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.data_table").select("td.number");
                String now = elements.get(addressIndex * 8 + 4).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            dischargedP.setText(result);
        }
    }

    private class getAddressCured extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.data_table").select("td.number");
                String now = elements.get(addressIndex * 8 + 5).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            curedP.setText(result);
        }
    }

    private class getAddressDeath extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.data_table").select("td.number");
                String now = elements.get(addressIndex * 8 + 6).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            deathP.setText(result);
        }
    }

    private class getAddressDiagnosis extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.data_table").select("td.number");
                String now = elements.get(addressIndex * 8 + 3).text();
                return now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            totalP.setText(result);
        }
    }

    private class getAddressPlus extends AsyncTask<String, Void, String> {
        // String 으로 값을 전달받은 값을 처리하고, Boolean 으로 doInBackground 결과를 넘겨준다.
        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0].toString()).get(); // Web에서 내용을 가져온다.
                Elements elements = document.select("div.data_table").select("td.number");
                String now = elements.get(addressIndex * 8).text();
                return "+" + now;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            plusP.setText(result);
        }
    }
}