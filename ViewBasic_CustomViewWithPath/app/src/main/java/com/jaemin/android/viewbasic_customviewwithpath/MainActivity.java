package com.jaemin.android.viewbasic_customviewwithpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new CustomView(this));

        FrameLayout ground = (FrameLayout) findViewById(R.id.ground);

        cv = new CustomView(this);

        ground.addView(cv);

        Button clear = (Button) findViewById(R.id.button_clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.reset();
            }
        });
    }
}

class CustomView extends View {

    Paint paint = new Paint(); // 그림을 그려줄 도구, 물감 !
    Path path = new Path();


    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    public void reset() {
        path = new Path();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y); // 시작점을 표시
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y); // 이동경로상에 라인을 그려준다
                break;
        }
        invalidate();

        return true;
    }

}