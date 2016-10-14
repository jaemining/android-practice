package com.jaemin.android.customview_rectai;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUND_LIMIT = 10;

    private int groundUnit = 0;
    private int unit = 0;
    private int player_x = 0; // 박스 좌표
    private int player_y = 0; // 박스 좌표

    private int enemy_x = 0;
    private int enemy_y = 0;
    private int enemy_size = 10;

    FrameLayout ground;
    Button up;
    Button down;
    Button right;
    Button left;

    Button start;

    CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        groundUnit = dp.widthPixels; // 핸드폰 화면의 넓이 사이즈
        unit = groundUnit / 10; //10x10으로 나누기 위해

        up = (Button) findViewById(R.id.button_up);
        down = (Button) findViewById(R.id.button_down);
        right = (Button) findViewById(R.id.button_right);
        left = (Button) findViewById(R.id.button_left);
        start = (Button) findViewById(R.id.button_start);

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        right.setOnClickListener(this);
        left.setOnClickListener(this);
        start.setOnClickListener(this);

        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_up: player_y = player_y -1; break;
            case R.id.button_down: player_y = player_y + 1; break;
            case R.id.button_right: player_x = player_x -1; break;
            case R.id.button_left: player_x = player_x +1; break;
            case R.id.button_start :
                new Enemy(cv).start();
                break;
        }
        cv.invalidate();
    }

    class Enemy extends Thread {
        int x = 0;
        int y = 0;
        int size = unit;

        CustomView cv;

        // 초기화 될 때 커스텀뷰를 받는다
        public Enemy(CustomView cv) {
            this.cv = cv;
            cv.add(this);
        }

        @Override
        public void run() {
            int distance_x;
            int distance_y;

            while (true) {
                // TODO 플레이어가 있는 곳으로 나의 좌표를 변경한다
                // 거리를 계산한다
                distance_x = player_x * unit - x;
                distance_y = player_y * unit - y;

                if (distance_x > 0) {
                   x = x + 1;
                } else if (distance_x < 0) {
                    x = x - 1;
                }
                if(distance_y > 0) {
                    y = y + 1;
                } else if (distance_y < 0) {
                    y = y - 1;
                }

                cv.postInvalidate();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class CustomView extends View {

        Paint paint = new Paint(); // 그림을 그려줄 도구, 물감 !
        // 생성될 적군을 담아줄 변수
        ArrayList<Enemy> enemies = new ArrayList<>();
        public void add(Enemy enemy) {
            enemies.add(enemy);
        }

        public CustomView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // ground 배경 그리기
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);


            // player 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(player_x*unit, player_y*unit, player_x*unit+unit, player_y*unit+unit, paint);

            // 적군 그리기
            for (Enemy enemy : enemies) {
                paint.setColor(Color.YELLOW);
                canvas.drawCircle(enemy.x, enemy.y, enemy.size, paint);
            }
        }

    }
}