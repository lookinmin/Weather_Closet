package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondBottom_tomo extends Fragment {

    private TextView tomo_wear_bottom2;
    private ImageView tomo_bottom_wear2;
    String strBottom;
    int numBottom;

    public SecondBottom_tomo(String bottoms2, int num2) {
        strBottom = bottoms2;
        numBottom = num2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView4 = (ViewGroup) inflater.inflate(R.layout.view_bottom2_tomo,container,false);
        tomo_wear_bottom2 = rootView4.findViewById(R.id.tomo_wear_bottom2);
        tomo_bottom_wear2 = rootView4.findViewById(R.id.tomo_bottom_wear2);
        tomo_wear_bottom2.setText(strBottom);
        tomo_bottom_wear2.setImageResource(numBottom);
        return rootView4;
    }
}