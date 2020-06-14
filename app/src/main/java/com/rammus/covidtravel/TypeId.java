package com.rammus.covidtravel;

public class TypeId {
    public static String ContentTypeId(int position) {

        if (position == 0) {
            return "";
        } else if (position == 1) {
            return "12";
        } else if (position == 2) {
            return "14";
        } else if (position == 3) {
            return "15";
        } else if (position == 4) {
            return "25";
        } else if (position == 5) {
            return "28";
        } else if (position == 6) {
            return "32";
        } else if (position == 7) {
            return "38";
        } else if (position == 8) {
            return "39";
        }
        return "";
    }

    public static String cat1(int position) {
        if (position == 0) {
            return "";
        } else if (position == 1) {
            return "A01";
        } else if (position == 2) {
            return "A02";
        } else if (position == 3) {
            return "A03";
        } else if (position == 4) {
            return "A04";
        } else if (position == 5) {
            return "A05";
        } else if (position == 6) {
            return "B02";
        } else if (position == 7) {
            return "C01";
        }
        return "";
    }


    public static String cat2(int cat1, int position) {
        if (position == 0) {
            return "";
        }
        if (cat1 == 1) {
            return "A010" + String.valueOf(position);
        } else if (cat1 == 2) {
            return "A020" + String.valueOf(position);
        } else if (cat1 == 3) {
            return "A030" + String.valueOf(position);
        } else if (cat1 == 4) {
            return "A0401";
        } else if (cat1 == 5) {
            return "A0502";
        } else if (cat1 == 6) {
            return "B0201";
        } else if (cat1 == 7) {
            return "C011" + String.valueOf(position + 1);
        }
        return "";
    }

    public static String arrange(int position) {
        if (position == 0) {
            return "A";
        } else if (position == 1) {
            return "B";
        } else if (position == 2) {
            return "C";
        } else if (position == 3) {
            return "D";
        }
        return "";
    }

    public static String areacode(int position){
        if(position == 0){
            return "";
        }
        if(position <= 8){
            return String.valueOf(position);
        }
        if(position > 8){
            return String.valueOf(position + 22);
        }
        return "";
    }

    public static String nxny(String x) {
        if(x.equals("1")){
            return "nx=60&ny=127";
        }
        if(x.equals("2")){
            return "nx=55&ny=124";
        }
        if(x.equals("3")){
            return "nx=67&ny=100";
        }
        if(x.equals("4")){
            return "nx=89&ny=90";
        }
        if(x.equals("5")){
            return "nx=58&ny=74";
        }
        if(x.equals("6")){
            return "nx=98&ny=76";
        }
        if(x.equals("7")){
            return "nx=102&ny=84";
        }
        if(x.equals("8")){
            return "nx=66&ny=103";
        }
        if(x.equals("31")){
            return "nx=60&ny=120";
        }
        if(x.equals("32")){
            return "nx=73&ny=134";
        }
        if(x.equals("33")){
            return "nx=69&ny=107";
        }
        if(x.equals("34")){
            return "nx=68&ny=100";
        }
        if(x.equals("35")){
            return "nx=89&ny=91";
        }
        if(x.equals("36")){
            return "nx=91&ny=77";
        }
        if(x.equals("37")){
            return "nx=63&ny=89";
        }
        if(x.equals("38")){
            return "nx=51&ny=67";
        }
        if(x.equals("39")){
            return "nx=52&ny=38";
        }
        return"";
    }
}