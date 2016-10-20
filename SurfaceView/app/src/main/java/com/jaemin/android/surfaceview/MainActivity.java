package com.jaemin.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    int deviceWidth = 0;
    int deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        deviceHeight = metrics.heightPixels;
        deviceWidth = metrics.widthPixels;

        CustomSurfaceView surfaceView = new CustomSurfaceView(this);
        setContentView(surfaceView);
    }

    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceThread thread;

        public CustomSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this); // 변경사항이 있을 때 나를 다시 호출하겠다는 코드
            thread = new SurfaceThread(getHolder());
            //thread.setDaemon();
            //setFocusable();// 뷰에 클릭하는 이벤트 만들어줌

        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        // 뷰가 화면에 보여지는 시점
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            thread.running = true;
            thread.start();
        }

        // 뷰에 변경사항 생김 (사이즈 변경 등 .. )
        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        // 화면에서 뷰가 보이지 않는 시점 (생명주기 끝)
        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            //thread.interrupt();
            boolean retry = true;
            thread.running = false;

            while (retry) {
                try {
                    thread.join();
                    retry = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    // 무한 반복하면서 위에서 정의한 surface 뷰에 그림을 그려주는 역할을 한다
    public class SurfaceThread extends Thread {

        public boolean running = true;

        private SurfaceHolder surfaceHolder;
        Paint paint = new Paint();

        int x;
        int y;

        // 생성자 생성
        public SurfaceThread(SurfaceHolder holder) {
            // surface 뷰에서 넘겨준 홀더를 가지고 작업을 한다
            surfaceHolder = holder;
            paint.setColor(Color.MAGENTA);
        }

        @Override
        public void run() {
            Canvas canvas = null;

            try {
                // 무한반복하면서 그림을 그려준다
                while (running) {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        paint.setColor(Color.YELLOW);
                        canvas.drawRect(0, 0, deviceWidth, deviceHeight, paint);

                        paint.setColor(Color.MAGENTA);
                        canvas.drawRect(x, y, x+50, y+50, paint);
                    }
                    x++;
                    y++;

                    if (x > deviceWidth) {
                        x = 0;
                        y = 0;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if(canvas != null) {
                    // 여기서 실제 디스플레이에 그려주게 된다
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

        }
    }
}
