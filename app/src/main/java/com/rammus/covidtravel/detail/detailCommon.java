package com.rammus.covidtravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailCommon {
    detailCommon(){};
    detailCommon(String addr1, String booktour, String firstimage, String mapx, String mapy, String mlevel,String overview, String title, String zipcode,String homepage, String tel, String telname,String areacode,String sigungucode){
        this.addr1 = addr1; //주소
        this.booktour=booktour; // 교과서 여행지 여부
        this.firstimage=firstimage; // 썸네일 이미지
        this.mapx=mapx; // GPS X좌표(WGS84 경도 좌표)
        this.mapy=mapy; // GPS Y좌표(WGS84 위도 좌표)
        this.mlevel=mlevel; // Map Level
        this.overview=overview; // 개요
        this.title=title; // 이름
        this.zipcode=zipcode; // 우편번호
        this.homepage = homepage; // 홈페이지
        this.tel = tel; // 전화번호
        this.telname = telname; // 전화번호명명
        this.areacode = areacode;
        this.sigungucode = sigungucode;
    }
    private String homepage;
    private String tel;
    private String telname;
    private String addr1;
    private String booktour;
    private String firstimage;
    private String mapx;
    private String mapy;
    private String mlevel;
    private String overview;
    private String title;
    private String zipcode;
    private String areacode;
    private String sigungucode;

    public String getTelname() {
        return telname;
    }

    public void setTelname(String telname) {
        this.telname = telname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(String sigungucode) {
        this.sigungucode = sigungucode;
    }



    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getBooktour() {
        return booktour;
    }

    public void setBooktour(String booktour) {
        this.booktour = booktour;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy;
    }

    public String getMlevel() {
        return mlevel;
    }

    public void setMlevel(String mlevel) {
        this.mlevel = mlevel;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel_name() {
        return telname;
    }

    public void setTel_name(String tel_name) {
        this.telname = tel_name;
    }

    public detailCommon JSONParsing(String JSONFromdetailCommonUrl) {
        String removehtml = Html.fromHtml(JSONFromdetailCommonUrl).toString();
        detailCommon subclass=null;
        String addr1,booktour, firstimage,mapx,mapy,mlevel,overview,title,zipcode,homepage,tel,telname,areacode,sigungucode;
        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            JSONObject jsonObject_item = new JSONObject(item);

            try{
                addr1 = jsonObject_item.getString("addr1");
            }catch (JSONException e){
                addr1="";
            }
            try{
                booktour = jsonObject_item.getString("booktour");
            }catch (JSONException e){
                booktour="";
            }
            try{
                firstimage = jsonObject_item.getString("firstimage");
            }catch (JSONException e){
                firstimage="";
            }
            try{
                mapx = jsonObject_item.getString("mapx");
            }catch (JSONException e){
                mapx="";
            }
            try{
                mapy = jsonObject_item.getString("mapy");
            } catch (JSONException e){
                mapy="";
            }
            try{
                mlevel = jsonObject_item.getString("mlevel");
            }catch (JSONException e){
                mlevel="";
            }
            try {
                overview = jsonObject_item.getString("overview");
            }catch (JSONException e){
                overview="";
            }
            try{
                title = jsonObject_item.getString("title");
            }catch (JSONException e){
                title="";
            }
            try{
                zipcode = jsonObject_item.getString("zipcode");
            }catch (JSONException e){
                zipcode="";
            }
            try{
                homepage = jsonObject_item.getString("homepage");
            }catch (JSONException e){
                homepage="";
            }
            try{
                tel = jsonObject_item.getString("tel");
            }catch (JSONException e){
                tel="";
            }
            try{
                telname = jsonObject_item.getString("telname");
            }catch (JSONException e){
                telname="";
            }
            try{
                areacode = jsonObject_item.getString("areacode");
            }catch (JSONException e){
                areacode="";
            }
            try{
                sigungucode = jsonObject_item.getString("sigungucode");
            }catch (JSONException e){
                sigungucode="";
            }

            subclass = new detailCommon(addr1,booktour,firstimage,mapx,mapy,mlevel,overview,title,zipcode,homepage,tel,telname,areacode,sigungucode);
        } catch (JSONException e) {
            Log.d("TAG", "detailCommon parsing error");
        }
        return subclass;
    }
}
