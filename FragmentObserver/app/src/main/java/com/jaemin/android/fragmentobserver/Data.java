package com.jaemin.android.fragmentobserver;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 10. 7..
 */

public class Data {

    ArrayList<Observer> observers = new ArrayList<>();
    ArrayList<MusicData> datas = new ArrayList<>();

    int position = -1;

    public void attach(Observer o) {
        observers.add(o);
    }

    public static MusicData newData() {
        return new MusicData();
    }

    public interface Observer {
        public void update();
    }

    public int getCount() {
        return datas.size();
    }

}

class MusicData {

}