package com.jaemin.android.remote_retrofitwithokhttp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jaemin on 2016. 11. 3..
 */

public interface MovieOpenData {
    //http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json
    // ?key=309451bfb5c29d77bd719001fc1e3d71&targetDt=20161010


    @GET("/{key}/{targetDt}/")
    Call<BoxOfficeResult> getData(@Path("key")String key, @Path("targetDt")String targetDt);

}
