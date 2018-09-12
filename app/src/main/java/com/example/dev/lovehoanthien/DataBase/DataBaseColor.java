package com.example.dev.lovehoanthien.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseColor {

    private final String DB_NAME="ChonMau";
    private final String TB_MAU="Mau";
    private final int DB_VERSION=1;
    private SQLiteDatabase database;

    public DataBaseColor(Context context){
        OpenHelper helper=new OpenHelper(context);
        database=helper.getWritableDatabase();
    }


    public class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String chonmau="CREATE TABLE IF NOT EXISTS Mau(_id INTEGER PRIMARY KEY AUTOINCREMENT,Color INTEGER)";
            db.execSQL(chonmau);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Mau");
            onCreate(db);
        }
    }

    public void insertMau(int color){
        ContentValues values=new ContentValues();
        values.put("Color",color);
        database.insert(TB_MAU,null,values);

    }

    public void updateMau(int color,int id){
        ContentValues values=new ContentValues();
        values.put("Color",color);
        database.update(TB_MAU,values,"_id="+id,null);
    }
    public Cursor getMau(){
        return database.query(TB_MAU,null,null,null,null,null,null);
    }
}
