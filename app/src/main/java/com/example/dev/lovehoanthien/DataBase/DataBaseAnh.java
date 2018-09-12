package com.example.dev.lovehoanthien.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataBaseAnh extends SQLiteOpenHelper {
    public DataBaseAnh(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public void INSERT_ANH(byte[] anh){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String sql="INSERT INTO AnhNam VALUES(null,?)";
        SQLiteStatement statement=sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,anh);
        statement.executeInsert();


    }

    public void INSERT_ANH1(byte[] anh){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String sql="INSERT INTO AnhNu VALUES(null,?)";
        SQLiteStatement statement=sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,anh);
        statement.executeInsert();


    }

    public void Update_AnhNam(byte[] anh,int id){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String sql="INSERT INTO AnhNam VALUES(null,?)";
        SQLiteStatement statement=sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,anh);
        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
