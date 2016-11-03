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
        //String key = "6d527467476a61653639544f446167";
        //String serviceName = "CardSubwayStatisticsService";

        String key = "309451bfb5c29d77bd719001fc1e3d71";
        String targetDt = "20161010";


        //int begin = 1; // 시작 위치
        //int end = 5;

        //Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        Call<BoxOfficeResult> remoteData = RestAdapter.getInstance().getData(key, targetDt);

        remoteData.enqueue(new Callback<BoxOfficeResult>() {
            @Override
            public void onResponse(Call<BoxOfficeResult> call, Response<BoxOfficeResult> response) {
                if (response.isSuccessful()) {
                    BoxOfficeResult data = response.body();

                    for(DailyBoxOfficeList movie : data.getDailyBoxOfficeList()) {
                        Log.w("OkHttp", "sub station name = "+movie.getMovieNm());
                        Log.w("OkHttp", "sub station name = "+movie.getRank());

                    }
                } else {
                    Log.e("ERROR", response.message());
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResult> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}
