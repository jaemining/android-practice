package com.jaemin.android.fragmentbasic_pager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    RecyclerView recyclerView;
    private OnListFragmentInteractionListener mListener;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        ArrayList<RecyclerData> items = new ArrayList<>();
        for (int i=0; i<100; i++) {
            RecyclerData data = new RecyclerData();
            data.setTitle((i+1) + " title");
            data.setContent((i+1) + " content");
            items.add(data);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(items, R.layout.recycler_item, mListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(RecyclerData item);
    }
}
