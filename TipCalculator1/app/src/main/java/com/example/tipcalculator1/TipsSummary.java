package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

public class TipsSummary extends AppCompatActivity {
ImageButton goBack;
ImageView chart;
TextView tipsView;
Fragment frag;
    DataBaseHelperShift db;
    ArrayList<String> allTips = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_summary);
        db = new DataBaseHelperShift(this);
        goBack = findViewById(R.id.logout);
        tipsView = findViewById(R.id.tips);
        FragmentManager fm = getSupportFragmentManager();
        frag =  fm.findFragmentById(R.id.fragmentContainerView);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Cursor res = db.getShiftByUser(getIntent().getStringExtra("id"));
        while(res.moveToNext()) {
            allTips.add(res.getString(3));
        }
        Collections.reverse(allTips);

        System.out.println(allTips);
        if (allTips.size() >= 10) {
            int newTips = 0;
            int pastTips = 0;
            int size = allTips.size();
            for(int i = 0; i < size;i++){
                if (i <5)
                    newTips += Integer.parseInt(allTips.get(i));
                else
                    pastTips += Integer.parseInt(allTips.get(i));
            }
            int avgNew = newTips/5;
            int avgPast = pastTips/(size-5);
            if(avgNew>=avgPast) {
                tipsView.setText("$Tips+");
                tipsView.setTextColor(Color.GREEN);
                FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                        new TipsUp(), null).addToBackStack(null).commit();
            }
            else {
                tipsView.setText("$Tips-");
                tipsView.setTextColor(Color.RED);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                        new TipsDown(), null).addToBackStack(null).commit();
            }
            chart = findViewById(R.id.chartView);
            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load("https://quickchart.io/chart?width=500&height=300&chart={type:%27bar%27,data:{labels:[%20%27Past%205%20Shifts%20Vs%20Average%20Tips%27],%20datasets:[{label:%27Past%205%20Shifts%27,data:["+avgNew+"]},{label:%27Average%20Tips%27,data:["+avgPast+",60]}]}}"
                    )
                    .into(chart);
        }
        else {
            tipsView.setText("");
            Toast.makeText(getApplicationContext(),"Not enough data to show summary",Toast.LENGTH_LONG).show();
        }
    }
}