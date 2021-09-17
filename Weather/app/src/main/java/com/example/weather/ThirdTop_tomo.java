package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ThirdTop_tomo extends Fragment {

    private TextView tomo_wear_top3;
    private ImageView tomo_top_wear3;
    String strTop;
    int numTop;

    public ThirdTop_tomo(String top3, int num3) {
        strTop = top3;
        numTop = num3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView3 = (ViewGroup) inflater.inflate(R.layout.view_top3_tomo,container,false);
        tomo_wear_top3 = rootView3.findViewById(R.id.tomo_wear_top3);
        tomo_top_wear3 = rootView3.findViewById(R.id.tomo_top_wear3);
        tomo_wear_top3.setText(strTop);
        tomo_top_wear3.setImageResource(numTop);
        return rootView3;
    }
}