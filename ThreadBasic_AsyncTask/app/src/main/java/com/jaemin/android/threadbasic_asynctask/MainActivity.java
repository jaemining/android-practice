package com.jaemin.android.threadbasic_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView percent;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setProgress(0); // 시작을 0으로 세팅
        percent = (TextView) findViewById(R.id.textView);
        btnDownload = (Button) findViewById(R.id.button_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AsyncTask 동작함 ...
                new DownloadTask().execute(100); // execute로는 doOnBackground만 호출된다
            }
        });
    }

    class DownloadTask extends AsyncTask<Integer, Integer, String> {
                            // AsyncTask 첫 번째 인자 : doInBackground의 parameter type
                            // AsyncTask 두 번쨰 인자 : onProgressUpdate의 parameter type
                            // AsyncTask 세 번째 인자 : onPostExecute의 parameter type


        @Override
        protected void onPreExecute() { // 제일 먼저 호출되는 함수
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            try {
                for (int i=0; i<=max; i++) {
                    publishProgress(i); // 이 함수를 통해 onProgressUpdate를 호출한다
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);

            progressBar.setProgress(values[0]);
            percent.setText(values[0] + "%");
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(aVoid);
            Log.i("Download Task", "msg = "+result);
        }
    }

}
