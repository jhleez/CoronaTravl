package com.example.coronatravel;

import android.provider.BaseColumns;

public final class DataBase {
    public static final class CreateDb implements BaseColumns {

        public static final String _TABLENAME0 = "usertable";
        public static final String ADDR1 = "addr1";
        public static final String CONTENTID = "contentid";
        public static final String CONTENTTYPEID = "contenttypeid";
        public static final String FIRSTIMAGE = "firstimage";
        public static final String TITLE = "title";

        public static final String _CREATE0 = "CREATE TABLE IF NOT EXISTS " + _TABLENAME0 + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADDR1 + " TEXT, "
                + CONTENTID + " TEXT, "
                + CONTENTTYPEID + " TEXT, "
                + FIRSTIMAGE + " TEXT, "
                + TITLE + " TEXT );";
    }
}