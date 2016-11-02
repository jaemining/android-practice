package com.jaemin.android.firebase_database01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Jaemin on 2016. 9. 29..
 */
public class RecyclerCardAdapter<T> extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {

    List<T> datas;
    int itemLayout;
    Context context;

    public RecyclerCardAdapter(List<T> datas, int itemLayout, Context context) {
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

        T data = datas.get(position);

        if (data instanceof Branch) {
            Glide.with(context).load(((Branch) data).getLOGO()).into(holder.image);
            holder.title.setText(((Branch) data).getBRANCH());
            holder.price.setText(((Branch) data).getDELIVERY_FEE()+"");

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });

        } else if (data instanceof MENU) {
            Glide.with(context).load(((MENU) data).getMENU_IMAGE()).into(holder.image);
            holder.title.setText(((MENU) data).getMENU_NAME());
            holder.price.setText(((MENU) data).getMENU_PRICE()+"");
        }
        //holder.itemView.setTag(data);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        TextView price;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
        }
    }
}
