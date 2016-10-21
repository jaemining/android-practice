package com.jaemin.android.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNaver();
            }
        });

    }

    private void getNaver() {
        new AsyncTask<Void, Void, String>() {
            ProgressDialog progress;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);

                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);

                progress.show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                String result = "";
                try {
                    result = Remote.getData("http://openapi.seoul.go.kr:8088/6d527467476a61653639544f446167/json/CardSubwayStatisticsService/1/5/201306/");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                StringBuffer sb = new StringBuffer();

                try {
                    // 전체 문자열을 JSON 오브젝트로 변환
                    JSONObject json = new JSONObject(s); // s를 문자열로 바꿔준다
                    // 특정 키를 가진 단일값을 꺼낸다
                    JSONObject topObject = json.getJSONObject("CardSubwayStatisticsService");
                    // 특정 키를 가진 배열 형태의 값을 꺼낸다
                    JSONArray rows = topObject.getJSONArray("row");

                    int rowsCount = rows.length();
                    for (int i=0; i<rowsCount; i++) {
                        // 배열 반복문을 돌면서 배열의 index로 값을 꺼낸다
                        JSONObject result = (JSONObject) rows.get(i);
                        // 최종적으로 각 열의 컬럼의 키 이름에 해당하는 값을 꺼낸다 
                        String name = result.getString("SUB_STA_NM");
                        sb.append(name + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                tv.setText(sb.toString()); // doInBackground에서 넘어오는 값을 s로 받는다
                progress.dismiss();
            }
        }.execute();


    }
}
