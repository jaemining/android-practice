package com.jaemin.android.remote_rest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Remote {

    private static final String TAG = "Remote";

    public static String getData(String webUrl) throws IOException{

        StringBuffer result = new StringBuffer();

        URL url = new URL(webUrl);

        // 아래 한 줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // http와 통신하기 위한 방법
        // REST API = GET(조회), POST(입력), DELETE, PUT(수정) / 보안문제로 DELETE와 PUT을 지원하지 않는 서버가 있다
        connection.setRequestMethod("GET"); // 이 부분이 바뀌면 요청하는 rest 부분이 바뀌는 것

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

//    public static String postData(String webUrl, Map params) throws IOException {
//        String result = "";
//
//        URL url = new URL(webUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//
//        // ----- post 처리 일 경우만 -----
//        connection.setDoOutput(true);
//        OutputStream os = connection.getOutputStream();
//        ArrayList<String> keyset = new ArrayList<>(params.keySet());
//        for (String key : keyset) {
//            String param = key + " = " + params.get(key);
//            os.write(param.getBytes());
//        }
//        os.flush();
//        os.close();
//        // ----------------------------
//
//        int responseCode = connection.getResponseCode(); // 이 요청에 정상적인 응답을 넘겨주겠다라는 코드값을 responseCode에 담아 넘겨줄것
//
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//
//            // 데이터를 주고 받을 때 inputStream으로 받아 BufferedReader로 빠르게 처리하겠다는 코드
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//            String dataLine = null;
//            // readLine 으로 한 줄 씩 가져온다
//            while ((dataLine = br.readLine()) != null) {
//                result.append(dataLine);
//            }
//            br.close();
//        } else {
//            Log.e(TAG, " HTTP error code = " + responseCode);
//        }
//
//        return result;
//    }
}
