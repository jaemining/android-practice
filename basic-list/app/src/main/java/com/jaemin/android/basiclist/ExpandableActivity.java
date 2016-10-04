package com.jaemin.android.basiclist;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.expandableListView);

        // adapter에 넘겨줄 data 정의
        ArrayList<ExpandData> data = new ArrayList<>();

        ExpandData data2 = new ExpandData();
        data2.cityName = "서울";
        data2.guNames.add("강동");
        data2.guNames.add("강서");
        data2.guNames.add("동작");
        data2.guNames.add("관악");
        data2.guNames.add("서초");

        data.add(data2);

        data2 = new ExpandData();
        data2.cityName = "광주";
        data2.guNames.add("광산");
        data2.guNames.add("중구");
        data2.guNames.add("북구");
        data.add(data2);

        data2 = new ExpandData();
        data2.cityName = "부산";
        data2.guNames.add("동래");
        data2.guNames.add("서면");
        data2.guNames.add("북구");
        data.add(data2);

        ExpandableAdapter ea = new ExpandableAdapter(this, R.layout.expand_parent_item, R.layout.expand_child_item, data);

        DisplayMetrics metrics = new DisplayMetrics(); // 화면에 실제 들어가는 픽셀 사이즈를 가져올 때 많이 사용
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels; // 화면의 실제 가로 사이즈

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
            listView.setIndicatorBounds(width - getPixelFromDisplay(50), width - getPixelFromDisplay(10));// width -50  부터 width 까지
        else
            listView.setIndicatorBoundsRelative(width-getPixelFromDisplay(50), width - getPixelFromDisplay(10));

        listView.setAdapter(ea);

        // dp를 px로 변환할 때
        int convertedPixel = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics()
        ); // 200 : dp 값 입력하는 부분

    }

    public int getPixelFromDisplay(float pixels) {
        // 화면 밀도 스케일
        final float scale = getResources().getDisplayMetrics().density;
        // 컨버팅 dps > pixel - 화면 밀도 스케일을 기준으로
        return (int)(pixels * scale + 0.5f);
    }

    public int pxToDp(Context context, int px) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics(); // 실제 사이즈를 측정하기위해 metrics 이용
        int dp = Math.round(px / (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (metrics.xdpi / metrics.DENSITY_DEFAULT));
        return px;
    }
}
