package com.jaemin.android.firebase_database01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    int position = -1;
    Branch branch;

    ImageView imageView;
    TextView tvStoreName;
    TextView tvBranchName;
    TextView tvFee;
    // TextView tvMenu;
    RecyclerView detailRecyclerView;
    RecyclerCardAdapter<MENU> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        branch = MainActivity.branches.get(position);

        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this).load(branch.getLOGO()).into(imageView);

        tvStoreName = (TextView) findViewById(R.id.textView_storeName);
        tvStoreName.setText(branch.getNAME());

        tvBranchName = (TextView) findViewById(R.id.textView_branchName);
        tvBranchName.setText(branch.getBRANCH());

        tvFee = (TextView) findViewById(R.id.textView_fee);
        tvFee.setText(branch.getDELIVERY_FEE()+"");

//        tvMenu = (TextView) findViewById(R.id.textView_menu);
//        tvMenu.setText(branch.getMENU()+"");

        detailRecyclerView = (RecyclerView) findViewById(R.id.detail_recyclerView);
        adapter = new RecyclerCardAdapter<>(branch.getMENU(), R.layout.main_list_item, this);
        detailRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        detailRecyclerView.setLayoutManager(manager);



    }
}
