package com.jaemin.android.customview_pushpush;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUND_LIMIT = 10;

    private int groundUnit = 0;
    private int unit = 0;
    private int player_x = 0; // 박스 좌표
    private int player_y = 0; // 박스 좌표

    FrameLayout ground;
    Button up;
    Button down;
    Button right;
    Button left;

    CustomView cv;

    int map[][] = {
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
        {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 1, 1, 1, 0, 0, 0}
    };

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

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        right.setOnClickListener(this);
        left.setOnClickListener(this);

        ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_up: player_y = player_y + checkCollision("y", -1); break;
            case R.id.button_down: player_y = player_y + checkCollision("y", +1); break;
            case R.id.button_right: player_x = player_x + checkCollision("x", -1); break;
            case R.id.button_left: player_x = player_x + checkCollision("x", +1); break;
        }
        cv.invalidate();
    }

    private int checkCollision(String direction, int nextValue) {

        // 외곽선 체크
        if (direction.equals("y")) {
            // y축에서 다음 이동하는 곳의 좌표가 0보다 작거나 ,
            // GROUND_LIMIT 즉 canvas 보다 크면 0을 리턴해서 이동하지 않게 한다
            if ((player_y + nextValue) < 0 || (player_y + nextValue) >= GROUND_LIMIT) {
                return 0;
            }
        } else {
            if ((player_x + nextValue) < 0 || (player_x + nextValue) >= GROUND_LIMIT) {
                return 0;
            }
        }

        // 장애물 체크
        if (direction.equals("y")) {
            // 내가 진행 할 방향의 다음칸
            int temp_y = player_y + nextValue;
            // 내가 진행할 y축 방향의 다음칸이 1이면
            if(map[temp_y][player_x] == 1) {
                // 다음 다음 칸에 장애물이 있거나 범위를 넘어서면 0
                if(temp_y + nextValue < 0 || temp_y + nextValue >= GROUND_LIMIT || map[temp_y+nextValue][player_x] != 0) {
                    return 0;
                // 다음 다음칸이 빈칸이면 다음칸의 장애물을 다음 다음칸으로 이동시킨다
                } else {
                    map[temp_y][player_x] = 0;
                    map[temp_y+nextValue][player_x] = 1;
                }
            }

        } else {
            int temp_x = player_x + nextValue;
            if(map[player_y][temp_x] == 1) {
                if(temp_x + nextValue < 0 || temp_x + nextValue >=  GROUND_LIMIT || map[player_y][temp_x+nextValue] != 0) {
                    return 0;
                } else {
                    map[player_y][temp_x] = 0;
                    map[player_y][temp_x+nextValue] = 1;
                }
            }
        }

        return nextValue;
    }

    class CustomView extends View {

        Paint paint = new Paint(); // 그림을 그려줄 도구, 물감 !

        public CustomView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // ground 배경 그리기
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);

            // map에 세팅된 장애물 그리기
            paint.setColor(Color.YELLOW);
            for (int i=0; i<GROUND_LIMIT; i++) {
                for (int j=0; j<GROUND_LIMIT; j++) {
                    if(map[i][j] == 1) {
                        canvas.drawRect(j*unit, i*unit, j*unit+unit, i*unit+unit, paint);
                    }
                }
            }

            // player 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(player_x*unit, player_y*unit, player_x*unit+unit, player_y*unit+unit, paint);
        }

    }
}
