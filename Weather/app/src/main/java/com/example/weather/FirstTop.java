package com.example.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class FirstTop extends Fragment {

    private TextView wear_top1;
    private ImageView top_wear1;
    String strTop;
    int numTop;

    public FirstTop(String temp, int num) {
        strTop = temp;
        numTop = num;
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.view_top1,container,false);
        wear_top1 = rootView.findViewById(R.id.wear_top1);
        top_wear1 = rootView.findViewById(R.id.top_wear1);
        wear_top1.setText(strTop);
        top_wear1.setImageResource(numTop);
        return rootView;
    }
}