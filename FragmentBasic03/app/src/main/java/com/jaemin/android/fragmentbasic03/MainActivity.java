package com.jaemin.android.fragmentbasic03;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment fragmentOne;
    Fragment fragmentTwo;
    ListView listView;
    CustomAdapter adapter;
    public static ArrayList<ListData> datas = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        datas = new ArrayList<>();

        for (int i=0; i<50; i++) {
            ListData dummyContent = new ListData();
            dummyContent.title = (i+1) + ") Dummy title";
            dummyContent.content = "Dummy Content";
            datas.add(dummyContent);
        }

        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                
            }
        });

        adapter = new CustomAdapter(this, datas, R.layout.fragment_item);
        listView.setAdapter(adapter);
    }
}

class CustomAdapter extends BaseAdapter {

    Context context; // context
    ArrayList<ListData> datas; // data 배열
    LayoutInflater inflater; // xml 파일을 instance화 해서 메모리에 올려준다
    int item;

    public CustomAdapter(Context context, ArrayList data, int item) {
        this.context = context;
        this.datas = data;
        this.item = item;
        // 시스템에서 xml을 개체화 시켜주는 인플레이터를 가져온다
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() { // 자식뷰들의 개수를 리턴해준다
        int size = datas.size();
        return size;
    }

    @Override
    public Object getItem(int position) { // 자식뷰를 리턴해준다, position : 자식뷰의 순서, 1부터 시작한다

        return datas.get(position);
    }

    @Override
    public long getItemId(int position) { // 자식뷰의 아이디값을 넘겨준다

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(item, null);
        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.editText_title);
        TextView tv2 = (TextView) convertView.findViewById(R.id.editText_content);

        tv1.setText(datas.get(position).title);
        tv2.setText(datas.get(position).content);


        return convertView;
    }
}