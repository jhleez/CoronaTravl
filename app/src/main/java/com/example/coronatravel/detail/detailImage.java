package com.example.coronatravel.detail;

import android.util.Log;

import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailImage {
    public static ArrayList<String> Images;


    public static void JSONParsing(String JSONFromLocationBasedListaddr) {
        String image;

        try {
            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
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
                image = subJsonObject.getString("originimgurl");
                Log.d("TAG",image);
                Images.add(image);
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
    }
}
