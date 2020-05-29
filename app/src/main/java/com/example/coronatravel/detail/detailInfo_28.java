package com.example.coronatravel.detail;

import android.text.Html;
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

    public String getAccomcountleports() {
        return accomcountleports;
    }

    public void setAccomcountleports(String accomcountleports) {
        this.accomcountleports = accomcountleports;
    }

    public String getChkbabycarriageleports() {
        return chkbabycarriageleports;
    }

    public void setChkbabycarriageleports(String chkbabycarriageleports) {
        this.chkbabycarriageleports = chkbabycarriageleports;
    }

    public String getChkcreditcardleports() {
        return chkcreditcardleports;
    }

    public void setChkcreditcardleports(String chkcreditcardleports) {
        this.chkcreditcardleports = chkcreditcardleports;
    }

    public String getChkpetleports() {
        return chkpetleports;
    }

    public void setChkpetleports(String chkpetleports) {
        this.chkpetleports = chkpetleports;
    }

    public String getExpagerangeleports() {
        return expagerangeleports;
    }

    public void setExpagerangeleports(String expagerangeleports) {
        this.expagerangeleports = expagerangeleports;
    }

    public String getInfocenterleports() {
        return infocenterleports;
    }

    public void setInfocenterleports(String infocenterleports) {
        this.infocenterleports = infocenterleports;
    }

    public String getOpenperiod() {
        return openperiod;
    }

    public void setOpenperiod(String openperiod) {
        this.openperiod = openperiod;
    }

    public String getReservation() {
        return reservation;
    }

    public void setReservation(String reservation) {
        this.reservation = reservation;
    }

    public String getRestdateleports() {
        return restdateleports;
    }

    public void setRestdateleports(String restdateleports) {
        this.restdateleports = restdateleports;
    }

    public String getScaleleports() {
        return scaleleports;
    }

    public void setScaleleports(String scaleleports) {
        this.scaleleports = scaleleports;
    }

    public String getUsefeeleports() {
        return usefeeleports;
    }

    public void setUsefeeleports(String usefeeleports) {
        this.usefeeleports = usefeeleports;
    }

    public String getUsetimeleports() {
        return usetimeleports;
    }

    public void setUsetimeleports(String usetimeleports) {
        this.usetimeleports = usetimeleports;
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
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
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
            JSONObject jsonObject = new JSONObject(removehtml);
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
                usetimeleports = jsonObject_item.getString("usetimeleports");
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