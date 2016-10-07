package com.jaemin.android.fragmentbasic_pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaemin on 2016. 9. 28..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<RecyclerData> items;
    int itemLayout;
    BlankFragment.OnListFragmentInteractionListener mListener;

    public RecyclerAdapter(ArrayList<RecyclerData> items, int itemLayout, BlankFragment.OnListFragmentInteractionListener mListener) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.mListener = mListener;
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
        final RecyclerData data = items.get(position);
        holder.title.setText(data.title);
        holder.itemView.setTag(data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    // 데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
