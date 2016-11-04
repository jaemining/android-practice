package com.jaemin.android.rxandroid_basic07_rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxView
                .clicks(findViewById(R.id.button_bind))
                .map(event -> new Random().nextInt())
                .subscribe(
                        rand -> ((TextView) findViewById(R.id.textView)).setText("value = " + rand)
                );

        Observable<String> leftObs =
                RxView
                .clicks(findViewById(R.id.button_left))
                .map(event -> "left");

        Observable<String> rightObs =
                RxView
                .clicks(findViewById(R.id.button_right))
                .map(event -> "right");

        Observable.merge(leftObs, rightObs)
                .subscribe(
                  text -> Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                );

        // text change event
        RxTextView.textChangeEvents((TextView)findViewById(R.id.editText_inputWord))
                .subscribe(
                        word -> Log.i("RxBinding", "search / word : " + word.text().toString())
                );
    }
}
