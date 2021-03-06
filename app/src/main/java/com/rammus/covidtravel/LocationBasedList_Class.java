package com.rammus.covidtravel;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationBasedList_Class {
    LocationBasedList_Class(){ // 여기에 SQLite 에 저장한 내용 삽입시키면 될듯?

    }
    LocationBasedList_Class(String addr1,String contentid,String contenttypeid,String firstimage,String title,String dist)
    {
        this.addr1 =addr1; //주소
        this.contentid=contentid;
        this.contenttypeid=contenttypeid;
        this.firstimage=firstimage; // 사진
        this.title=title; //제목
        this.dist =dist;
    }

    public static String totalcount;
    private String addr1;
    private String contentid;
    private String contenttypeid;
    private String firstimage;
    private String title;
    private String dist;

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(String contenttypeid) {
        this.contenttypeid = contenttypeid;
    }


    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static void JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        String addr1;
        String contentid;
        String contenttypeid;
        String dist;
        String firstimage;
        String title;

        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String  response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            if(totalcount.equals("1")){
                Log.d("tag","검색결과가 1개일때");
                JSONObject jsonObject_item = new JSONObject(item);
                try{
                    addr1 = jsonObject_item.getString("addr1");
                }catch (JSONException e){
                    addr1="";
                }
                try{
                    firstimage = jsonObject_item.getString("firstimage2");
                }catch (JSONException e){
                    firstimage="";
                }
                try {
                    dist = jsonObject_item.getString("dist");
                } catch (JSONException e) {
                    dist = "";
                }
                contentid =jsonObject_item.getString("contentid");
                contenttypeid = jsonObject_item.getString("contenttypeid");
                title = jsonObject_item.getString("title");

                LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,firstimage,title,dist);
                MainActivity.LocationBasedList_ArrayList.add(subclass);
            }
            else {
                JSONArray jsonArray_item = new JSONArray(item);
                Log.d("tag","검색결과가 여러개일때");
                for (int i = 0; i < jsonArray_item.length(); i++) {
                    JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                    try {
                        addr1 = subJsonObject.getString("addr1");
                    } catch (JSONException e) {
                        addr1 = "";
                    }

                    try {
                        dist = subJsonObject.getString("dist");
                    } catch (JSONException e) {
                        dist = "";
                    }


                    try {
                        firstimage = subJsonObject.getString("firstimage");
                    } catch (JSONException e) {
                        firstimage = "";
                    }
                    contentid = subJsonObject.getString("contentid");
                    contenttypeid = subJsonObject.getString("contenttypeid");
                    title = subJsonObject.getString("title");

                    LocationBasedList_Class subclass = new LocationBasedList_Class(addr1, contentid, contenttypeid, firstimage, title,dist);
                    MainActivity.LocationBasedList_ArrayList.add(subclass);
                }
            }
        } catch (JSONException e) {
            Log.d("TAG","parsing error");
        }
    }
}

