package com.jaemin.android.remote_httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jaemin on 2016. 10. 21..
 */

public class Remote {

    private static final String TAG = "Remote";

    public static String getData(String webUrl) throws IOException{

        StringBuffer result = new StringBuffer();

        URL url = new URL(webUrl);

        // 아래 한 줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // http와 통신하기 위한 방법
        // REST API = GET(조회), POST(입력), DELETE, PUT(수정) / 보안문제로 DELETE와 PUT을 지원하지 않는 서버가 있다
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode(); // 이 요청에 정상적인 응답을 넘겨주겠다라는 코드값을 responseCode에 담아 넘겨줄것

        if (responseCode == HttpURLConnection.HTTP_OK) {

            // 데이터를 주고 받을 때 inputStream으로 받아 BufferedReader로 빠르게 처리하겠다는 코드
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String dataLine = null;
            // readLine 으로 한 줄 씩 가져온다
            while ((dataLine = br.readLine()) != null) {
                result.append(dataLine);
            }
            br.close();
        } else {
            Log.e(TAG, " HTTP error code = " + responseCode);
        }

        return result.toString();
    }
}
