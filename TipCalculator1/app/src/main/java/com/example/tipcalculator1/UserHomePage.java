package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserHomePage extends AppCompatActivity {
    ImageButton logout, editInfo, viewShifts, viewTips;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        logout = findViewById(R.id.logout);
        editInfo = findViewById(R.id.editInfo);
        viewShifts = findViewById(R.id.shiftsView);
        viewTips = findViewById(R.id.tipsView);

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, UserEdit.class);
                i.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(i);
            }
        });

        viewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, ShiftInformation.class);
                i.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(i);
            }
        });

        viewTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, TipsSummary.class);
                i.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}