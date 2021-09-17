package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

class WeekWeather{
    double minTemp, maxTemp, dayTemp, nightTemp;
    String humidity;
    WeekWeather(double minTemp, double maxTemp, String humidity, double dayTemp, double nightTemp){
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.humidity = humidity;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
    }
    public double getMinTemp(){
        return minTemp;
    }
    public double getMaxTemp(){
        return maxTemp;
    }
    public String getHumidity(){
        return humidity;
    }
    public double getDayTemp() { return dayTemp; }
    public double getNightTemp() { return nightTemp; }
}
public class Week extends AppCompatActivity {

    private LinearLayout background;
    private TextView day1;
    private TextView set_day1;
    private ImageView day1_pic;
    private TextView day1_avgtemp;
    private TextView day1_HT;
    private TextView day1_LT;
    private ImageView day1_humidity_image;
    private TextView day1_humidity;
    private ImageView day1_top_pic;
    private TextView day1_top;
    private ImageView day1_bottom_pic;
    private TextView day1_bottom;

    private TextView day2;
    private TextView set_day2;
    private ImageView day2_pic;
    private TextView day2_avgtemp;
    private TextView day2_HT;
    private TextView day2_LT;
    private ImageView day2_humidity_image;
    private TextView day2_humidity;
    private ImageView day2_top_pic;
    private TextView day2_top;
    private ImageView day2_bottom_pic;
    private TextView day2_bottom;

    private TextView day3;
    private TextView set_day3;
    private ImageView day3_pic;
    private TextView day3_avgtemp;
    private TextView day3_HT;
    private TextView day3_LT;
    private ImageView day3_humidity_image;
    private TextView day3_humidity;
    private ImageView day3_top_pic;
    private TextView day3_top;
    private ImageView day3_bottom_pic;
    private TextView day3_bottom;

    private TextView day4;
    private TextView set_day4;
    private ImageView day4_pic;
    private TextView day4_avgtemp;
    private TextView day4_HT;
    private TextView day4_LT;
    private ImageView day4_humidity_image;
    private TextView day4_humidity;
    private ImageView day4_top_pic;
    private TextView day4_top;
    private ImageView day4_bottom_pic;
    private TextView day4_bottom;

    private TextView day5;
    private TextView set_day5;
    private ImageView day5_pic;
    private TextView day5_avgtemp;
    private TextView day5_HT;
    private TextView day5_LT;
    private ImageView day5_humidity_image;
    private TextView day5_humidity;
    private ImageView day5_top_pic;
    private TextView day5_top;
    private ImageView day5_bottom_pic;
    private TextView day5_bottom;

    private TextView day6;
    private TextView set_day6;
    private ImageView day6_pic;
    private TextView day6_avgtemp;
    private TextView day6_HT;
    private TextView day6_LT;
    private ImageView day6_humidity_image;
    private TextView day6_humidity;
    private ImageView day6_top_pic;
    private TextView day6_top;
    private ImageView day6_bottom_pic;
    private TextView day6_bottom;

    private TextView day7;
    private TextView set_day7;
    private ImageView day7_pic;
    private TextView day7_avgtemp;
    private TextView day7_HT;
    private TextView day7_LT;
    private ImageView day7_humidity_image;
    private TextView day7_humidity;
    private ImageView day7_top_pic;
    private TextView day7_top;
    private ImageView day7_bottom_pic;
    private TextView day7_bottom;

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;

    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    Calendar c3 = Calendar.getInstance();
    Calendar c4 = Calendar.getInstance();
    Calendar c5 = Calendar.getInstance();
    Calendar c6 = Calendar.getInstance();
    Calendar c7 = Calendar.getInstance();

    final String App_ID = "739a4fc797b6fdd61987265968e8ecd8";
    final String WEEk_URL = "https://api.openweathermap.org/data/2.5/onecall?";
    String week_url;

    ArrayList<WeekWeather> weekWeathers = new ArrayList<>();
    ArrayList<String> tmpDescription = new ArrayList<>();

    ArrayList<Double> tempClothes = new ArrayList<>();
    ArrayList<String> top = new ArrayList<>();
    ArrayList<String> bottoms = new ArrayList<>();
    ArrayList<String> traingTop = new ArrayList<>();

    String last_top, last_bottoms;
    String spTop="", spBottoms="";
    boolean spFlag = true;
    String[] weekTop;
    String[] weekBottoms;

    String gender;

    public class DownloadJSON extends AsyncTask<String, Void , String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            String result="";
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while(data!=-1){
                    result+=(char)data;
                    data = inputStreamReader.read();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        background = findViewById(R.id.background);

        c1.add(Calendar.DATE, 1);
        int dayofWeek1 = c1.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DATE, 2);
        int dayofWeek2 = c2.get(Calendar.DAY_OF_WEEK);
        c3.add(Calendar.DATE, 3);
        int dayofWeek3 = c3.get(Calendar.DAY_OF_WEEK);
        c4.add(Calendar.DATE, 4);
        int dayofWeek4 = c4.get(Calendar.DAY_OF_WEEK);
        c5.add(Calendar.DATE, 5);
        int dayofWeek5 = c5.get(Calendar.DAY_OF_WEEK);
        c6.add(Calendar.DATE, 6);
        int dayofWeek6 = c6.get(Calendar.DAY_OF_WEEK);
        c7.add(Calendar.DATE, 7);
        int dayofWeek7 = c7.get(Calendar.DAY_OF_WEEK);

        day1 = (TextView)findViewById(R.id.day1);
        set_day1 = (TextView)findViewById(R.id.set_day1);
        day1_pic = (ImageView)findViewById(R.id.day1_pic);
        day1_avgtemp = (TextView)findViewById(R.id.day1_avgtemp);
        day1_HT = (TextView)findViewById(R.id.day1_HT);
        day1_LT = (TextView)findViewById(R.id.day1_LT);
        day1_humidity_image = (ImageView)findViewById(R.id.day1_humidity_image);
        day1_humidity = (TextView)findViewById(R.id.day1_humidity);
        day1_top = (TextView)findViewById(R.id.day1_top);
        day1_top_pic = (ImageView)findViewById(R.id.day1_top_pic);
        day1_bottom = (TextView)findViewById(R.id.day1_bottom);
        day1_bottom_pic = (ImageView)findViewById(R.id.day1_bottom_pic);

