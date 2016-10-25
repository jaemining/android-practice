package com.jaemin.android.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText id;
    EditText pwd;
    Button signIn;
    TextView result;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getApplicationContext().getSharedPreferences("cookie", getBaseContext().MODE_PRIVATE); // xml 파일이 생성됨
        editor = sp.edit();
        
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.editText_id);
        pwd = (EditText) findViewById(R.id.editText_pwd);
        signIn = (Button) findViewById(R.id.button_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        result = (TextView) findViewById(R.id.textView_result);

        String keyID = "USERID";
        String keyPW = "USERPWD";

        result.setText(keyID + " = " + sp.getString(keyID, "") + ";" + keyPW + " = " + sp.getString(keyPW, ""));
    }

    private void signIn() {
        Map userInfo = new HashMap();

        userInfo.put("user_id", id.getText().toString());
        userInfo.put("user_pwd", pwd.getText().toString());

        new AsyncTask<Map, Void, String>() {
            ProgressDialog progress;

            @Override
            protected String doInBackground(Map... params) {
                String result = "";
                String url = "http://192.168.0.153:8080/setCookies.jsp";
                try {
                    result = Remote.postData(url, params[0], "POST");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);

                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);

                progress.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //result.setText("Result : " + s);

                List<HttpCookie> cookies = Remote.cookieManager.getCookieStore().getCookies();
                StringBuffer cookieString = new StringBuffer();
                for (HttpCookie cookie : cookies) {
                    cookieString.append(cookie.getName() + " = " + cookie.getValue() + "\n");
                    editor.putString(cookie.getName(), cookie.getValue());
                    //삭제 - editor.remove("키");
                    //전체삭제 - editor.clear();
                }
                editor.commit(); // commit을 해줘야 xml에 써지게된다

                //result.setText("Cookie : " + cookieString.toString());

                progress.dismiss();
            }
        }.execute(userInfo);
    }
}
