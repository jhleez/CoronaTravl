package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_14 {
    detailInfo_14(){};
    detailInfo_14(String accomcountculture, String chkbabycarriageculture, String chkcreditcardculture, String chkpetculture,
                  String discountinfo, String infocenterculture, String parkingculture, String parkingfee, String restdateculture, String usefee, String usetimeculture,String spendtime){
        this.accomcountculture=accomcountculture;
        this.chkbabycarriageculture=chkbabycarriageculture;
        this.chkcreditcardculture=chkcreditcardculture;
        this.chkpetculture=chkpetculture;
        this.discountinfo=discountinfo;
        this.infocenterculture=infocenterculture;
        this.parkingculture=parkingculture;
        this.parkingfee=parkingfee;
        this.restdateculture=restdateculture;
        this.usefee=usefee;
        this.usetimeculture=usetimeculture;
        this.spendtime=spendtime;
    }
    private String accomcountculture;//수용인원
    private String chkbabycarriageculture;//유모차대여정보
    private String chkcreditcardculture;//신용카드가능정보
    private String chkpetculture;//애완동물동반가능정보
    private String discountinfo;//할인정보
    private String infocenterculture;//문의 및 안내
    private String parkingculture;//주차시설
    private String parkingfee;//주차요금
    private String restdateculture;//쉬는날
    private String usefee;//이용요금
    private String usetimeculture;//이용시간
    private String spendtime;//관람소요시간

    public String getAccomcountculture() {
        return accomcountculture;
    }

    public void setAccomcountculture(String accomcountculture) {
        this.accomcountculture = accomcountculture;
    }

    public String getChkbabycarriageculture() {
        return chkbabycarriageculture;
    }

    public void setChkbabycarriageculture(String chkbabycarriageculture) {
        this.chkbabycarriageculture = chkbabycarriageculture;
    }

    public String getChkcreditcardculture() {
        return chkcreditcardculture;
    }

    public void setChkcreditcardculture(String chkcreditcardculture) {
        this.chkcreditcardculture = chkcreditcardculture;
    }

    public String getChkpetculture() {
        return chkpetculture;
    }

    public void setChkpetculture(String chkpetculture) {
        this.chkpetculture = chkpetculture;
    }

    public String getDiscountinfo() {
        return discountinfo;
    }

    public void setDiscountinfo(String discountinfo) {
        this.discountinfo = discountinfo;
    }

    public String getInfocenterculture() {
        return infocenterculture;
    }

    public void setInfocenterculture(String infocenterculture) {
        this.infocenterculture = infocenterculture;
    }

    public String getParkingculture() {
        return parkingculture;
    }

    public void setParkingculture(String parkingculture) {
        this.parkingculture = parkingculture;
    }

    public String getParkingfee() {
        return parkingfee;
    }

    public void setParkingfee(String parkingfee) {
        this.parkingfee = parkingfee;
    }

    public String getRestdateculture() {
        return restdateculture;
    }

    public void setRestdateculture(String restdateculture) {
        this.restdateculture = restdateculture;
    }

    public String getUsefee() {
        return usefee;
    }

    public void setUsefee(String usefee) {
        this.usefee = usefee;
    }

    public String getUsetimeculture() {
        return usetimeculture;
    }

    public void setUsetimeculture(String usetimeculture) {
        this.usetimeculture = usetimeculture;
    }

    public String getSpendtime() {
        return spendtime;
    }

    public void setSpendtime(String spendtime) {
        this.spendtime = spendtime;
    }

    public detailInfo_14 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_14 subclass=null;
        String accomcountculture;//수용인원
        String chkbabycarriageculture;//유모차대여정보
        String chkcreditcardculture;//신용카드가능정보
        String chkpetculture;//애완동물동반가능정보
        String discountinfo;//할인정보
        String infocenterculture;//문의 및 안내
        String parkingculture;//주차시설
        String parkingfee;//주차요금
        String restdateculture;//쉬는날
        String usefee;//이용요금
        String usetimeculture;//이용시간
        String spendtime;//관람소요시간

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
                accomcountculture = jsonObject_item.getString("accomcountculture");
            }catch (JSONException e){
                accomcountculture="";
            }
            try{
                chkbabycarriageculture = jsonObject_item.getString("chkbabycarriageculture");
            }catch (JSONException e){
                chkbabycarriageculture="";
            }
            try{
                chkcreditcardculture = jsonObject_item.getString("chkcreditcardculture");
            }catch (JSONException e){
                chkcreditcardculture="";
            }
            try{
                chkpetculture = jsonObject_item.getString("chkpetculture");
            }catch (JSONException e){
                chkpetculture="";
            }
            try{
                discountinfo = jsonObject_item.getString("discountinfo");
            } catch (JSONException e){
                discountinfo="";
            }
            try {
                infocenterculture = jsonObject_item.getString("infocenterculture");
            }catch (JSONException e){
                infocenterculture="";
            }
            try{
                parkingculture = jsonObject_item.getString("parkingculture");
            }catch (JSONException e){
                parkingculture="";
            }
            try{
                parkingfee = jsonObject_item.getString("parkingfee");
            }catch (JSONException e){
                parkingfee="";
            }
            try{
                restdateculture = jsonObject_item.getString("restdateculture");
            }catch (JSONException e){
                restdateculture="";
            }
            try{
                usefee = jsonObject_item.getString("usefee");
            }catch (JSONException e){
                usefee="";
            }
            try{
                usetimeculture = jsonObject_item.getString("usetimeculture");
            }catch (JSONException e){
                usetimeculture="";
            }
            try{
                spendtime = jsonObject_item.getString("spendtime");
            }catch (JSONException e){
                spendtime="";
            }

            subclass = new detailInfo_14(accomcountculture,chkbabycarriageculture,chkcreditcardculture,chkpetculture,discountinfo,infocenterculture,parkingculture
                    ,parkingfee,restdateculture,usefee,usetimeculture,spendtime);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_12 parsing error");
        }
        return subclass;
    }
}
