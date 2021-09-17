package com.example.weather;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
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
import java.util.List;
import java.util.Locale;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator3;
class TodayWeather{
    String date, humidity;
    double temperature;

    public TodayWeather(String date, double temp, String humidity){
        this.date = date;
        this.temperature = temp;
        this.humidity = humidity;
    }
    public double getTemperature() { return temperature ; }
    public String getHumidity(){ return humidity; }
    public String getDate(){ return date; }
}
public class Page2 extends AppCompatActivity {

    private LinearLayout background;

    Calendar cal = Calendar.getInstance();

    private TextView set_date;
    private ImageButton get_gps;
    private TextView show_gps;
    private TextView summary;
    private TextView weather_now;
    private ImageView icon_summary;
    private ImageButton menu;
    private ImageButton close_menu;
    private LinearLayout menu_page;
    private LinearLayout menu_tomorrow;
    private LinearLayout menu_week;
    private LinearLayout menu_gps;
    private LinearLayout menu_help;
    private FrameLayout main_page;

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    private ImageView fine_dust;             //미세먼지
    private ImageView superfine_dust;        //초미세먼지
    private TextView temp_high;             //최고기온
    private TextView temp_low;              //최저기온
    private TextView dust1;
    private TextView dust2;
    private TextView set_day;
    private ImageView weather_image1;
    private ImageView weather_image2;
    private ImageView weather_image3;
    private ImageView weather_image4;
    private ImageView weather_image5;
    private ImageView weather_image6;
    private ImageView weather_image7;
    private ImageView weather_image8;

    private TextView temp1;
    private TextView temp2;
    private TextView temp3;
    private TextView temp4;
    private TextView temp5;
    private TextView temp6;
    private TextView temp7;
    private TextView temp8;

    private ImageView humidity_image1;
    private ImageView humidity_image2;
    private ImageView humidity_image3;
    private ImageView humidity_image4;
    private ImageView humidity_image5;
    private ImageView humidity_image6;
    private ImageView humidity_image7;
    private ImageView humidity_image8;

    private TextView humidity1;
    private TextView humidity2;
    private TextView humidity3;
    private TextView humidity4;
    private TextView humidity5;
    private TextView humidity6;
    private TextView humidity7;
    private TextView humidity8;

    private TextView timeline1;
    private TextView timeline2;
    private TextView timeline3;
    private TextView timeline4;
    private TextView timeline5;
    private TextView timeline6;
    private TextView timeline7;
    private TextView timeline8;

    private ViewPager2 view_top;
    private ViewPager2 view_bottom;
    private int num_topPage = 3;
    private int num_bottomPage = 3;
    private CircleIndicator3 indicator1;
    private CircleIndicator3 indicator2;
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //슬라이딩 레이아웃 관련 변수
    boolean PageMenu = false;
    Animation translateLeft;
    Animation translateRight;

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    long now = System.currentTimeMillis();
    Date mDate = new Date(now);
    SimpleDateFormat month = new SimpleDateFormat("MM");
    String getMonth = month.format(mDate);
    SimpleDateFormat day = new SimpleDateFormat("dd");
    String getDay = day.format(mDate);
    String show_day = null;

    String address_now = null;

    private long backKeyPressedTime = 0;
    private Toast toast;

    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    final String App_ID = "739a4fc797b6fdd61987265968e8ecd8";
    final String CURRENT_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?";
    final String CURRENT_FINEDUST_URL = "http://api.openweathermap.org/data/2.5/air_pollution?";
    final String FIVEDAYS_WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    final String WEEk_URL = "https://api.openweathermap.org/data/2.5/onecall?";
    String current_weather_url;
    String current_finedust_url;
    String fivedays_url;
    String week_url;

    ArrayList<TodayWeather> dailyWeather = new ArrayList<>();
    ArrayList<String> timeline = new ArrayList<>();
    ArrayList<String> todayTemp = new ArrayList<>();
    ArrayList<String> todayHumidity = new ArrayList<>();
    ArrayList<String> tmpDescription = new ArrayList<>();
    ArrayList<String> todayweather = new ArrayList<>();

    double temp_now;
    ArrayList<String> top = new ArrayList<>();
    ArrayList<String> bottoms = new ArrayList<>();
    int[] numTop = new int[3];
    int[] numBottoms = new int[3];

    int now_dt, sunrise, sunset;
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
        setContentView(R.layout.activity_page2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        background = findViewById(R.id.background);
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }
        else{
            checkRunTimePermission();
        }

