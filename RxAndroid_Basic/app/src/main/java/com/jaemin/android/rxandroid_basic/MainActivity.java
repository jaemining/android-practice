package com.jaemin.android.rxandroid_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Observable을 생성
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxAndroid ~!");
                subscriber.onNext("My name is Jaemin");
                subscriber.onCompleted();
            }
        });

        // 2. Observable을 통해 데이터를 가져온다
        observable.subscribe(new Subscriber<String>() { // call()이 호출된다
            @Override
            public void onCompleted() {
                Log.i("RxAndroid", "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("RxAndroid", "next value === " + s);
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ((TextView) findViewById(R.id.textView)).setText(s);
            }
        }, new Action1<Throwable>() { // onError에 해당
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() { // onCompleted에 해당
            @Override
            public void call() {

            }
        });
    }
}
