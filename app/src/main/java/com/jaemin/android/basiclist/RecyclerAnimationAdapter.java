package com.jaemin.android.basiclist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 29..
 */
public class RecyclerAnimationAdapter extends RecyclerView.Adapter<RecyclerAnimationAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;

    public RecyclerAnimationAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context; // 공통적인 context를 쓸 수 있게 됨
    }

    @Override
    public RecyclerAnimationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAnimationAdapter.ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);
        holder.image.setBackgroundResource(data.image);

        // 이미지에 리스너 달기
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "image clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.title.setText(data.title);
        holder.name.setText(data.name);
        holder.itemView.setTag(data);

        setAnimation(holder.image, position);

    }

    int lastPosition = -1;// 이미 나온 이미지들은 애니매이션을 또 다시 적용하지 않는다 
    public void setAnimation(View view, int position) {
        if(position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            view.startAnimation(animation);
            lastPosition = position;
        }
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
