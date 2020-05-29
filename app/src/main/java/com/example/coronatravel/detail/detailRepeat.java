package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class detailRepeat {
    public detailRepeat() {
    }

    public detailRepeat(String infoname, String infotext) {
        this.infoname = infoname;
        this.infotext = infotext;
    }

    String infoname;
    String infotext;

    public String getInfoname() {
        return infoname;
    }

    public void setInfoname(String infoname) {
        this.infoname = infoname;
    }

    public String getInfotext() {
        return infotext;
    }

    public void setInfotext(String infotext) {
        this.infotext = infotext;
    }

    public detailRepeat JSONParsing(String JSONFromLocationBasedListaddr) {
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailRepeat subclass = null;
        String totalcount;
        String infoname="";
        String infotext="";

        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");

            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");
            if(totalcount.equals("1")){
                JSONObject jsonObject_item = new JSONObject(item);
                try{
                    infoname = jsonObject_item.getString("infoname");
                }catch (JSONException e){
                    infoname="";
                }
                try{
                    infotext = jsonObject_item.getString("infotext");
                }catch (JSONException e){
                    infotext="";
                }

                subclass = new detailRepeat(infoname,infotext);

            }
            else {
                JSONArray jsonArray_item = new JSONArray(item);

                for (int i = 0; i < jsonArray_item.length(); i++) {
                    JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                    try{
                        infoname = subJsonObject.getString("infoname");
                    }catch (JSONException e){
                        infoname="";
                    }
                    try{
                        infotext = subJsonObject.getString("infotext");
                    }catch (JSONException e){
                        infotext="";
                    }

                    subclass = new detailRepeat(infoname,infotext);
                }
            }
        } catch (JSONException e) {
            Log.d("TAG","parsing error");
        }
        return subclass;
    }
}