package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Page1 extends AppCompatActivity {

    private EditText et_name;
    private RadioGroup choose_gender;
    private RadioButton male;
    private RadioButton female;
    private Button start;

    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        CheckFirstPageExecute();

        et_name = (EditText) findViewById(R.id.et_name);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        start = (Button)findViewById(R.id.start);
        choose_gender = (RadioGroup) findViewById(R.id.choose_gender);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        Intent i = new Intent(Page1.this, start_page.class);


        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);   //나이 환경변수 저장
        SharedPreferences.Editor edit_sex = sharedSEX.edit();

        SharedPreferences sharedNAME = getSharedPreferences("name", MODE_PRIVATE);   //나이 환경변수 저장
        SharedPreferences.Editor edit_name = sharedNAME.edit();


        choose_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                imm.hideSoftInputFromWindow(choose_gender.getWindowToken(), 0);
                if(checkedId==R.id.male){
                    edit_sex.putString("SEX", "male");
                }
                else if(checkedId==R.id.female){
                    edit_sex.putString("SEX", "female");
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_name.putString("NAME",et_name.getText().toString());
                edit_name.apply();
                edit_sex.apply();
                startActivity(i);
            }
        });
    }

    private void CheckFirstPageExecute() {
        SharedPreferences pref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);
        boolean first = pref.getBoolean("isFirst", false);
        if(first==false){
            Log.d("Is first Time?", "first");
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst",true);
            editor.apply();
            //앱 최초 실행시 하고 싶은 작업
        }else{
            Log.d("Is first Time?", "not first");
            Intent i = new Intent(Page1.this, start_page.class);
            startActivity(i);
        }
    }
}