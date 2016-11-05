package com.jaemin.android.rxandroid_basic05_filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxAndroid";

    TextView textView;
    Button filter, foreach, take, first, last, distinct, groupBy;
    TextView textViewDataSet;

    Integer dataset[] = {5, 6, 2, 7, 9, 1, 3, 2, 9, 6, 3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        filter = (Button) findViewById(R.id.button_filter);
        foreach = (Button) findViewById(R.id.button_foreach);
        take = (Button) findViewById(R.id.button_take);
        first = (Button) findViewById(R.id.button_first);
        last = (Button) findViewById(R.id.button_last);
        distinct = (Button) findViewById(R.id.button_distinct);
        groupBy = (Button) findViewById(R.id.button_groupBy);
        textViewDataSet = (TextView) findViewById(R.id.textView_dataSet);

        filter.setOnClickListener(this);
        foreach.setOnClickListener(this);
        take.setOnClickListener(this);
        first.setOnClickListener(this);
        last.setOnClickListener(this);
        distinct.setOnClickListener(this);
        groupBy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_filter:
                filter();
                break;
            case R.id.button_foreach:
                foreach();
                break;
            case R.id.button_take:
                take(7);
                break;
            case R.id.button_first:
                first();
                break;
            case R.id.button_last:
                last();
                break;
            case R.id.button_distinct:
                distinct();
                break;
            case R.id.button_groupBy:
                groupBy();
                break;
        }
    }

    public void filter() {
        Observable.from(dataset)
                .filter(item -> item%2 == 0 )
                .subscribe(result -> Log.i(TAG, result+"")
                );
    }

    public void foreach() {
        Observable.from(dataset)
                .forEach(result -> Log.i(TAG, result+"")
                );
    }

    public void take(int takeCount) {
        Observable.from(dataset)
                .take(takeCount)
                .subscribe(result -> Log.i(TAG, result+"")
                );
    }

    public void first() {
        Observable.from(dataset)
                .first()     //첫번째 값 리턴
                .subscribe(result -> Log.i(TAG, result+"")
                );
    }

    public void last() {
        Observable.from(dataset)
                .last()     //마지막 값 리턴
                .subscribe(result -> Log.i(TAG, result+"")
                );
    }

    public void distinct() {
        Observable.from(dataset)
                .distinct()     //유일한 값만 리턴
                .subscribe(result -> Log.i(TAG, result+"")
                );
    }

    public void groupBy() {
        Observable.from(dataset)
                .groupBy(item -> item%2 == 0)     //첫번째 값 리턴
                .subscribe(grouped -> grouped.toList().subscribe(result -> Log.i(TAG, result+""+" Even:"+grouped.getKey()))
                );
    }

}
