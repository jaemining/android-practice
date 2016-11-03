package com.jaemin.android.rxandroid_basic02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button btnJust;
    Button btnFrom;
    Button btnDefer;
    ListView listView;

    ArrayList<String> datas = new ArrayList<>();

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnJust = (Button) findViewById(R.id.button_just);
        btnFrom = (Button) findViewById(R.id.button_from);
        btnDefer = (Button) findViewById(R.id.button_defer);
        listView = (ListView) findViewById(R.id.listView);

        btnJust.setOnClickListener(this);
        btnFrom.setOnClickListener(this);
        btnDefer.setOnClickListener(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_just:
                doJust();
                break;
            case R.id.button_from:
                doFrom();
                break;
            case R.id.button_defer:
                doDefer();
                break;
        }
    }

    // Java 데이터를 바로 Observable 객체로 변환할 수 있다
    public void doJust() {
        Observable<String> observable = Observable.just("Result : dog");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        });
    }

    // 컬렉션 형태의 자바 객체로부터 observable을 생성한다
    public void doFrom() {
        Observable<String> observable = Observable.from(new String[] {"dog", "bird", "chicken", "horse", "turtle", "rabbit", "tiger", "puppy", "pig", "frog", "rabbit", "jandi", "office", "her"});
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                datas.add(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 지연처리 함수를 제공하고
    // 호출 할 때마다 observable 객체를 매번 생성한다
    public void doDefer() {
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("bird");
            }
        });

        observable
                .delaySubscription(1, TimeUnit.SECONDS)
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                Log.w("RxAndroid", "Completed");
            }
        });
    }
}
