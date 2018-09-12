package com.example.dev.lovehoanthien.SpanSceen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dev.lovehoanthien.DataBase.DataBase;
import com.example.dev.lovehoanthien.KhoiTaoDuLieu.KhoiTao_Activity;
import com.example.dev.lovehoanthien.MainActivity;
import com.example.dev.lovehoanthien.PassWord.CreatPass;
import com.example.dev.lovehoanthien.PassWord.InPutPass;
import com.example.dev.lovehoanthien.R;

public class Span_Screen extends AppCompatActivity {
    private Cursor cursorData, cursorData1;
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span__screen);
        dataBase=new DataBase(this);


        ChuyenTrang();


    }

    void Chuyen(){

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2800);
                }catch (Exception e){

                }finally {
                    startActivity(new Intent(Span_Screen.this, KhoiTao_Activity.class));
                    finish();
                }
            }
        };
        thread.start();
        }

    void dulieu() {
        cursorData = dataBase.getSoNgayYeu();
        cursorData1 = dataBase.getTen();
        if (!cursorData1.moveToNext() || !cursorData.moveToNext()) {
            startActivity(new Intent(Span_Screen.this, KhoiTao_Activity.class));
            overridePendingTransition(R.anim.divao,R.anim.dira);
            finish();
        } else {

            startActivity(new Intent(Span_Screen.this, MainActivity.class));
            overridePendingTransition(R.anim.divao,R.anim.dira);
            finish();
        }
    }

    private void ChuyenTrang(){
        final Intent intent=new Intent(this,KhoiTao_Activity.class);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (Exception e){

                }finally {
                    Cursor cursor=dataBase.getPassWord();
                    SharedPreferences preferences=getSharedPreferences("PREFS",0);
                    String password=preferences.getString("password","0");
                    if (cursor.moveToNext()){
                        int a=cursor.getInt(1);
                        if (a==0){

                            if (password.equals("0")){
                                Intent intent1=new Intent(getApplicationContext(), CreatPass.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.divao,R.anim.dira);
                                finish();
                            }else {
                                Intent intent1=new Intent(getApplicationContext(), InPutPass.class);
                                startActivity(intent1);
                                finish();
                            }

                        }else {
                            dulieu();
                            overridePendingTransition(R.anim.divao,R.anim.dira);
                            finish();
                        }


                        }else {
                        dulieu();
                        overridePendingTransition(R.anim.divao,R.anim.dira);
                        finish();
                    }


                }
            }
        };
        thread.start();
    }
}
