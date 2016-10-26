package com.jaemin.android.remote_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    /*
        6d527467476a61653639544f446167

        http://openapi.seoul.go.kr:8088/6d527467476a61653639544f446167/json/CardSubwayStatisticsService/1/5/201306/
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String key = "6d527467476a61653639544f446167";
        String serviceName = "CardSubwayStatisticsService";

        int begin = 1; // 시작 위치
        int end = 5;

        String url = "http://openapi.seoul.go.kr:8088/"+key+"/json/"+serviceName+"/"+begin+"/"+end+"/201306/";


        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder().baseUrl("http://openapi.seoul.go.kr:8088").addConverterFactory(GsonConverterFactory.create()).build();

        // 2. Retrofit client에서 사용할 interface 지정
        ISeoulOpenData service = client.create(ISeoulOpenData.class);

        // 3. interface(서비스)를 통해서 데이터를 호출한다
        Call<RemoteData> remoteData = service.getData(key, serviceName, begin, end);

        // 4. 비동기 데이터를 받기위한 리스너 세팅
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {
                if(response.isSuccessful()){
                    RemoteData data = response.body();
                    Log.i("AAA", (data.getCardSubwayStatisticsService()==null)+"");
                    for(Row row : data.getCardSubwayStatisticsService().getRow()) {
                        Log.i("Remote Data Result","sub station name="+row.getSUB_STA_NM());
                    }
                }else{
                    Log.e("RemoteData",response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

interface ISeoulOpenData {
    @GET("/{key}/json/{serviceName}/{begin}/{end}/201306")
    Call<RemoteData> getData(@Path("key")String key, @Path("serviceName")String serviceName, @Path("begin")int begin, @Path("end")int end);
}

