package com.jaemin.android.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 28..
 */
public class CustomGridAdapter extends BaseAdapter {

    // 기본 속성값 설
    Context context;
    ArrayList data;
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
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
