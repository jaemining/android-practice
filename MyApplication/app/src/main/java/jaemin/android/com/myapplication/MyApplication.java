package jaemin.android.com.myapplication;

import android.app.Application;

/**
 * Created by Jaemin on 2016. 11. 21..
 */

public class MyApplication extends Application {

    private static boolean firstService = false;

    public static void setServiceStatus(boolean flag) {
        firstService = flag;
    }

    public static boolean isFirstService() {
        return firstService;
    }
}
