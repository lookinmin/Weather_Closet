package com.example.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewAdapter_bottom_tomo extends FragmentStateAdapter {

    public int mCount;
    String bottoms1, bottoms2, bottoms3;
    int num1, num2, num3;
    String gender;

    public ViewAdapter_bottom_tomo(@NonNull FragmentActivity fragmentActivity, int count,
                                   String s, String s1, String s2, String gender) {
        super(fragmentActivity);
        mCount = count;
        bottoms1 = s;
        bottoms2 = s1;
        bottoms3 = s2;
        this.gender = gender;
        num1 = GetImageNumber(bottoms1);
        num2 = GetImageNumber(bottoms2);
        num3 = GetImageNumber(bottoms3);
    }

    private int GetImageNumber(String name) {
        int ret = 0;
        switch (name){
            case "트레이닝 쇼츠":
                if(gender.equals("female"))
                    return R.drawable.women_training;
                else
                    return R.drawable.training_short;
            case "카고 쇼츠": return R.drawable.cargo;
            case "데님 쇼츠":
                if(gender.equals("female"))
                    return R.drawable.denim_shorts;
                else
                    return R.drawable.jeans;
            case "코튼 쇼츠":
                if(gender.equals("female"))
                    return R.drawable.shortw;
                else
                    return R.drawable.shorts;
            case "린넨 팬츠": return R.drawable.linen;
            case "기모 팬츠":
            case "코튼 팬츠": return R.drawable.cotton_long;
            case "플리츠": return R.drawable.plitz;
            case "얇은 슬랙스":
            case "슬랙스": return R.drawable.slax;
            case "트레이닝 팬츠":
                return R.drawable.training_long;
            case "데님 팬츠": return R.drawable.jean;
            case "크림 진": return R.drawable.jeanw;
            case "블랙 진": return R.drawable.jeanb;
            case "조거 팬츠": return R.drawable.zoger;
            case "데님 스커트": return R.drawable.denim_skirt;
            case "테니스 스커트": return R.drawable.tennis;
            case "A라인 스커트": return R.drawable.a_skirt;
            case "H라인 스커트": return R.drawable.h_skirt;
            case "롱 스커트": return R.drawable.long_skirt;
            case "레깅스": return R.drawable.leggings;
        }
        return ret;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        if(index == 0) return new FirstBottom_tomo(bottoms1, num1);
        else if(index == 1)return new SecondBottom_tomo(bottoms2, num2);
        else return new ThirdBottom_tomo(bottoms3, num3);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public int getRealPosition(int position){
        return position % mCount;
    }
}