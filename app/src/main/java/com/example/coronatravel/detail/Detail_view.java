package com.example.coronatravel.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.coronatravel.HttpReqTask;
import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;
import com.example.coronatravel.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_view extends AppCompatActivity {

    TextView testCommon, testInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        testCommon = (TextView) findViewById(R.id.testCommon);
        testInfo = (TextView) findViewById(R.id.testInfo);
        // 리스트뷰에서 넘길 때 contenttype , contentid를 같이 넘겨줘야되는데 일단 그냥 테스트케이스로 잡고했음


        Intent intent = getIntent();
        int position = intent.getExtras().getInt("position");
        String contentTypeId = MainActivity.LocationBasedList_ArrayList.get(position).getContenttypeid();
        String contentId = MainActivity.LocationBasedList_ArrayList.get(position).getContentid();
        String ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
        detailCommon detail_C = new detailCommon();
        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                "ServiceKey=" + ServiceKey +
                "&contentTypeId=" + contentTypeId +
                "&contentId=" + contentId +
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
                "&contentTypeId="+contentTypeId+
                "&contentId="+contentId +
                "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";

        String JSONFromdetailInfoURL = "";
        try {
            JSONFromdetailInfoURL = new HttpReqTask().execute(detailInfoUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        detailInfo_12 detail_I_12 = new detailInfo_12();
        if (contentTypeId.equals("12")) {
            detail_I_12 = detail_I_12.JSONParsing(JSONFromdetailInfoURL);
            testInfo.setText("\n\n탭 2에 들어갈 설명정보" +
                    "\n유모차대여 : " + detail_I_12.getChkbabycarriage() +
                    "\n애완동물동반 가능 : " + detail_I_12.getChkpet());
        }


        else if(contentTypeId.equals("14")) {
            detailInfo_14 detail_I_14 = new detailInfo_14();
            detail_I_14 = detail_I_14.JSONParsing(detailInfoUrl);
        }
    }

}
