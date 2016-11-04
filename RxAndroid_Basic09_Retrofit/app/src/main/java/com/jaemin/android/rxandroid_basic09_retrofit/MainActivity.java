package com.jaemin.android.rxandroid_basic09_retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "http://api.openweathermap.org";
    static final String API_KEY = "d3683a7a9271d1c396b67bdc97f32d5a";

    EditText etCityName;
    TextView tvResult;
    Button btnGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCityName = (EditText) findViewById(R.id.editText_cityName);
        tvResult = (TextView) findViewById(R.id.textView_result);

        btnGetWeather = (Button) findViewById(R.id.button_getWeather);
        btnGetWeather.setOnClickListener(e -> getWeather());
    }

    public void getWeather() {
        String cityName = etCityName.getText().toString();

        // 1. Retrofit client 생성
        Retrofit client = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        // 2. Rest API 서비스 생성
        iWeather service = client.create(iWeather.class);

        // 3. Data Observable 생성
        Observable<Data> weatherData = service.getData(cityName, API_KEY);

        // 4. subscribeOn 생성
        // 가장 많이 쓰는 패턴
        //  1) 데이터를 가져오는 대상 : newThread로 새로운 Thread에서 작업한다
        //  2) 화면에 세팅하는 Observer : mainThread에서 작업한다
        weatherData.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                  data -> {
                      String contents = "";
                      contents += "ID : " + data.getId();
                      contents += ", NAME : " + data.getName();
                      contents += ", BASE : " + data.getBase();
                      tvResult.setText(contents);

                  }
                );
    }
}
