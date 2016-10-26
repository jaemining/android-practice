package com.jaemin.android.remote_retrofitwithokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callHttp();
    }

    public void callHttp() {
        String key = "6d527467476a61653639544f446167";
        String serviceName = "CardSubwayStatisticsService";

        int begin = 1; // 시작 위치
        int end = 5;

        Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if (response.isSuccessful()) {
                    RemoteData data = response.body();

                    for(Row row : data.getCardSubwayStatisticsService().getRow()) {
                        Log.w("OkHttp", "sub station name = "+row.getSUB_STA_NM());
                    }
                } else {
                    Log.e("ERROR", response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
