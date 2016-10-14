package com.jaemin.android.customview_moverectwiththread;

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

    FrameLayout frameLayout;
    CustomView cv;
    Button animate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        cv = new CustomView(this);

        frameLayout.addView(cv);

        animate = (Button) findViewById(R.id.button_animate);
        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomThread thread = new CustomThread(cv); // 커스텀 스레드에 cv를 넘긴다
                thread.start();
            }
        });
    }
}

class CustomView extends View {

    Paint paint = new Paint(); // 그림을 그려줄 도구, 물감 !

    private int x = 0;
    private int y = 0;
    private final static int WIDTH = 100;
    private final static int HEIGHT = 100;


    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, x, y, paint);
    }

    public void moveRect(int offset) {
        x = x + offset;
        y = y + offset;
    }

}

class CustomThread extends Thread {
    CustomView cv;
    private static final int OFFSET = 2;

    public CustomThread(CustomView cv) {
        this.cv = cv;
    }

    @Override
    public void run() {
        int limit = 0;
        while (limit < 1000) {
            // cv에 그려지는 사각형의 좌표값을 조작한다
            cv.moveRect(OFFSET);
            cv.postInvalidate();
            limit++;

            try {
                Thread.sleep(100); // sleep()은 기본적으로 예외처리 해줘야 한다
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}