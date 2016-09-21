package com.jaemin.android.materialdesign_propertyanimation02;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSpread;
    Button button1;
    Button button2;
    Button button3;
    Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }

    public void spread(View v) {
        move(button1, button1.getWidth()/2*-1, button1.getHeight()/2*-1);
        move(button2, button2.getWidth()/2, button2.getHeight()/2*-1);
        move(button3, button3.getWidth()/2*-1, button3.getHeight()/2);
        move(button4, button4.getWidth()/2, button4.getHeight()/2);

    }
    public void move(View btn, int x, int y) {
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(btn, "translationX", x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(btn, "translationY", y);
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(ani1, ani2);
        aniSet.start();
    }
}
