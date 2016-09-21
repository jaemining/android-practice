package com.jaemin.android.materialdesign_propertyanimation01;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    public void show(View v) {
        Toast.makeText(this, "I am Here ~", Toast.LENGTH_SHORT).show();
    }

}
