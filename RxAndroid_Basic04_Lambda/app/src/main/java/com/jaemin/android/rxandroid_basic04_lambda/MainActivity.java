package com.jaemin.android.rxandroid_basic04_lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxAndroid";

    TextView textView;
    Button btnLambda;
    Button btnMap;
    Button btnFlatmap;
    Button btnZip;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnLambda = (Button) findViewById(R.id.button_lambda);
        btnMap = (Button) findViewById(R.id.button_map);
        btnFlatmap = (Button) findViewById(R.id.button_flatmap);
        btnZip = (Button) findViewById(R.id.button_zip);
        listView = (ListView) findViewById(R.id.listView);

        btnLambda.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnFlatmap.setOnClickListener(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_lambda:
                doLambda();
                break;
            case R.id.button_map:
                doMap();
                break;
            case R.id.button_flatmap:
                doFlatMap();
                break;
            case R.id.button_zip:
                break;
        }
    }

    public void doLambda() {
        Observable<String> observable = Observable.just("I am lambda");
        observable.subscribe(
                item -> textView.setText(item),
                error -> error.printStackTrace(),
                () -> Log.i(TAG, "Completed")
        );
    }

    public void doMap() {
        Observable
                .from(new String[] {"dog", "bird", "chicken", "horse", "turtle", "rabbit", "tiger", "puppy", "pig", "frog", "rabbit", "jandi", "office", "her"})
                .map(item -> "[" + item + "]")
                .subscribe(
                        item -> datas.add(item),
                        e -> e.printStackTrace(),
                        () -> adapter.notifyDataSetChanged()
                );

    }

    public void doFlatMap() {
        Observable
                .from(new String[] {"dog", "bird", "chicken", "horse", "turtle", "rabbit", "tiger", "puppy", "pig", "frog", "rabbit", "jandi", "office", "her"})
                .flatMap(item -> Observable.from(new String[]{"name : " + item, item.getBytes()+""}))
                .subscribe(
                        item -> datas.add(item),
                        e -> e.printStackTrace(),
                        () -> adapter.notifyDataSetChanged()
                );
    }

    public void doZip() {
        Observable.zip(
                Observable.just("Jaemin"),
                Observable.just("Image.jpg"),
                (item1, item2) -> "Name : " + item1 + ", Profile image : " + item2
        ).subscribe(
                zipped -> Log.w("zip", "onNext item = " + zipped)
        );
    }
}
