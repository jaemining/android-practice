package com.jaemin.android.firebase_message;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Jaemin on 2016. 10. 31..
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken); // 토큰을 기준으로 폰에 메세지를 날린다


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on     the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(refreshedToken);
    }

}
