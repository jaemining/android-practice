package com.jaemin.android.broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    // .MESSAGE라는 구분자를 설정해준다.
    static final String BROADCAST_ACTION = "com.jaemin.android.broadcastreciever01.MESSAGE";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // 해당 BROADCAST_ACTION을 get 한다
        if(intent.getAction().equals(BROADCAST_ACTION)) {
            //Activity 화면을 띄워준다.
            Intent i = new Intent(context, MapsActivity.class);
            //activity 순서가 밀리는 경우가 발생할수도 있다
            // addFlags <- activity가 실행될 때, 상태를 결정한다
            i.addFlags(
                    i.FLAG_ACTIVITY_NEW_TASK
                    | i.FLAG_ACTIVITY_CLEAR_TOP
            );
            context.startActivity(i);
        }
    }
}
