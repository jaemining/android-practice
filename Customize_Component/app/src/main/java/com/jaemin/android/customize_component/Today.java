package com.jaemin.android.customize_component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jaemin on 2016. 10. 20..
 */

public class Today extends TextView {

    public String delimiter;

    public Today(Context context) {
        super(context);
    }

    public Today(Context context, AttributeSet attrs) {
        super(context, attrs);
        // res/values/attrs.xml 에 정의된 어트리뷰트를 가져온다
        // 이름이 <declare-styleable name="Today"> 이렇게 정의된
        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.Today);
        // 해당 이름으로 정의된 attr의 개수를 가져오고
        int size = typed.getIndexCount();

        // 반복문을 돌면서 해당 attr에 입력된 값을 처리해 준다
        for (int i=0; i<size; i++) {
            int attr = typed.getIndex(i);

            switch (attr) {
                case R.styleable.Today_delimiter:
                    delimiter = typed.getString(attr);//변수에 정의된 구분자를 담아준다
                    setDate();
                    break;
                default:
                    break;
            }
        }

    }

    private void setDate() {
        /*
        setText()
         */

        // 오늘 날짜 가져오기
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+delimiter+"MM"+delimiter+"dd");
        setText(sdf.format(today));
    }

    public Today(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
