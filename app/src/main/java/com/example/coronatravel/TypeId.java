package com.example.coronatravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}