package com.jaemin.android.rxandroid_basic08_rxbindinglogincheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignIn = (Button) findViewById(R.id.button_signIn);
        btnSignIn.setEnabled(false);

        Observable<TextViewTextChangeEvent> idObs =
                RxTextView.textChangeEvents((EditText)findViewById(R.id.editText_id));
        Observable<TextViewTextChangeEvent> pwdObs =
                RxTextView.textChangeEvents((EditText)findViewById(R.id.editText_password));

        Observable.combineLatest
                (
                idObs,
                pwdObs,
                (idChanges, pwdChanges) -> {
                    boolean idCheck = idChanges.text().length() >= 5;
                    boolean pwdCheck = pwdChanges.text().length() >= 8;
                    return idCheck && pwdCheck;
                })
        .subscribe(
                checkFlag -> btnSignIn.setEnabled(checkFlag)
        );
    }
}
