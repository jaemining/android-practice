package com.jaemin.android.rxandroid_basic06_subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPublish, btnBehavior, btnReplay, btnAsync, btnAsyncComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPublish = (Button) findViewById(R.id.button_publish);
        btnBehavior = (Button) findViewById(R.id.button_behavior);
        btnReplay = (Button) findViewById(R.id.button_replay);
        btnAsync = (Button) findViewById(R.id.button_async);
        btnAsyncComplete = (Button) findViewById(R.id.button_asyncComplete);

        btnPublish.setOnClickListener(this);
        btnBehavior.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        btnAsync.setOnClickListener(this);
        btnAsyncComplete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_publish:
                publishSubject();
                break;
            case R.id.button_behavior:
                behaviorSubject();
                break;
            case R.id.button_replay:
                replaySubject();
                break;
            case R.id.button_async:
                asyncSubject();
                break;
            case R.id.button_asyncComplete:
                asyncCompleteSubject();
                break;
        }
    }

    public void publishSubject() {
        // 구독한 시점부터 받는다
        PublishSubject<String> subject = PublishSubject.create();

        // A, B, C, D, E 발행
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        // 구독
        subject.subscribe( item -> Log.e("Subject", "publish / item ==== " + item) );
        subject.onNext("D");
        subject.onNext("E");
    }

    public void behaviorSubject() {
        // 가장 최근데 관찰된 아이템부터 구독한다
        BehaviorSubject<String> subject = BehaviorSubject.create();

        // A, B, C, D, E 발행
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        // 구독
        subject.subscribe( item -> Log.e("Subject", "behavior / item ==== " + item) );
        subject.onNext("D");
        subject.onNext("E");
    }

    public void replaySubject() {
        // 버퍼에 담아두었다가 다 보여준다
        ReplaySubject<String> subject = ReplaySubject.create();

        // A, B, C, D, E 발행
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        // 구독
        subject.subscribe( item -> Log.e("Subject", "replay / item ==== " + item) );
        subject.onNext("D");
        subject.onNext("E");
    }

    public void asyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        // A, B, C, D, E 발행
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        // 구독
        subject.subscribe( item -> Log.e("Subject", "async / item ==== " + item) );
        subject.onNext("D");
        subject.onNext("E");
    }

    public void asyncCompleteSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();

        // A, B, C, D, E 발행
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        // 구독
        subject.subscribe( item -> Log.e("Subject", "async / item ==== " + item) );
        subject.onNext("D");
        subject.onNext("E");
        subject.onCompleted();
    }

}
