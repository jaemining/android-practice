package com.jaemin.android.rxandroid_basic03_async;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private  static final String TAG = "RxAndroid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doDeferAsync();
    }

    public void doDeferAsync() {

        Log.i(TAG, Thread.currentThread().getName() + " ---- in MAIN");

        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Log.i(TAG, Thread.currentThread().getName() + " ---- in Func0");

                return Observable.just("I'm here ~~~");
            }
        });

        observable
                .subscribeOn(Schedulers.computation()) // computation thread에서 defer의 Func가 실행되고 subscribeOn에서 지정한 thread에서 defer의 Func에 넘겨준 함수가 실행된다
                .observeOn(Schedulers.newThread()) // 구독이 새로운 thread에서 subscriber로 이벤트가 전달되고 Subscriber가 실행되는 thread를 지정한다
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, Thread.currentThread().getName() + " ---- in completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, Thread.currentThread().getName() + " ---- in subscribe / value ==== " + s);

            }
        });

        observable
                .subscribeOn(Schedulers.computation()) // 발행자(Observable) thread를 지정한다
                .observeOn(Schedulers.newThread()) // 구독자(Observer, subscriber) thread를 지정한다
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, Thread.currentThread().getName() + " ---- in completed2");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.i(TAG, Thread.currentThread().getName() + " ---- in subscribe2 / value ==== " + s);

                    }
                });
    }
}
