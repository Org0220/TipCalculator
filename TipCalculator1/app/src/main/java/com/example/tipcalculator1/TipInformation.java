package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class TipInformation extends AppCompatActivity {
ImageButton goBack;
TextView hourly,hours,date,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_information);
        goBack = findViewById(R.id.logout);
        hourly = findViewById(R.id.hourlyRate);
        hours = findViewById(R.id.password);
        date = findViewById(R.id.Date);
        amount = findViewById(R.id.amountEarned);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        double hourlyR = (Double.parseDouble(getIntent().getStringExtra("tips"))+10.80);
        hourly.setText(String.format("Rate: %.2f",hourlyR));
        hours.setText("Hours: "+getIntent().getStringExtra("hours"));
        date.setText(getIntent().getStringExtra("date"));
        double amountEarn = Double.parseDouble(getIntent().getStringExtra("hours")) * hourlyR;
        amount.setText(String.format("Amount earned: %.2f",amountEarn));
    }
}