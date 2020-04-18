package com.example.coronatravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class LocationBasedList extends AppCompatActivity {
    TextView url;
    String totalcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_based_list);

        String id = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
      //임의의 샘플데이터를 그냥 대충 설정해놨음
        String contentTypeId = "12"; //컨텐츠 타입
        String mapX = "126.981611"; // 경도 소숫점자리 무조건 이렇게 맞춰야함
        String mapY = "37.568477"; //위도 소숫점자리 무조건 이렇게 맞춰야함
        String radius = "1000"; // 반경 m단위
        String arrange = "A"; //정렬 (A=제목순, B=조회순,C=수정일순, D=생성일순, E=거리순)


        String LocationBasedListaddr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=" + id +
                                        "&contentTypeId=" + contentTypeId +
                                        "&mapX=" + mapX +
                                        "&mapY=" + mapY +
                                        "&radius=" + radius +
                                        "&listYN=Y" +
                                        "&arrange=" + arrange +
                                        "&numOfRows=5&pageNo=1"+ //한 페이지 몇개씩 가져올껀지, 뒤에는 페이지 수
                                        "&MobileOS=AND&MobileApp=AppTest&_type=json";

        String JSONFromLocationBasedListaddr = "";
        try {
            JSONFromLocationBasedListaddr = new HttpReqTask().execute(LocationBasedListaddr).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        url = (TextView)findViewById(R.id.url);
        MainActivity.LocationBasedList_ArrayList.clear();
        JSONParsing( JSONFromLocationBasedListaddr);

        //DB의 첫번째 데이터 하나 textview에 띄우기
        LocationBasedList_Class asd = MainActivity.LocationBasedList_ArrayList.get(1);
        url.setText("조회된 관광지 개수 : " + totalcount +"\n\n" +
                    "첫번째 데이터의 정보\n" +
                    "주소 :"+ asd.getAddr1() +"\n"+
                    "컨텐츠 id : " +asd.getContentid() +"\n"+
                    "컨텐츠 타입 : "+asd.getContenttypeid() + "\n"+
                    "내 위치로부터의 거리 : "+asd.getDist() + "\n"+
                    "제목 : " + asd.getTitle() + "\n" +
                    "썸네일 주소 : " + asd.getFirstimage() +"\n\n"+
                    "Arraylist 속의 데이터 개수 " + String.valueOf(MainActivity.LocationBasedList_ArrayList.size()));
    }


    public void JSONParsing(String JSONFromLocationBasedListaddr) {
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String  response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONArray jsonArray_item = new JSONArray(item);
            for(int i=0;i<jsonArray_item.length();i++){
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                String addr1 = subJsonObject.getString("addr1");
                String contentid =subJsonObject.getString("contentid");
                String contenttypeid = subJsonObject.getString("contenttypeid");
                String dist = subJsonObject.getString("dist");
                String firstimage = subJsonObject.getString("firstimage");
                String title = subJsonObject.getString("title");
                LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,dist,firstimage,title);
                MainActivity.LocationBasedList_ArrayList.add(subclass);
            }
        } catch (JSONException e) {
            Log.d("TAG","parsing error");
        }
    }
}
