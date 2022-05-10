package com.example.tipcalculator1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(isNumeric(tips.getText().toString()) && !tips.getText().toString().isEmpty()) {
                    if (db.createShift(date, hours, tips.getText().toString(), user_id)) {
                        System.out.println("f");
                        Intent i = new Intent(AfterClockOut.this, TipInformation.class);
                        i.putExtra("hours",hours);
                        i.putExtra("date", date);
                        i.putExtra("tips", tips.getText().toString());
                        startActivityForResult(i, 1);
                    }
                }
                else
                    Toast.makeText(AfterClockOut.this, "Input Valid Tip!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                finish();
            }

        }
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