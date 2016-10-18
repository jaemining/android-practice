package com.jaemin.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SubThread thread;
    LooperHandler handlerThread;
    Handler subHandler;

    TextView tv;
    Button btnStart;
    Button btnCall;
    Button btnStartHandler;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new SubThread(handler);
        handlerThread = new LooperHandler(this, "Looper Handler");
        subHandler = handlerThread.looperHandler; // 서브 스레드에  handler를 등록한다

        tv = (TextView) findViewById(R.id.textView);
        btnStart = (Button) findViewById(R.id.button_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.start();
            }
        });

        btnStartHandler = (Button) findViewById(R.id.button_startHandlerThread);
        btnStartHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerThread.start();
                //handlerThread.looperHandler.sendEmptyMessage(LooperHandler.SHOW_PROGRESS);
                run();
                //handlerThread.looperHandler.sendEmptyMessage(LooperHandler.HIDE_PROGRESS);
                handlerThread.hideProgress();

            }
        });

        btnCall = (Button) findViewById(R.id.button_call);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.printLog();
            }
        });
    }

    public void run() {
        try {
            int sum = 10;
            for (int i=0; i<20; i++) {
                sum += i;
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int SET_RESULT = 1;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case SET_RESULT:
                    int temp = msg.arg1;
                    tv.setText("Result = " + temp);
                    break;
            }
        }
    };

    // 루퍼가 구현되어 있는 스레드
    class LooperHandler extends HandlerThread {
        public static final int SHOW_PROGRESS = 1;
        public static final int HIDE_PROGRESS = 2;

        Handler looperHandler;
        Context context;

        public LooperHandler(Context context, String name) {
            super(name);
            this.context = context;
        }

        @Override
        protected void onLooperPrepared() {
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("Message");
            progress.setCancelable(false);

//            super.onLooperPrepared();
//            looperHandler = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//
//                    switch (msg.what) {
//                        case SHOW_PROGRESS:
//                            showProgress();
//                            break;
//                        case HIDE_PROGRESS:
//                            hideProgress();
//                            break;
//                    }
//                }
//            };

            progress.show();
            Log.i("Looper Handler", "진행 상태 확인");
        }

//        public void showProgress() {
//            progress.show();
//            Log.i("Looper Handler", "진행 상태 확인");
//        }

        public void hideProgress() {
            progress.dismiss();
            Log.i("Looper Handler", "진행 상태 해제");
            quit();// 루퍼를 빠져나가는 명령어
        }
    }

    class SubThread extends Thread {

        Handler mainHandler;

        public  SubThread(Handler mHandler) {
            mainHandler = mHandler;

        }
        public void run() {
            int sum = 0;

            for(int i=0; i<5000; i++) {
                sum += i;
            }

            Message msg = new Message();
            msg.what = SET_RESULT;
            msg.arg1 = sum;
            mainHandler.sendMessage(msg);
        }

        public void printLog() {
            Log.i("subThread", "called from the mainThread");
        }
    }


}
