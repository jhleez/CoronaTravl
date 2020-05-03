package com.example.coronatravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBase.CreateDb._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBase.CreateDb._TABLENAME0);
            onCreate(db);
            //    open();
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }


    public void create(){
        mDBHelper.onCreate(mDB);
    }


    public void close(){
        mDB.close();
    }



    public static final String ADDR1 = "addr1";
    public static final String CONTENTID = "contentid";
    public static final String CONTENTTYPEID = "contenttypeid";
    public static final String FIRSTIMAGE = "firstimage";
    public static final String TITLE = "title";
    // Insert DB
    public long insertColumn(String addr1, String contentid, String contenttypeid, String firstimage, String title){
        ContentValues values = new ContentValues();
        values.put(DataBase.CreateDb.ADDR1,addr1);
        values.put(DataBase.CreateDb.CONTENTID,contentid);
        values.put(DataBase.CreateDb.CONTENTTYPEID,contenttypeid);
        values.put(DataBase.CreateDb.FIRSTIMAGE,firstimage);
        values.put(DataBase.CreateDb.TITLE,title);
        return mDB.insert(DataBase.CreateDb._TABLENAME0, null, values);
    }

    //Update DB
//    public boolean updateColumn(int id, int number){
//        ContentValues values = new ContentValues();
//        values.put(DataBase.CreateDb.NUMBER, number);
//        r
//        eturn mDB.update(DataBase.CreateDb._TABLENAME0, values, "_id=" + id, null) > 0;
//    }
//


    // Delete All
    public void deleteAllColumns() {
        mDB.delete(DataBase.CreateDb._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(String contentid){
        return mDB.delete(DataBase.CreateDb._TABLENAME0, "contentid="+contentid, null) > 0;
    }
    // Select DB
    public Cursor selectColumns(String contentid){
        String sqlSelect = "SELECT * FROM usertable WHERE CONTENTID='"+contentid+"'      ";

        return mDB.rawQuery(sqlSelect, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }

    public Cursor searchColumn(){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ;", null);
        return c;
    }
}