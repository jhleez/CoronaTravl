package com.example.coronatravel;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mask {
    Mask(String addr, String name, String remain_stat, String type, String stock_at) {
        this.addr = addr;
        this.name = name;
        this.remain_stat = remain_stat;
        this.type = type;
        this.stock_at = stock_at;
    }

    public static String count;
    private String addr; // 주소
    private String name;// 이름
    private String remain_stat; //재고       100개 이상(녹색): 'plenty' / 30개 이상 100개미만(노랑색): 'some' / 2개 이상 30개 미만(빨강색): 'few' / 1개 이하(회색): 'empty' / 판매중지: 'break'
    private String type; //타입 약국:       '01', 우체국: '02', 농협: '03'
    private String stock_at;// 입고시간

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr1) {
        this.addr = addr1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemain_stat() {
        return remain_stat;
    }

    public void setRemain_stat(String remain_stat) {
        this.remain_stat = remain_stat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStock_at() {
        return stock_at;
    }

    public void setStock_at(String stock_at) {
        this.stock_at = stock_at;
    }

    public static boolean JSONParsing(String JSONFROMMASK){
        String addr="";
        String name="";
        String remain_stat="";
        String type="";
        String stock_at="";
        Log.d("마스크","함수 진입");
        MainActivity.MASK_AraayList.clear();
        try {
            JSONObject jsonObject = new JSONObject(JSONFROMMASK);
            count = jsonObject.getString("count");
            String stores = jsonObject.getString("stores");
            JSONArray jsonArray_stores = new JSONArray(stores);
            for(int i=0;i<jsonArray_stores.length();i++){
                JSONObject subJsonObject = jsonArray_stores.getJSONObject(i);

                addr=subJsonObject.getString("addr");
                name = subJsonObject.getString("name");
                remain_stat = subJsonObject.getString("remain_stat");
                type = subJsonObject.getString("type");
                stock_at = subJsonObject.getString("stock_at");
                Log.d("마스크","어레이리스트 추가");
                Mask subclass = new Mask(addr,name,remain_stat,type,stock_at);
                MainActivity.MASK_AraayList.add(subclass);
                return true;
            }
        } catch (JSONException e) {
            Log.d("TAG","parsing error");
        }
        return false;
    }
}
