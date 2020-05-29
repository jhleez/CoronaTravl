package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_32 {
    detailInfo_32() {
    }

    ;

    detailInfo_32(String benikia, String checkintime, String checkouttime, String goodstay, String infocenterlodging, String pickup, String reservationlodging, String reservationurl, String refundregulation, String barbecue) {
        this.benikia = benikia; //베니키아 여부
        this.checkintime = checkintime; // 입실시간
        this.checkouttime = checkouttime; // 퇴실 시간
        this.goodstay = goodstay; // 굿스테이 여부
        this.infocenterlodging = infocenterlodging; // 문의 및 안내
        this.pickup = pickup; // 픽업 서비스
        this.reservationlodging = reservationlodging; // 예약안내
        this.reservationurl = reservationurl; // 예약안내 홈페이지
        this.refundregulation = refundregulation; //환불규정
        this.barbecue = barbecue; // 바베큐장 여부
    }

    private String benikia; //베니키아 여부
    private String checkintime; // 입실시간
    private String checkouttime; // 퇴실 시간
    private String goodstay; // 굿스테이 여부
    private String infocenterlodging; // 문의 및 안내
    private String pickup; // 픽업 서비스
    private String reservationlodging; // 예약안내
    private String reservationurl; // 예약안내 홈페이지
    private String refundregulation; //환불규정
    private String barbecue; // 바베큐장 여부

    public String getBenikia() {
        return benikia;
    }

    public void setBenikia(String benikia) {
        this.benikia = benikia;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public String getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(String checkouttime) {
        this.checkouttime = checkouttime;
    }

    public String getGoodstay() {
        return goodstay;
    }

    public void setGoodstay(String goodstay) {
        this.goodstay = goodstay;
    }

    public String getInfocenterlodging() {
        return infocenterlodging;
    }

    public void setInfocenterlodging(String infocenterlodging) {
        this.infocenterlodging = infocenterlodging;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getReservationlodging() {
        return reservationlodging;
    }

    public void setReservationlodging(String reservationlodging) {
        this.reservationlodging = reservationlodging;
    }

    public String getReservationurl() {
        return reservationurl;
    }

    public void setReservationurl(String reservationurl) {
        this.reservationurl = reservationurl;
    }

    public String getRefundregulation() {
        return refundregulation;
    }

    public void setRefundregulation(String refundregulation) {
        this.refundregulation = refundregulation;
    }

    public String getBarbecue() {
        return barbecue;
    }

    public void setBarbecue(String barbecue) {
        this.barbecue = barbecue;
    }

    public detailInfo_32 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_32 subclass = null;
        String benikia; //베니키아 여부
        String checkintime; // 입실시간
        String checkouttime; // 퇴실 시간
        String goodstay; // 굿스테이 여부
        String infocenterlodging; // 문의 및 안내
        String pickup; // 픽업 서비스
        String reservationlodging; // 예약안내
        String reservationurl; // 예약안내 홈페이지
        String refundregulation; //환불규정
        String barbecue; // 바베큐장 여부


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
                benikia = jsonObject_item.getString("benikia");
            } catch (JSONException e) {
                benikia = "";
            }
            try {
                checkintime = jsonObject_item.getString("checkintime");
            } catch (JSONException e) {
                checkintime = "";
            }
            try {
                checkouttime = jsonObject_item.getString("checkouttime");
            } catch (JSONException e) {
                checkouttime = "";
            }
            try {
                goodstay = jsonObject_item.getString("goodstay");
            } catch (JSONException e) {
                goodstay = "";
            }
            try {
                infocenterlodging = jsonObject_item.getString("infocenterlodging");
            } catch (JSONException e) {
                infocenterlodging = "";
            }
            try {
                pickup = jsonObject_item.getString("pickup");
            } catch (JSONException e) {
                pickup = "";
            }
            try {
                reservationlodging = jsonObject_item.getString("reservationlodging");
            } catch (JSONException e) {
                reservationlodging = "";
            }
            try {
                reservationurl = jsonObject_item.getString("reservationurl");
            } catch (JSONException e) {
                reservationurl = "";
            }
            try {
                refundregulation = jsonObject_item.getString("refundregulation");
            } catch (JSONException e) {
                refundregulation = "";
            }
            try {
                barbecue = jsonObject_item.getString("barbecue");
            } catch (JSONException e) {
                barbecue = "";
            }

            subclass = new detailInfo_32(benikia, checkintime, checkouttime, goodstay, infocenterlodging, pickup, reservationlodging
                    , reservationurl, refundregulation,barbecue);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_32 parsing error");
        }
        return subclass;
    }
}
