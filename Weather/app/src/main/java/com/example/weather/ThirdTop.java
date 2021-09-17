package com.example.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdTop extends Fragment {
    private TextView wear_top3;
    private ImageView top_wear3;
    String strTop;
    int numTop;
    public ThirdTop(String top3, int num3) {
        strTop = top3;
        numTop = num3;
        Log.v("third ", String.valueOf(numTop));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.view_top3,container,false);
        wear_top3 = rootView.findViewById(R.id.wear_top3);
        top_wear3 = rootView.findViewById(R.id.top_wear3);
        wear_top3.setText(strTop);
        top_wear3.setImageResource(numTop);
        return rootView;
    }
}