package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.sql.Array;
import java.util.ArrayList;

public class UsersInfo extends AppCompatActivity {
ImageButton goBack;
RecyclerView recyclerView;
UserRecyclerViewAdapter adapter;
DataBaseHelperUser db;
ArrayList<String> ids = new ArrayList<>();
ArrayList<String> firstNames = new ArrayList<>();
ArrayList<String> lastNames = new ArrayList<>();
ArrayList<String> positions = new ArrayList<>();
ArrayList<String> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);
        goBack = findViewById(R.id.logout);
        recyclerView = findViewById(R.id.users);
        db = new DataBaseHelperUser(this);
        getUsers();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getUsers() {
        Cursor res = db.getALlData();

        while(res.moveToNext()) {
            ids.add(res.getString(0) + "");
            users.add(res.getString(1));
            firstNames.add(res.getString(3));
            lastNames.add(res.getString(4));
            positions.add(res.getString(5));
        }
        adapter = new
                UserRecyclerViewAdapter( ids, firstNames, lastNames, positions, users, this);

        recyclerView.setAdapter(adapter);
        ids = new ArrayList<>();
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
        positions = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}