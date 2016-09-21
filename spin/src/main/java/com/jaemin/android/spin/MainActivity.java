package com.jaemin.android.spin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    Button btnSpin;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (RelativeLayout) findViewById(R.id.Layout);
        btnSpin = (Button) findViewById(R.id.btnSpin);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }
    boolean SPREADED = false;
    int r = 0;
    int count = 0;
    public void spread(View v) {
        while(true) {
            if(!SPREADED) {

                move(button1, button1.getWidth()/2*-1, button1.getHeight()/2*-1);
                move(button2, button2.getWidth()/2, button2.getHeight()/2*-1);
                move(button3, button3.getWidth()/2*-1, button3.getHeight()/2);
                move(button4, button4.getWidth()/2, button4.getHeight()/2);

                rotate(layout, 3600, 10000);

                btnSpin.setText("Combine");
                count++;
                SPREADED = true;
            } else {
                combine();
                SPREADED = false;
            }
            Log.i("debugging",count+"");
            if(count == 50)
                break;
        }

    }

    public void combine() {
        move(button1, 0, 0);
        move(button2, 0, 0);
        move(button3, 0, 0);
        move(button4, 0, 0);

        rotate(layout,3600,10000);

    }

    public void move(View btn, int x, int y) {
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(btn, "translationX", x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(btn, "translationY", y);
        AnimatorSet aniSet = new AnimatorSet();
        aniSet.setStartDelay(5000);
        aniSet.setDuration(1000);
        aniSet.playTogether(ani1, ani2);
        aniSet.start();
    }

    public void rotate(View v, int angle, int duration) {
        ObjectAnimator ani = ObjectAnimator.ofFloat(v, "rotation", angle);
        ani.setStartDelay(5000);
        ani.setDuration(duration);
        ani.start();
    }
}
