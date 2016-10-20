package com.jaemin.android.threadbasic_tetris;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout relativeLayout;
    FrameLayout ground;
    Button rotation;
    Button left;
    Button down;
    Button right;

    Stage stage;

    int deviceWidth = 0;
    int deviceHeight = 0;

    int blockPixelUnit = 0;
    private static final int WIDTH_MAX_COUNT = 23;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Stage.REFRESH:
                    // 화면 갱신을 요청한다
                    stage.invalidate();
                    break;
                case Stage.NEW_BLOCK:
                    stage.setBlock();
                    stage.invalidate();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        ground = (FrameLayout) findViewById(R.id.frameLayout_stage);

        rotation = (Button) findViewById(R.id.button_rotation);
        rotation.setOnClickListener(this);
        left = (Button) findViewById(R.id.button_left);
        left.setOnClickListener(this);
        down = (Button) findViewById(R.id.button_down);
        down.setOnClickListener(this);
        right = (Button) findViewById(R.id.button_right);
        right.setOnClickListener(this);

        DisplayMetrics  metrics = getResources().getDisplayMetrics();
        deviceWidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        blockPixelUnit = deviceWidth / WIDTH_MAX_COUNT;

        Stage.running = true;
        stage = new Stage(this, handler, blockPixelUnit);
        ground.addView(stage);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_rotation:
                stage.rotateBlock();
                break;
            case R.id.button_left:
                stage.leftBlock();
                break;
            case R.id.button_right:
                stage.rightBlock();
                break;
            case R.id.button_down:
                stage.downBlock();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Stage.running = false;
    }
}
