package com.jaemin.android.butterknife;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindBitmap;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.textView_result)
    TextView tvResult;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindString(R.string.butterknife)
    String value;

    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_click)
    public void onClickBind() {
        tvResult.setText("클릭했음");
    }

    @OnClick(R.id.button_string)
    public void setString() {
        tvResult.setText(value);
    }

    @OnClick(R.id.button_image)
    public void setImage() {
        imageView.setImageBitmap(image);
    }
}
