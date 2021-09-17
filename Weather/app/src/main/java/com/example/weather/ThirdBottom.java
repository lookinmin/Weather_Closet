package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdBottom extends Fragment {

    private TextView wear_bottom3;
    private ImageView bottom_wear3;
    String strBottom;
    int numBottom;

    public ThirdBottom(String bottoms3, int num3) {
        strBottom = bottoms3;
        numBottom = num3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.view_bottom3,container,false);
        wear_bottom3 = rootView2.findViewById(R.id.wear_bottom3);
        bottom_wear3 = rootView2.findViewById(R.id.bottom_wear3);
        wear_bottom3.setText(strBottom);
        bottom_wear3.setImageResource(numBottom);
        return rootView2;
    }
}