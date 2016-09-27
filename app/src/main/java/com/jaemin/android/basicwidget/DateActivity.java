package com.jaemin.android.basicwidget;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class DateActivity extends AppCompatActivity {

    Chronometer timer;
    Button start;
    Button stop;
    Button pause;

    boolean pauseFlag = false;// pause의 상태를 체크하는 flag
    long timeWhenStopped = 0;
    long resumeTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        timer = (Chronometer) findViewById(R.id.chronometer);
        start = (Button) findViewById(R.id.btnStart);
        stop = (Button) findViewById(R.id.btnStop);
        pause = (Button) findViewById(R.id.btnPause);

        start.setOnClickListener(onClickListener);
        stop.setOnClickListener(onClickListener);
        pause.setOnClickListener(onClickListener);


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btnStart:
                    timer.setBase(SystemClock.elapsedRealtime());
                    timer.start();
                    break;

                case R.id.btnStop:
                    timer.stop();
                    break;

                case R.id.btnPause:
                    // TODO 일시정지 구현
                    if(pauseFlag == false ) {
                        timeWhenStopped = SystemClock.elapsedRealtime();
                        pauseFlag = true;
                        timer.stop();
                    } else {
                        resumeTime = SystemClock.elapsedRealtime() - timeWhenStopped;
                        timer.setBase(timer.getBase() + resumeTime);
                        pauseFlag = false;
                        timer.start();
                    }

                    break;
            }
        }
    };
}
