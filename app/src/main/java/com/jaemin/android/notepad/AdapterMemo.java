package com.jaemin.android.notepad;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class AdapterMemo extends RecyclerView.Adapter<AdapterMemo.ViewHolder> {
    ArrayList<MemoData> datas;
    int itemLayout;
    public AdapterMemo(ArrayList<MemoData> datas, int itemLayout) {
        this.datas = datas;
        this.itemLayout = itemLayout;
    }

    @Override
    public AdapterMemo.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMemo.ViewHolder holder, int position) {
        MemoData data = datas.get(position);
        holder.content.setText(data.memo);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);

            content = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
