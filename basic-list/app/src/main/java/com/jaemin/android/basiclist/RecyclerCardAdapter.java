package com.jaemin.android.basiclist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;
    Intent intent;

    public RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public RecyclerCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerCardAdapter.ViewHolder holder, final int position) {

        RecyclerData data = datas.get(position);
        holder.image.setImageResource(data.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "image clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.title.setText(data.title);
        //holder.name.setText(data.name);
        holder.itemView.setTag(data);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, DetailActivity.class);

                intent.putExtra("position", position);

                context.startActivity(intent);

            }
        });

        setAnimation(holder.image, position);
    }

    int lastPosition = -1;
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            cardView = (CardView) itemView.findViewById(R.id.cardItem);
        }
    }
}
