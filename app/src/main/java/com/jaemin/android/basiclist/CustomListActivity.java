package com.jaemin.android.basiclist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> data = new ArrayList<>();
        for(int i=0; i<30; i++) {
            data.add("data: " + i);
        }
        adapter = new CustomAdapter(this, data);
        listView.setAdapter(adapter);

    }
}


class CustomAdapter extends BaseAdapter {

    Context context; // context
    ArrayList data; // data 배열
    LayoutInflater inflater; // xml 파일을 instance화 해서 메모리에 올려준다

    public CustomAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        // 시스템에서 xml을 개체화 시켜주는 인플레이터를 가져온다
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { // 자식뷰들의 개수를 리턴해준다
        int size = data.size();
        return size;
    }

    @Override
    public Object getItem(int position) { // 자식뷰를 리턴해준다, position : 자식뷰의 순서, 1부터 시작한다
        return data.get(position);
    }

    @Override
    public long getItemId(int position) { // 자식뷰의 아이디값을 넘겨준다

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("GETVIEW", "---------------position" + position);
        Log.i("VIEWGROUP", ">>>>>>>>>>>>> parent" + parent.getId());

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_item, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(data.get(position).toString());

        return convertView;
    }
}