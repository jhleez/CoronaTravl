//package com.example.coronatravel.detail;
//
//import android.util.Log;
//
//import com.example.coronatravel.LocationBasedList_Class;
//import com.example.coronatravel.MainActivity;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class detailRepeat {
//    String infoname;
//    String infotext;
//
//
//    public detailRepeat JSONParsing(String JSONFromLocationBasedListaddr) {
//        detailRepeat subclass = null;
//
//        String totalcount;
//
//        try {
//            JSONObject jsonObject = new JSONObject(JSONFromLocationBasedListaddr);
//            String response = jsonObject.getString("response");
//            JSONObject jsonObject_response = new JSONObject(response);
//
//            String body = jsonObject_response.getString("body");
//            JSONObject jsonObject_body = new JSONObject(body);
//
//            totalcount = jsonObject_body.getString("totalCount");
//
//            String items = jsonObject_body.getString("items");
//            JSONObject jsonObject_items = new JSONObject(items);
//
//            String item = jsonObject_items.getString("item");
//            if(totalcount.equals("1")){
//                Log.d("tag","검색결과가 1개일때");
//                JSONObject jsonObject_item = new JSONObject(item);
//                try{
//                    infoname = jsonObject_item.getString("infoname");
//                }catch (JSONException e){
//                    infoname="";
//                }
//                try{
//                    infotext = jsonObject_item.getString("infotext");
//                }catch (JSONException e){
//                    infotext="";
//                }
//                contentid =jsonObject_item.getString("contentid");
//                contenttypeid = jsonObject_item.getString("contenttypeid");
//                title = jsonObject_item.getString("title");
//
//                LocationBasedList_Class subclass = new LocationBasedList_Class(addr1,contentid,contenttypeid,firstimage,title);
//                MainActivity.LocationBasedList_ArrayList.add(subclass);
//            }
//            else {
//                JSONArray jsonArray_item = new JSONArray(item);
//                Log.d("tag","검색결과가 여러개일때");
//                for (int i = 0; i < jsonArray_item.length(); i++) {
//                    JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
//                    try {
//                        addr1 = subJsonObject.getString("addr1");
//                    } catch (JSONException e) {
//                        addr1 = "";
//                    }
//
//                    try {
//                        firstimage = subJsonObject.getString("firstimage");
//                    } catch (JSONException e) {
//                        firstimage = "";
//                    }
//                    contentid = subJsonObject.getString("contentid");
//                    contenttypeid = subJsonObject.getString("contenttypeid");
//                    title = subJsonObject.getString("title");
//
//                    LocationBasedList_Class subclass = new LocationBasedList_Class(addr1, contentid, contenttypeid, firstimage, title);
//                    MainActivity.LocationBasedList_ArrayList.add(subclass);
//                }
//            }
//        } catch (JSONException e) {
//            Log.d("TAG","parsing error");
//        }
//    }
//}