package com.jaemin.android.materialdesign_propertyanimation01;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton player;
    int x = 0;
    int y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (ImageButton) findViewById(R.id.player);
    }

    public void up(View v) {
        y = y - 50;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
        ani.start();
    }

    public void down(View v) {
        y = y + 50;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
        ani.start();
    }

    public void left(View v) {
        x = x - 50;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
        ani.start();
    }

    public void right(View v) {
        x = x + 50;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
        ani.start();
    }

    public void show(View v) {
        Toast.makeText(this, "I am Here ~", Toast.LENGTH_SHORT).show();
    }

}