        day2 = (TextView)findViewById(R.id.day2);
        set_day2 = (TextView)findViewById(R.id.set_day2);
        day2_pic = (ImageView)findViewById(R.id.day2_pic);
        day2_avgtemp = (TextView)findViewById(R.id.day2_avgtemp);
        day2_HT = (TextView)findViewById(R.id.day2_HT);
        day2_LT = (TextView)findViewById(R.id.day2_LT);
        day2_humidity_image = (ImageView)findViewById(R.id.day2_humidity_image);
        day2_humidity = (TextView)findViewById(R.id.day2_humidity);
        day2_top = (TextView)findViewById(R.id.day2_top);
        day2_top_pic = (ImageView)findViewById(R.id.day2_top_pic);
        day2_bottom = (TextView)findViewById(R.id.day2_bottom);
        day2_bottom_pic = (ImageView)findViewById(R.id.day2_bottom_pic);

        day3 = (TextView)findViewById(R.id.day3);
        set_day3 = (TextView)findViewById(R.id.set_day3);
        day3_pic = (ImageView)findViewById(R.id.day3_pic);
        day3_avgtemp = (TextView)findViewById(R.id.day3_avgtemp);
        day3_HT = (TextView)findViewById(R.id.day3_HT);
        day3_LT = (TextView)findViewById(R.id.day3_LT);
        day3_humidity_image = (ImageView)findViewById(R.id.day3_humidity_image);
        day3_humidity = (TextView)findViewById(R.id.day3_humidity);
        day3_top = (TextView)findViewById(R.id.day3_top);
        day3_top_pic = (ImageView)findViewById(R.id.day3_top_pic);
        day3_bottom = (TextView)findViewById(R.id.day3_bottom);
        day3_bottom_pic = (ImageView)findViewById(R.id.day3_bottom_pic);

        day4 = (TextView)findViewById(R.id.day4);
        set_day4 = (TextView)findViewById(R.id.set_day4);
        day4_pic = (ImageView)findViewById(R.id.day4_pic);
        day4_avgtemp = (TextView)findViewById(R.id.day4_avgtemp);
        day4_HT = (TextView)findViewById(R.id.day4_HT);
        day4_LT = (TextView)findViewById(R.id.day4_LT);
        day4_humidity_image = (ImageView)findViewById(R.id.day4_humidity_image);
        day4_humidity = (TextView)findViewById(R.id.day4_humidity);
        day4_top = (TextView)findViewById(R.id.day4_top);
        day4_top_pic = (ImageView)findViewById(R.id.day4_top_pic);
        day4_bottom = (TextView)findViewById(R.id.day4_bottom);
        day4_bottom_pic = (ImageView)findViewById(R.id.day4_bottom_pic);

        day5 = (TextView)findViewById(R.id.day5);
        set_day5 = (TextView)findViewById(R.id.set_day5);
        day5_pic = (ImageView)findViewById(R.id.day5_pic);
        day5_avgtemp = (TextView)findViewById(R.id.day5_avgtemp);
        day5_HT = (TextView)findViewById(R.id.day5_HT);
        day5_LT = (TextView)findViewById(R.id.day5_LT);
        day5_humidity_image = (ImageView)findViewById(R.id.day5_humidity_image);
        day5_humidity = (TextView)findViewById(R.id.day5_humidity);
        day5_top = (TextView)findViewById(R.id.day5_top);
        day5_top_pic = (ImageView)findViewById(R.id.day5_top_pic);
        day5_bottom = (TextView)findViewById(R.id.day5_bottom);
        day5_bottom_pic = (ImageView)findViewById(R.id.day5_bottom_pic);

        day6 = (TextView)findViewById(R.id.day6);
        set_day6 = (TextView)findViewById(R.id.set_day6);
        day6_pic = (ImageView)findViewById(R.id.day6_pic);
        day6_avgtemp = (TextView)findViewById(R.id.day6_avgtemp);
        day6_HT = (TextView)findViewById(R.id.day6_HT);
        day6_LT = (TextView)findViewById(R.id.day6_LT);
        day6_humidity_image = (ImageView)findViewById(R.id.day6_humidity_image);
        day6_humidity = (TextView)findViewById(R.id.day6_humidity);
        day6_top = (TextView)findViewById(R.id.day6_top);
        day6_top_pic = (ImageView)findViewById(R.id.day6_top_pic);
        day6_bottom = (TextView)findViewById(R.id.day6_bottom);
        day6_bottom_pic = (ImageView)findViewById(R.id.day6_bottom_pic);

        day7 = (TextView)findViewById(R.id.day7);
        set_day7 = (TextView)findViewById(R.id.set_day7);
        day7_pic = (ImageView)findViewById(R.id.day7_pic);
        day7_avgtemp = (TextView)findViewById(R.id.day7_avgtemp);
        day7_HT = (TextView)findViewById(R.id.day7_HT);
        day7_LT = (TextView)findViewById(R.id.day7_LT);
        day7_humidity_image = (ImageView)findViewById(R.id.day7_humidity_image);
        day7_humidity = (TextView)findViewById(R.id.day7_humidity);
        day7_top = (TextView)findViewById(R.id.day7_top);
        day7_top_pic = (ImageView)findViewById(R.id.day7_top_pic);
        day7_bottom = (TextView)findViewById(R.id.day7_bottom);
        day7_bottom_pic = (ImageView)findViewById(R.id.day7_bottom_pic);

