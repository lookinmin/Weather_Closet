package com.example.weather;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SearchLocation extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleMap gMap;
    private Geocoder geocoder;
    private static final String TAG="info : ";
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    private ImageButton complete;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_location);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        complete = (ImageButton)findViewById(R.id.complete);
        location = (TextView)findViewById(R.id.location);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        Places.initialize(getApplicationContext(), "AIzaSyBrUjvCG24AwquXLc27s2yaTf5vA3utZm8");
        PlacesClient placesClient = Places.createClient(this);

        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteSupportFragment.setTypeFilter(TypeFilter.REGIONS);
        autocompleteSupportFragment.setCountries("KR");
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        SharedPreferences sp_address = getSharedPreferences("sp_address", MODE_PRIVATE);
        String last_address = sp_address.getString("address", "null");
        if(!last_address.equals("null"))
            location.setText(last_address);

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                mMap.clear();
                List<Address> list = null;
                String placename = place.getName();
                try{
                    list = geocoder.getFromLocationName(placename, 10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(list.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = list.get(0).toString().split(",");
                String address1 = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                String[] address2 = address1.split("대한민국");
                StringBuilder address = new StringBuilder();
                for(int i=1;i<address2.length;i++)
                    address.append(address2[i]);
                location.setText(address.toString());

                SharedPreferences sp_address = getSharedPreferences("sp_address", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp_address.edit();
                editor.putString("address", address.toString());
                editor.apply();

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도

                SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
                SharedPreferences.Editor editor_lat = sp_lat.edit();
                editor_lat.putString("last_lat", latitude);
                editor_lat.apply();

                SharedPreferences sp_lon = getSharedPreferences("longitude", MODE_PRIVATE);
                SharedPreferences.Editor editor_lon = sp_lon.edit();
                editor_lon.putString("last_lon", longitude);
                editor_lon.apply();

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                // 마커 생성
                @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.location);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                Bitmap marker = Bitmap.createScaledBitmap(bitmap, 45, 45, false);

                MarkerOptions mOptions2 = new MarkerOptions();
                mOptions2.title("검색 위치");
                mOptions2.snippet(address.toString());
                mOptions2.position(point);
                mOptions2.icon(BitmapDescriptorFactory.fromBitmap(marker));
                // 마커 추가
                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,12));
                Toast.makeText(getApplicationContext(), address+"이(가) 선택되었습니다", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(@NonNull Status status) {
                Log.i(TAG, "error occured : "+status);
            }
        });
    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.location);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap marker = Bitmap.createScaledBitmap(bitmap, 45, 45, false);

        mMap = googleMap;
        geocoder = new Geocoder(this);

        SharedPreferences sp_lat = getSharedPreferences("latitude", MODE_PRIVATE);
        double last_lat = Double.parseDouble(sp_lat.getString("last_lat", "37.56"));

        SharedPreferences sp_lon = getSharedPreferences("longitude", MODE_PRIVATE);
        double last_lon = Double.parseDouble(sp_lon.getString("last_lon", "126.97"));

        LatLng lastLocation = new LatLng(last_lat, last_lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(lastLocation);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(marker));
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 12));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                googleMap.clear();
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("검색 위치");
                double latitude = point.latitude; // 위도
                double longitude = point.longitude; // 경도

                SharedPreferences.Editor edit_lat = sp_lat.edit();
                edit_lat.putString("last_lat", Double.toString(latitude));
                edit_lat.apply();

                SharedPreferences.Editor edit_lon = sp_lon.edit();
                edit_lon.putString("last_lon", Double.toString(longitude));
                edit_lon.apply();
                // 마커의 스니펫(간단한 텍스트) 설정
                String address = getCurrentAddress(latitude, longitude);
                address = SplitAddress(address);
                if (address.equals("null")) {
                    Toast.makeText(getApplicationContext(), "위치를 다시 설정해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    mOptions.snippet(address);
                    mOptions.position(new LatLng(latitude, longitude));
                    mOptions.icon(BitmapDescriptorFactory.fromBitmap(marker));
                    googleMap.addMarker(mOptions);
                    String searchAddress = address;
                    location.setText(searchAddress);
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            Toast.makeText(getApplicationContext(), searchAddress + "이(가) 선택되었습니다", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp_address = getSharedPreferences("sp_address", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp_address.edit();
                            editor.putString("address", searchAddress);
                            editor.apply();
                            return false;
                        }
                    });
                }
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "위치정보가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchLocation.this, Page2.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SearchLocation.this, Page2.class);
        startActivity(intent);
    }
    private String SplitAddress (String address){
        if (address.equals("")) {
            return "null";
        }
        String[] words = address.split(" ");
        String ret = words[1] + " " + words[2] + " " + words[3];
        return ret;
    }

    private String getCurrentAddress ( double latitude, double longitude){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            Toast.makeText(this, "NETWORK 상태 불량", Toast.LENGTH_SHORT).show();
            return "지오코더 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS좌표", Toast.LENGTH_SHORT).show();
            return "GPS 좌표 오류";
        }

        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_SHORT).show();
            return "주소 미발견";
        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString() + "\n";
    }

}