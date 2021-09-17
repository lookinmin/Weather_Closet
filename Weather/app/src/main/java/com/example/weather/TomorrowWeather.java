package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;

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
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator3;

class TomoWeather{
    String  humidity, date;
    double temperature;
    public TomoWeather(String date, double temp, String humidity){
        this.date = date;
        this.temperature = temp;
        this.humidity = humidity;
    }
    public double getTemperature() {
        return temperature ;
    }
    public String getHumidity(){
        return humidity;
    }
    public String getDate(){
        return date;
    }
}
public class TomorrowWeather extends AppCompatActivity {

    private  LinearLayout tomo_page;

    private ImageView tomo_fine_dust;             //미세먼지
    private ImageView tomo_superfine_dust;        //초미세먼지
    private TextView tomo_temp_high;             //최고기온
    private TextView tomo_temp_low;              //최저기온
    private TextView tomorrow_date;
    private TextView tomo_show_gps;
    private TextView tomo_weather_now;
    private ImageView tomo_icon_summary;
    private TextView tomo_summary;
    private TextView dust1;
    private TextView dust2;
    private TextView title;

    Calendar cal = Calendar.getInstance();
    private ImageView tomo_weather_image1;
    private ImageView tomo_weather_image2;
    private ImageView tomo_weather_image3;
    private ImageView tomo_weather_image4;
    private ImageView tomo_weather_image5;
    private ImageView tomo_weather_image6;
    private ImageView tomo_weather_image7;
    private ImageView tomo_weather_image8;

    private TextView set_day;
    private TextView tomo_temp1;
    private TextView tomo_temp2;
    private TextView tomo_temp3;
    private TextView tomo_temp4;
    private TextView tomo_temp5;
    private TextView tomo_temp6;
    private TextView tomo_temp7;
    private TextView tomo_temp8;

    private ImageView tomo_humidity_image1;
    private ImageView tomo_humidity_image2;
    private ImageView tomo_humidity_image3;
    private ImageView tomo_humidity_image4;
    private ImageView tomo_humidity_image5;
    private ImageView tomo_humidity_image6;
    private ImageView tomo_humidity_image7;
    private ImageView tomo_humidity_image8;

    private TextView tomo_humidity1;
    private TextView tomo_humidity2;
    private TextView tomo_humidity3;
    private TextView tomo_humidity4;
    private TextView tomo_humidity5;
    private TextView tomo_humidity6;
    private TextView tomo_humidity7;
    private TextView tomo_humidity8;

    private ViewPager2 tomo_view_top;
    private ViewPager2 tomo_view_bottom;
    private int tomo_num_topPage = 3;
    private int tomo_num_bottomPage = 3;
    private CircleIndicator3 tomo_indicator1;
    private CircleIndicator3 tomo_indicator2;

    String address = null;

    final String App_ID = "739a4fc797b6fdd61987265968e8ecd8";
    final String TOMO_FINEDUST_URL = "http://api.openweathermap.org/data/2.5/air_pollution/forecast?";
    final String FIVEDAYS_WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    String tomo_finedust_url;
    String fivedays_url;

    ArrayList<TomoWeather> tomoweather = new ArrayList<>();
    ArrayList<String> timeLine = new ArrayList<>();
    ArrayList<Double> timeLine_temp = new ArrayList<>();
    ArrayList<String> timeLine_humidity = new ArrayList<>();
    ArrayList<String> timeLine_description = new ArrayList<>();
    ArrayList<Double> tomo_pm10 = new ArrayList<>();
    ArrayList<Double> tomo_pm2_5 = new ArrayList<>();

    boolean timeflag = false;

    ArrayList<String> tmpDescription = new ArrayList<>();

