package com.example.dev.lovehoanthien.ChinhSuaDuLieu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dev.lovehoanthien.R;

public class Activity_ChinhSuaDuLieu extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_chinhsua,container,false);
        return view;
    }

    void AnhXa(){

    }
}
