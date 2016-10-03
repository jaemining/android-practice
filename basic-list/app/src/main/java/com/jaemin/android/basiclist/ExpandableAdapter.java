package com.jaemin.android.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 28..
 */
public class ExpandableAdapter extends BaseExpandableListAdapter{

    Context context;
    int groupLayout; // 부모셀의 item 레이아웃
    int childLayout; // 자식셀의 item 레이아웃
    ArrayList<ExpandData> datas;
    LayoutInflater inflater;

    // 생성자
    public ExpandableAdapter(Context context, int groupLayout, int childLayout, ArrayList datas) {
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        this.datas = datas;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return datas.get(i).guNames.size();
    }

    @Override
    public Object getGroup(int i) {
        return datas.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(groupPosition).guNames.get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(groupLayout, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(datas.get(groupPosition).cityName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(childLayout, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.text1);
        tv.setText(datas.get(groupPosition).guNames.get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
