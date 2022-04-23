package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterClockOut extends AppCompatActivity {
Button createShift;
EditText tips;
DataBaseHelperShift db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_clock_out);
        tips = findViewById(R.id.tips);
        createShift = findViewById(R.id.createShift);
        String user_id = getIntent().getStringExtra("id");
        String hours = getIntent().getStringExtra("hours");
        String date = getIntent().getStringExtra("date");
        db = new DataBaseHelperShift(this);
        createShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNumeric(tips.getText().toString())) {
                    db.createShift(date, hours, tips.getText().toString(), user_id);
                }
            }
        });
    }
    public static boolean isNumeric (String s) {
        for(char chr : s.toCharArray()) {
            if(57 < chr || chr < 48) {
                return false;
            }
        }
        return true;
    }
}