package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondTop_tomo extends Fragment {

    private TextView tomo_wear_top2;
    private ImageView tomo_top_wear2;
    String strTop;
    int numTop;

    public SecondTop_tomo(String top2, int num2) {
        strTop = top2;
        numTop = num2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView3 = (ViewGroup) inflater.inflate(R.layout.view_top2_tomo,container,false);
        tomo_wear_top2 = rootView3.findViewById(R.id.tomo_wear_top2);
        tomo_top_wear2 = rootView3.findViewById(R.id.tomo_top_wear2);
        tomo_wear_top2.setText(strTop);
        tomo_top_wear2.setImageResource(numTop);
        return rootView3;
    }
}