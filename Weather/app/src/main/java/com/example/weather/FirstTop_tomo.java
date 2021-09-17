package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstTop_tomo extends Fragment {

    private TextView wear_top_tomo1;
    private ImageView top_wear_tomo1;
    String strTop;
    int numTop;

    public FirstTop_tomo(String top1, int num1) {
        strTop = top1;
        numTop = num1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView3 = (ViewGroup) inflater.inflate(R.layout.view_top1_tomo,container,false);
        wear_top_tomo1 = rootView3.findViewById(R.id.tomo_wear_top1);
        top_wear_tomo1 = rootView3.findViewById(R.id.tomo_top_wear1);
        wear_top_tomo1.setText(strTop);
        top_wear_tomo1.setImageResource(numTop);
        return rootView3;
    }
}