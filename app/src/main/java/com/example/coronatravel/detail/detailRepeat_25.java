package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detailRepeat_25 {
    public detailRepeat_25() {
    }

    public detailRepeat_25(String subdetailalt, String subdetailimg, String subdetailoverview) {
        this.subdetailalt = subdetailalt;
        this.subdetailimg = subdetailimg;
        this.subdetailoverview = subdetailoverview;
    }

    String subdetailalt; // 이름
    String subdetailimg; // 사진
    String subdetailoverview; // 설명

    public String getSubdetailalt() {
        return subdetailalt;
    }

    public void setSubdetailalt(String subdetailalt) {
        this.subdetailalt = subdetailalt;
    }

    public String getSubdetailimg() {
        return subdetailimg;
    }

    public void setSubdetailimg(String subdetailimg) {
        this.subdetailimg = subdetailimg;
    }

    public String getSubdetailoverview() {
        return subdetailoverview;
    }

    public void setSubdetailoverview(String subdetailoverview) {
        this.subdetailoverview = subdetailoverview;
    }

    public static detailRepeat_25 JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailRepeat_25 subclass = null;

        String subdetailalt; // 이름
        String subdetailimg; // 사진
        String subdetailoverview;
        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");


            JSONArray jsonArray_item = new JSONArray(item);

            for (int i = 0; i < jsonArray_item.length(); i++) {
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                try {
                    subdetailalt = subJsonObject.getString("subdetailalt");
                } catch (JSONException e) {
                    subdetailalt = "";
                }
                try {
                    subdetailimg = subJsonObject.getString("subdetailimg");
                } catch (JSONException e) {
                    subdetailimg = "";
                }
                try {
                    subdetailoverview = subJsonObject.getString("subdetailoverview");
                    if(subdetailoverview.startsWith("는") || subdetailoverview.startsWith("은")){
                        subdetailoverview = "[" + subdetailalt + "]" + subdetailoverview;
                    }
                } catch (JSONException e) {
                    subdetailoverview = "";
                }

                subclass = new detailRepeat_25(subdetailalt, subdetailimg,subdetailoverview);
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        return subclass;
    }
}
