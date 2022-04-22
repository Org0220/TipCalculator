package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ShiftInformation extends AppCompatActivity {
    ImageButton goBack;
    RecyclerView shifts;
    ShiftRecyclerViewAdapter adapter;
    DataBaseHelperUser db;
    ArrayList<String> userId = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> workedHours = new ArrayList<>();
    ArrayList<String> hourlyRate = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_information);
        goBack = findViewById(R.id.logout);
        shifts = findViewById(R.id.shifts);
        getShift();
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void getShift() {
        Cursor res = db.getALlData();

        while(res.moveToNext()) {

            hourlyRate.add(res.getString(2));
            date.add(res.getString(3));
            workedHours.add(res.getString(4));
            userId.add(res.getString(5));
        }
        adapter = new
                ShiftRecyclerViewAdapter(date, workedHours, hourlyRate, userId,this);

        shifts.setAdapter(adapter);
        userId = new ArrayList<>();
        date = new ArrayList<>();
        workedHours = new ArrayList<>();
        shifts.setLayoutManager(new LinearLayoutManager(this));
    }
}