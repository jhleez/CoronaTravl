package com.example.coronatravel;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationBasedList_Class {

    LocationBasedList_Class(String addr1,String contentid,String contenttypeid,String dist,String firstimage,String title)
    {
        this.addr1 =addr1; //주소
        this.contentid=contentid;
        this.contenttypeid=contenttypeid;
        this.dist=dist; //내위치에서 거리
        this.firstimage=firstimage; // 사진
        this.title=title; //제목
    }

    private String addr1;
    private String contentid;
    private String contenttypeid;
    private String dist;
    private String firstimage;
    private String title;


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

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
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
        String addr1;
        String contentid;
        String contenttypeid;
        String dist;
        String firstimage;
        String title;



        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
            String  response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            //totalcount = jsonObject_body.getString("totalCount");

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONArray jsonArray_item = new JSONArray(item);
            for(int i=0;i<jsonArray_item.length();i++){
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                try{
                    addr1 = subJsonObject.getString("addr1");
                }catch (JSONException e){
                    addr1="";
                }

                try{
                    dist = subJsonObject.getString("dist");
                }catch (JSONException e){
                    dist="";
                }
                try{
                    firstimage = subJsonObject.getString("firstimage");
                }catch (JSONException e){
                    firstimage="";
                }
                contentid =subJsonObject.getString("contentid");
                contenttypeid = subJsonObject.getString("contenttypeid");
                firstimage = subJsonObject.getString("firstimage");
                title = subJsonObject.getString("title");

                LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,dist,firstimage,title);
                MainActivity.LocationBasedList_ArrayList.add(subclass);
            }
        } catch (JSONException e) {
            Log.d("TAG","parsing error");
        }
    }
}

