package com.jaemin.android.firebase_database01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mainRecyclerView;

    FirebaseDatabase database;
    DatabaseReference chickenRef;

    public static ArrayList<Branch> branches = new ArrayList<>();
    RecyclerCardAdapter<Branch> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        database = FirebaseDatabase.getInstance();
        chickenRef = database.getReference("CHICKENSTORE");

        adapter = new RecyclerCardAdapter<>(branches, R.layout.main_list_item, this);
        mainRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(manager);


        chickenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // branches = new ArrayList<>();
                branches.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Branch branch = snapshot.getValue(Branch.class);
                    branches.add(branch);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
