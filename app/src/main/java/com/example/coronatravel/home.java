package com.example.coronatravel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.coronatravel.detail.detailCommon;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class home extends AppCompatActivity {

    String startdate = "", enddate = "";
    TextView test;
    ImageView firstImage;
    String totalcount;
    Button change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        test = (TextView) findViewById(R.id.test);
        firstImage = (ImageView) findViewById(R.id.firstImage);

        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        startdate = df.format(cal.getTime());
        cal.add(Calendar.DATE, 14);
        enddate = df.format(cal.getTime());


        String ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
        String RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                "ServiceKey=" + ServiceKey +
                "&eventStartDate=" + startdate +
                "&eventEndDate=" + enddate +
                "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange=O&numOfRows=1&pageNo=1&_type=json";

        String JSONFromRandomFestivalUrl = "";
        try {
            JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }
        totalcount = ShortJSONParsing(JSONFromRandomFestivalUrl);
        Random rnd = new Random();
        String randomnum = String.valueOf(rnd.nextInt((Integer.parseInt(totalcount) / 10) * 8));
        int randomarrange =rnd.nextInt(4);
        String arrange[] = {"O","P","Q","R"};
        RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                "ServiceKey=" + ServiceKey +
                "&eventStartDate=" + startdate +
                "&eventEndDate=" + enddate +
                "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&arrange=" + arrange[randomarrange] +
                "&numOfRows=1&pageNo=" + randomnum + "&_type=json";


        try {
            JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }


        Festival festival = JSONParsing(JSONFromRandomFestivalUrl);

        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                "ServiceKey=" + ServiceKey +
                "&contentTypeId=" + festival.getContenttypeid() +
                "&contentId=" + festival.getContentid() +
                "&MobileOS=AND&MobileApp=CoronaTravel" +
                "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
                "&_type=json";

//        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
//                "ServiceKey=" + ServiceKey +
//                "&contentTypeId=" + "15" +
//                "&contentId=" + "292961" +
//                "&MobileOS=AND&MobileApp=CoronaTravel" +
//                "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
//                "&_type=json";

        String JSONFromdetailCommonUrl = "";
        try {
            JSONFromdetailCommonUrl = new HttpReqTask().execute(detailCommonUrl).get();
        } catch (Exception e) {
            Log.d("TAG", "jsonparsing error");
        }

        String a = OverviewJSONParsing(JSONFromdetailCommonUrl);
        try{
            a = URLDecoder.decode(a,"UTF-8");
        }catch (Exception e) {

        }
        a = a.replace("<b>","");
        a = a.replace("공지사항","공지사항\n");
        a = a.replace("<br>","");
        a = a.replace("</b><u><a href=\"https://korean.visitkorea.or.kr/notice/news_detail.do?nwsid=8cdd65e1-59f1-4904-8bc9-884001e40911","");
        a = a.replace("\" title=\"여행정보 변동사항 페이지로 이동\">→ 코로나바이러스감염증-19 여행정보 변동사항 확인하기","");
        a = a.replace("</a></u>","\n\n");
        a = a.replace("</a></u><"+festival.getTitle()+">","");
        a = a.replace("<strong>","");
        a = a.replace("</strong>","");
        festival.setOverview(a);
        test.setText(a);


        String URI = festival.getFirstimage();
        if (URI == "") URI = "http://";
        Picasso.get().load(URI).placeholder(R.drawable.sunnyicon).into(firstImage);


        Button change = (Button)findViewById(R.id.change);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ServiceKey = "2YHyxt5iKCnOzEiYHMcML%2FgiOywB9tnJeL6D%2BHqsL48iMsSOXwPxQHTjCHq5dA1zAEcNIdcQUXnvFMN0aIdLsQ%3D%3D";
                String RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                        "ServiceKey=" + ServiceKey +
                        "&eventStartDate=" + startdate +
                        "&eventEndDate=" + enddate +
                        "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                        "&MobileOS=AND&MobileApp=CoronaTravel" +
                        "&arrange=O&numOfRows=1&pageNo=1&_type=json";

                String JSONFromRandomFestivalUrl = "";
                try {
                    JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
                } catch (Exception e) {
                    Log.d("TAG", "jsonparsing error");
                }
                totalcount = ShortJSONParsing(JSONFromRandomFestivalUrl);
                Random rnd = new Random();
                String randomnum = String.valueOf(rnd.nextInt((Integer.parseInt(totalcount) / 10) * 8) + 1);
                int randomarrange =rnd.nextInt(3);
                String arrange[] = {"O","P","Q","R"};
                RandomFestivalUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?" +
                        "ServiceKey=" + ServiceKey +
                        "&eventStartDate=" + startdate +
                        "&eventEndDate=" + enddate +
                        "&areaCode=&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y" +
                        "&MobileOS=AND&MobileApp=CoronaTravel" +
                        "&arrange=" + arrange[randomarrange] +
                        "&numOfRows=1&pageNo=" + randomnum + "&_type=json";

                Log.d("randomfestival",RandomFestivalUrl);

                try {
                    JSONFromRandomFestivalUrl = new HttpReqTask().execute(RandomFestivalUrl).get();
                } catch (Exception e) {
                    Log.d("TAG", "jsonparsing error");
                }


                Festival festival = JSONParsing(JSONFromRandomFestivalUrl);

                String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
                        "ServiceKey=" + ServiceKey +
                        "&contentTypeId=" + festival.getContenttypeid() +
                        "&contentId=" + festival.getContentid() +
                        "&MobileOS=AND&MobileApp=CoronaTravel" +
                        "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
                        "&_type=json";

//        String detailCommonUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?" +
//                "ServiceKey=" + ServiceKey +
//                "&contentTypeId=" + "15" +
//                "&contentId=" + "292961" +
//                "&MobileOS=AND&MobileApp=CoronaTravel" +
//                "&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y" +
//                "&_type=json";

                String JSONFromdetailCommonUrl = "";
                try {
                    JSONFromdetailCommonUrl = new HttpReqTask().execute(detailCommonUrl).get();
                } catch (Exception e) {
                    Log.d("TAG", "jsonparsing error");
                }

                String a = OverviewJSONParsing(JSONFromdetailCommonUrl);
                try{
                    a = URLDecoder.decode(a,"UTF-8");
                }catch (Exception e) {

                }
                a = a.replace("<b>","");
                a = a.replace("공지사항","공지사항\n");
                a = a.replace("<br>","");
                a = a.replace("</b><u><a href=\"https://korean.visitkorea.or.kr/notice/news_detail.do?nwsid=8cdd65e1-59f1-4904-8bc9-884001e40911","");
                a = a.replace("\" title=\"여행정보 변동사항 페이지로 이동\">→ 코로나바이러스감염증-19 여행정보 변동사항 확인하기","");
                a = a.replace("</a></u>","\n\n");
                a = a.replace("</a></u><"+festival.getTitle()+">","");
                a = a.replace("<strong>","");
                a = a.replace("</strong>","");
                festival.setOverview(a);
                test.setText(a);


                String URI = festival.getFirstimage();
                if (URI == "") URI = "http://";
                Picasso.get().load(URI).placeholder(R.drawable.sunnyicon).into(firstImage);
            }
        });

    }

    public static String ShortJSONParsing(String JSONFromLocationBasedListaddr) {
        String totalcount = "";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");
        } catch (Exception e) {

        }
        return totalcount;
    }


    public static Festival JSONParsing(String JSONFromLocationBasedListaddr) {
        String addr1="";
        String areacode="";
        String eventenddate="";
        String eventstartdate="";
        String firstimage="";
        String title="";
        String contentid="";
        String contenttypeid="";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonArray_item = new JSONObject(item);

            try {
                addr1 = jsonArray_item.getString("addr1");
            } catch (JSONException e) {
                addr1 = "";
            }
            try {
                areacode = jsonArray_item.getString("areacode");
            } catch (JSONException e) {
                areacode = "";
            }
            try {
                eventenddate = jsonArray_item.getString("eventenddate");
            } catch (JSONException e) {
                eventenddate = "";
            }
            try {
                eventstartdate = jsonArray_item.getString("eventstartdate");
            } catch (JSONException e) {
                eventstartdate = "";
            }
            try {
                firstimage = jsonArray_item.getString("firstimage");
            } catch (JSONException e) {
                firstimage = "";
            }
            try {
                title = jsonArray_item.getString("title");
            } catch (JSONException e) {
                title = "";
            }

            contentid = jsonArray_item.getString("contentid");
            contenttypeid = jsonArray_item.getString("contenttypeid");
            Festival festival = new Festival(addr1, areacode, eventenddate, eventstartdate, firstimage, title, contentid, contenttypeid, "");
            return festival;
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        Festival festival = new Festival(addr1, areacode, eventenddate, eventstartdate, firstimage, title, contentid, contenttypeid, "");
        return festival;
    }

    public String OverviewJSONParsing(String JSONFromdetailCommonUrl) {
        String overview ="";
        try {
            JSONObject jsonObject = new JSONObject(JSONFromdetailCommonUrl);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonObject_item = new JSONObject(item);
            try {
                overview = jsonObject_item.getString("overview");
            }catch (JSONException e){
                overview="";
            }
            return overview;
        } catch (JSONException e) {
            Log.d("TAG", "detailCommon parsing error");
        }
        return overview;
    }
}

