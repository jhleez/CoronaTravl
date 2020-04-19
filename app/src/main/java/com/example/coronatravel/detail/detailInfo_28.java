package com.example.coronatravel.detail;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_28 {
    detailInfo_28() {};

    detailInfo_28(String accomcountleports, String chkbabycarriageleports, String chkcreditcardleports, String chkpetleports, String expagerangeleports, String infocenterleports, String openperiod, String reservation, String restdateleports, String scaleleports, String usefeeleports, String usetimeleports) {
        this.accomcountleports = accomcountleports;
        this.chkbabycarriageleports =chkbabycarriageleports;
        this.chkcreditcardleports=chkcreditcardleports;
        this.chkpetleports=chkpetleports;
        this.expagerangeleports=expagerangeleports;
        this.infocenterleports=infocenterleports;
        this.openperiod=openperiod;
        this.reservation=reservation;
        this.restdateleports=restdateleports;
        this.scaleleports=scaleleports;
        this.usefeeleports=usefeeleports;
        this.usetimeleports=usetimeleports;
    }

    private String accomcountleports; //수용인원
    private String chkbabycarriageleports; //유모차대여 정보
    private String chkcreditcardleports;// 신용카드가능 정보
    private String chkpetleports; // 애완동물동반가능 정보
    private String expagerangeleports; // 체험가능연령
    private String infocenterleports; // 문의 및 안내
    private String openperiod; // 개장기간
    private String reservation; // 예약안내
    private String restdateleports; // 쉬는날
    private String scaleleports;//규모
    private String usefeeleports;//입장료
    private String usetimeleports;//이용시간


    public detailInfo_28 JSONParsing(String JSONFromLocationBasedListaddr) {
        detailInfo_28 subclass = null;
        String accomcountleports;
        String chkbabycarriageleports;
        String chkcreditcardleports;
        String chkpetleports;
        String expagerangeleports;
        String infocenterleports;
        String openperiod;
        String reservation;
        String restdateleports;
        String scaleleports;
        String usefeeleports;
        String usetimeleports;

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

            try {
                accomcountleports = jsonObject_item.getString("accomcountleports");
            } catch (JSONException e) {
                accomcountleports = "";
            }
            try {
                chkbabycarriageleports = jsonObject_item.getString("chkbabycarriageleports");
            } catch (JSONException e) {
                chkbabycarriageleports = "";
            }
            try {
                chkcreditcardleports = jsonObject_item.getString("chkcreditcardleports");
            } catch (JSONException e) {
                chkcreditcardleports = "";
            }
            try {
                chkpetleports = jsonObject_item.getString("chkpetleports");
            } catch (JSONException e) {
                chkpetleports = "";
            }
            try {
                expagerangeleports = jsonObject_item.getString("expagerangeleports");
            } catch (JSONException e) {
                expagerangeleports = "";
            }
            try {
                infocenterleports = jsonObject_item.getString("infocenterleports");
            } catch (JSONException e) {
                infocenterleports = "";
            }
            try {
                openperiod = jsonObject_item.getString("openperiod");
            } catch (JSONException e) {
                openperiod = "";
            }
            try {
                reservation = jsonObject_item.getString("reservation");
            } catch (JSONException e) {
                reservation = "";
            }
            try {
                restdateleports = jsonObject_item.getString("restdateleports");
            } catch (JSONException e) {
                restdateleports = "";
            }
            try {
                scaleleports = jsonObject_item.getString("scaleleports");
            } catch (JSONException e) {
                scaleleports = "";
            }
            try {
                usefeeleports = jsonObject_item.getString("usefeeleports");
            } catch (JSONException e) {
                usefeeleports = "";
            }
            try {
                usetimeleports = jsonObject_item.getString("usetimefestival");
            } catch (JSONException e) {
                usetimeleports = "";
            }

            subclass = new detailInfo_28(accomcountleports, chkbabycarriageleports, chkcreditcardleports, chkpetleports, expagerangeleports, infocenterleports, openperiod
                    , reservation, restdateleports, scaleleports, usefeeleports, usetimeleports);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_28 parsing error");
        }
        return subclass;
    }
}