package com.example.coronatravel.detail;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronatravel.Adapter.MaskSwipeAdapter;
import com.example.coronatravel.DbOpenHelper;
import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.Mask;
import com.example.coronatravel.R;
import com.example.coronatravel.TypeId;
import com.example.coronatravel.myWeatherAdapter;
import com.example.coronatravel.myWeatherListviewDecoration;
import com.example.coronatravel.weatherListViewItem;

import java.util.ArrayList;

public class    Detail_view extends AppCompatActivity {

    TextView testCommon, testInfo,testImage,testMask;
    CheckBox checkbox;
    DbOpenHelper mDbOpenHelper;
    String addr1, contentid,contenttypeid,firstimage,title;
    ConstraintLayout weatehr_expandlayout,mask_expandlayout,corona_expandlayout;

    ViewPager viewPager_mask;
    MaskSwipeAdapter maskSwipeAdapter;

    private RecyclerView weatherListview;
    private myWeatherAdapter weatherAdapter;
    Button weatherexpendbt,maskexpandbt,coronaexpandbt;
    CardView weathercardview,maskcardview,coronacardview;
    LinearLayoutManager layoutManager;
    ArrayList<weatherListViewItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        testCommon = (TextView) findViewById(R.id.testCommon);
        testInfo = (TextView) findViewById(R.id.testInfo);
        checkbox = (CheckBox)findViewById(R.id.checkbox);
        testMask =(TextView) findViewById(R.id.testMask);
        mDbOpenHelper = new DbOpenHelper(this);

        weatehr_expandlayout =findViewById(R.id.expend_layout_weather);
        mask_expandlayout=findViewById(R.id.expand_layout_mask);
        corona_expandlayout=findViewById(R.id.expand_layout_corona);

        weatherexpendbt=findViewById(R.id.weather_expend_bt);
        maskexpandbt=findViewById(R.id.mask_expend_bt);
        coronaexpandbt=findViewById(R.id.corona_expend_bt);

        weathercardview=findViewById(R.id.weather_cardview);
        maskcardview=findViewById(R.id.mask_cardview);
        coronacardview=findViewById(R.id.corona_cardview);

        viewPager_mask=findViewById(R.id.mask_viewpager);

