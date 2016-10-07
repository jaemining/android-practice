package com.jaemin.android.fragmentbasic_pager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private TextView title;
    private TextView content;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        title = (TextView) view.findViewById(R.id.textView_title);
        content = (TextView) view.findViewById(R.id.textView_content);

        return view;
    }

    public void setItem(RecyclerData item){
        title.setText(item.getTitle());
        content.setText(item.getContent());
    }
}
