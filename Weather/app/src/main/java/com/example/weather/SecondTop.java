package com.example.weather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondTop extends Fragment {
    private TextView wear_top2;
    private ImageView top_wear2;
    String strTop;
    int numTop;
    public SecondTop(String top2, int num2) {
        strTop = top2;
        numTop = num2;
        Log.v("second", String.valueOf(numTop));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.view_top2,container,false);
        wear_top2 = rootView.findViewById(R.id.wear_top2);
        top_wear2 = rootView.findViewById(R.id.top_wear2);
        wear_top2.setText(strTop);
        top_wear2.setImageResource(numTop);
        return rootView;
    }
}