        weatherexpendbt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (weatehr_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(weathercardview, new AutoTransition());
                    weatehr_expandlayout.setVisibility(View.VISIBLE);
                    weatherexpendbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(weathercardview, new AutoTransition());
                    weatehr_expandlayout.setVisibility(View.GONE);
                    weatherexpendbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });
        maskexpandbt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mask_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(maskcardview, new AutoTransition());
                    mask_expandlayout.setVisibility(View.VISIBLE);
                    maskexpandbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(maskcardview, new AutoTransition());
                    mask_expandlayout.setVisibility(View.GONE);
                    maskexpandbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });
        coronaexpandbt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (corona_expandlayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(coronacardview, new AutoTransition());
                    corona_expandlayout.setVisibility(View.VISIBLE);
                    coronaexpandbt.setBackgroundResource(R.drawable.ic_expand_less_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(coronacardview, new AutoTransition());
                    corona_expandlayout.setVisibility(View.GONE);
                    coronaexpandbt.setBackgroundResource(R.drawable.ic_expand_more_black_24dp);
                }
            }
        });


        weatherListview = findViewById(R.id.weatherListview);
        init();

        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");
        contenttypeid = MainActivity.LocationBasedList_ArrayList.get(position).getContenttypeid();
        contentid = MainActivity.LocationBasedList_ArrayList.get(position).getContentid();
        addr1 = MainActivity.LocationBasedList_ArrayList.get(position).getAddr1();
        firstimage = MainActivity.LocationBasedList_ArrayList.get(position).getFirstimage();
        title=MainActivity.LocationBasedList_ArrayList.get(position).getTitle();



        String ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
        detailCommon detail_C = new detailCommon();
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
            Log.d("TAG", "jsonparsing error");
        }
        detail_C = detail_C.JSONParsing(JSONFromdetailCommonUrl);

       //체크박스 처리
        int i=0;
        Cursor iCursor = mDbOpenHelper.selectColumns(contentid);
        while (iCursor.moveToNext()) {
            i++;
        }
        if(i==0){
            checkbox.setChecked(false);
        }
        else
        {
            checkbox.setChecked(true);
        }


        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbOpenHelper.open();
                mDbOpenHelper.create();

                if(((CheckBox) v).isChecked()){
                    mDbOpenHelper.insertColumn(addr1,contentid,contenttypeid,firstimage,title);
                }
                else
                {
                    mDbOpenHelper.deleteColumn(contentid);
                }
            }
        });






        detail_C = detail_C.JSONParsing(JSONFromdetailCommonUrl);
        //모든 데이터가 다들어 있는게 아님 이 케이스의 경우 홈페이지, 전화번호, 전화번호 명이없음
        testCommon.setText(
                "텝1에 들어갈 공통정보" +
                        "\n이름 : " + detail_C.getTitle() +
                        "\n홈페이지 : " + detail_C.getHomepage() +
                        "\n주소 : " + detail_C.getAddr1() +
                        "\n우편번호 : " + detail_C.getZipcode()
        );
        TypeId.nxny(detail_C.getAreacode());

        String detailInfoUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?" +
                "ServiceKey="+ServiceKey+
                "&contentTypeId="+contenttypeid+
                "&contentId="+contentid +
                "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";

        String JSONFromdetailInfoURL = "";
        try {
            JSONFromdetailInfoURL = new HttpReqTask().execute(detailInfoUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        detailInfo_12 detail_I_12 = new detailInfo_12();
        if (contenttypeid.equals("12")) {
            detail_I_12 = detail_I_12.JSONParsing(JSONFromdetailInfoURL);
            testInfo.setText("\n\n탭 2에 들어갈 설명정보" +
                    "\n유모차대여 : " + detail_I_12.getChkbabycarriage() +
                    "\n애완동물동반 가능 : " + detail_I_12.getChkpet());
        }

        else if(contenttypeid.equals("14")) {
            detailInfo_14 detail_I_14 = new detailInfo_14();
            detail_I_14 = detail_I_14.JSONParsing(JSONFromdetailInfoURL);
        }

        else if(contenttypeid.equals("15")) {
            detailInfo_15 detail_I_15 = new detailInfo_15();
            detail_I_15 = detail_I_15.JSONParsing(JSONFromdetailInfoURL);
        }

        else if(contenttypeid.equals("25")) {
            Log.d("여행코스", "여행코스");
            detailInfo_25 detail_I_25 = new detailInfo_25();
            detail_I_25 = detail_I_25.JSONParsing(JSONFromdetailInfoURL);
            testInfo.setText("\n\n탭 2에 들어갈 설명정보" +
                    "\n총 거리 : " + detail_I_25.getDistance() +
                    "\n소요 시간 : " + detail_I_25.getTaketime());
        }
        else if(contenttypeid.equals("28")) {
            detailInfo_28 detail_I_28 = new detailInfo_28();
            detail_I_28 = detail_I_28.JSONParsing(JSONFromdetailInfoURL);
        }
        else if(contenttypeid.equals("32")) {
            detailInfo_32 detail_I_32 = new detailInfo_32();
            detail_I_32 = detail_I_32.JSONParsing(JSONFromdetailInfoURL);
        }
        else if(contenttypeid.equals("38")) {
            detailInfo_38 detail_I_38 = new detailInfo_38();
            detail_I_38 = detail_I_38.JSONParsing(JSONFromdetailInfoURL);
        }
        else if(contenttypeid.equals("39")) {
            detailInfo_39 detail_I_39 = new detailInfo_39();
            detail_I_39 = detail_I_39.JSONParsing(JSONFromdetailInfoURL);
        }


//        String detailImageUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?" +
//                "ServiceKey="+ServiceKey+
//                "&contentId="+contentId+
//                "&imageYN=N&MobileOS=AND&MobileApp=CoronaTravel&_type=json";

        String detailImageUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?" +
                "ServiceKey="+ServiceKey+
                "&contentTypeId=" +contenttypeid +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&contentId=" +contentid+
                "&imageYN=Y&_type=json";

        String JSONFromdetailImageUrl = "";
        String image;
        try {
            JSONFromdetailImageUrl = new HttpReqTask().execute(detailImageUrl).get();
        } catch (Exception e) {;
            Log.d("TAG", "jsonparsing error");
        }
        detailImage.JSONParsing(JSONFromdetailImageUrl);

        testImage = (TextView)findViewById(R.id.testImage);
        if(detailImage.Images.size() != 0) {
            testImage.setText("\n\n텝4에 들어갈 추가이미지 중 첫 번째: " + detailImage.Images.get(0));
        }

        String dist="10000";
        String maskUrl ="";
        maskUrl = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?" +
                "lat="+detail_C.getMapy()+"&" +
                "lng="+ detail_C.getMapx()+"&" +
                "m="+dist;
        String JSONFromTotalSearch = "";
        try {
            JSONFromTotalSearch = new HttpReqTask().execute(maskUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        Mask.JSONParsing(JSONFromTotalSearch);
        testMask.setText(String.valueOf(MainActivity.MASK_AraayList.size()));

        if(MainActivity.MASK_AraayList.size() == 0){
            testMask.setText("근방 2km 이내의 마스크 판매처 없음");
        }
        else {
            testMask.setText("\n\n근방 2km 이내의 마스크 판매처 중 1개\n이름 : " + MainActivity.MASK_AraayList.get(0).getName()
                    + "\n주소 : " + MainActivity.MASK_AraayList.get(0).getAddr()
                    + "\n재고 : " + MainActivity.MASK_AraayList.get(0).getRemain_stat());
        }

        maskSwipeAdapter = new MaskSwipeAdapter(getSupportFragmentManager(),MainActivity.MASK_AraayList);
        if(MainActivity.MASK_AraayList.size()!=0) viewPager_mask.setAdapter(maskSwipeAdapter);
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
                Toast.makeText(this, "rainState is " + rainState, Toast.LENGTH_SHORT).show();

            itemList.add(new weatherListViewItem(skyIcon, month + "월 " + date + "일",
                    MainActivity.ShortWeather_ArrayList.get(i).getTMN() + "/" + MainActivity.ShortWeather_ArrayList.get(i).getTMX()));
        }

        weatherAdapter = new myWeatherAdapter(this, itemList);
        weatherListview.setAdapter(weatherAdapter);

        myWeatherListviewDecoration decoration = new myWeatherListviewDecoration();
        weatherListview.addItemDecoration(decoration);
    }
}
