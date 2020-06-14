package com.rammus.covidtravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_39 {
    detailInfo_39() {
    }

    ;

    detailInfo_39(String chkcreditcardfood, String discountinfofood, String firstmenu, String infocenterfood, String kidsfacility, String opendatefood, String opentimefood, String packing, String treatmenu) {

        this.chkcreditcardfood = chkcreditcardfood;
        this.discountinfofood = discountinfofood;
        this.firstmenu = firstmenu;
        this.infocenterfood = infocenterfood;
        this.kidsfacility = kidsfacility;
        this.opendatefood = opendatefood;
        this.opentimefood = opentimefood;
        this.packing = packing;
        this.treatmenu = treatmenu;
    }

    private String chkcreditcardfood; // 신용카드가능 정보
    private String discountinfofood; // 할인정보
    private String firstmenu; // 대표매뉴
    private String infocenterfood; // 문의 및 안내
    private String kidsfacility; // 어린이 놀이방 여부
    private String opendatefood; // 개업일
    private String opentimefood; // 영업시간
    private String packing; // 포장가능 여부
    private String treatmenu;//취급메뉴

    public String getChkcreditcardfood() {
        return chkcreditcardfood;
    }

    public void setChkcreditcardfood(String chkcreditcardfood) {
        this.chkcreditcardfood = chkcreditcardfood;
    }

    public String getDiscountinfofood() {
        return discountinfofood;
    }

    public void setDiscountinfofood(String discountinfofood) {
        this.discountinfofood = discountinfofood;
    }

    public String getFirstmenu() {
        return firstmenu;
    }

    public void setFirstmenu(String firstmenu) {
        this.firstmenu = firstmenu;
    }

    public String getInfocenterfood() {
        return infocenterfood;
    }

    public void setInfocenterfood(String infocenterfood) {
        this.infocenterfood = infocenterfood;
    }

    public String getKidsfacility() {
        return kidsfacility;
    }

    public void setKidsfacility(String kidsfacility) {
        this.kidsfacility = kidsfacility;
    }

    public String getOpendatefood() {
        return opendatefood;
    }

    public void setOpendatefood(String opendatefood) {
        this.opendatefood = opendatefood;
    }

    public String getOpentimefood() {
        return opentimefood;
    }

    public void setOpentimefood(String opentimefood) {
        this.opentimefood = opentimefood;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getTreatmenu() {
        return treatmenu;
    }

    public void setTreatmenu(String treatmenu) {
        this.treatmenu = treatmenu;
    }


    public detailInfo_39 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_39 subclass = null;
        String chkcreditcardfood; // 신용카드가능 정보
        String discountinfofood; // 할인정보
        String firstmenu; // 대표매뉴
        String infocenterfood; // 문의 및 안내
        String kidsfacility; // 어린이 놀이방 여부
        String opendatefood; // 개업일
        String opentimefood; // 영업시간
        String packing; // 포장가능 여부
        String treatmenu;//취급메뉴

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
                chkcreditcardfood = jsonObject_item.getString("chkcreditcardfood");
            } catch (JSONException e) {
                chkcreditcardfood = "";
            }
            try {
                discountinfofood = jsonObject_item.getString("discountinfofood");
            } catch (JSONException e) {
                discountinfofood = "";
            }
            try {
                firstmenu = jsonObject_item.getString("firstmenu");
            } catch (JSONException e) {
                firstmenu = "";
            }
            try {
                infocenterfood = jsonObject_item.getString("infocenterfood");
            } catch (JSONException e) {
                infocenterfood = "";
            }
            try {
                kidsfacility = jsonObject_item.getString("kidsfacility");
            } catch (JSONException e) {
                kidsfacility = "";
            }
            try {
                opendatefood = jsonObject_item.getString("opendatefood");
            } catch (JSONException e) {
                opendatefood = "";
            }
            try {
                opentimefood = jsonObject_item.getString("opentimefood");
            } catch (JSONException e) {
                opentimefood = "";
            }
            try {
                packing = jsonObject_item.getString("packing");
            } catch (JSONException e) {
                packing = "";
            }
            try {
                treatmenu = jsonObject_item.getString("treatmenu");
            } catch (JSONException e) {
                treatmenu = "";
            }

            subclass = new detailInfo_39(chkcreditcardfood, discountinfofood, firstmenu, infocenterfood, kidsfacility, opendatefood, opentimefood
                    , packing, treatmenu);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_38 parsing error");
        }
        return subclass;
    }
}