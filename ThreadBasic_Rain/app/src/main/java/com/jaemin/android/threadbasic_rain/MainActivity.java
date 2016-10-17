package com.jaemin.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static boolean running = true;
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;

    FrameLayout ground;
    CustomView cv;
    Button start;
    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels; // 화면 가로 픽셀 개수
        deviceHeight = metrics.heightPixels; //  화면 세로 픽셀 개수

        ground = (FrameLayout) findViewById(R.id.frameLayout);
        cv = new CustomView(this);
        ground.addView(cv);
        start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
                new MakeDrop(cv).start();
            }
        });
        stop = (Button) findViewById(R.id.button_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });
    }

    // 빗망울을 만들고 화면을 그려주는 thread를 동작시키는 thread
    class MakeDrop extends Thread {
        CustomView cv;

        public MakeDrop(CustomView cv) {
            this.cv = cv;
        }

        public void run() {
            // 화면을 그리는 thread 생성 후 동작
            CallDraw cd = new CallDraw(cv, 10);
            new Thread(cd).start();

            while (running) {
                // 빗말울 thread 생성 후 동작
                RainDrop rainDrop = new RainDrop(cv);

                new Thread(rainDrop).start();

                // 0.5초에 한 번씩 비를 생성한다
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class CustomView extends View {
        ArrayList<RainDrop> rainDrops = new ArrayList<>();
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.BLUE);
        }

        public void add(RainDrop rd) {
            rainDrops.add(rd);
        }

        public void remove(RainDrop rd) {
            rainDrops.remove(rd);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            try {
                for (RainDrop rainDrop : rainDrops) {
                    // 하나씩 꺼내서 그려준다
                    canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.size, paint);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 빗방울 1개
    class RainDrop implements Runnable{
        int x; // 좌표값
        int y; // 좌표값
        int size; //  크기
        int speed; // 속도
        int size_limit;
        int speed_limit;

        CustomView cv;

        public RainDrop(CustomView cv) {
            this.cv = cv;
            cv.add(this); // RainDrop 안에 cv가 들어옴

            Random random = new Random();

            x = random.nextInt(deviceWidth);
            y = 0; // 고정
            size_limit = deviceWidth / 20; // 빗방울의 최대 크기 - 화면 사이즈의 1/20
            size = random.nextInt(size_limit);
            speed_limit = deviceHeight / 100; // 한번에 이동 가능한 최대 거리
            speed = random.nextInt(speed_limit)+1;
        }
        @Override
        public void run() {
            while (y <= deviceHeight) { // 화면 밖으로 벗어나면 while문을 빠져 나가며 thread 종료

                if(running) {
                    y = y + speed;
                }

                try {
                    Thread.sleep(10); // y축으로 이동 후 그려지는 간격을 조절한다. 0.1초에 한번 이동
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cv.remove(this); // 메모리에 너무 많이 쌓이면 다운되기때문에 remove 해줘야 한다
        }
    }

    // 화면을 그려주는 thread
    class CallDraw implements Runnable {
        CustomView cv;
        int interval;

        public CallDraw(CustomView cv, int interval) {
            this.cv = cv;
            this.interval = interval;
        }


        @Override
        public void run() {
            // interval 값 만큼 sleep한 후 화면을 반복해서 그려준다
            while (running) {
                cv.postInvalidate();

                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
