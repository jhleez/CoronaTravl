package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_12 {
    detailInfo_12(){};
    detailInfo_12(String accomcount, String chkbabycarriage, String chkcreditcard, String chkpet, String expagerange, String expguide, String infocenter, String opendate,String restdate, String parking, String useseason, String usetime) {
        this.accomcount=accomcount;
        this.chkbabycarriage=chkbabycarriage;
        this.chkcreditcard=chkcreditcard;
        this.chkpet=chkpet;
        this.expagerange=expagerange;
        this.expguide=expguide;
        this.infocenter= infocenter;
        this.opendate=opendate;
        this.restdate=restdate;
        this.parking=parking;
        this.useseason=useseason;
        this.usetime=usetime;
    }
    private String accomcount;//수용인원
    private String chkbabycarriage;//유모차대여 정보
    private String chkcreditcard;//신용카드가능 정보
    private String chkpet;//애완동물동반가능 정보
    private String expagerange; // 체험가능 연령
    private String expguide;// 체험 안내
    private String infocenter;// 문의 및 안내
    private String opendate;//개장일
    private String restdate;//쉬는날
    private String parking;//주차시설
    private String useseason;//이용시기
    private String usetime;//이용시간

    public String getAccomcount() {
        return accomcount;
    }

    public void setAccomcount(String accomcount) {
        this.accomcount = accomcount;
    }

    public String getChkbabycarriage() {
        return chkbabycarriage;
    }

    public void setChkbabycarriage(String chkbabycarriage) {
        this.chkbabycarriage = chkbabycarriage;
    }

    public String getChkcreditcard() {
        return chkcreditcard;
    }

    public void setChkcreditcard(String chkcreditcard) {
        this.chkcreditcard = chkcreditcard;
    }

    public String getChkpet() {
        return chkpet;
    }

    public void setChkpet(String chkpet) {
        this.chkpet = chkpet;
    }

    public String getExpagerange() {
        return expagerange;
    }

    public void setExpagerange(String expagerange) {
        this.expagerange = expagerange;
    }

    public String getExpguide() {
        return expguide;
    }

    public void setExpguide(String expguide) {
        this.expguide = expguide;
    }

    public String getInfocenter() {
        return infocenter;
    }

    public void setInfocenter(String infocenter) {
        this.infocenter = infocenter;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getUseseason() {
        return useseason;
    }

    public void setUseseason(String useseason) {
        this.useseason = useseason;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public String getRestdate() {
        return restdate;
    }

    public void setRestdate(String restdate) {
        this.restdate = restdate;
    }

    public detailInfo_12 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_12 subclass=null;
        String accomcount;//수용인원
        String chkbabycarriage;//유모차대여 정보
        String chkcreditcard;//신용카드가능 정보
        String chkpet;//애완동물동반가능 정보
        String expagerange; // 체험가능 연령
        String expguide;// 체험 안내
        String infocenter;// 문의 및 안내
        String opendate;
        String restdate;//쉬는날
        String parking;//주차시설
        String useseason;//이용시기
        String usetime;//이용시간

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
                accomcount = jsonObject_item.getString("accomcount");
            }catch (JSONException e){
                accomcount="";
            }
            try{
                chkbabycarriage = jsonObject_item.getString("chkbabycarriage");
            }catch (JSONException e){
                chkbabycarriage="";
            }
            try{
                chkcreditcard = jsonObject_item.getString("chkcreditcard");
            }catch (JSONException e){
                chkcreditcard="";
            }
            try{
                chkpet = jsonObject_item.getString("chkpet");
            }catch (JSONException e){
                chkpet="";
            }
            try{
                expagerange = jsonObject_item.getString("expagerange");
            } catch (JSONException e){
                expagerange="";
            }
            try {
                expguide = jsonObject_item.getString("expguide");
            }catch (JSONException e){
                expguide="";
            }
            try{
                infocenter = jsonObject_item.getString("infocenter");
            }catch (JSONException e){
                infocenter="";
            }
            try{
                opendate = jsonObject_item.getString("opendate");
            }catch (JSONException e){
                opendate="";
            }
            try{
                restdate = jsonObject_item.getString("restdate");
            }catch (JSONException e){
                restdate="";
            }
            try{
                parking = jsonObject_item.getString("parking");
            }catch (JSONException e){
                parking="";
            }
            try{
                useseason = jsonObject_item.getString("useseason");
            }catch (JSONException e){
                useseason="";
            }
            try{
                usetime = jsonObject_item.getString("usetime");
            }catch (JSONException e){
                usetime="";
            }

            subclass = new detailInfo_12(accomcount,chkbabycarriage,chkcreditcard,chkpet,expagerange,expguide,infocenter,opendate,restdate,parking,useseason,usetime);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_12 parsing error");
        }
        return subclass;
    }
}
