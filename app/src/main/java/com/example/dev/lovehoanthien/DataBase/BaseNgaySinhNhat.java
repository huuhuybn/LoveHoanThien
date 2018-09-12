package com.example.dev.lovehoanthien.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseNgaySinhNhat {

    private final String DB_NAME="SinhNhat";
    private final String TB_NSNAM="SinhNhatNam";
    private final String TB_NSNU="SinhNhatNu";
    private final int DB_VERSION=1;
    private SQLiteDatabase database;

    public BaseNgaySinhNhat(Context context){
        OpenHelper helper=new OpenHelper(context);
        database=helper.getWritableDatabase();
    }


    public class OpenHelper extends SQLiteOpenHelper{
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String taobangNam="CREATE TABLE IF NOT EXISTS SinhNhatNam(_id INTEGER PRIMARY KEY AUTOINCREMENT,Ngay INTEGER,Thang INTEGER,Nam INTEGER)";
            String taobangNu="CREATE TABLE IF NOT EXISTS SinhNhatNu(_id INTEGER PRIMARY KEY AUTOINCREMENT,Ngay INTEGER,Thang INTEGER,Nam INTEGER)";
            db.execSQL(taobangNam);
            db.execSQL(taobangNu);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS SinhNhatNam");
          db.execSQL("DROP TABLE IF EXISTS SinhNhatNu");
          onCreate(db);
        }

    }

    public  void insertNgaySinhNhatNam(int ngay,int thang,int nam){
        ContentValues values=new ContentValues();
        values.put("Ngay",ngay);
        values.put("Thang",thang);
        values.put("Nam",nam);
        database.insert(TB_NSNAM,null,values);
    }
   public void insertNgaySinhNhatNu(int ngay,int thang,int nam){
        ContentValues values=new ContentValues();
        values.put("Ngay",ngay);
        values.put("Thang",thang);
        values.put("Nam",nam);
        database.insert(TB_NSNU,null,values);
    }

   public void updateNgaySinhNhatNam(int ngay,int thang,int nam,int id){
        ContentValues values=new ContentValues();
        values.put("Ngay",ngay);
        values.put("Thang",thang);
        values.put("Nam",nam);
        database.update(TB_NSNAM,values,"_id="+id,null);
    }

    public void updateNgaySinhNhatNu(int ngay,int thang,int nam,int id){
        ContentValues values=new ContentValues();
        values.put("Ngay",ngay);
        values.put("Thang",thang);
        values.put("Nam",nam);
        database.update(TB_NSNU,values,"_id="+id,null);
    }

    public Cursor getNam(){
      return   database.query(TB_NSNAM,null,null,null,null,null,null);
    }
    public Cursor getNu(){
        return   database.query(TB_NSNU,null,null,null,null,null,null);
    }
}
