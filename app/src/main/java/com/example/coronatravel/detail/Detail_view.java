package com.example.coronatravel.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_view extends AppCompatActivity {

    TextView testCommon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        // 리스트뷰에서 넘길 때 contenttype , contentid를 같이 넘겨줘야되는데 일단 그냥 테스트케이스로 잡고했음
        String contentTypeId = "12";
        String ontentId = "127480";
        detailCommon detail_C;
        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                "ServiceKey=2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D" +
                "&contentTypeId=12&contentId=127480" +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
                "&_type=json";

        String JSONFromdetailCommonUrl = "AA";
        try {
            JSONFromdetailCommonUrl = new HttpReqTask().execute(detailCommonUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        testCommon = (TextView) findViewById(R.id.testCommon);
        detail_C = JSONParsing(JSONFromdetailCommonUrl);

        //모든 데이터가 다들어 있는게 아님 이 케이스의 경우 홈페이지, 전화번호, 전화번호 명이없음
//        testCommon.setText(
//                "텝1에 들어갈 공통정보" +
//                "\n이름 : "+ detail_C.getTitle()+
//                "\n주소 : "+detail_C.getAddr1()+
//                "\n우편번호 : "+detail_C.getZipcode()+
//                "\n개요 : "+detail_C.getOverview());
    }

    public detailCommon JSONParsing(String JSONFromLocationBasedListaddr) {
        detailCommon subclass=null;
        String addr1="",booktour ="", firstimage ="",mapx="",mapy="",mlevel="",overview="",title="",zipcode="",homepage="",telname="";
        String tel = "텔";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonObject_item = new JSONObject(item);

            addr1 = jsonObject_item.getString("addr1");
            booktour = jsonObject_item.getString("booktour");
            firstimage = jsonObject_item.getString("firstimage");
            mapx = jsonObject_item.getString("mapx");
            mapy = jsonObject_item.getString("mapy");
            mlevel = jsonObject_item.getString("mlevel");
            overview = jsonObject_item.getString("overview");
            title = jsonObject_item.getString("title");
            zipcode = jsonObject_item.getString("zipcode");
            testCommon.setText(tel);
            homepage = jsonObject_item.getString("homepage");
//            tel = jsonObject_item.getString("tel");
            testCommon.setText(tel);
//            telname = jsonObject_item.getString("telname");
            subclass = new detailCommon(addr1,booktour,firstimage,mapx,mapy,mlevel,overview,title,zipcode,homepage,tel,telname);
        } catch (JSONException e) {
            Log.d("TAG", "detailCommon parsing error");
        }
        return subclass;
    }
}