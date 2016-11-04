package com.jaemin.android.rxandroid_basic09_retrofit;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jaemin on 2016. 11. 4..
 */

public interface iWeather {
    // http://api.openweathermap.org
    // /data/2.5/weather?q=Seoul&APPID=d3683a7a9271d1c396b67bdc97f32d5a

    @GET("/data/2.5/weather")
    Observable<Data> getData(@Query("q") String cityName, @Query("APPID") String key);

}
