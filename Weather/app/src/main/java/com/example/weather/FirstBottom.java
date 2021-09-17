package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.internal.IMapFragmentDelegate;

public class FirstBottom extends Fragment {

    private TextView wear_bottom1;
    private ImageView bottom_wear1;
    String strBottom;
    int numBottom;

    public FirstBottom(String bottoms1, int num1) {
        strBottom = bottoms1;
        numBottom = num1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.view_bottom1,container,false);
        wear_bottom1 = rootView2.findViewById(R.id.wear_bottom1);
        bottom_wear1 = rootView2.findViewById(R.id.bottom_wear1);
        wear_bottom1.setText(strBottom);
        bottom_wear1.setImageResource(numBottom);
        return rootView2;
    }
}