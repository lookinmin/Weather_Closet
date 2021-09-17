package com.example.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondBottom extends Fragment {

    private TextView wear_bottom2;
    private ImageView bottom_wear2;
    String strBottom;
    int numBottom;

    public SecondBottom(String bottoms2, int num2) {
        strBottom = bottoms2;
        numBottom = num2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView2 = (ViewGroup) inflater.inflate(R.layout.view_bottom2,container,false);
        wear_bottom2 = rootView2.findViewById(R.id.wear_bottom2);
        bottom_wear2 = rootView2.findViewById(R.id.bottom_wear2);
        wear_bottom2.setText(strBottom);
        bottom_wear2.setImageResource(numBottom);
        return rootView2;
    }
}