package com.example.dev.lovehoanthien.PassWord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.example.dev.lovehoanthien.MainActivity;
import com.example.dev.lovehoanthien.R;

import java.util.List;

public class InPutPass extends AppCompatActivity {
    PatternLockView mPatternLockView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pass);
        SharedPreferences preferences=getSharedPreferences("PREFS",0);
        final String password=preferences.getString("password","0");

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
                if (password.equals( PatternLockUtils.patternToString(mPatternLockView, pattern))){
                    Intent intent1=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();
                }

            }

            @Override
            public void onCleared() {

            }
        });

    }
}
