package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_38 {
    detailInfo_38() {
    }

    ;

    detailInfo_38(String chkbabycarriageshopping, String chkcreditcardshopping, String chkpetshopping, String culturecenter, String fairday, String infocentershopping, String opendateshopping, String opentime, String restdateshopping, String saleitem, String saleitemcost, String shopguide) {

        this.chkbabycarriageshopping = chkbabycarriageshopping;
        this.chkcreditcardshopping = chkcreditcardshopping;
        this.chkpetshopping = chkpetshopping;
        this.culturecenter = culturecenter;
        this.fairday = fairday;
        this.infocentershopping = infocentershopping;
        this.opendateshopping = opendateshopping;
        this.opentime = opentime;
        this.restdateshopping = restdateshopping;
        this.saleitem = saleitem;
        this.saleitemcost = saleitemcost;
        this.shopguide = shopguide;
    }

    private String chkbabycarriageshopping;//유모차대여 정보
    private String chkcreditcardshopping; // 신용카드 가능 정보
    private String chkpetshopping; // 애완동물동반가능 정보
    private String culturecenter; // 문화센터 바로가기
    private String fairday; // 장서는 날
    private String infocentershopping; // 문의 및 안내
    private String opendateshopping; // 개장일
    private String opentime; // 영업시간
    private String restdateshopping; // 쉬는날
    private String saleitem; // 판매품목
    private String saleitemcost; //판매 품목별 가격
    private String shopguide; //매장안내

    public String getChkbabycarriageshopping() {
        return chkbabycarriageshopping;
    }

    public void setChkbabycarriageshopping(String chkbabycarriageshopping) {
        this.chkbabycarriageshopping = chkbabycarriageshopping;
    }

    public String getChkcreditcardshopping() {
        return chkcreditcardshopping;
    }

    public void setChkcreditcardshopping(String chkcreditcardshopping) {
        this.chkcreditcardshopping = chkcreditcardshopping;
    }

    public String getChkpetshopping() {
        return chkpetshopping;
    }

    public void setChkpetshopping(String chkpetshopping) {
        this.chkpetshopping = chkpetshopping;
    }

    public String getCulturecenter() {
        return culturecenter;
    }

    public void setCulturecenter(String culturecenter) {
        this.culturecenter = culturecenter;
    }

    public String getFairday() {
        return fairday;
    }

    public void setFairday(String fairday) {
        this.fairday = fairday;
    }

    public String getInfocentershopping() {
        return infocentershopping;
    }

    public void setInfocentershopping(String infocentershopping) {
        this.infocentershopping = infocentershopping;
    }

    public String getOpendateshopping() {
        return opendateshopping;
    }

    public void setOpendateshopping(String opendateshopping) {
        this.opendateshopping = opendateshopping;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getRestdateshopping() {
        return restdateshopping;
    }

    public void setRestdateshopping(String restdateshopping) {
        this.restdateshopping = restdateshopping;
    }

    public String getSaleitem() {
        return saleitem;
    }

    public void setSaleitem(String saleitem) {
        this.saleitem = saleitem;
    }

    public String getSaleitemcost() {
        return saleitemcost;
    }

    public void setSaleitemcost(String saleitemcost) {
        this.saleitemcost = saleitemcost;
    }

    public String getShopguide() {
        return shopguide;
    }

    public void setShopguide(String shopguide) {
        this.shopguide = shopguide;
    }

    public detailInfo_38 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailInfo_38 subclass = null;
        String chkbabycarriageshopping;//유모차대여 정보
        String chkcreditcardshopping; // 신용카드 가능 정보
        String chkpetshopping; // 애완동물동반가능 정보
        String culturecenter; // 문화센터 바로가기
        String fairday; // 장서는 날
        String infocentershopping; // 문의 및 안내
        String opendateshopping; // 개장일
        String opentime; // 영업시간
        String restdateshopping; // 쉬는날
        String saleitem; // 판매품목
        String saleitemcost; //판매 품목별 가격
        String shopguide; //매장안내

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
                chkbabycarriageshopping = jsonObject_item.getString("chkbabycarriageshopping");
            } catch (JSONException e) {
                chkbabycarriageshopping = "";
            }
            try {
                chkcreditcardshopping = jsonObject_item.getString("chkcreditcardshopping");
            } catch (JSONException e) {
                chkcreditcardshopping = "";
            }
            try {
                chkpetshopping = jsonObject_item.getString("chkpetshopping");
            } catch (JSONException e) {
                chkpetshopping = "";
            }
            try {
                culturecenter = jsonObject_item.getString("culturecenter");
            } catch (JSONException e) {
                culturecenter = "";
            }
            try {
                fairday = jsonObject_item.getString("fairday");
            } catch (JSONException e) {
                fairday = "";
            }
            try {
                infocentershopping = jsonObject_item.getString("infocentershopping");
            } catch (JSONException e) {
                infocentershopping = "";
            }
            try {
                opendateshopping = jsonObject_item.getString("opendateshopping");
            } catch (JSONException e) {
                opendateshopping = "";
            }
            try {
                opentime = jsonObject_item.getString("opentime");
            } catch (JSONException e) {
                opentime = "";
            }
            try {
                restdateshopping = jsonObject_item.getString("restdateshopping");
            } catch (JSONException e) {
                restdateshopping = "";
            }
            try {
                saleitem = jsonObject_item.getString("saleitem");
            } catch (JSONException e) {
                saleitem = "";
            }
            try {
                saleitemcost = jsonObject_item.getString("saleitemcost");
            } catch (JSONException e) {
                saleitemcost = "";
            }
            try {
                shopguide = jsonObject_item.getString("shopguide");
            } catch (JSONException e) {
                shopguide = "";
            }

            subclass = new detailInfo_38(chkbabycarriageshopping, chkcreditcardshopping, chkpetshopping, culturecenter, fairday, infocentershopping, opendateshopping
                    , opentime, restdateshopping,saleitem,saleitemcost,shopguide);
        } catch (JSONException e) {
            Log.d("TAG", "detailInfo_38 parsing error");
        }
        return subclass;
    }
}