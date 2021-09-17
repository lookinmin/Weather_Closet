package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdBottom_tomo extends Fragment {

    private TextView tomo_wear_bottom3;
    private ImageView tomo_bottom_wear3;
    String strBottom;
    int numBottom;

    public ThirdBottom_tomo(String bottoms3, int num3) {
        strBottom = bottoms3;
        numBottom = num3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView4 = (ViewGroup) inflater.inflate(R.layout.view_bottom3_tomo,container,false);
        tomo_wear_bottom3 = rootView4.findViewById(R.id.tomo_wear_bottom3);
        tomo_bottom_wear3 = rootView4.findViewById(R.id.tomo_bottom_wear3);
        tomo_wear_bottom3.setText(strBottom);
        tomo_bottom_wear3.setImageResource(numBottom);
        return rootView4;
    }
}