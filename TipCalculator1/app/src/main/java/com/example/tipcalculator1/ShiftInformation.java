package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ShiftInformation extends AppCompatActivity {
    ImageButton goBack;
    RecyclerView shifts;
    ShiftRecyclerViewAdapter adapter;
    DataBaseHelperShift db;
    ArrayList<String> userId = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> workedHours = new ArrayList<>();
    ArrayList<String> tipss = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_information);
        goBack = findViewById(R.id.logout);
        shifts = findViewById(R.id.shifts);
        db = new DataBaseHelperShift(this);
        getShift();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void getShift() {
        Cursor res = db.getShiftByUser(getIntent().getStringExtra("id"));

        while(res.moveToNext()) {
            System.out.println(res.getString(0));
            date.add(res.getString(1));
            workedHours.add(res.getString(2));
            tipss.add(res.getString(3));
            userId.add(res.getString(4));
        }
        Collections.reverse(date);
        Collections.reverse(workedHours);
        Collections.reverse(tipss);
        Collections.reverse(userId);

        adapter = new
                ShiftRecyclerViewAdapter(date, workedHours, tipss, userId,this);

        shifts.setAdapter(adapter);
        userId = new ArrayList<>();
        date = new ArrayList<>();
        workedHours = new ArrayList<>();
        shifts.setLayoutManager(new LinearLayoutManager(this));
    }
}