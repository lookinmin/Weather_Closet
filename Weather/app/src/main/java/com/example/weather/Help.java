package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Help extends AppCompatActivity {

    private ImageButton fix;
    private ImageButton fix2;
    private EditText et_name;
    private RadioGroup choose_gender;
    private RadioButton male;
    private RadioButton female;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fix = (ImageButton) findViewById(R.id.fix);
        fix2 = (ImageButton) findViewById(R.id.fix2);
        et_name = (EditText)findViewById(R.id.et_name);
        choose_gender = (RadioGroup) findViewById(R.id.choose_gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        SharedPreferences sharedNAME = getSharedPreferences("name", MODE_PRIVATE);   //나이 환경변수 저장
        SharedPreferences.Editor edit_name = sharedNAME.edit();
        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);   //나이 환경변수 저장
        SharedPreferences.Editor edit_sex = sharedSEX.edit();

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

        fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "닉네임이 "+et_name.getText()+"로 변경되었습니다.", Toast.LENGTH_LONG).show();
                edit_name.putString("NAME",et_name.getText().toString());
                edit_name.apply();
            }
        });

        fix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Help.this, "성별이 변경되었습니다.", Toast.LENGTH_LONG).show();
                edit_sex.apply();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Help.this, Page2.class);
        startActivity(intent);
    }
}