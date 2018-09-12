package com.example.dev.lovehoanthien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Gif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        final Thread thread =new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(Gif.this,MainActivity.class));
                    overridePendingTransition(R.anim.divao,R.anim.dira);
                    finish();
                }
            }
        };

        thread.start();
    }
}
