package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstBottom_tomo extends Fragment {

    private TextView tomo_wear_bottom1;
    private ImageView tomo_bottom_wear1;
    String strBottom;
    int numBottom;

    public FirstBottom_tomo(String bottoms1, int num1) {
        strBottom = bottoms1;
        numBottom = num1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView4 = (ViewGroup) inflater.inflate(R.layout.view_bottom1_tomo, container, false);
        tomo_wear_bottom1 = rootView4.findViewById(R.id.tomo_wear_bottom1);
        tomo_bottom_wear1 = rootView4.findViewById(R.id.tomo_bottom_wear1);
        tomo_wear_bottom1.setText(strBottom);
        tomo_bottom_wear1.setImageResource(numBottom);
        return rootView4;
    }
}