package com.example.coronatravel.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.coronatravel.DbOpenHelper;
import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.Mask;
import com.example.coronatravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Detail_view extends AppCompatActivity {

    TextView testCommon, testInfo,testImage,testMask;
    CheckBox checkbox;
    DbOpenHelper mDbOpenHelper;
    String addr1, contentid,contenttypeid,firstimage,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        testCommon = (TextView) findViewById(R.id.testCommon);
        testInfo = (TextView) findViewById(R.id.testInfo);
        checkbox = (CheckBox)findViewById(R.id.checkbox);
        testMask =(TextView) findViewById(R.id.testMask);
        mDbOpenHelper = new DbOpenHelper(this);

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
            detail_I_14 = detail_I_14.JSONParsing(detailInfoUrl);
        }

        else if(contenttypeid.equals("15")) {
            detailInfo_15 detail_I_15 = new detailInfo_15();
            detail_I_15 = detail_I_15.JSONParsing(detailInfoUrl);
        }

        else if(contenttypeid.equals("15")) {
            detailInfo_15 detail_I_15 = new detailInfo_15();
            detail_I_15 = detail_I_15.JSONParsing(detailInfoUrl);
        }
        else if(contenttypeid.equals("25")) {
            detailInfo_25 detail_I_25 = new detailInfo_25();
            detail_I_25 = detail_I_25.JSONParsing(detailInfoUrl);
        }
        else if(contenttypeid.equals("28")) {
            detailInfo_28 detail_I_28 = new detailInfo_28();
            detail_I_28 = detail_I_28.JSONParsing(detailInfoUrl);
        }
        else if(contenttypeid.equals("32")) {
            detailInfo_32 detail_I_32 = new detailInfo_32();
            detail_I_32 = detail_I_32.JSONParsing(detailInfoUrl);
        }
        else if(contenttypeid.equals("38")) {
            detailInfo_38 detail_I_38 = new detailInfo_38();
            detail_I_38 = detail_I_38.JSONParsing(detailInfoUrl);
        }
        else if(contenttypeid.equals("39")) {
            detailInfo_39 detail_I_39 = new detailInfo_39();
            detail_I_39 = detail_I_39.JSONParsing(detailInfoUrl);
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


        String maskUrl = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/storesByGeo/json?" +
                "lat="+detail_C.getMapy()+"&" +
                "lng="+ detail_C.getMapx()+"&" +
                "m=20000";
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
    }

}