    ArrayList<String> top = new ArrayList<>();
    ArrayList<String> bottoms = new ArrayList<>();
    int[] numTop = new int[3];
    int[] numBottoms = new int[3];

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
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow_weather);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        cal.add(Calendar.DATE, 1);
        int dayofWeek = cal.get(Calendar.DAY_OF_WEEK);

        tomo_page = findViewById(R.id.tomo_page);

        tomo_fine_dust = (ImageView)findViewById(R.id.tomo_fine_dust);
        tomo_superfine_dust = (ImageView)findViewById(R.id.tomo_superfine_dust);
        tomo_temp_high = (TextView)findViewById(R.id.tomo_temp_high);
        tomo_temp_low = (TextView)findViewById(R.id.tomo_temp_low);
        tomorrow_date = (TextView)findViewById(R.id.tomorrow_date);
        tomo_show_gps = (TextView)findViewById(R.id.tomo_show_gps);
        tomo_weather_now = (TextView)findViewById(R.id.tomo_weather_now);
        tomo_icon_summary = (ImageView)findViewById(R.id.tomo_icon_summary);
        tomo_summary = (TextView)findViewById(R.id.tomo_summary);
        dust1 = (TextView)findViewById(R.id.dust1);
        dust2 = (TextView)findViewById(R.id.dust2);
        set_day = (TextView)findViewById(R.id.set_day);
        title = (TextView)findViewById(R.id.title);

        tomo_weather_image1 = (ImageView)findViewById(R.id.tomo_weather_image1);
        tomo_weather_image2 = (ImageView)findViewById(R.id.tomo_weather_image2);
        tomo_weather_image3 = (ImageView)findViewById(R.id.tomo_weather_image3);
        tomo_weather_image4 = (ImageView)findViewById(R.id.tomo_weather_image4);
        tomo_weather_image5 = (ImageView)findViewById(R.id.tomo_weather_image5);
        tomo_weather_image6 = (ImageView)findViewById(R.id.tomo_weather_image6);
        tomo_weather_image7 = (ImageView)findViewById(R.id.tomo_weather_image7);
        tomo_weather_image8 = (ImageView)findViewById(R.id.tomo_weather_image8);

        tomo_temp1 = (TextView)findViewById(R.id.tomo_temp1);
        tomo_temp2 = (TextView)findViewById(R.id.tomo_temp2);
        tomo_temp3 = (TextView)findViewById(R.id.tomo_temp3);
        tomo_temp4 = (TextView)findViewById(R.id.tomo_temp4);
        tomo_temp5 = (TextView)findViewById(R.id.tomo_temp5);
        tomo_temp6 = (TextView)findViewById(R.id.tomo_temp6);
        tomo_temp7 = (TextView)findViewById(R.id.tomo_temp7);
        tomo_temp8 = (TextView)findViewById(R.id.tomo_temp8);

        tomo_humidity_image1 = (ImageView)findViewById(R.id.tomo_humidity_image1);
        tomo_humidity_image2 = (ImageView)findViewById(R.id.tomo_humidity_image2);
        tomo_humidity_image3 = (ImageView)findViewById(R.id.tomo_humidity_image3);
        tomo_humidity_image4 = (ImageView)findViewById(R.id.tomo_humidity_image4);
        tomo_humidity_image5 = (ImageView)findViewById(R.id.tomo_humidity_image5);
        tomo_humidity_image6 = (ImageView)findViewById(R.id.tomo_humidity_image6);
        tomo_humidity_image7 = (ImageView)findViewById(R.id.tomo_humidity_image7);
        tomo_humidity_image8 = (ImageView)findViewById(R.id.tomo_humidity_image8);

        tomo_humidity1 = (TextView)findViewById(R.id.tomo_humidity1);
        tomo_humidity2 = (TextView)findViewById(R.id.tomo_humidity2);
        tomo_humidity3 = (TextView)findViewById(R.id.tomo_humidity3);
        tomo_humidity4 = (TextView)findViewById(R.id.tomo_humidity4);
        tomo_humidity5 = (TextView)findViewById(R.id.tomo_humidity5);
        tomo_humidity6 = (TextView)findViewById(R.id.tomo_humidity6);
        tomo_humidity7 = (TextView)findViewById(R.id.tomo_humidity7);
        tomo_humidity8 = (TextView)findViewById(R.id.tomo_humidity8);

        tomo_indicator1 = (CircleIndicator3)findViewById(R.id.tomo_indicator1);
        tomo_indicator2 = (CircleIndicator3)findViewById(R.id.tomo_indicator2);

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

        tomorrow_date.setText(getTomorrow("MM")+"월"+" "+getTomorrow("dd")+"일");

        boolean flag = true;
        SharedPreferences sharedPreferences = getSharedPreferences("tomo_sp_day", MODE_PRIVATE);
        String tomo_day = sharedPreferences.getString("tomo_day", "null");
        if(!tomo_day.equals(getTomorrow("MM")+"월"+" "+getTomorrow("dd")+"일")){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tomo_day", getTomorrow("MM")+"월"+" "+getTomorrow("dd")+"일");
            editor.apply();
            flag = false;
        }
        Intent tomo = getIntent();
        address = tomo.getStringExtra("address");

        tomo_show_gps.setText(address);

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

        SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
        String last_lat = sp_lat.getString("last_lat", "null");

        SharedPreferences sp_lon = getSharedPreferences("longitude",MODE_PRIVATE);
        String last_lon = sp_lon.getString("last_lon", "null");

        WeatherSummary(last_lat, last_lon);

        tomo_finedust_url = TOMO_FINEDUST_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&appid=" + App_ID;

        fivedays_url = FIVEDAYS_WEATHER_URL + "lat=" + last_lat + "&lon=" + last_lon
                + "&appid=" + App_ID;

        double tomo_temp = 0;
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
                String humid = object.getString("humidity");
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                for(int j=0;j<jsonArray.length();j++){
                    JSONObject object1 = jsonArray.getJSONObject(j);
                    String main = object1.getString("main");
                    tmpDescription.add(main);
                }
                tomoweather.add(new TomoWeather(dt_txt, temp, humid));
            }
            int cnt = 0;
            for(int i=2;i<tomoweather.size();i++){
                SplitTimeLine(tomoweather.get(i).getDate());
                double temp = tomoweather.get(i).getTemperature()-273.15;
                if(timeflag) {
                    cnt++;
                    String str = String.format("%.1f", temp);
                    timeLine_temp.add(Double.parseDouble(str));
                    timeLine_humidity.add(tomoweather.get(i).getHumidity());
                    timeLine_description.add(tmpDescription.get(i));
                }
                if(cnt==12)
                    break;
            }
            String ave_temp = CalAve(timeLine_temp);
            tomo_temp = Double.parseDouble(ave_temp);
            tomo_weather_now.setText(ave_temp);
            Collections.sort(timeLine_temp);
            tomo_temp_high.setText(""+timeLine_temp.get(timeLine_temp.size()-1));
            tomo_temp_low.setText(Double.toString(timeLine_temp.get(0)));
            ShowTimeLine_Temp();
            ShowTimeLine_Humidity();
            ShowTimeLine_Description();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences sp_gender = getSharedPreferences("sex", MODE_PRIVATE);
        String gender = sp_gender.getString("SEX", "null");
        SharedPreferences sp_check_gender = getSharedPreferences("sp_check_gender", MODE_PRIVATE);
        String check_gender = sp_check_gender.getString("check_gender", "check_gender");
        Log.v("gender, check_gender", gender+", "+check_gender);

        SharedPreferences preferences = getSharedPreferences("tomo_sp_temp", MODE_PRIVATE);
        String sp_startTag = preferences.getString("tomo_Temp", "null");
        String startTag = SetClothesList(tomo_temp);
        if(!sp_startTag.equals(startTag)|| !flag ||!gender.equals(check_gender)){
            Log.v("new", "new");
            parser(startTag);
            SharedPreferences sharedPreferences1 = getSharedPreferences("tomo_sp_temp", MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedPreferences1.edit();
            editor1.putString("tomo_Temp", startTag);
            editor1.apply();

            RandomNumber(top.size(), numTop);
            SharedPreferences sharedPreferences2 = getSharedPreferences("tomo_sp_top", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
            editor2.putString("tomo_Top", top.get(numTop[0])+","+top.get(numTop[1])+","+top.get(numTop[2]));
            editor2.apply();

            tomo_view_top = (ViewPager2)findViewById(R.id.tomo_view_top);
            ViewAdapter_top_tomo tomo_top_adapter = new ViewAdapter_top_tomo(this, tomo_num_topPage,
                    top.get(numTop[0]),top.get(numTop[1]), top.get(numTop[2]), gender);
            tomo_view_top.setAdapter(tomo_top_adapter);
            tomo_indicator1.setViewPager(tomo_view_top);
            tomo_indicator1.createIndicators(tomo_num_topPage,0);
            tomo_view_top.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            tomo_view_top.setCurrentItem(0);
            tomo_view_top.setOffscreenPageLimit(2);

            RandomNumber(bottoms.size(), numBottoms);
            SharedPreferences sharedPreferences3 = getSharedPreferences("tomo_sp_bottoms", MODE_PRIVATE);
            SharedPreferences.Editor editor3 = sharedPreferences3.edit();
            editor3.putString("tomo_Bottoms", bottoms.get(numBottoms[0])+","+bottoms.get(numBottoms[1])+","+bottoms.get(numBottoms[2]));
            editor3.apply();

            tomo_view_bottom = (ViewPager2)findViewById(R.id.tomo_view_bottom);
            ViewAdapter_bottom_tomo tomo_bottom_adapter = new ViewAdapter_bottom_tomo(this,tomo_num_bottomPage,
                    bottoms.get(numBottoms[0]),bottoms.get(numBottoms[1]), bottoms.get(numBottoms[2]), gender);
            tomo_view_bottom.setAdapter(tomo_bottom_adapter);
            tomo_indicator2.setViewPager(tomo_view_bottom);
            tomo_indicator2.createIndicators(tomo_num_bottomPage,0);
            tomo_view_bottom.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            tomo_view_bottom.setCurrentItem(0);
            tomo_view_bottom.setOffscreenPageLimit(2);
        }
        else {
            SharedPreferences sharedPreferences1 = getSharedPreferences("tomo_sp_top", MODE_PRIVATE);
            String strTop = sharedPreferences1.getString("tomo_Top", "null");
            String[] sp_top = strTop.split(",");
            tomo_view_top = (ViewPager2) findViewById(R.id.tomo_view_top);
            ViewAdapter_top_tomo tomo_top_adapter = new ViewAdapter_top_tomo(this, tomo_num_topPage,
                    sp_top[0], sp_top[1], sp_top[2], gender);
            tomo_view_top.setAdapter(tomo_top_adapter);
            tomo_indicator1.setViewPager(tomo_view_top);
            tomo_indicator1.createIndicators(tomo_num_topPage, 0);
            tomo_view_top.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            tomo_view_top.setCurrentItem(0);
            tomo_view_top.setOffscreenPageLimit(2);

            SharedPreferences sharedPreferences2 = getSharedPreferences("tomo_sp_bottoms", MODE_PRIVATE);
            String strBottoms = sharedPreferences2.getString("tomo_Bottoms", "null");
            Log.v("strBottoms", strBottoms);
            String[] sp_bottoms = strBottoms.split(",");
            Log.v("tomo_sp_bottoms",sp_bottoms[0]+", "+ sp_bottoms[1]+", "+ sp_bottoms[2]);
            tomo_view_bottom = (ViewPager2) findViewById(R.id.tomo_view_bottom);
            ViewAdapter_bottom_tomo tomo_bottom_adapter = new ViewAdapter_bottom_tomo(this, tomo_num_bottomPage,
                    sp_bottoms[0], sp_bottoms[1], sp_bottoms[2], gender);
            tomo_view_bottom.setAdapter(tomo_bottom_adapter);
            tomo_indicator2.setViewPager(tomo_view_bottom);
            tomo_indicator2.createIndicators(tomo_num_bottomPage, 0);
            tomo_view_bottom.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            tomo_view_bottom.setCurrentItem(0);
            tomo_view_bottom.setOffscreenPageLimit(2);
        }

        tomo_view_top.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    tomo_view_top.setCurrentItem(position);
                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                tomo_indicator1.animatePageSelected(position%tomo_num_topPage);
            }
        });

        tomo_view_bottom.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    tomo_view_bottom.setCurrentItem(position);
                }
            }
            public void onPageSelected_top(int position){
                super.onPageSelected(position);
                tomo_indicator2.animatePageSelected(position%tomo_num_bottomPage);
            }
        });

        DownloadJSON downloadJSON_finedust = new DownloadJSON();
        try{
            String tomo_finedust = downloadJSON_finedust.execute(tomo_finedust_url).get();
            JSONObject jsonObject_tomo_finedust = new JSONObject(tomo_finedust);
            JSONArray jsonArray_tomo_finedust = jsonObject_tomo_finedust.getJSONArray("list");
            for(int i=136;i<=160;i++){
                JSONObject jsonObject = jsonArray_tomo_finedust.getJSONObject(i);
                String str = jsonObject.getString("components");
                JSONObject tmp = new JSONObject(str);
                tomo_pm10.add(tmp.getDouble("pm10"));
                tomo_pm2_5.add(tmp.getDouble("pm2_5"));
            }
            String pm10 = CalAve(tomo_pm10);
            String pm2_5 = CalAve(tomo_pm2_5);
            SetFineDustImage(Double.parseDouble(pm10),Double.parseDouble(pm2_5));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (28 <= temp)
            ret = "Temp9";
        else if (23 <= temp && temp < 28)
            ret = "Temp8";
        else if (17 <= temp && temp < 23)
            ret = "Temp7";
        else if (13.5 <= temp && temp < 12)
            ret = "Temp6";
        else if (8 <= temp && temp < 13.5)
            ret = "Temp5";
        else if (4 <= temp && temp < 8)
            ret = "Temp4";
        else if (-10 <= temp && temp < 4)
            ret = "Temp3";
        else if (-20 <= temp && temp < -10)
            ret = "Temp2";
        else if (-20 > temp)
            ret = "Temp1";
        return ret;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("SetTextI18n")
    private void WeatherSummary(String last_lat, String last_lon) {
        String week_url ="https://api.openweathermap.org/data/2.5/onecall?" + "lat=" + last_lat + "&lon=" + last_lon
                + "&exclude=hourly&appid=" + App_ID;
        int id=0;
        DownloadJSON downloadJSON = new DownloadJSON();
        try{
            String url = downloadJSON.execute(week_url).get();
            JSONObject jsonObject = new JSONObject(url);
            JSONArray jsonArray = jsonObject.getJSONArray("daily");
            for(int i=1;i<2;i++){
                JSONObject object = jsonArray.getJSONObject(i);
                JSONArray jsonArray1 = object.getJSONArray("weather");
                for(int j=0;j<1;j++){
                    JSONObject object1 = jsonArray1.getJSONObject(j);
                    id = object1.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.v("id : ", String.valueOf(id));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if(id == 800){
            tomo_page.setBackgroundResource(R.drawable.gradation);
            window.setStatusBarColor(getResources().getColor(R.color.day));
        }
        else {
            tomo_page.setBackgroundResource(R.drawable.gradation_cloudy);
            window.setStatusBarColor(getResources().getColor(R.color.cloudy));
        }
        tomo_summary.setText(WeatherImage(id));
    }

    private String WeatherImage(int id) {
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
                tomo_icon_summary.setImageResource(R.drawable.thunder);
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
                tomo_icon_summary.setImageResource(R.drawable.rainy);
                break;
            case 501:
            case 502:
                result = "비";
                tomo_icon_summary.setImageResource(R.drawable.rainy);
                break;
            case 503:
                result = "강한 비";
                tomo_icon_summary.setImageResource(R.drawable.rainy);
                break;
            case 504:
                result = "매우 강한 비";
                tomo_icon_summary.setImageResource(R.drawable.rainy);
                break;
            case 511:
            case 615:
            case 616:
                result = "눈비";
                tomo_icon_summary.setImageResource(R.drawable.snowrain);
                break;
            case 520:
            case 521:
            case 522:
            case 531:
                result = "소나기";
                tomo_icon_summary.setImageResource(R.drawable.rainy);
                break;
            case 600:
            case 612:
            case 620:
                result = "약한 눈";
                tomo_icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 601:
            case 611:
            case 613:
            case 621:
                result = "눈";
                tomo_icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 602:
            case 622:
                result = "강한 눈";
                tomo_icon_summary.setImageResource(R.drawable.snowy);
                break;
            case 701:
            case 711:
            case 721:
            case 741:
                result = "안개";
                tomo_icon_summary.setImageResource(R.drawable.haze);
                break;
            case 771:
                result = "바람";
                tomo_icon_summary.setImageResource(R.drawable.wind);
                break;
            case 781:
                result = "태풍";
                tomo_icon_summary.setImageResource(R.drawable.storm);
                break;
            case 731:
            case 751:
            case 761:
            case 762:
                result = "황사";
                tomo_icon_summary.setImageResource(R.drawable.sand);
                break;
            case 800:
                result = "맑음";
                tomo_icon_summary.setImageResource(R.drawable.sun);
                break;
            case 801:
                result = "구름 조금";
                tomo_icon_summary.setImageResource(R.drawable.cloudy);
                break;
            case 802:
            case 803:
                result = "흐림";
                tomo_icon_summary.setImageResource(R.drawable.cloud);
                break;
            case 804:
                result = "구름 많음";
                tomo_icon_summary.setImageResource(R.drawable.cloud);
                break;
        }
        return result;
    }

    private void ShowTimeLine_Description() {
        switch (timeLine_description.get(0)) {
            case "Thunderstorm":
                tomo_weather_image1.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image1.setImageResource(R.drawable.rainy_night);
                break;
            case "Snow":
                tomo_weather_image1.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image1.setImageResource(R.drawable.night);
                break;
            case "Clouds":
                tomo_weather_image1.setImageResource(R.drawable.cloudy_night);
                break;
        }
        switch (timeLine_description.get(1)) {
            case "Thunderstorm":
                tomo_weather_image2.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image2.setImageResource(R.drawable.rainy_night);
                break;
            case "Snow":
                tomo_weather_image2.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image2.setImageResource(R.drawable.night);
                break;
            case "Clouds":
                tomo_weather_image2.setImageResource(R.drawable.cloudy_night);
                break;
        }
        switch (timeLine_description.get(2)) {
            case "Thunderstorm":
                tomo_weather_image3.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image3.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                tomo_weather_image3.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image3.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                tomo_weather_image3.setImageResource(R.drawable.cloud);
                break;
        }
        switch (timeLine_description.get(3)) {
            case "Thunderstorm":
                tomo_weather_image4.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image4.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                tomo_weather_image4.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image4.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                tomo_weather_image4.setImageResource(R.drawable.cloud);
                break;
        }
        switch (timeLine_description.get(4)) {
            case "Thunderstorm":
                tomo_weather_image5.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image5.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                tomo_weather_image5.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image5.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                tomo_weather_image5.setImageResource(R.drawable.cloud);
                break;
        }
        switch (timeLine_description.get(5)) {
            case "Thunderstorm":
                tomo_weather_image6.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image6.setImageResource(R.drawable.rainy);
                break;
            case "Snow":
                tomo_weather_image6.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image6.setImageResource(R.drawable.sun);
                break;
            case "Clouds":
                tomo_weather_image6.setImageResource(R.drawable.cloud);
                break;
        }
        switch (timeLine_description.get(6)) {
            case "Thunderstorm":
                tomo_weather_image7.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image7.setImageResource(R.drawable.rainy_night);
                break;
            case "Snow":
                tomo_weather_image7.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image7.setImageResource(R.drawable.night);
                break;
            case "Clouds":
                tomo_weather_image7.setImageResource(R.drawable.cloudy_night);
                break;
        }
        switch (timeLine_description.get(7)) {
            case "Thunderstorm":
                tomo_weather_image8.setImageResource(R.drawable.storm);
                break;
            case "Drizzle":
            case "Rain":
                tomo_weather_image8.setImageResource(R.drawable.rainy_night);
                break;
            case "Snow":
                tomo_weather_image8.setImageResource(R.drawable.snowy);
                break;
            case "Clear":
                tomo_weather_image8.setImageResource(R.drawable.night);
                break;
            case "Clouds":
                tomo_weather_image8.setImageResource(R.drawable.cloudy_night);
                break;
        }
    }

    public static String getTomorrow(String pattern){
        DateFormat dtf = new SimpleDateFormat(pattern);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        return dtf.format(cal.getTime());
    }

    private void SetFineDustImage(double finedustpm10, double finedustpm2_5) {
        if(0<=finedustpm10&&finedustpm10<=30) {
            tomo_fine_dust.setImageResource(R.drawable.fine_dust1);
            dust1.setText("좋음"); }
        else if(31<=finedustpm10&&finedustpm10<=80){
            tomo_fine_dust.setImageResource(R.drawable.fine_dust2);
            dust1.setText("보통"); }
        else if(81<=finedustpm10&&finedustpm10<=150){
            tomo_fine_dust.setImageResource(R.drawable.fine_dust3);
            dust1.setText("나쁨"); }
        else {
            tomo_fine_dust.setImageResource(R.drawable.fine_dust4);
            dust1.setText("좋음"); }

        if(0<=finedustpm2_5&&finedustpm2_5<=15){
            tomo_superfine_dust.setImageResource(R.drawable.fine_dust1);
            dust2.setText("좋음");}
        else if(16<=finedustpm2_5&&finedustpm2_5<=35){
            tomo_superfine_dust.setImageResource(R.drawable.fine_dust2);
            dust2.setText("보통");}
        else if(36<=finedustpm2_5&&finedustpm2_5<=75){
            tomo_superfine_dust.setImageResource(R.drawable.fine_dust3);
            dust2.setText("나쁨");}
        else{
            tomo_superfine_dust.setImageResource(R.drawable.fine_dust4);
            dust2.setText("매우나쁨");}
    }

    @SuppressLint("DefaultLocale")
    private String CalAve(ArrayList<Double> list) {
        String ret = "";
        double sum = 0;
        for(int i=0;i<list.size();i++)
            sum+=list.get(i);
        sum = sum/list.size();
        ret = String.format("%.1f", sum);
        return ret;
    }

    private void ShowTimeLine_Humidity() {
        tomo_humidity1.setText(timeLine_humidity.get(0));
        tomo_humidity2.setText(timeLine_humidity.get(1));
        tomo_humidity3.setText(timeLine_humidity.get(2));
        tomo_humidity4.setText(timeLine_humidity.get(3));
        tomo_humidity5.setText(timeLine_humidity.get(4));
        tomo_humidity6.setText(timeLine_humidity.get(5));
        tomo_humidity7.setText(timeLine_humidity.get(6));
        tomo_humidity8.setText(timeLine_humidity.get(7));
        ShowDropImage();
    }

    private void ShowDropImage(){
        if(0<=Double.parseDouble(timeLine_humidity.get(0))&&Double.parseDouble(timeLine_humidity.get(0))<20)
            tomo_humidity_image1.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(0))&&Double.parseDouble(timeLine_humidity.get(0))<40)
            tomo_humidity_image1.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(0))&&Double.parseDouble(timeLine_humidity.get(0))<60)
            tomo_humidity_image1.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(0))&&Double.parseDouble(timeLine_humidity.get(0))<80)
            tomo_humidity_image1.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image1.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(1))&&Double.parseDouble(timeLine_humidity.get(1))<20)
            tomo_humidity_image2.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(1))&&Double.parseDouble(timeLine_humidity.get(1))<40)
            tomo_humidity_image2.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(1))&&Double.parseDouble(timeLine_humidity.get(1))<60)
            tomo_humidity_image2.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(1))&&Double.parseDouble(timeLine_humidity.get(1))<80)
            tomo_humidity_image2.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image2.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(2))&&Double.parseDouble(timeLine_humidity.get(2))<20)
            tomo_humidity_image3.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(2))&&Double.parseDouble(timeLine_humidity.get(2))<40)
            tomo_humidity_image3.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(2))&&Double.parseDouble(timeLine_humidity.get(2))<60)
            tomo_humidity_image3.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(2))&&Double.parseDouble(timeLine_humidity.get(2))<80)
            tomo_humidity_image3.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image3.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(3))&&Double.parseDouble(timeLine_humidity.get(3))<20)
            tomo_humidity_image4.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(3))&&Double.parseDouble(timeLine_humidity.get(3))<40)
            tomo_humidity_image4.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(3))&&Double.parseDouble(timeLine_humidity.get(3))<60)
            tomo_humidity_image4.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(3))&&Double.parseDouble(timeLine_humidity.get(3))<80)
            tomo_humidity_image4.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image4.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(4))&&Double.parseDouble(timeLine_humidity.get(4))<20)
            tomo_humidity_image5.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(4))&&Double.parseDouble(timeLine_humidity.get(4))<40)
            tomo_humidity_image5.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(4))&&Double.parseDouble(timeLine_humidity.get(4))<60)
            tomo_humidity_image5.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(4))&&Double.parseDouble(timeLine_humidity.get(4))<80)
            tomo_humidity_image5.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image5.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(5))&&Double.parseDouble(timeLine_humidity.get(5))<20)
            tomo_humidity_image6.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(5))&&Double.parseDouble(timeLine_humidity.get(5))<40)
            tomo_humidity_image6.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(5))&&Double.parseDouble(timeLine_humidity.get(5))<60)
            tomo_humidity_image6.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(5))&&Double.parseDouble(timeLine_humidity.get(5))<80)
            tomo_humidity_image6.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image6.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(6))&&Double.parseDouble(timeLine_humidity.get(6))<20)
            tomo_humidity_image7.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(6))&&Double.parseDouble(timeLine_humidity.get(6))<40)
            tomo_humidity_image7.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(6))&&Double.parseDouble(timeLine_humidity.get(6))<60)
            tomo_humidity_image7.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(6))&&Double.parseDouble(timeLine_humidity.get(6))<80)
            tomo_humidity_image7.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image7.setImageResource(R.drawable.drop4);

        if(0<=Double.parseDouble(timeLine_humidity.get(7))&&Double.parseDouble(timeLine_humidity.get(7))<20)
            tomo_humidity_image8.setImageResource(R.drawable.drop);
        else if(20<=Double.parseDouble(timeLine_humidity.get(7))&&Double.parseDouble(timeLine_humidity.get(7))<40)
            tomo_humidity_image8.setImageResource(R.drawable.drop1);
        else if(40<=Double.parseDouble(timeLine_humidity.get(7))&&Double.parseDouble(timeLine_humidity.get(7))<60)
            tomo_humidity_image8.setImageResource(R.drawable.drop2);
        else if(60<=Double.parseDouble(timeLine_humidity.get(7))&&Double.parseDouble(timeLine_humidity.get(7))<80)
            tomo_humidity_image8.setImageResource(R.drawable.drop3);
        else
            tomo_humidity_image8.setImageResource(R.drawable.drop4);
    }

    @SuppressLint("SetTextI18n")
    private void ShowTimeLine_Temp() {
        tomo_temp1.setText(""+timeLine_temp.get(0));
        tomo_temp2.setText(""+timeLine_temp.get(1));
        tomo_temp3.setText(""+timeLine_temp.get(2));
        tomo_temp4.setText(""+timeLine_temp.get(3));
        tomo_temp5.setText(""+timeLine_temp.get(4));
        tomo_temp6.setText(""+timeLine_temp.get(5));
        tomo_temp7.setText(""+timeLine_temp.get(6));
        tomo_temp8.setText(""+timeLine_temp.get(7));
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

    private void SplitTimeLine(String date) {
        String[] words = date.split(" ");
        String str = words[1];
        String[] words2 = str.split(":");
        if(words2[0].equals("00")) {
            timeflag = true;
        }
        if(timeflag)
            timeLine.add(words2[0]);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(TomorrowWeather.this, Page2.class);
        startActivity(intent);
    }

}