        translateLeft = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        cal.add(Calendar.DATE, 0);
        int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);

        SlidingPageAnimationListener animationListner = new SlidingPageAnimationListener();
        translateLeft.setAnimationListener(animationListner);
        translateRight.setAnimationListener(animationListner);

        set_date = (TextView) findViewById(R.id.set_date);
        weather_now = (TextView)findViewById(R.id.weater_now);
        summary = (TextView)findViewById(R.id.summary);
        icon_summary = (ImageView)findViewById(R.id.icon_summary);
        get_gps = (ImageButton) findViewById(R.id.get_gps);
        show_gps = (TextView) findViewById(R.id.show_gps);
        menu = (ImageButton)findViewById(R.id.menu);
        menu_page = (LinearLayout)findViewById(R.id.menu_page);
        menu_tomorrow = (LinearLayout)findViewById(R.id.menu_tomorrow);
        menu_week = (LinearLayout)findViewById(R.id.menu_week);
        menu_gps = (LinearLayout)findViewById(R.id.menu_gps);
        main_page = (FrameLayout)findViewById(R.id.main_page);
        menu_help = (LinearLayout)findViewById(R.id.menu_help);
        close_menu = (ImageButton)findViewById(R.id.close_menu);

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ오늘 쓰는 변수ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        fine_dust = (ImageView)findViewById(R.id.fine_dust);
        superfine_dust = (ImageView)findViewById(R.id.superfine_dust);
        temp_high = (TextView)findViewById(R.id.temp_high);
        temp_low = (TextView)findViewById(R.id.temp_low);
        dust1 = (TextView)findViewById(R.id.dust1);
        dust2 = (TextView)findViewById(R.id.dust2);
        set_day = (TextView)findViewById(R.id.set_day);

        weather_image1 = (ImageView)findViewById(R.id.weather_image1);
        weather_image2 = (ImageView)findViewById(R.id.weather_image2);
        weather_image3 = (ImageView)findViewById(R.id.weather_image3);
        weather_image4 = (ImageView)findViewById(R.id.weather_image4);
        weather_image5 = (ImageView)findViewById(R.id.weather_image5);
        weather_image6 = (ImageView)findViewById(R.id.weather_image6);
        weather_image7 = (ImageView)findViewById(R.id.weather_image7);
        weather_image8 = (ImageView)findViewById(R.id.weather_image8);

        temp1 = (TextView)findViewById(R.id.temp1);
        temp2 = (TextView)findViewById(R.id.temp2);
        temp3 = (TextView)findViewById(R.id.temp3);
        temp4 = (TextView)findViewById(R.id.temp4);
        temp5 = (TextView)findViewById(R.id.temp5);
        temp6 = (TextView)findViewById(R.id.temp6);
        temp7 = (TextView)findViewById(R.id.temp7);
        temp8 = (TextView)findViewById(R.id.temp8);

        humidity_image1 = (ImageView)findViewById(R.id.humidity_image1);
        humidity_image2 = (ImageView)findViewById(R.id.humidity_image2);
        humidity_image3 = (ImageView)findViewById(R.id.humidity_image3);
        humidity_image4 = (ImageView)findViewById(R.id.humidity_image4);
        humidity_image5 = (ImageView)findViewById(R.id.humidity_image5);
        humidity_image6 = (ImageView)findViewById(R.id.humidity_image6);
        humidity_image7 = (ImageView)findViewById(R.id.humidity_image7);
        humidity_image8 = (ImageView)findViewById(R.id.humidity_image8);

        humidity1 = (TextView)findViewById(R.id.humidity1);
        humidity2 = (TextView)findViewById(R.id.humidity2);
        humidity3 = (TextView)findViewById(R.id.humidity3);
        humidity4 = (TextView)findViewById(R.id.humidity4);
        humidity5 = (TextView)findViewById(R.id.humidity5);
        humidity6 = (TextView)findViewById(R.id.humidity6);
        humidity7 = (TextView)findViewById(R.id.humidity7);
        humidity8 = (TextView)findViewById(R.id.humidity8);

        timeline1 = findViewById(R.id.timeline1);
        timeline2 = findViewById(R.id.timeline2);
        timeline3 = findViewById(R.id.timeline3);
        timeline4 = findViewById(R.id.timeline4);
        timeline5 = findViewById(R.id.timeline5);
        timeline6 = findViewById(R.id.timeline6);
        timeline7 = findViewById(R.id.timeline7);
        timeline8 = findViewById(R.id.timeline8);

        indicator1 = (CircleIndicator3)findViewById(R.id.indicator1);
        indicator2 = (CircleIndicator3)findViewById(R.id.indicator2);
        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ코드시작ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        show_day = (getMonth + "월" +" "+ getDay + "일");
        set_date.setText(show_day);

        SharedPreferences sp_address =getSharedPreferences("sp_address", MODE_PRIVATE);
        String last_address = sp_address.getString("address", "서울특별시");
        if(!last_address.equals("null")){
            show_gps.setText(last_address);
        }
        address_now = last_address;

        SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
        String last_lat = sp_lat.getString("last_lat", "37.566535");

        SharedPreferences sp_lon = getSharedPreferences("longitude",MODE_PRIVATE);
        String last_lon = sp_lon.getString("last_lon", "126.97796919999999");

        current_weather_url = CURRENT_WEATHER_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&appid=" + App_ID;
        current_finedust_url = CURRENT_FINEDUST_URL+ "lat=" + last_lat + "&lon=" + last_lon
                + "&appid=" + App_ID;
        fivedays_url = FIVEDAYS_WEATHER_URL+ "lat=" + last_lat + "&lon=" + last_lon
                + "&appid=" + App_ID;
        week_url = WEEk_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&exclude=hourly&appid=" + App_ID;

        Log.v("sp_current", current_weather_url);
        Log.v("sp_five", fivedays_url);
        Log.v("sp_week", week_url);

        if(!last_lat.equals("null")&&!last_lon.equals("null")){
            DownloadJSON downloadJSON = new DownloadJSON();
            double min_temp = 0, max_temp = 0;
            try{
                String url = downloadJSON.execute(week_url).get();
                JSONObject jsonObject = new JSONObject(url);
                JSONArray jsonArray = jsonObject.getJSONArray("daily");
                for(int i=0;i<1;i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    sunset = object.getInt("sunset");
                    sunrise = object.getInt("sunrise");
                    String str = object.getString("temp");
                    JSONObject tmp = new JSONObject(str);
                    min_temp = tmp.getDouble("min");
                    min_temp -= 273.15;
                    max_temp = tmp.getDouble("max");;
                    max_temp -= 273.15;
                }
                temp_high.setText(String.format("%.1f", max_temp));
                temp_low.setText(String.format("%.1f", min_temp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!last_lat.equals("null")&&!last_lon.equals("null")){
            DownloadJSON downloadJSON_current_weather = new DownloadJSON();
            try{
                String current_weather_result = downloadJSON_current_weather.execute(current_weather_url).get();
                JSONObject jsonObject_current_weather = new JSONObject(current_weather_result);
                temp_now = jsonObject_current_weather.getJSONObject("main").getDouble("temp");
                Log.v("temp_now", String.valueOf(temp_now));
                temp_now -= 273.15;
                now_dt = jsonObject_current_weather.getInt("dt");
                JSONArray jsonArray_current_weather = jsonObject_current_weather.getJSONArray("weather");
                int id = 0;
                for(int i=0;i<jsonArray_current_weather.length();i++){
                    JSONObject jsonObject1 = jsonArray_current_weather.getJSONObject(i);
                    id = jsonObject1.getInt("id");
                }
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                String set_time = SetBackground(now_dt);
                Log.v("set_Time", set_time);
                switch (set_time) {
                    case "day":
                        background.setBackgroundResource(R.drawable.gradation);
                        window.setStatusBarColor(getResources().getColor(R.color.day));
                        break;
                    case "sunrise":
                        background.setBackgroundResource(R.drawable.gradation_sunrise);
                        window.setStatusBarColor(getResources().getColor(R.color.sunrise));
                        break;
                    case "sunset":
                        background.setBackgroundResource(R.drawable.gradation_sunset);
                        window.setStatusBarColor(getResources().getColor(R.color.sunset));
                        break;
                    case "night":
                        background.setBackgroundResource(R.drawable.gradation_night);
                        window.setStatusBarColor(getResources().getColor(R.color.night));
                        break;
                }
                String description = DescriptionToKorean(id);
                if(!(id == 800)&&set_time.equals("day")){
                    background.setBackgroundResource(R.drawable.gradation_cloudy);
                    window.setStatusBarColor(getResources().getColor(R.color.cloudy));
                }
                Log.v("2temp_now", String.valueOf(temp_now));

                weather_now.setText(String.format("%.1f", temp_now));
                summary.setText(description);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        SharedPreferences sp_gender = getSharedPreferences("sex", MODE_PRIVATE);
        gender = sp_gender.getString("SEX", "null");
        SharedPreferences sp_check_gender = getSharedPreferences("sp_check_gender", MODE_PRIVATE);
        String check_gender = sp_check_gender.getString("check_gender", "check_gender");
        Log.v("gender, check_gender", gender+", "+check_gender);
        SharedPreferences preferences = getSharedPreferences("sp_temp", MODE_PRIVATE);
        String sp_startTag = preferences.getString("Temp", "null");

        String startTag = SetClothesList(temp_now);

        if(!sp_startTag.equals(startTag)||!gender.equals(check_gender)) {
            SharedPreferences sharedPreferences = getSharedPreferences("sp_temp", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Temp", startTag);
            editor.apply();
            parser(startTag);
            RandomNumber(top.size(), numTop);

            SharedPreferences sharedPreferences1 = getSharedPreferences("sp_top", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            editor1.putString("Top", top.get(numTop[0])+","+top.get(numTop[1])+","+top.get(numTop[2]));
            editor1.apply();

            view_top = (ViewPager2)findViewById(R.id.view_top);
            ViewAdapter_top top_adapter = new ViewAdapter_top(this, num_topPage,
                    top.get(numTop[0]), top.get(numTop[1]), top.get(numTop[2]), gender);
            view_top.setAdapter(top_adapter);
            indicator1.setViewPager(view_top);
            indicator1.createIndicators(num_topPage,0);
            view_top.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            view_top.setCurrentItem(0);
            view_top.setOffscreenPageLimit(2);

            RandomNumber(bottoms.size(), numBottoms);
            SharedPreferences sharedPreferences2 = getSharedPreferences("sp_bottoms", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
            editor2.putString("Bottoms", bottoms.get(numBottoms[0])+","+bottoms.get(numBottoms[1])+","+bottoms.get(numBottoms[2]));
            editor2.apply();

            view_bottom = (ViewPager2)findViewById(R.id.view_bottom);
            ViewAdapter_bottom bottom_adapter = new ViewAdapter_bottom(this,num_bottomPage,
                    bottoms.get(numBottoms[0]),bottoms.get(numBottoms[1]), bottoms.get(numBottoms[2]), gender);
            view_bottom.setAdapter(bottom_adapter);
            indicator2.setViewPager(view_bottom);
            indicator2.createIndicators(num_bottomPage,0);
            view_bottom.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            view_bottom.setCurrentItem(0);
            view_bottom.setOffscreenPageLimit(2);
        }
        else {
            SharedPreferences sharedPreferences = getSharedPreferences("sp_top", MODE_PRIVATE);
            String strTop = sharedPreferences.getString("Top", "null");
            String[] sp_top = strTop.split(",");
            for(int i=0;i<sp_top.length;i++)
                System.out.println(i+", "+sp_top[i]);
            view_top = (ViewPager2) findViewById(R.id.view_top);
            ViewAdapter_top top_adapter = new ViewAdapter_top(this, num_topPage,
                    sp_top[0], sp_top[1], sp_top[2], gender);
            view_top.setAdapter(top_adapter);
            indicator1.setViewPager(view_top);
            indicator1.createIndicators(num_topPage, 0);
            view_top.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            view_top.setCurrentItem(0);
            view_top.setOffscreenPageLimit(2);

            SharedPreferences sharedPreferences1 = getSharedPreferences("sp_bottoms", MODE_PRIVATE);
            String strBottoms = sharedPreferences1.getString("Bottoms", "null");
            String[] sp_bottoms = strBottoms.split(",");
            Log.v("sp_bottoms :",sp_bottoms[0]+" "+ sp_bottoms[1]+" "+sp_bottoms[2] );
            view_bottom = (ViewPager2) findViewById(R.id.view_bottom);
            ViewAdapter_bottom bottom_adapter = new ViewAdapter_bottom(this, num_bottomPage,
                    sp_bottoms[0], sp_bottoms[1], sp_bottoms[2], gender);
            view_bottom.setAdapter(bottom_adapter);
            indicator2.setViewPager(view_bottom);
            indicator2.createIndicators(num_bottomPage, 0);
            view_bottom.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            view_bottom.setCurrentItem(0);
            view_bottom.setOffscreenPageLimit(2);
        }

        view_top.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    view_top.setCurrentItem(position);

                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                indicator1.animatePageSelected(position%num_topPage);
            }
        });

        view_bottom.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    view_bottom.setCurrentItem(position);
                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                indicator2.animatePageSelected(position%num_bottomPage);
            }
        });

        if(!last_lat.equals("null")&&!last_lon.equals("null")){
            double finedustpm10=0;
            double finedustpm2_5=0;
            DownloadJSON downloadJSON_current_finedust = new DownloadJSON();
            try{
                String current_finedust = downloadJSON_current_finedust.execute(current_finedust_url).get();
                JSONObject jsonObject_current_finedust = new JSONObject(current_finedust);
                JSONArray jsonArray_current_finedust = jsonObject_current_finedust.getJSONArray("list");
                String str = "";
                for(int i=0;i<jsonArray_current_finedust.length();i++)
                {
                    JSONObject jsonObject =jsonArray_current_finedust.getJSONObject(i);
                    str=jsonObject.getString("components");
                    JSONObject tmp = new JSONObject(str);
                    finedustpm10 = tmp.getDouble("pm10");
                    finedustpm2_5 = tmp.getDouble("pm2_5");
                    SetFineDustImage(finedustpm10, finedustpm2_5);
                }
                SetFineDustImage(finedustpm10, finedustpm2_5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(!last_lat.equals("null")&&!last_lon.equals("null")){
            DownloadJSON downloadJSON_fivedays = new DownloadJSON();
            try{
                String fivedays = downloadJSON_fivedays.execute(fivedays_url).get();
                JSONObject jsonObject_fivedays = new JSONObject(fivedays);
                JSONArray jsonArray_fivedays = jsonObject_fivedays.getJSONArray("list");
                for(int i=0;i<jsonArray_fivedays.length();i++){
                    JSONObject jsonObject = jsonArray_fivedays.getJSONObject(i);
                    String tmp = jsonObject.getString("main");
                    String dt_txt = jsonObject.getString("dt_txt");
                    JSONObject object = new JSONObject(tmp);
                    double temp = object.getDouble("temp");
                    temp = temp - 273.15;
                    String humid = object.getString("humidity");
                    JSONArray jsonArray = jsonObject.getJSONArray("weather");
                    for(int j=0;j<jsonArray.length();j++){
                        JSONObject object1 = jsonArray.getJSONObject(j);
                        String main = object1.getString("main");
                        tmpDescription.add(main);
                    }
                    dailyWeather.add(new TodayWeather(dt_txt, temp, humid));
                }
                for(int i=2;i<dailyWeather.size();i++){
                    SplitTimeLine(dailyWeather.get(i).getDate());
                    String strtemp = String.format("%.1f", dailyWeather.get(i).getTemperature());
                    todayTemp.add(strtemp);
                    todayHumidity.add(dailyWeather.get(i).getHumidity());
                    todayweather.add(tmpDescription.get(i));
                    if(i==10)
                        break;
                }
                ShowTimeLine();
                ShowTimeLine_Temp();
                ShowTimeLine_Humidity();
                ShowTimeLine_Description();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PageMenu){
                    menu_page.startAnimation(translateRight);
                    main_page.setBackground( new ColorDrawable(0xf000000));
                }
                else{
                    menu_page.setVisibility(View.VISIBLE);
                    menu_page.startAnimation(translateLeft);
                    main_page.setBackground( new ColorDrawable(0x7f000000));
                }
            }
        });

        close_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_page.startAnimation(translateRight);
                main_page.setBackground( new ColorDrawable(0xf000000));
            }
        });

        get_gps.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                boolean flag = true;
                gpsTracker = new GpsTracker(Page2.this);
                double latitude = gpsTracker.getLatitude();
                double longitude = gpsTracker.getLongitude();

                SharedPreferences lat = getSharedPreferences("latitude", MODE_PRIVATE);
                String last_lat = lat.getString("last_lat", "-1000");
                SharedPreferences lon = getSharedPreferences("longitude",MODE_PRIVATE);
                String last_lon = lon.getString("last_lon", "-1000");

                if(!last_lat.equals(Double.toString(latitude))||!last_lon.equals(Double.toString(longitude)))
                    flag = false;

                SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
                SharedPreferences.Editor editor_lat = sp_lat.edit();
                editor_lat.putString("last_lat", String.valueOf(latitude));
                editor_lat.apply();

                SharedPreferences sp_lon = getSharedPreferences("longitude", MODE_PRIVATE);
                SharedPreferences.Editor editor_lon = sp_lon.edit();
                editor_lon.putString("last_lon", String.valueOf(longitude));
                editor_lon.apply();

                String tmpaddress = getCurrentAddress(latitude,longitude);
                String address = SplitAddress(tmpaddress);
                address_now = address;
                show_gps.setText(address);

                Toast.makeText(getApplicationContext(),"위치정보가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                current_weather_url = CURRENT_WEATHER_URL + "lat=" + latitude + "&lon=" + longitude
                        + "&appid=" + App_ID;
                current_finedust_url = CURRENT_FINEDUST_URL+ "lat=" + latitude + "&lon=" + longitude
                        + "&appid=" + App_ID;
                fivedays_url = FIVEDAYS_WEATHER_URL+ "lat=" + latitude + "&lon=" + longitude
                        + "&appid=" + App_ID;
                week_url = WEEk_URL + "lat=" + latitude + "&lon=" + longitude
                        + "&exclude=hourly&appid=" + App_ID;

                Log.v("current", current_weather_url);
                Log.v("five", fivedays_url);
                Log.v("week", week_url);

                DownloadJSON downloadJSON = new DownloadJSON();
                double min_temp = 0, max_temp = 0;
                try{
                    String url = downloadJSON.execute(week_url).get();
                    JSONObject jsonObject = new JSONObject(url);
                    JSONArray jsonArray = jsonObject.getJSONArray("daily");
                    for(int i=0;i<1;i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        now_dt = object.getInt("dt");
                        sunset = object.getInt("sunset");
                        sunrise = object.getInt("sunrise");
                        String str = object.getString("temp");
                        JSONObject tmp = new JSONObject(str);
                        min_temp = tmp.getDouble("min");
                        Log.v("min", String.valueOf(min_temp));
                        min_temp-=273.15;
                        max_temp = tmp.getDouble("max");
                        Log.v("max", String.valueOf(max_temp));
                        max_temp -= 273.15;
                    }
                    if(!flag) {
                        temp_high.setText(String.format("%.1f", max_temp));
                        temp_low.setText(String.format("%.1f", min_temp));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                double gps_temp = 0;
                DownloadJSON downloadJSON_current_weather = new DownloadJSON();
                try {
                    String current_weather_result = downloadJSON_current_weather.execute(current_weather_url).get();

                    JSONObject jsonObject_current_weather = new JSONObject(current_weather_result);
                    gps_temp = jsonObject_current_weather.getJSONObject("main").getDouble("temp");
                    System.out.println("current : " + gps_temp);
                    gps_temp = gps_temp - 273.15;
                    now_dt = jsonObject_current_weather.getInt("dt");
                    JSONArray jsonArray_current_weather = jsonObject_current_weather.getJSONArray("weather");
                    int id = 0;
                    for (int i = 0; i < jsonArray_current_weather.length(); i++) {
                        JSONObject jsonObject1 = jsonArray_current_weather.getJSONObject(i);
                        id = jsonObject1.getInt("id");
                    }
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    String set_time = SetBackground(now_dt);
                    Log.v("set_Time", set_time);
                    switch (set_time) {
                        case "day":
                            background.setBackgroundResource(R.drawable.gradation);
                            window.setStatusBarColor(getResources().getColor(R.color.day));
                            break;
                        case "sunrise":
                            background.setBackgroundResource(R.drawable.gradation_sunrise);
                            window.setStatusBarColor(getResources().getColor(R.color.sunrise));
                            break;
                        case "sunset":
                            background.setBackgroundResource(R.drawable.gradation_sunset);
                            window.setStatusBarColor(getResources().getColor(R.color.sunset));
                            break;
                        case "night":
                            background.setBackgroundResource(R.drawable.gradation_night);
                            window.setStatusBarColor(getResources().getColor(R.color.night));
                            break;
                    }
                    String description = DescriptionToKorean(id);
                    if (!(id == 800) && set_time.equals("day")) {
                        background.setBackgroundResource(R.drawable.gradation_cloudy);
                        window.setStatusBarColor(getResources().getColor(R.color.cloudy));
                    }
                    weather_now.setText(String.format("%.1f", gps_temp));
                    summary.setText(description);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                String gpsTag = SetClothesList(gps_temp);
                SharedPreferences sharedPreferences = getSharedPreferences("sp_temp", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Temp", gpsTag);
                editor.apply();

                top.clear(); bottoms.clear();
                parser(gpsTag);
                RandomNumber(top.size(), numTop);
                RandomNumber(bottoms.size(), numBottoms);

                SharedPreferences sharedPreferences1 = getSharedPreferences("sp_top", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                editor1.putString("Top", top.get(numTop[0])+","+top.get(numTop[1])+","+top.get(numTop[2]));
                editor1.apply();

                SharedPreferences sharedPreferences2 = getSharedPreferences("sp_bottoms", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putString("Bottoms", bottoms.get(numBottoms[0])+","+bottoms.get(numBottoms[1])+","+bottoms.get(numBottoms[2]));
                editor2.apply();

                UpdateClothes();

                double finedustpm10=0;
                double finedustpm2_5=0;
                DownloadJSON downloadJSON_current_finedust = new DownloadJSON();
                try{
                    String current_finedust = downloadJSON_current_finedust.execute(current_finedust_url).get();
                    JSONObject jsonObject_current_finedust = new JSONObject(current_finedust);
                    JSONArray jsonArray_current_finedust = jsonObject_current_finedust.getJSONArray("list");
                    String str="";
                    for(int i=0;i<jsonArray_current_finedust.length();i++)
                    {
                        JSONObject jsonObject = jsonArray_current_finedust.getJSONObject(i);
                        str=jsonObject.getString("components");
                        JSONObject tmp = new JSONObject(str);
                        finedustpm10 = tmp.getDouble("pm10");
                        finedustpm2_5 = tmp.getDouble("pm2_5");
                    }
                    SetFineDustImage(finedustpm10, finedustpm2_5);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                DownloadJSON downloadJSON_fivedays = new DownloadJSON();
                try{
                    String fivedays = downloadJSON_fivedays.execute(fivedays_url).get();
                    JSONObject jsonObject_fivedays = new JSONObject(fivedays);
                    Log.v("obj", String.valueOf(jsonObject_fivedays));
                    JSONArray jsonArray_fivedays = jsonObject_fivedays.getJSONArray("list");
                    Log.v("fivedays", String.valueOf(jsonArray_fivedays));
                    for(int i=0;i<jsonArray_fivedays.length();i++){
                        JSONObject jsonObject = jsonArray_fivedays.getJSONObject(i);
                        String tmp = jsonObject.getString("main");
                        String dt_txt = jsonObject.getString("dt_txt");
                        JSONObject object = new JSONObject(tmp);
                        double temp = object.getDouble("temp");
                        temp = temp - 273.15;
                        String humid = object.getString("humidity");
                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        for(int j=0;j<jsonArray.length();j++){
                            JSONObject object1 = jsonArray.getJSONObject(j);
                            String main = object1.getString("main");
                            tmpDescription.add(main);
                        }
                        dailyWeather.add(new TodayWeather(dt_txt, temp, humid));
                    }
                    for(int i=2;i<dailyWeather.size();i++){
                        SplitTimeLine(dailyWeather.get(i).getDate());
                        String strtemp = String.format("%.1f", dailyWeather.get(i).temperature);
                        todayTemp.add(strtemp);
                        todayHumidity.add(dailyWeather.get(i).getHumidity());
                        todayweather.add(tmpDescription.get(i));
                        if(i==10)
                            break;
                    }
                    ShowTimeLine();
                    ShowTimeLine_Temp();
                    ShowTimeLine_Humidity();
                    ShowTimeLine_Description();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if(Whatday(dayofWeek)=="SUN"){
            set_day.setTextColor(Color.RED);
            set_day.setText(Whatday(dayofWeek));
        }
        else if(Whatday(dayofWeek)=="SAT"){
            set_day.setTextColor(Color.BLUE);
            set_day.setText(Whatday(dayofWeek));
        }
        else{
            set_day.setTextColor(Color.WHITE);
            set_day.setText(Whatday(dayofWeek));
        }

        menu_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent week = new Intent(Page2.this,Week.class);
                Toast.makeText(getApplicationContext(),"주간 날씨 데이터 받아오는중...", Toast.LENGTH_SHORT).show();
                startActivity(week);
            }
        });

        menu_tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomo = new Intent(Page2.this,TomorrowWeather.class);
                Toast.makeText(getApplicationContext(),"내일 날씨 데이터 받아오는중...", Toast.LENGTH_SHORT).show();
                tomo.putExtra("address",address_now);
                startActivity(tomo);
            }
        });

        menu_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Page2.this, SearchLocation.class);
                startActivity(intent);
            }
        });

        menu_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(Page2.this, Help.class);
                startActivity(help);
            }
        });
    }

    private String SetBackground(int now_dt) {
        System.out.println("rise : "+sunrise);
        System.out.println("set : "+sunset);
        System.out.println("now : "+now_dt);

        if(sunrise+1800<=now_dt&&now_dt<sunset-900)
            return "day";
        else if(sunset-900<=now_dt&&now_dt<sunset+1800)
            return "sunset";
        else if(sunrise-900<=now_dt&&now_dt<sunrise+1800)
            return "sunrise";
        else return "night";
    }

    private void UpdateClothes() {
        view_top = (ViewPager2) findViewById(R.id.view_top);
        ViewAdapter_top top_adapter = new ViewAdapter_top(Page2.this, num_topPage,
                top.get(numTop[0]), top.get(numTop[1]), top.get(numTop[2]), gender);
        view_top.setAdapter(top_adapter);
        indicator1.setViewPager(view_top);
        indicator1.createIndicators(num_topPage, 0);
        view_top.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        view_top.setCurrentItem(0);
        view_top.setOffscreenPageLimit(2);

        view_bottom = (ViewPager2)findViewById(R.id.view_bottom);
        ViewAdapter_bottom bottom_adapter = new ViewAdapter_bottom(Page2.this,num_bottomPage,
                bottoms.get(numBottoms[0]),bottoms.get(numBottoms[1]), bottoms.get(numBottoms[2]), gender);
        view_bottom.setAdapter(bottom_adapter);
        indicator2.setViewPager(view_bottom);
        indicator2.createIndicators(num_bottomPage,0);
        view_bottom.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        view_bottom.setCurrentItem(0);
        view_bottom.setOffscreenPageLimit(2);

        view_top.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    view_top.setCurrentItem(position);

                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                indicator1.animatePageSelected(position%num_topPage);
            }
        });

        view_bottom.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    view_bottom.setCurrentItem(position);
                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                indicator2.animatePageSelected(position%num_bottomPage);
            }
        });
    }

    private void RandomNumber(int size, int[] arr) {
        int a, b, c;
        do {
            Random random = new Random();
            a = random.nextInt(size);
            b = random.nextInt(size);
            c = random.nextInt(size);
        } while (a == b || a == c || b == c);
        arr[0] = a; arr[1] = b; arr[2] = c;
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

    private void ShowTimeLine_Description() {
        switch (todayweather.get(0)) {
            case "Thunderstorm":
                weather_image1.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image1.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image1.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image1.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image1.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(1)) {
            case "Thunderstorm":
                weather_image2.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image2.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image2.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image2.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image2.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(2)) {
            case "Thunderstorm":
                weather_image3.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image3.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image3.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image3.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image3.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(3)) {
            case "Thunderstorm":
                weather_image4.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image4.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image4.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image4.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image4.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(4)) {
            case "Thunderstorm":
                weather_image5.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image5.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image5.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image5.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image5.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(5)) {
            case "Thunderstorm":
                weather_image6.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image6.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image6.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image6.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image6.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(6)) {
            case "Thunderstorm":
                weather_image7.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image7.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image7.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image7.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image7.setImageResource(R.drawable.cloud);
                break;
        }
        switch (todayweather.get(7)) {
            case "Thunderstorm":
                weather_image8.setImageResource(R.drawable.thunder);
                break;
            case "Drizzle":
            case "Rain":
                weather_image8.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                weather_image8.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                weather_image8.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                weather_image8.setImageResource(R.drawable.cloud);
                break;
        }
    }

    private void ShowTimeLine_Humidity() {
        humidity1.setText(todayHumidity.get(0));
        humidity2.setText(todayHumidity.get(1));
        humidity3.setText(todayHumidity.get(2));
        humidity4.setText(todayHumidity.get(3));
        humidity5.setText(todayHumidity.get(4));
        humidity6.setText(todayHumidity.get(5));
        humidity7.setText(todayHumidity.get(6));
        humidity8.setText(todayHumidity.get(7));
        ShowDropImage();
    }

    private void ShowDropImage() {
        if(0<=Double.parseDouble(todayHumidity.get(0))&&Double.parseDouble(todayHumidity.get(0))<20)
            humidity_image1.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(0))&&Double.parseDouble(todayHumidity.get(0))<40)
            humidity_image1.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(0))&&Double.parseDouble(todayHumidity.get(0))<60)
            humidity_image1.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(0))&&Double.parseDouble(todayHumidity.get(0))<80)
            humidity_image1.setImageResource(R.drawable.drop3);
        else
            humidity_image1.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(1))&&Double.parseDouble(todayHumidity.get(1))<20)
            humidity_image2.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(1))&&Double.parseDouble(todayHumidity.get(1))<40)
            humidity_image2.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(1))&&Double.parseDouble(todayHumidity.get(1))<60)
            humidity_image2.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(1))&&Double.parseDouble(todayHumidity.get(1))<80)
            humidity_image2.setImageResource(R.drawable.drop3);
        else
            humidity_image2.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(2))&&Double.parseDouble(todayHumidity.get(2))<20)
            humidity_image3.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(2))&&Double.parseDouble(todayHumidity.get(2))<40)
            humidity_image3.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(2))&&Double.parseDouble(todayHumidity.get(2))<60)
            humidity_image3.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(2))&&Double.parseDouble(todayHumidity.get(2))<80)
            humidity_image3.setImageResource(R.drawable.drop3);
        else
            humidity_image3.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(3))&&Double.parseDouble(todayHumidity.get(3))<20)
            humidity_image4.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(3))&&Double.parseDouble(todayHumidity.get(3))<40)
            humidity_image4.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(3))&&Double.parseDouble(todayHumidity.get(3))<60)
            humidity_image4.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(3))&&Double.parseDouble(todayHumidity.get(3))<80)
            humidity_image4.setImageResource(R.drawable.drop3);
        else
            humidity_image4.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(4))&&Double.parseDouble(todayHumidity.get(4))<20)
            humidity_image5.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(4))&&Double.parseDouble(todayHumidity.get(4))<40)
            humidity_image5.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(4))&&Double.parseDouble(todayHumidity.get(4))<60)
            humidity_image5.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(4))&&Double.parseDouble(todayHumidity.get(4))<80)
            humidity_image5.setImageResource(R.drawable.drop3);
        else
            humidity_image5.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(5))&&Double.parseDouble(todayHumidity.get(5))<20)
            humidity_image6.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(5))&&Double.parseDouble(todayHumidity.get(5))<40)
            humidity_image6.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(5))&&Double.parseDouble(todayHumidity.get(5))<60)
            humidity_image6.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(5))&&Double.parseDouble(todayHumidity.get(5))<80)
            humidity_image6.setImageResource(R.drawable.drop3);
        else
            humidity_image6.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(6))&&Double.parseDouble(todayHumidity.get(6))<20)
            humidity_image7.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(6))&&Double.parseDouble(todayHumidity.get(6))<40)
            humidity_image7.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(6))&&Double.parseDouble(todayHumidity.get(6))<60)
            humidity_image7.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(6))&&Double.parseDouble(todayHumidity.get(6))<80)
            humidity_image7.setImageResource(R.drawable.drop3);
        else
            humidity_image7.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(todayHumidity.get(7))&&Double.parseDouble(todayHumidity.get(7))<20)
            humidity_image8.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(todayHumidity.get(7))&&Double.parseDouble(todayHumidity.get(7))<40)
            humidity_image8.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(todayHumidity.get(7))&&Double.parseDouble(todayHumidity.get(7))<60)
            humidity_image8.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(todayHumidity.get(7))&&Double.parseDouble(todayHumidity.get(7))<80)
            humidity_image8.setImageResource(R.drawable.drop3);
        else
            humidity_image8.setImageResource(R.drawable.drop4);
    }

    @SuppressLint("SetTextI18n")
    private void ShowTimeLine_Temp() {
        temp1.setText(""+todayTemp.get(0));
        temp2.setText(""+todayTemp.get(1));
        temp3.setText(""+todayTemp.get(2));
        temp4.setText(""+todayTemp.get(3));
        temp5.setText(""+todayTemp.get(4));
        temp6.setText(""+todayTemp.get(5));
        temp7.setText(""+todayTemp.get(6));
        temp8.setText(""+todayTemp.get(7));
    }

    @SuppressLint("SetTextI18n")
    private void ShowTimeLine() {
        timeline1.setText(timeline.get(0)+" ~ "+timeline.get(1));
        timeline2.setText(timeline.get(1)+" ~ "+timeline.get(2));
        timeline3.setText(timeline.get(2)+" ~ "+timeline.get(3));
        timeline4.setText(timeline.get(3)+" ~ "+timeline.get(4));
        timeline5.setText(timeline.get(4)+" ~ "+timeline.get(5));
        timeline6.setText(timeline.get(5)+" ~ "+timeline.get(6));
        timeline7.setText(timeline.get(6)+" ~ "+timeline.get(7));
        timeline8.setText(timeline.get(7)+" ~ "+timeline.get(8));
    }

    private void SplitTimeLine(String date) {
        String[] words = date.split(" ");
        String str = words[1];
        String[] words2 = str.split(":");
        timeline.add(words2[0]+" : "+words2[1]);
    }

    private String SplitAddress(String address) {
        String[] words = address.split(" ");
        String ret=words[1]+" "+words[2]+" "+words[3];
        return ret;
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(PageMenu){
                menu_page.setVisibility(View.INVISIBLE);

                PageMenu = false;
            }
            else{
                PageMenu = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void SetFineDustImage(double finedustpm10, double finedustpm2_5) {
        if(0<=finedustpm10&&finedustpm10<=30) {
            fine_dust.setImageResource(R.drawable.fine_dust1);
            dust1.setText("좋음"); }
        else if(31<=finedustpm10&&finedustpm10<=80) {
            fine_dust.setImageResource(R.drawable.fine_dust2);
            dust1.setText("보통");}
        else if(81<=finedustpm10&&finedustpm10<=150){
            fine_dust.setImageResource(R.drawable.fine_dust3);
            dust1.setText("나쁨");}
        else{
            fine_dust.setImageResource(R.drawable.fine_dust4);
            dust1.setText("매우나쁨");}

        if(0<=finedustpm2_5&&finedustpm2_5<=15) {
            superfine_dust.setImageResource(R.drawable.fine_dust1);
            dust2.setText("좋음");}
        else if(16<=finedustpm2_5&&finedustpm2_5<=35){
            superfine_dust.setImageResource(R.drawable.fine_dust2);
            dust2.setText("보통");}
        else if(36<=finedustpm2_5&&finedustpm2_5<=75){
            superfine_dust.setImageResource(R.drawable.fine_dust3);
            dust2.setText("나쁨");}
        else{
            superfine_dust.setImageResource(R.drawable.fine_dust4);
            dust2.setText("매우나쁨");}
    }

    public String DescriptionToKorean(int id) {
        String result="";
        switch (id){
            case 200:
            case 201:
            case 202:
            case 210:
            case 211:
            case 212:
            case 221:
            case 230:
            case 231:
            case 232:
                result = "뇌우";
                icon_summary.setImageResource(R.drawable.thunder);
                break;
            case 300:
            case 301:
            case 302:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 321:
            case 500:
                result = "약한 비";
                icon_summary.setImageResource(R.drawable.rainy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.rainy_night);
                break;
            case 501:
            case 502:
                result = "비";
                icon_summary.setImageResource(R.drawable.rainy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.rainy_night);
                break;
            case 503:
                result = "강한 비";
                icon_summary.setImageResource(R.drawable.rainy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.rainy_night);
                break;
            case 504:
                result = "매우 강한 비";
                icon_summary.setImageResource(R.drawable.rainy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.rainy_night);
                break;
            case 511:
            case 615:
            case 616:
                result = "눈비";
                icon_summary.setImageResource(R.drawable.snowrain);
                break;
            case 520:
            case 521:
            case 522:
            case 531:
                result = "소나기";
                icon_summary.setImageResource(R.drawable.rainy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.rainy_night);
                break;
            case 600:
            case 612:
            case 620:
                result = "약한 눈";
                icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 601:
            case 611:
            case 613:
            case 621:
                result = "눈";
                icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 602:
            case 622:
                result = "강한 눈";
                icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 701:
            case 711:
            case 721:
            case 741:
                result = "안개";
                icon_summary.setImageResource(R.drawable.haze);
                break;
            case 771:
                result = "바람";
                icon_summary.setImageResource(R.drawable.wind);
                break;
            case 781:
                result = "태풍";
                icon_summary.setImageResource(R.drawable.storm);
                break;
            case 731:
            case 751:
            case 761:
            case 762:
                result = "황사";
                icon_summary.setImageResource(R.drawable.sand);
                break;
            case 800:
                result = "맑음";
                icon_summary.setImageResource(R.drawable.sun);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.night);
                break;
            case 801:
                result = "구름 조금";
                icon_summary.setImageResource(R.drawable.cloudy);
                if(SetBackground(now_dt).equals("night"))
                    icon_summary.setImageResource(R.drawable.cloudy_night);
                break;
            case 802:
            case 803:
                result = "흐림";
                icon_summary.setImageResource(R.drawable.cloud);
                break;
            case 804:
                result = "구름 많음";
                icon_summary.setImageResource(R.drawable.cloud);
                break;
        }
        return result;
    }

    public void onRequestPermissionResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResult) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResult.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;

            for (int result : grandResult) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (check_result) {
                ;
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0]) || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                    Toast.makeText(Page2.this, "퍼미션이 거부되었습니다. 앱을 재실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(Page2.this, "퍼미션이 거부되었습니다. 설정에서 퍼미션을 허용해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        }
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

    void checkRunTimePermission(){
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(Page2.this,Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(Page2.this, Manifest.permission.ACCESS_COARSE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){}
        else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(Page2.this,REQUIRED_PERMISSIONS[0])){
                Toast.makeText(Page2.this, "이 앱을 실행하기 위해서 위치 접근 권환이 필요합니다.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(Page2.this,REQUIRED_PERMISSIONS,PERMISSIONS_REQUEST_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(Page2.this, REQUIRED_PERMISSIONS,PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    public String getCurrentAddress(double latitude, double longtitude){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longtitude,
                    7);
        } catch (IOException ioException) {
            Toast.makeText(this,"NETWORK 상태 불량",Toast.LENGTH_SHORT).show();
            return "지오코더 사용불가";
        } catch (IllegalArgumentException illegalArgumentException){
            Toast.makeText(this, "잘못된 GPS좌표",Toast.LENGTH_SHORT).show();
            return "GPS 좌표 오류";
        }

        if (addresses == null || addresses.size() == 0){
            Toast.makeText(this,"주소 미발견", Toast.LENGTH_SHORT).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";
    }

    private void showDialogForLocationServiceSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Page2.this);
        builder.setTitle("GPS 비활성화");
        builder.setMessage("원활한 앱사용을 위해 GPS를 켜주세요");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPS = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPS,GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GPS_ENABLE_REQUEST_CODE:
                if(checkLocationServicesStatus()){
                    if (checkLocationServicesStatus()){
                        Log.d("@@@", "onActivityResult : GPS ON");
                        checkRunTimePermission();
                        return;
                    }
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            ActivityCompat.finishAffinity(this);
            toast.cancel();
        }
    }
}