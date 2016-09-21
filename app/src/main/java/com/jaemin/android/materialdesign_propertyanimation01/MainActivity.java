package com.jaemin.android.materialdesign_propertyanimation01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

/*
    ObjectAnimator 사용법
    1. 애니매이터를 정의한다
    ObjectAnimator ani = ObjectAnimator.ofFloat(대상개체, "개체의 속성", 속성값(숫자));

    2. 정의된 애니매이터를 실행한다
    ani.start()
 */

public class MainActivity extends AppCompatActivity {

    RelativeLayout ground;
    ImageButton player;
    int x = 0;
    int y = 0;

    int gx = 0;
    int gy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ground = (RelativeLayout) findViewById(R.id.ground);
        player = (ImageButton) findViewById(R.id.player);
    }

    private void setGroundSize() {
        int playerHeight = player.getHeight();
        int playerWidth = player.getWidth();
        gx = ground.getWidth() - playerWidth;
        gy = ground.getHeight() - playerHeight;
        Log.i("MainActivity onResume()", "gx = " + gx + ", gy = " + gy);// 태그와 로그내용을 써준다.
    }

    public void up(View v) {
        setGroundSize();
        y = y - 50;
        if(-(gy/2) <= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        } else {
            y = y + 50;
        }
    }

    public void down(View v) {
        setGroundSize();
        y = y + 50;
        if((gy/2) >= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.start();
        } else {
            y = y - 50;
        }

    }

    public void left(View v) {
        setGroundSize();
        x = x - 50;
        if(-(gx/2) <= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        } else {
            x = x + 50;
        }

    }

    public void right(View v) {
        setGroundSize();
        x = x + 50;
        if(gx/2 >= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.start();
        } else {
            x = x - 50;
        }

    }

    int r = 0;
    public void rotate(View v) {
        r = r + 90;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "rotation", r);
        ani.start();
    }

    float scale_x = 1;
    float scale_y = 1;
    public void smaller(View v) {
        scale_x = scale_x/2;
        scale_y = scale_y/2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y);

        // 여러개의 애니매이션 동시에 사용하기
        // 1. AnimatorSet을 초기화 한다
        AnimatorSet aniSet = new AnimatorSet();
        // 2. playTogether에 애니매이션을 담아준다
        aniSet.playTogether(ani1, ani2);
        // 3. 애니매이션을 실행한다 
        aniSet.start();
    }

    public void show(View v) {
        Toast.makeText(this, "I am Here ~", Toast.LENGTH_SHORT).show();
    }

}
