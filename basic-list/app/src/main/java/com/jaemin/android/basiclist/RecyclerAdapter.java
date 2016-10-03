package com.jaemin.android.basiclist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 28..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;

    public RecyclerAdapter(ArrayList<RecyclerData> datas, int itemLayout) {
        this.datas = datas;
        this.itemLayout = itemLayout;
    }

    // ViewHolder : 뷰를 만들어서 홀더에 저장하는 역할 <- 뷰캐시?!
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    // 일반 ListAdapter의 getView를 대체하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);
        holder.image.setBackgroundResource(data.image);
        holder.title.setText(data.title);
        holder.name.setText(data.name);
        holder.itemView.setTag(data);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
