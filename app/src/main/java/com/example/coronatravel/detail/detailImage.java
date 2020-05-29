package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailImage {
    public static ArrayList<String> Images = new ArrayList<String>();


    public static void JSONParsing(String JSONFromLocationBasedListaddr) {
        Images.clear();
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        String image;
        String totalcount;
        try {
            JSONObject jsonObject = new JSONObject(removehtml);
            String response = jsonObject.getString("response");
            JSONObject jsonObject_response = new JSONObject(response);

            String body = jsonObject_response.getString("body");
            JSONObject jsonObject_body = new JSONObject(body);

            totalcount = jsonObject_body.getString("totalCount");
            if(Integer.parseInt(totalcount) == 0){
                return;
            }
            String items = jsonObject_body.getString("items");
            JSONObject jsonObject_items = new JSONObject(items);

            String item = jsonObject_items.getString("item");

            if(Integer.parseInt(totalcount)==1){
                JSONObject jsonObject_item = new JSONObject(item);
                image = jsonObject_item.getString("originimgurl");
                Log.d("TAG", image);
                Images.add(image);
            }
            else{
                JSONArray jsonArray_item = new JSONArray(item);
                for (int i = 0; i < jsonArray_item.length(); i++) {
                    JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                    image = subJsonObject.getString("originimgurl");
                    Log.d("TAG", image);
                    Images.add(image);
                }
            }
            JSONArray jsonArray_item = new JSONArray(item);


            for (int i = 0; i < jsonArray_item.length(); i++) {
                JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                image = subJsonObject.getString("originimgurl");
                Log.d("TAG",image);
                Images.add(image);
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
    }
}
