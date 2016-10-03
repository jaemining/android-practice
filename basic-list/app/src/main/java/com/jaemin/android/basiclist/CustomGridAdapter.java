package com.jaemin.android.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 28..
 */
public class CustomGridAdapter extends BaseAdapter {

    // 기본 속성값 설
    Context context;
    ArrayList<GridItem> data;
    int gridItem;// 레이아웃 아이템
    LayoutInflater inflater;// 인플레이터

    public CustomGridAdapter(Context context, ArrayList data, int gridItem) {
        this.context = context;
        this.data = data;
        this.gridItem = gridItem;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. 뷰를 생성 / 있으면 그냥 사용
        if(convertView == null) {
            convertView = inflater.inflate(gridItem, null);
        }

        TextView tv1 = (TextView) convertView.findViewById(R.id.gridtext1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.gridtext2);

        tv1.setText(data.get(position).title);
        tv2.setText(data.get(position).num+"");


        return convertView;
    }
}
