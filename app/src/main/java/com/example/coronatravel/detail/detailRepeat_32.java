package com.example.coronatravel.detail;

import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detailRepeat_32 {
    public static ArrayList<detailRepeat_32> repeat_array = new ArrayList<>();

    public detailRepeat_32() {
    }

    public detailRepeat_32(String roomtitle, String roomsize1, String roombasecount, String roommaxcount, String roomimg1, String roomimg2, String roomimg3, String roomimg4, String roomimg5, String roomoffseasonminfee1, String roomoffseasonminfee2, String roompeakseasonminfee1, String roompeakseasonminfee2) {
        this.roomtitle = roomtitle;
        this.roomsize1 = roomsize1;
        this.roombasecount = roombasecount;
        this.roommaxcount = roommaxcount;
        this.roomimg1 = roomimg1;
        this.roomimg2 = roomimg2;
        this.roomimg3 = roomimg3;
        this.roomimg4 = roomimg4;
        this.roomimg5 = roomimg5;
        this.roomoffseasonminfee1 = roomoffseasonminfee1;
        this.roomoffseasonminfee2 = roomoffseasonminfee2;
        this.roompeakseasonminfee1 = roompeakseasonminfee1;
        this.roompeakseasonminfee2 = roompeakseasonminfee2;
    }

    private String roomtitle; // 방이름
    private String roomsize1; // 방 사이즈(평수)
    private String roombasecount; // 기준인원
    private String roommaxcount; // 최대 인원
    private String roomimg1; // 방사진 1~5, 5개까지 없을수도 있음
    private String roomimg2;
    private String roomimg3;
    private String roomimg4;
    private String roomimg5;
    private String roomoffseasonminfee1; //비수기주중최소 가격
    private String roomoffseasonminfee2; // 비수기 주말 최소 가격
    private String roompeakseasonminfee1; //성수기 주중 최소 가격
    private String roompeakseasonminfee2;// 성수기 주말 최소 가격


    public String getRoomtitle() {
        return roomtitle;
    }

    public void setRoomtitle(String roomtitle) {
        this.roomtitle = roomtitle;
    }

    public String getRoomsize1() {
        return roomsize1;
    }

    public void setRoomsize1(String roomsize1) {
        this.roomsize1 = roomsize1;
    }

    public String getRoombasecount() {
        return roombasecount;
    }

    public void setRoombasecount(String roombasecount) {
        this.roombasecount = roombasecount;
    }

    public String getRoommaxcount() {
        return roommaxcount;
    }

    public void setRoommaxcount(String roommaxcount) {
        this.roommaxcount = roommaxcount;
    }

    public String getRoomimg1() {
        return roomimg1;
    }

    public void setRoomimg1(String roomimg1) {
        this.roomimg1 = roomimg1;
    }

    public String getRoomimg2() {
        return roomimg2;
    }

    public void setRoomimg2(String roomimg2) {
        this.roomimg2 = roomimg2;
    }

    public String getRoomimg3() {
        return roomimg3;
    }

    public void setRoomimg3(String roomimg3) {
        this.roomimg3 = roomimg3;
    }

    public String getRoomimg4() {
        return roomimg4;
    }

    public void setRoomimg4(String roomimg4) {
        this.roomimg4 = roomimg4;
    }

    public String getRoomimg5() {
        return roomimg5;
    }

    public void setRoomimg5(String roomimg5) {
        this.roomimg5 = roomimg5;
    }

    public String getRoomoffseasonminfee1() {
        return roomoffseasonminfee1;
    }

    public void setRoomoffseasonminfee1(String roomoffseasonminfee1) {
        this.roomoffseasonminfee1 = roomoffseasonminfee1;
    }

    public String getRoomoffseasonminfee2() {
        return roomoffseasonminfee2;
    }

    public void setRoomoffseasonminfee2(String roomoffseasonminfee2) {
        this.roomoffseasonminfee2 = roomoffseasonminfee2;
    }

    public String getRoompeakseasonminfee1() {
        return roompeakseasonminfee1;
    }

    public void setRoompeakseasonminfee1(String roompeakseasonminfee1) {
        this.roompeakseasonminfee1 = roompeakseasonminfee1;
    }

    public String getRoompeakseasonminfee2() {
        return roompeakseasonminfee2;
    }

    public void setRoompeakseasonminfee2(String roompeakseasonminfee2) {
        this.roompeakseasonminfee2 = roompeakseasonminfee2;
    }

    public static detailRepeat_32 JSONParsing(String JSONFromLocationBasedListaddr) {
        repeat_array.clear();
        String removehtml = Html.fromHtml(JSONFromLocationBasedListaddr).toString();
        detailRepeat_32 subclass = null;

        String roomtitle; // 방이름
        String roomsize1; // 방 사이즈(평수)
        String roombasecount; // 기준인원
        String roommaxcount; // 최대 인원
        String roomimg1; // 방사진 1~5, 5개까지 없을수도 있음
        String roomimg2;
        String roomimg3;
        String roomimg4;
        String roomimg5;
        String roomoffseasonminfee1; //비수기주중최소 가격
        String roomoffseasonminfee2; // 비수기 주말 최소 가격
        String roompeakseasonminfee1; //성수기 주중 최소 가격
        String roompeakseasonminfee2;// 성수기 주말 최소 가격
        String totalcount;
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

            if (totalcount.equals("1")) {
                JSONObject jsonObject_item = new JSONObject(item);
                try {
                    roomtitle = jsonObject_item.getString("roomtitle");
                } catch (JSONException e) {
                    roomtitle = "";
                }
                try {
                    roomsize1 = jsonObject_item.getString("roomsize1");
                } catch (JSONException e) {
                    roomsize1 = "";
                }
                try {
                    roombasecount = jsonObject_item.getString("roombasecount");
                } catch (JSONException e) {
                    roombasecount = "";
                }
                try {
                    roommaxcount = jsonObject_item.getString("roommaxcount");
                } catch (JSONException e) {
                    roommaxcount = "";
                }
                try {
                    roomimg1 = jsonObject_item.getString("roomimg1");
                } catch (JSONException e) {
                    roomimg1 = "";
                }
                try {
                    roomimg2 = jsonObject_item.getString("roomimg2");
                } catch (JSONException e) {
                    roomimg2 = "";
                }
                try {
                    roomimg3 = jsonObject_item.getString("roomimg3");
                } catch (JSONException e) {
                    roomimg3 = "";
                }
                try {
                    roomimg4 = jsonObject_item.getString("roomimg4");
                } catch (JSONException e) {
                    roomimg4 = "";
                }
                try {
                    roomimg5 = jsonObject_item.getString("roomimg5");
                } catch (JSONException e) {
                    roomimg5 = "";
                }
                try {
                    roomoffseasonminfee1 = jsonObject_item.getString("roomoffseasonminfee1");
                } catch (JSONException e) {
                    roomoffseasonminfee1 = "";
                }

                try {
                    roomoffseasonminfee2 = jsonObject_item.getString("roomoffseasonminfee2");
                } catch (JSONException e) {
                    roomoffseasonminfee2 = "";
                }
                try {
                    roompeakseasonminfee1 = jsonObject_item.getString("roompeakseasonminfee1");
                } catch (JSONException e) {
                    roompeakseasonminfee1 = "";
                }
                try {
                    roompeakseasonminfee2 = jsonObject_item.getString("roompeakseasonminfee2");
                } catch (JSONException e) {
                    roompeakseasonminfee2 = "";
                }

                subclass = new detailRepeat_32(roomtitle, roomsize1, roombasecount, roommaxcount, roomimg1, roomimg2,
                        roomimg3, roomimg4, roomimg5, roomoffseasonminfee1, roomoffseasonminfee2, roompeakseasonminfee1, roompeakseasonminfee2);
                repeat_array.add(subclass);

            } else {
                JSONArray jsonArray_item = new JSONArray(item);

                for (int i = 0; i < jsonArray_item.length(); i++) {
                    JSONObject subJsonObject = jsonArray_item.getJSONObject(i);
                    try {
                        roomtitle = subJsonObject.getString("roomtitle");
                    } catch (JSONException e) {
                        roomtitle = "";
                    }
                    try {
                        roomsize1 = subJsonObject.getString("roomsize1");
                    } catch (JSONException e) {
                        roomsize1 = "";
                    }
                    try {
                        roombasecount = subJsonObject.getString("roombasecount");
                    } catch (JSONException e) {
                        roombasecount = "";
                    }
                    try {
                        roommaxcount = subJsonObject.getString("roommaxcount");
                    } catch (JSONException e) {
                        roommaxcount = "";
                    }
                    try {
                        roomimg1 = subJsonObject.getString("roomimg1");
                    } catch (JSONException e) {
                        roomimg1 = "";
                    }
                    try {
                        roomimg2 = subJsonObject.getString("roomimg2");
                    } catch (JSONException e) {
                        roomimg2 = "";
                    }
                    try {
                        roomimg3 = subJsonObject.getString("roomimg3");
                    } catch (JSONException e) {
                        roomimg3 = "";
                    }
                    try {
                        roomimg4 = subJsonObject.getString("roomimg4");
                    } catch (JSONException e) {
                        roomimg4 = "";
                    }
                    try {
                        roomimg5 = subJsonObject.getString("roomimg5");
                    } catch (JSONException e) {
                        roomimg5 = "";
                    }
                    try {
                        roomoffseasonminfee1 = subJsonObject.getString("roomoffseasonminfee1");
                    } catch (JSONException e) {
                        roomoffseasonminfee1 = "";
                    }

                    try {
                        roomoffseasonminfee2 = subJsonObject.getString("roomoffseasonminfee2");
                    } catch (JSONException e) {
                        roomoffseasonminfee2 = "";
                    }
                    try {
                        roompeakseasonminfee1 = subJsonObject.getString("roompeakseasonminfee1");
                    } catch (JSONException e) {
                        roompeakseasonminfee1 = "";
                    }
                    try {
                        roompeakseasonminfee2 = subJsonObject.getString("roompeakseasonminfee2");
                    } catch (JSONException e) {
                        roompeakseasonminfee2 = "";
                    }

                    subclass = new detailRepeat_32(roomtitle, roomsize1, roombasecount, roommaxcount, roomimg1, roomimg2,
                            roomimg3, roomimg4, roomimg5, roomoffseasonminfee1, roomoffseasonminfee2, roompeakseasonminfee1, roompeakseasonminfee2);
                    repeat_array.add(subclass);
                }
            }
        } catch (JSONException e) {
            Log.d("TAG", "parsing error");
        }
        return subclass;
    }
}