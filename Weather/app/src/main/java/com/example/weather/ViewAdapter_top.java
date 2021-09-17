package com.example.weather;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewAdapter_top extends FragmentStateAdapter {

    public int mCount;
    String top1, top2, top3;
    int num1, num2, num3;
    String gender;

    public ViewAdapter_top(@NonNull FragmentActivity fragmentActivity, int count, String s1, String s2, String s3, String gender) {
        super(fragmentActivity);
        mCount = count;
        top1 = s1;
        top2 = s2;
        top3 = s3;
        this.gender = gender;
        num1 = GetImageNumber(top1);
        num2 = GetImageNumber(top2);
        num3 = GetImageNumber(top3);
    }

    private int GetImageNumber(String name) {
        int ret = 0;
        switch (name){
            case "반팔 티": return R.drawable.tshirt;
            case "반팔 셔츠": return R.drawable.half_shirts;
            case "PK 반팔티": return R.drawable.pk;
            case "긴팔 티": return R.drawable.long_sleeve;
            case "얇은 셔츠":
            case "셔츠": return R.drawable.shirt;
            case "PK 티": return R.drawable.polo;
            case "민소매":
                if(gender.equals("female"))
                    return R.drawable.women_nasi;
                return R.drawable.nasi;
            case "스웨트 셔츠":
            case "기모 맨투맨": return R.drawable.sweater;
            case "후드티":
            case "기모 후드티": return R.drawable.hood;
            case "린넨 자켓": return R.drawable.linen_jacket;
            case "후드 집업": return R.drawable.hood_zipup;
            case "트레이닝 재킷": return R.drawable.training_jacket;
            case "블레이저": return R.drawable.blazer;
            case "트러커 자켓": return R.drawable.trucker;
            case "데님 자켓": return R.drawable.denim_jacket;
            case "바람막이(아노락)": return R.drawable.anorak;
            case "트렌치 코트": return R.drawable.trench_coat;
            case "바버 자켓": return R.drawable.barber;
            case "카디건": return R.drawable.cardigan;
            case "니트": return R.drawable.neets;
            case "레더 자켓": return R.drawable.leather;
            case "MA-1": return R.drawable.ma;
            case "블루종": return R.drawable.bluezong;
            case "스타디움 자켓": return R.drawable.stadium;
            case "패딩 베스트": return R.drawable.padding_vest;
            case "숏 코트": return R.drawable.short_coat;
            case "롱 코트":
                if(gender.equals("female"))
                    return R.drawable.women_coat;
                return R.drawable.longcoat;
            case "숏 패딩": return R.drawable.padding;
            case "롱 패딩": return R.drawable.long_padding;
            case "무스탕": return R.drawable.mustang;
            case "데님 원피스": return R.drawable.denim_one;
            case "여름 원피스": return R.drawable.dress;
            case "블라우스": return R.drawable.blouse;
            case "원피스": return R.drawable.one_piece;
            case "크롭 티": return R.drawable.crop;
            case "트위드 자켓": return R.drawable.tweed;

            default: return ret;
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index == 0) return new FirstTop(top1, num1);
        else if(index == 1)return new SecondTop(top2, num2);
        else return new ThirdTop(top3, num3);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public int getRealPosition(int position){
        return position % mCount;
    }
}