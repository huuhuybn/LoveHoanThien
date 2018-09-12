package com.example.dev.lovehoanthien.PassWord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.dev.lovehoanthien.DataBase.DataBase;
import com.example.dev.lovehoanthien.MainActivity;
import com.example.dev.lovehoanthien.R;

import java.util.List;

public class CreatPass extends AppCompatActivity {
    PatternLockView mPatternLockView;
    DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pass);
        dataBase=new DataBase(this);
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                SharedPreferences preferences=getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern));
                editor.apply();
                Cursor cursor=dataBase.getPassWord();
                if (cursor.moveToNext()){
                    dataBase.updatetPassWord(0,1);
                }else {
                    dataBase.PassWord(0);
                }
                Toast.makeText(CreatPass.this, "Mời bạn khởi đông lại ", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCleared() {

            }
        });
    }
}
