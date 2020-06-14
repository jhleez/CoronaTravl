package com.rammus.covidtravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class detailInfo_15 {
    detailInfo_15(){};
    detailInfo_15(String agelimit, String bookingplace, String discountinfofestival, String eventenddate,String eventhomepage,String eventplace, String placeinfo,String eventstartdate, String playtime,String program,String spendtimefestival,String usetimefestival){
        this.agelimit=agelimit;
        this.bookingplace=bookingplace;
        this.discountinfofestival=discountinfofestival;
        this.eventenddate=eventenddate;
        this.eventhomepage=eventhomepage;
        this.eventplace=eventplace;
        this.eventstartdate=eventstartdate;
        this.placeinfo=placeinfo;
        this.playtime =playtime;
        this.program=program;
        this.spendtimefestival=spendtimefestival;
        this.usetimefestival=usetimefestival;
    }

    private String agelimit; //관람가능연력
    private String bookingplace; // 예매처
    private String discountinfofestival; //할인정보
    private String eventenddate; // 행사 종료일
    private String eventhomepage; // 행사 홈페이지
    private String eventplace; // 행사장소
    private String eventstartdate; //행사 시작일
    private String placeinfo; // 행사장 위치 안내
    private String playtime; //공연시간
    private String program; // 행사 프로그램
    private String spendtimefestival; // 관람 소요시간
    private String usetimefestival; // 이용요금

    public String getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(String agelimit) {
        this.agelimit = agelimit;
    }

    public String getBookingplace() {
        return bookingplace;
    }

    public void setBookingplace(String bookingplace) {
        this.bookingplace = bookingplace;
    }

    public String getDiscountinfofestival() {
        return discountinfofestival;
    }

    public void setDiscountinfofestival(String discountinfofestival) {
        this.discountinfofestival = discountinfofestival;
    }

    public String getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(String eventenddate) {
        this.eventenddate = eventenddate;
    }

    public String getEventhomepage() {
        return eventhomepage;
    }

    public void setEventhomepage(String eventhomepage) {
        this.eventhomepage = eventhomepage;
    }

    public String getEventplace() {
        return eventplace;
    }

    public void setEventplace(String eventplace) {
        this.eventplace = eventplace;
    }

    public String getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(String eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getPlaceinfo() {
        return placeinfo;
    }

    public void setPlaceinfo(String placeinfo) {
        this.placeinfo = placeinfo;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSpendtimefestival() {
        return spendtimefestival;
    }

    public void setSpendtimefestival(String spendtimefestival) {
        this.spendtimefestival = spendtimefestival;
    }

    public String getUsetimefestival() {
        return usetimefestival;
    }

    public void setUsetimefestival(String usetimefestival) {
        this.usetimefestival = usetimefestival;
    }



 public detailInfo_15 JSONParsing(String JSONFromLocationBasedListaddr) {
     String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
     detailInfo_15 subclass = null;
     String agelimit; //관람가능연력ㅇ
     String bookingplace; // 예매처
     String discountinfofestival; //할인정보
     String eventenddate; // 행사 종료일
     String eventhomepage; // 행사 홈페이지
     String eventplace; // 행사장소
     String eventstartdate; //행사 시작일
     String placeinfo; // 행사장 위치 안내
     String playtime; //공연시간
     String program; // 행사 프로그램
     String spendtimefestival; // 관람 소요시간
     String usetimefestival; // 이용요금

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
             agelimit = jsonObject_item.getString("agelimit");
         } catch (JSONException e) {
             agelimit = "";
         }
         try {
             bookingplace = jsonObject_item.getString("bookingplace");
         } catch (JSONException e) {
             bookingplace = "";
         }
         try {
             discountinfofestival = jsonObject_item.getString("discountinfofestival");
         } catch (JSONException e) {
             discountinfofestival = "";
         }
         try {
             eventenddate = jsonObject_item.getString("eventenddate");
         } catch (JSONException e) {
             eventenddate = "";
         }
         try {
             eventhomepage = jsonObject_item.getString("eventhomepage");
         } catch (JSONException e) {
             eventhomepage = "";
         }
         try {
             eventplace = jsonObject_item.getString("eventplace");
         } catch (JSONException e) {
             eventplace = "";
         }
         try {
             eventstartdate = jsonObject_item.getString("eventstartdate");
         } catch (JSONException e) {
             eventstartdate = "";
         }
         try {
             placeinfo = jsonObject_item.getString("placeinfo");
         } catch (JSONException e) {
             placeinfo = "";
         }
         try {
             playtime = jsonObject_item.getString("playtime");
         } catch (JSONException e) {
             playtime = "";
         }
         try {
             program = jsonObject_item.getString("program");
         } catch (JSONException e) {
             program = "";
         }
         try {
             spendtimefestival = jsonObject_item.getString("spendtimefestival");
         } catch (JSONException e) {
             spendtimefestival = "";
         }
         try {
             usetimefestival = jsonObject_item.getString("usetimefestival");
         } catch (JSONException e) {
             usetimefestival = "";
         }


             subclass = new detailInfo_15(agelimit, bookingplace, discountinfofestival, eventenddate, eventhomepage, eventplace, placeinfo,eventstartdate
                , playtime, program, spendtimefestival, usetimefestival);
        } catch (JSONException e) {
              Log.d("TAG", "detailInfo_15 parsing error");
         }
        return subclass;
    }
}