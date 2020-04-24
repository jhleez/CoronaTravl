package com.example.coronatravel;

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
            return "25";
        } else if (position == 6) {
            return "32";
        } else if (position == 7) {
            return "38";
        } else if (position == 8) {
            return "39";
        }
        return "";
    }

    public static String arrange(int position){
        if(position==0){
            return "A";
        }
        else if(position==1){
            return "B";
        }
        else  if(position ==2){
            return "C";
        }
        else  if(position == 3){
            return "D";
        }
        return "";
    }
}

//    public static String cat1(String ContentTypeId, int position){
//        if(ContentTypeId.equals(12)){
//            if(position == 0){
//                return "";
//            }
//            else if(position == 1){
//                return "A01";
//            }
//            else if(position == 2){
//                return "A02";
//            }
//        }
//    }
//}