package com.jaemin.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        setContentView(new CustomView(this));
    }
}

class CustomView extends View {

    // 전역변수로 빼준다
    int x = -1;
    int y = -1;
    int rad = 100; // 지름

    Paint paint = new Paint(); // 그림을 그려줄 도구, 물감 !


    public CustomView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        paint.setColor(Color.YELLOW);

        // canvas.drawRect(0, 0, 300, 300, paint); // left, top, right, bottom, paint
        if(x >= 0) {
            canvas.drawCircle(x, y, rad, paint); // x좌표, y좌표, 지름, paint
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                x = (int) event.getX();
                y = (int) event.getY();
                // invalidate() - 갱신 사항이 있을 때 화면을 다시 그려줌
                invalidate(); // onDraw()가 호출된다
                break;
        }
        return true;
    }
}