        text1 = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);
        text6 = (TextView)findViewById(R.id.text6);
        text7 = (TextView)findViewById(R.id.text7);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        background.setBackgroundResource(R.drawable.gradation_orange);
        window.setStatusBarColor(getResources().getColor(R.color.orange));

        day1.setText(getDATE("MM", 1)+"월"+" "+getDATE("dd", 1)+"일");
        if(Whatday(dayofWeek1)=="SUN"){
            set_day1.setTextColor(Color.RED);
            set_day1.setText(Whatday(dayofWeek1));
        }
        else if(Whatday(dayofWeek1)=="SAT"){
            set_day1.setTextColor(Color.BLUE);
            set_day1.setText(Whatday(dayofWeek1));
        }
        else{
            set_day1.setTextColor(getResources().getColor(R.color.grey));
            set_day1.setText(Whatday(dayofWeek1));
        }

        day2.setText(getDATE("MM", 2)+"월"+getDATE("dd", 2)+"일");
        if(Whatday(dayofWeek2)=="SUN"){
            set_day2.setTextColor(Color.RED);
            set_day2.setText(Whatday(dayofWeek2));
        }
        else if(Whatday(dayofWeek2)=="SAT"){
            set_day2.setTextColor(Color.BLUE);
            set_day2.setText(Whatday(dayofWeek2));
        }
        else{
            set_day2.setTextColor(getResources().getColor(R.color.grey));
            set_day2.setText(Whatday(dayofWeek2));
        }

        day3.setText(getDATE("MM", 3)+"월"+getDATE("dd", 3)+"일");
        if(Whatday(dayofWeek3)=="SUN"){
            set_day3.setTextColor(Color.RED);
            set_day3.setText(Whatday(dayofWeek3));
        }
        else if(Whatday(dayofWeek3)=="SAT"){
            set_day3.setTextColor(Color.BLUE);
            set_day3.setText(Whatday(dayofWeek3));
        }
        else{
            set_day3.setTextColor(getResources().getColor(R.color.grey));
            set_day3.setText(Whatday(dayofWeek3));
        }

        day4.setText(getDATE("MM", 4)+"월"+getDATE("dd", 4)+"일");
        if(Whatday(dayofWeek4)=="SUN"){
            set_day4.setTextColor(Color.RED);
            set_day4.setText(Whatday(dayofWeek4));
        }
        else if(Whatday(dayofWeek4)=="SAT"){
            set_day4.setTextColor(Color.BLUE);
            set_day4.setText(Whatday(dayofWeek4));
        }
        else{
            set_day4.setTextColor(getResources().getColor(R.color.grey));
            set_day4.setText(Whatday(dayofWeek4));
        }

        day5.setText(getDATE("MM", 5)+"월"+getDATE("dd", 5)+"일");
        if(Whatday(dayofWeek5)=="SUN"){
            set_day5.setTextColor(Color.RED);
            set_day5.setText(Whatday(dayofWeek5));
        }
        else if(Whatday(dayofWeek5)=="SAT"){
            set_day5.setTextColor(Color.BLUE);
            set_day5.setText(Whatday(dayofWeek5));
        }
        else{
            set_day5.setTextColor(getResources().getColor(R.color.grey));
            set_day5.setText(Whatday(dayofWeek5));
        }

        day6.setText(getDATE("MM", 6)+"월"+getDATE("dd", 6)+"일");
        if(Whatday(dayofWeek6)=="SUN"){
            set_day6.setTextColor(Color.RED);
            set_day6.setText(Whatday(dayofWeek6));
        }
        else if(Whatday(dayofWeek6)=="SAT"){
            set_day6.setTextColor(Color.BLUE);
            set_day6.setText(Whatday(dayofWeek6));
        }
        else{
            set_day6.setTextColor(getResources().getColor(R.color.grey));
            set_day6.setText(Whatday(dayofWeek6));
        }

        day7.setText(getDATE("MM", 7)+"월"+getDATE("dd", 7)+"일");
        if(Whatday(dayofWeek7)=="SUN"){
            set_day7.setTextColor(Color.RED);
            set_day7.setText(Whatday(dayofWeek7));
        }
        else if(Whatday(dayofWeek7)=="SAT"){
            set_day7.setTextColor(Color.BLUE);
            set_day7.setText(Whatday(dayofWeek7));
        }
        else{
            set_day7.setTextColor(getResources().getColor(R.color.grey));
            set_day7.setText(Whatday(dayofWeek7));
        }

        System.out.println("today : "+ getDATE("MM", 0)+"월"+getDATE("dd", 0)+"일");
        SharedPreferences spDay = getSharedPreferences("sp_day", MODE_PRIVATE);
        String day = spDay.getString("day", "100");

        SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
        String last_lat = sp_lat.getString("last_lat", "37.566535");

        SharedPreferences sp_lon = getSharedPreferences("longitude",MODE_PRIVATE);
        String last_lon = sp_lon.getString("last_lon", "126.97796919999999");

        SharedPreferences sharedPreferences = getSharedPreferences("sp_Week", MODE_PRIVATE);
        String spWeek = sharedPreferences.getString("Week",WEEk_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&exclude=hourly&appid=" + App_ID );

        System.out.println("spWeek : "+spWeek);
        week_url = WEEk_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&exclude=hourly&appid=" + App_ID;

        DownloadJSON downloadJSON = new DownloadJSON();
        try{
            String url = downloadJSON.execute(week_url).get();
            JSONObject jsonObject = new JSONObject(url);
            JSONArray jsonArray = jsonObject.getJSONArray("daily");
            for(int i=1;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                String humid = object.getString("humidity");
                String str = object.getString("temp");
                JSONObject tmp = new JSONObject(str);
                double min_temp = tmp.getDouble("min");
                min_temp-=273.15;
                double max_temp = tmp.getDouble("max");
                max_temp -= 273.15;
                double day_temp = tmp.getDouble("day");
                day_temp -= 273.15;
                double night_temp = tmp.getDouble("night");
                night_temp -= 273.15;
                JSONArray jsonArray1 = object.getJSONArray("weather");
                for(int j=0;j<jsonArray1.length();j++){
                    JSONObject object1 = jsonArray1.getJSONObject(j);
                    String main = object1.getString("main");
                    tmpDescription.add(main);
                }
                weekWeathers.add(new WeekWeather(min_temp, max_temp, humid, day_temp, night_temp));
            }
            ShowAveTemp();
            ShowMinMaxTemp();
            ShowHumidity();
            ShowTimeLineDescription();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<tempClothes.size();i++)
            System.out.println("tempClothes : "+tempClothes.get(i));

        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);
        gender = sharedSEX.getString("SEX", "null");

        SharedPreferences sp_check_gender = getSharedPreferences("sp_check_gender", MODE_PRIVATE);
        String check_gender = sp_check_gender.getString("check_gender", "check_gender");

        System.out.println("gender, check_gender : "+gender+", "+check_gender);

        SharedPreferences sp_top = getSharedPreferences("sp_week_top", MODE_PRIVATE);
        SharedPreferences sp_bottoms = getSharedPreferences("sp_week_bottoms", MODE_PRIVATE);
        String tmpTop = sp_top.getString("week_top", "noTop");
        String tmpBottom = sp_bottoms.getString("week_bottoms", "noBottom");
        System.out.println("spTop : "+tmpTop);
        System.out.println("spBottoms : "+tmpBottom);
        if(tmpTop.equals("noTop")||tmpBottom.equals("noBottom")||spWeek.equals(week_url)
                ||!day.equals(getDATE("MM", 0)+"월"+getDATE("dd", 0)+"일")
                ||!gender.equals(check_gender)) {
            spFlag = false;
            Log.v("bool", String.valueOf(false));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("week", week_url);
            editor.apply();
            SharedPreferences.Editor editor1 = spDay.edit();
            editor1.putString("day", getDATE("MM", 0)+"월"+getDATE("dd", 0)+"일");
            editor1.apply();
            SharedPreferences.Editor editor2 = sp_check_gender.edit();
            editor2.putString("check_gender", gender);
            editor2.apply();
        }
        else{
            Log.v("bool", String.valueOf(spFlag));
            System.out.println("weekTop : "+tmpTop);
            weekTop = tmpTop.split(",");
            weekBottoms = tmpBottom.split(",");
            for (String s : weekTop) System.out.println("weekTop : " + s);
        }

        ShowClothesList1();
        ShowClothesList2();
        ShowClothesList3();
        ShowClothesList4();
        ShowClothesList5();
        ShowClothesList6();
        ShowClothesList7();

        Log.v("spTop", spTop);
        Log.v("spBottoms", spBottoms);

        SharedPreferences.Editor editor_top = sp_top.edit();
        editor_top.putString("week_top", spTop);
        editor_top.apply();

        SharedPreferences.Editor editor_bottoms = sp_bottoms.edit();
        editor_bottoms.putString("week_bottoms", spBottoms);
        editor_bottoms.apply();
    }

    private void ClothesPic1(String top, String bottom){
        switch (top){
            case "반팔 티":
                day1_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day1_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day1_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")) {
                    day1_top_pic.setImageResource(R.drawable.women_nasi);
                    break;
                }
                day1_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day1_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day1_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day1_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day1_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day1_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day1_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day1_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day1_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day1_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day1_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day1_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day1_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day1_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day1_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day1_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day1_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day1_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day1_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day1_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day1_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day1_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day1_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")) {
                    day1_top_pic.setImageResource(R.drawable.women_coat);
                    break;
                }
                day1_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day1_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day1_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day1_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day1_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day1_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day1_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day1_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day1_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day1_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")) {
                    day1_bottom_pic.setImageResource(R.drawable.women_training);
                    break;
                }
                day1_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day1_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")) {
                    day1_bottom_pic.setImageResource(R.drawable.denim_shorts);
                    break;
                }
                day1_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")) {
                    day1_bottom_pic.setImageResource(R.drawable.shortw);
                    break;
                }
                day1_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day1_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day1_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day1_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day1_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day1_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day1_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day1_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day1_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day1_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day1_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day1_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day1_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day1_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day1_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day1_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic2(String top, String bottom){
        switch (top){
            case "반팔 티":
                day2_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day2_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day2_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")) {
                    day2_top_pic.setImageResource(R.drawable.women_nasi);
                    break;
                }
                day2_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day2_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day2_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day2_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day2_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day2_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day2_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day2_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day2_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day2_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day2_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day2_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day2_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day2_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day2_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day2_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day2_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day2_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day2_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day2_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day2_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day2_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day2_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")) {
                    day2_top_pic.setImageResource(R.drawable.women_coat);
                    break;
                }
                day2_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day2_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day2_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day2_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day2_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day2_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day2_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day2_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day2_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day2_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")) {
                    day2_bottom_pic.setImageResource(R.drawable.women_training);
                    break;
                }
                day2_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day2_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")) {
                    day2_bottom_pic.setImageResource(R.drawable.denim_shorts);
                    break;
                }
                day2_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")) {
                    day2_bottom_pic.setImageResource(R.drawable.shortw);
                    break;
                }
                day2_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day2_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day2_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day2_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day2_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day2_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day2_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day2_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day2_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day2_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day2_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day2_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day2_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day2_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day2_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day2_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic3(String top, String bottom){
        switch (top){
            case "반팔 티":
                day3_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day3_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day3_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")) {
                    day3_top_pic.setImageResource(R.drawable.women_nasi);
                    break;
                }
                day3_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day3_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day3_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day3_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day3_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day3_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day3_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day3_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day3_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day3_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day3_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day3_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day3_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day3_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day3_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day3_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day3_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day3_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day3_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day3_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day3_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day3_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day3_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")) {
                    day3_top_pic.setImageResource(R.drawable.women_coat);
                    break;
                }
                day3_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day3_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day3_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day3_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day3_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day3_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day3_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day3_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day3_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day3_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")) {
                    day3_bottom_pic.setImageResource(R.drawable.women_training);
                    break;
                }
                day3_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day3_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")) {
                    day3_bottom_pic.setImageResource(R.drawable.denim_shorts);
                    break;
                }
                day3_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")) {
                    day3_bottom_pic.setImageResource(R.drawable.shortw);
                    break;
                }
                day3_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day3_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day3_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day3_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day3_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day3_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day3_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day3_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day3_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day3_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day3_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day3_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day3_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day3_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day3_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day3_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic4(String top, String bottom){
        switch (top){
            case "반팔 티":
                day4_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day4_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day4_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")) {
                    day4_top_pic.setImageResource(R.drawable.women_nasi);
                    break;
                }
                day4_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day4_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day4_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day4_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day4_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day4_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day4_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day4_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day4_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day4_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day4_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day4_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day4_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day4_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day4_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day4_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day4_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day4_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day4_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day4_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day4_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day4_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day4_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")) {
                    day4_top_pic.setImageResource(R.drawable.women_coat);
                    break;
                }
                day4_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day4_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day4_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day4_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day4_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day4_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day4_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day4_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day4_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day4_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")){
                    day4_bottom_pic.setImageResource(R.drawable.women_training);break;}
                day4_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day4_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")) {
                    day4_bottom_pic.setImageResource(R.drawable.denim_shorts);
                    break;
                }
                day4_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")) {
                    day4_bottom_pic.setImageResource(R.drawable.shortw);
                    break;
                }
                day4_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day4_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day4_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day4_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day4_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day4_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day4_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day4_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day4_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day4_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day4_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day4_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day4_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day4_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day4_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day4_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic5(String top, String bottom){
        switch (top) {
            case "반팔 티":
                day5_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day5_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day5_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")){
                    day5_top_pic.setImageResource(R.drawable.women_nasi);break;}
                day5_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day5_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day5_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day5_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day5_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day5_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day5_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day5_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day5_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day5_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day5_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day5_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day5_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day5_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day5_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day5_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day5_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day5_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day5_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day5_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day5_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day5_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day5_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")){
                    day5_top_pic.setImageResource(R.drawable.women_coat);break;}
                day5_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day5_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day5_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day5_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day5_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day5_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day5_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day5_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day5_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day5_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")){
                    day5_bottom_pic.setImageResource(R.drawable.women_training);break;}
                day5_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day5_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")){
                    day5_bottom_pic.setImageResource(R.drawable.denim_shorts);break;}
                day5_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")){
                    day5_bottom_pic.setImageResource(R.drawable.shortw);break;}
                day5_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day5_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day5_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day5_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day5_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day5_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day5_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day5_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day5_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day5_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day5_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day5_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day5_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day5_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day5_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day5_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic6(String top, String bottom){
        switch (top){
            case "반팔 티":
                day6_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day6_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day6_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")){
                    day6_top_pic.setImageResource(R.drawable.women_nasi);break;}
                day6_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day6_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day6_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day6_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day6_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day6_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day6_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day6_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day6_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day6_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day6_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day6_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day6_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day6_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day6_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day6_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day6_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day6_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day6_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day6_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day6_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day6_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day6_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")){
                    day6_top_pic.setImageResource(R.drawable.women_coat);break;}
                day6_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day6_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day6_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day6_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day6_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day6_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day6_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day6_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day6_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day6_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")){
                    day6_bottom_pic.setImageResource(R.drawable.women_training);break;}
                day6_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day6_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")){
                    day6_bottom_pic.setImageResource(R.drawable.denim_shorts);break;}
                day6_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")){
                    day6_bottom_pic.setImageResource(R.drawable.shortw);break;}
                day6_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day6_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day6_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day6_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day6_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day6_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day6_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day6_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day6_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day6_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day6_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day6_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day6_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day6_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day6_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day6_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }
    private void ClothesPic7(String top, String bottom){
        switch (top){
            case "반팔 티":
                day7_top_pic.setImageResource(R.drawable.tshirt);
                break;
            case "반팔 셔츠":
                day7_top_pic.setImageResource(R.drawable.half_shirts);
                break;
            case "PK 반팔티":
                day7_top_pic.setImageResource(R.drawable.pk);
                break;
            case "민소매":
                if(gender.equals("female")){
                    day7_top_pic.setImageResource(R.drawable.women_nasi);break;}
                day7_top_pic.setImageResource(R.drawable.nasi);
                break;
            case "긴팔 티":
                day7_top_pic.setImageResource(R.drawable.long_sleeve);
                break;
            case "PK 티":
                day7_top_pic.setImageResource(R.drawable.polo);
                break;
            case "얇은 셔츠":
            case "셔츠":
                day7_top_pic.setImageResource(R.drawable.shirt);
                break;
            case "스웨트 셔츠":
                day7_top_pic.setImageResource(R.drawable.sweater);
                break;
            case "후드티":
                day7_top_pic.setImageResource(R.drawable.hood);
                break;
            case "린넨 자켓":
                day7_top_pic.setImageResource(R.drawable.linen_jacket);
                break;
            case "후드 집업":
                day7_top_pic.setImageResource(R.drawable.hood_zipup);
                break;
            case "트레이닝 재킷":
                day7_top_pic.setImageResource(R.drawable.training_jacket);
                break;
            case "블레이저":
                day7_top_pic.setImageResource(R.drawable.blazer);
                break;
            case "트러커 자켓":
                day7_top_pic.setImageResource(R.drawable.trucker);
                break;
            case "바람막이(아노락)":
                day7_top_pic.setImageResource(R.drawable.anorak);
                break;
            case "니트":
                day7_top_pic.setImageResource(R.drawable.neets);
                break;
            case "카디건":
                day7_top_pic.setImageResource(R.drawable.cardigan);
                break;
            case "데님 자켓":
                day7_top_pic.setImageResource(R.drawable.denim_jacket);
                break;
            case "레더 자켓":
                day7_top_pic.setImageResource(R.drawable.leather);
                break;
            case "MA-1":
                day7_top_pic.setImageResource(R.drawable.ma);
                break;
            case "블루종":
                day7_top_pic.setImageResource(R.drawable.bluezong);
                break;
            case "트렌치 코트":
                day7_top_pic.setImageResource(R.drawable.trench_coat);
                break;
            case "바버 자켓":
                day7_top_pic.setImageResource(R.drawable.barber);
                break;
            case "스타디움 재킷":
                day7_top_pic.setImageResource(R.drawable.stadium);
                break;
            case "패딩 베스트":
                day7_top_pic.setImageResource(R.drawable.padding_vest);
                break;
            case "숏 코트":
                day7_top_pic.setImageResource(R.drawable.short_coat);
                break;
            case "롱 코트":
                if(gender.equals("female")){
                    day7_top_pic.setImageResource(R.drawable.women_coat);break;}
                day7_top_pic.setImageResource(R.drawable.longcoat);
                break;
            case "숏 패딩":
                day7_top_pic.setImageResource(R.drawable.padding);
                break;
            case "롱 패딩":
                day7_top_pic.setImageResource(R.drawable.long_padding);
                break;
            case "무스탕":
                day7_top_pic.setImageResource(R.drawable.mustang);
                break;
            case "데님 원피스":
                day7_top_pic.setImageResource(R.drawable.denim_one);
                break;
            case "여름 원피스":
                day7_top_pic.setImageResource(R.drawable.dress);
                break;
            case "블라우스":
                day7_top_pic.setImageResource(R.drawable.blouse);
                break;
            case "원피스":
                day7_top_pic.setImageResource(R.drawable.one_piece);
                break;
            case "크롭 티":
                day7_top_pic.setImageResource(R.drawable.crop);
                break;
            case "트위드 자켓":
                day7_top_pic.setImageResource(R.drawable.tweed);
                break;
        }

        switch (bottom){
            case "트레이닝 쇼츠":
                if(gender.equals("female")){
                    day7_bottom_pic.setImageResource(R.drawable.women_training);break;}
                day7_bottom_pic.setImageResource(R.drawable.training_short);
                break;
            case "카고 쇼츠":
                day7_bottom_pic.setImageResource(R.drawable.cargo);
                break;
            case "데님 쇼츠":
                if(gender.equals("female")){
                    day7_bottom_pic.setImageResource(R.drawable.denim_shorts);break;}
                day7_bottom_pic.setImageResource(R.drawable.jeans);
                break;
            case "코튼 쇼츠":
                if(gender.equals("female")){
                    day7_bottom_pic.setImageResource(R.drawable.shortw);break;}
                day7_bottom_pic.setImageResource(R.drawable.shorts);
                break;
            case "린넨 팬츠":
            case "기모 팬츠":
                day7_bottom_pic.setImageResource(R.drawable.linen);
                break;
            case "플리츠":
                day7_bottom_pic.setImageResource(R.drawable.plitz);
                break;
            case "얇은 슬랙스":
            case "슬랙스":
                day7_bottom_pic.setImageResource(R.drawable.slax);
                break;
            case "트레이닝 팬츠":
                day7_bottom_pic.setImageResource(R.drawable.training_long);
                break;
            case "데님 팬츠":
                day7_bottom_pic.setImageResource(R.drawable.jean);
                break;
            case "크림 진":
                day7_bottom_pic.setImageResource(R.drawable.jeanw);
                break;
            case "블랙 진":
                day7_bottom_pic.setImageResource(R.drawable.jeanb);
                break;
            case "조거 팬츠":
                day7_bottom_pic.setImageResource(R.drawable.zoger);
                break;
            case "코튼 팬츠":
                day7_bottom_pic.setImageResource(R.drawable.cotton_long);
                break;
            case "데님 스커트":
                day7_bottom_pic.setImageResource(R.drawable.denim_skirt);
                break;
            case "A라인 스커트":
                day7_bottom_pic.setImageResource(R.drawable.a_skirt);
                break;
            case "H라인 스커트":
                day7_bottom_pic.setImageResource(R.drawable.h_skirt);
                break;
            case "롱 스커트":
                day7_bottom_pic.setImageResource(R.drawable.long_skirt);
                break;
            case "레깅스":
                day7_bottom_pic.setImageResource(R.drawable.leggings);
                break;
            case "테니스 스커트":
                day7_bottom_pic.setImageResource(R.drawable.tennis);
                break;
        }
    }

    private void ShowClothesList1() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(0));
            parser(startTag);
            int top_num = RandomNumber(top.size());
            int bottoms_num = RandomNumber(bottoms.size());
            String tmpTop = top.get(top_num), tmpBottoms = bottoms.get(bottoms_num);
            if(bottoms.get(bottoms_num).equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day1_top.setText(tmpTop);
            day1_bottom.setText(tmpBottoms);
            ClothesPic1(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day1_top.setText(weekTop[0]);
            day1_bottom.setText(weekBottoms[0]);
            ClothesPic1(weekTop[0], weekBottoms[0]);
        }

    }
    private void ShowClothesList2() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(1));
            parser(startTag);
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day2_top.setText(tmpTop);
            day2_bottom.setText(tmpBottoms);
            ClothesPic2(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else  {
            day2_top.setText(weekTop[1]);
            day2_bottom.setText(weekBottoms[1]);
            ClothesPic2(weekTop[1], weekBottoms[1]);
        }
    }
    private void ShowClothesList3() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(2));
            parser((startTag));
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day3_top.setText(tmpTop);
            day3_bottom.setText(tmpBottoms);
            ClothesPic3(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day3_top.setText(weekTop[2]);
            day3_bottom.setText(weekBottoms[2]);
            ClothesPic3(weekTop[2], weekBottoms[2]);
        }
    }
    private void ShowClothesList4() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(1));
            parser(startTag);
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day4_top.setText(tmpTop);
            day4_bottom.setText(tmpBottoms);
            ClothesPic4(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day4_top.setText(weekTop[3]);
            day4_bottom.setText(weekBottoms[3]);
            ClothesPic4(weekTop[3], weekBottoms[3]);
        }
    }
    private void ShowClothesList5() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(1));
            parser(startTag);
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day5_top.setText(tmpTop);
            day5_bottom.setText(tmpBottoms);
            ClothesPic5(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day5_top.setText(weekTop[4]);
            day5_bottom.setText(weekBottoms[4]);
            ClothesPic5(weekTop[4], weekBottoms[4]);
        }
    }
    private void ShowClothesList6() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(1));
            parser(startTag);
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day6_top.setText(tmpTop);
            day6_bottom.setText(tmpBottoms);
            ClothesPic6(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day6_top.setText(weekTop[5]);
            day6_bottom.setText(weekBottoms[5]);
            ClothesPic6(weekTop[5], weekBottoms[5]);
        }
    }
    private void ShowClothesList7() {
        if(!spFlag) {
            String startTag = SetClothesList(tempClothes.get(1));
            parser(startTag);
            int top_num, bottoms_num;
            do {
                top_num = RandomNumber(top.size());
                bottoms_num = RandomNumber(bottoms.size());
            } while (last_top.equals(top.get(top_num)) || last_bottoms.equals(bottoms.get(bottoms_num)));
            String tmpTop = top.get(top_num); String tmpBottoms = bottoms.get(bottoms_num);
            if(tmpBottoms.equals("트레이닝 팬츠")){
                parser_traing(startTag);
                top_num = RandomNumber(traingTop.size());
                tmpBottoms = bottoms.get(bottoms_num);
                tmpTop = traingTop.get(top_num);
                traingTop.clear();
            }
            last_top = tmpTop;
            last_bottoms = tmpBottoms;
            spTop = spTop + tmpTop + ",";
            spBottoms = spBottoms + tmpBottoms + ",";
            day7_top.setText(tmpTop);
            day7_bottom.setText(tmpBottoms);
            ClothesPic7(tmpTop, tmpBottoms);
            top.clear();
            bottoms.clear();
        }
        else {
            day7_top.setText(weekTop[6]);
            day7_bottom.setText(weekBottoms[6]);
            ClothesPic7(weekTop[6], weekBottoms[6]);
        }
    }

    private int RandomNumber(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    private String SetClothesList(double temp) {
        String ret = "";
        if(28<=temp)
            ret = "Temp9";
        else if(23<=temp&&temp<28)
            ret = "Temp8";
        else if(17<=temp&&temp<23)
            ret = "Temp7";
        else if(13.5<=temp&&temp<12)
            ret = "Temp6";
        else if(8<=temp&&temp<13.5)
            ret = "Temp5";
        else if(4<=temp&&temp<8)
            ret = "Temp4";
        else if(-10<=temp&&temp<4)
            ret = "Temp3";
        else if(-20<=temp&&temp<-10)
            ret = "Temp2";
        else if(-20>temp)
            ret = "Temp1";
        return ret;
    }

    private void parser_traing(String str){
        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);
        String gender = sharedSEX.getString("SEX", "null");
        SharedPreferences sharedPreferences = getSharedPreferences("sp_check_gender", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        if (gender.equals("male")) {
            inputStream = getResources().openRawResource(R.raw.training_top);
            inputStreamReader = new InputStreamReader(inputStream);
            editor.putString("check_gender", "male");
            editor.apply();
        }
        else {
            inputStream = getResources().openRawResource(R.raw.training_top_female);
            inputStreamReader = new InputStreamReader(inputStream);
            editor.putString("check_gender", "female");
            editor.apply();
        }

        XmlPullParserFactory factory = null;
        XmlPullParser xmlParser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();
            xmlParser.setInput(inputStreamReader);

            int eventType = xmlParser.getEventType();
            boolean flag = false;
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        try {
                            String startTag = xmlParser.getName();
                            if(startTag.equals("Temp9")){
                                flag = false;
                            }
                            if(startTag.equals(str)) {
                                flag = true;
                            }
                            if(startTag.equals("top")){
                                if(flag)
                                    traingTop.add(xmlParser.nextText());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        flag = false;
                        break;
                }
                try {
                    eventType = xmlParser.next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally{
            try{
                if(inputStreamReader !=null) inputStreamReader.close();
                if(inputStream !=null) inputStream.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }

    private void parser(String str) {
        // 내부 xml파일이용시
        SharedPreferences sharedSEX = getSharedPreferences("sex", MODE_PRIVATE);
        String gender = sharedSEX.getString("SEX", "null");
        SharedPreferences sharedPreferences = getSharedPreferences("sp_check_gender", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        InputStream inputStream;
        InputStreamReader inputStreamReader = null;
        if (gender.equals("male")) {
            inputStream = getResources().openRawResource(R.raw.clothes);
            inputStreamReader = new InputStreamReader(inputStream);
            editor.putString("check_gender", "male");
            editor.apply();
        }
        else {
            inputStream = getResources().openRawResource(R.raw.female_clothes);
            inputStreamReader = new InputStreamReader(inputStream);
            editor.putString("check_gender", "female");
            editor.apply();
        }

        XmlPullParserFactory factory = null;
        XmlPullParser xmlParser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            xmlParser = factory.newPullParser();
            xmlParser.setInput(inputStreamReader);

            int eventType = xmlParser.getEventType();
            boolean flag = false;
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        try {
                            String startTag = xmlParser.getName();
                            if(startTag.equals("Temp9")){
                                flag = false;
                            }
                            if(startTag.equals(str)) {
                                flag = true;
                            }
                            if(startTag.equals("top")){
                                if(flag)
                                    top.add(xmlParser.nextText());
                            }
                            if(startTag.equals("bottoms")){
                                if(flag)
                                    bottoms.add(xmlParser.nextText());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        flag = false;
                        break;
                }
                try {
                    eventType = xmlParser.next();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally{
            try{
                if(inputStreamReader !=null) inputStreamReader.close();
                if(inputStream !=null) inputStream.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }

    private void ShowTimeLineDescription() {
        switch (tmpDescription.get(0)) {
            case "Thunderstorm":
                day1_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day1_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day1_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day1_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day1_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(1)) {
            case "Thunderstorm":
                day2_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day2_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day2_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day2_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day2_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(2)) {
            case "Thunderstorm":
                day3_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day3_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day3_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day3_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day3_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(3)) {
            case "Thunderstorm":
                day4_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day4_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day4_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day4_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day4_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(4)) {
            case "Thunderstorm":
                day5_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day5_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day5_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day5_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day5_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(5)) {
            case "Thunderstorm":
                day6_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day6_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day6_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day6_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day6_pic.setImageResource(R.drawable.cloud);
                break;
        }
        switch (tmpDescription.get(6)) {
            case "Thunderstorm":
                day7_pic.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                day7_pic.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                day7_pic.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                day7_pic.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                day7_pic.setImageResource(R.drawable.cloud);
                break;
        }
    }

    @SuppressLint("DefaultLocale")
    private void ShowMinMaxTemp() {
        day1_HT.setText(String.format("%.1f", weekWeathers.get(0).getMaxTemp()));
        day2_HT.setText(String.format("%.1f", weekWeathers.get(1).getMaxTemp()));
        day3_HT.setText(String.format("%.1f", weekWeathers.get(2).getMaxTemp()));
        day4_HT.setText(String.format("%.1f", weekWeathers.get(3).getMaxTemp()));
        day5_HT.setText(String.format("%.1f", weekWeathers.get(4).getMaxTemp()));
        day6_HT.setText(String.format("%.1f", weekWeathers.get(5).getMaxTemp()));
        day7_HT.setText(String.format("%.1f", weekWeathers.get(6).getMaxTemp()));

        day1_LT.setText(String.format("%.1f", weekWeathers.get(0).getMinTemp()));
        day2_LT.setText(String.format("%.1f", weekWeathers.get(1).getMinTemp()));
        day3_LT.setText(String.format("%.1f", weekWeathers.get(2).getMinTemp()));
        day4_LT.setText(String.format("%.1f", weekWeathers.get(3).getMinTemp()));
        day5_LT.setText(String.format("%.1f", weekWeathers.get(4).getMinTemp()));
        day6_LT.setText(String.format("%.1f", weekWeathers.get(5).getMinTemp()));
        day7_LT.setText(String.format("%.1f", weekWeathers.get(6).getMinTemp()));
    }

    @SuppressLint("DefaultLocale")
    private void ShowAveTemp() {
        String date = getDATE("m", 1);
        double day, night;
        switch (date){
            case "1":
                day = 10;
                night = 14;
                break;
            case "2":
            case "10":
                day = 11;
                night = 13;
                break;
            case "4":
                day = 13;
                night = 11;
                break;
            case "5":
                day = 14;
                night = 10;
                break;
            case "6":
                day = 14.5;
                night = 9.5;
                break;
            case "7":
                day = 14.2;
                night = 9.8;
                break;
            case "8":
                day = 13.5;
                night = 10.5;
                break;
            case "9":
                day = 12.5;
                night = 11.5;
                break;
            case "11":
                day = 10.2;
                night = 13.8;
                break;
            case "12":
                day = 9.7;
                night = 14.3;
                break;
            default:
                day = 12;
                night = 12;
                break;
        }
        day1_avgtemp.setText(String.format("%.1f", (weekWeathers.get(0).getDayTemp()*day+weekWeathers.get(0).getNightTemp()*night)/24));
        day2_avgtemp.setText(String.format("%.1f", (weekWeathers.get(1).getDayTemp()*day+weekWeathers.get(1).getNightTemp()*night)/24));
        day3_avgtemp.setText(String.format("%.1f", (weekWeathers.get(2).getDayTemp()*day+weekWeathers.get(2).getNightTemp()*night)/24));
        day4_avgtemp.setText(String.format("%.1f", (weekWeathers.get(3).getDayTemp()*day+weekWeathers.get(3).getNightTemp()*night)/24));
        day5_avgtemp.setText(String.format("%.1f", (weekWeathers.get(4).getDayTemp()*day+weekWeathers.get(4).getNightTemp()*night)/24));
        day6_avgtemp.setText(String.format("%.1f", (weekWeathers.get(5).getDayTemp()*day+weekWeathers.get(5).getNightTemp()*night)/24));
        day7_avgtemp.setText(String.format("%.1f", (weekWeathers.get(6).getDayTemp()*day+weekWeathers.get(6).getNightTemp()*night)/24));
        for(int i=0;i<7;i++)
            tempClothes.add((weekWeathers.get(i).getDayTemp()*day+weekWeathers.get(i).getNightTemp()*night)/24);
    }

    private void ShowHumidity() {
        day1_humidity.setText(weekWeathers.get(0).getHumidity());
        day2_humidity.setText(weekWeathers.get(1).getHumidity());
        day3_humidity.setText(weekWeathers.get(2).getHumidity());
        day4_humidity.setText(weekWeathers.get(3).getHumidity());
        day5_humidity.setText(weekWeathers.get(4).getHumidity());
        day6_humidity.setText(weekWeathers.get(5).getHumidity());
        day7_humidity.setText(weekWeathers.get(6).getHumidity());
        ShowDropImage();
    }

    private void ShowDropImage() {
        if(0<=Double.parseDouble(weekWeathers.get(0).getHumidity())&&Double.parseDouble(weekWeathers.get(0).getHumidity())<20)
            day1_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(0).getHumidity())&&Double.parseDouble(weekWeathers.get(0).getHumidity())<40)
            day1_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(0).getHumidity())&&Double.parseDouble(weekWeathers.get(0).getHumidity())<60)
            day1_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(0).getHumidity())&&Double.parseDouble(weekWeathers.get(0).getHumidity())<80)
            day1_humidity_image.setImageResource(R.drawable.drop3);
        else
            day1_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(1).getHumidity())&&Double.parseDouble(weekWeathers.get(1).getHumidity())<20)
            day2_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(1).getHumidity())&&Double.parseDouble(weekWeathers.get(1).getHumidity())<40)
            day2_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(1).getHumidity())&&Double.parseDouble(weekWeathers.get(1).getHumidity())<60)
            day2_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(1).getHumidity())&&Double.parseDouble(weekWeathers.get(1).getHumidity())<80)
            day2_humidity_image.setImageResource(R.drawable.drop3);
        else
            day2_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(2).getHumidity())&&Double.parseDouble(weekWeathers.get(2).getHumidity())<20)
            day3_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(2).getHumidity())&&Double.parseDouble(weekWeathers.get(2).getHumidity())<40)
            day3_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(2).getHumidity())&&Double.parseDouble(weekWeathers.get(2).getHumidity())<60)
            day3_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(2).getHumidity())&&Double.parseDouble(weekWeathers.get(2).getHumidity())<80)
            day3_humidity_image.setImageResource(R.drawable.drop3);
        else
            day3_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(3).getHumidity())&&Double.parseDouble(weekWeathers.get(3).getHumidity())<20)
            day4_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(3).getHumidity())&&Double.parseDouble(weekWeathers.get(3).getHumidity())<40)
            day4_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(3).getHumidity())&&Double.parseDouble(weekWeathers.get(3).getHumidity())<60)
            day4_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(3).getHumidity())&&Double.parseDouble(weekWeathers.get(3).getHumidity())<80)
            day4_humidity_image.setImageResource(R.drawable.drop3);
        else
            day4_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(4).getHumidity())&&Double.parseDouble(weekWeathers.get(4).getHumidity())<20)
            day5_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(4).getHumidity())&&Double.parseDouble(weekWeathers.get(4).getHumidity())<40)
            day5_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(4).getHumidity())&&Double.parseDouble(weekWeathers.get(4).getHumidity())<60)
            day5_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(4).getHumidity())&&Double.parseDouble(weekWeathers.get(4).getHumidity())<80)
            day5_humidity_image.setImageResource(R.drawable.drop3);
        else
            day5_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(5).getHumidity())&&Double.parseDouble(weekWeathers.get(5).getHumidity())<20)
            day6_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(5).getHumidity())&&Double.parseDouble(weekWeathers.get(5).getHumidity())<40)
            day6_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(5).getHumidity())&&Double.parseDouble(weekWeathers.get(5).getHumidity())<60)
            day6_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(5).getHumidity())&&Double.parseDouble(weekWeathers.get(5).getHumidity())<80)
            day6_humidity_image.setImageResource(R.drawable.drop3);
        else
            day6_humidity_image.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(weekWeathers.get(6).getHumidity())&&Double.parseDouble(weekWeathers.get(6).getHumidity())<20)
            day7_humidity_image.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(weekWeathers.get(6).getHumidity())&&Double.parseDouble(weekWeathers.get(6).getHumidity())<40)
            day7_humidity_image.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(weekWeathers.get(6).getHumidity())&&Double.parseDouble(weekWeathers.get(6).getHumidity())<60)
            day7_humidity_image.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(weekWeathers.get(6).getHumidity())&&Double.parseDouble(weekWeathers.get(6).getHumidity())<80)
            day7_humidity_image.setImageResource(R.drawable.drop3);
        else
            day7_humidity_image.setImageResource(R.drawable.drop4);
    }

    public static String getDATE(String pattern, int num){
        DateFormat dtf = new SimpleDateFormat(pattern);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, num);
        return dtf.format(cal.getTime());
    }

    public String Whatday(int dayofWeek){
        String day = "";
        switch (dayofWeek){
            case 1:
                day = "SUN";
                break;
            case 2:
                day = "MON";
                break;
            case 3:
                day = "TUE";
                break;
            case 4:
                day = "WED";
                break;
            case 5:
                day = "THU";
                break;
            case 6:
                day = "FRI";
                break;
            case 7:
                day = "SAT";
                break;
        }
        return day;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Week.this, Page2.class);
        startActivity(intent);
    }
}