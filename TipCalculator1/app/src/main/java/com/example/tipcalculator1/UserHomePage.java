package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class UserHomePage extends AppCompatActivity implements Connector{
    ImageButton logout, editInfo, viewShifts, viewTips;
    FragmentContainerView fragment;
    FragmentManager fm;
    Date date1;
    Date date2;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        logout = findViewById(R.id.logout);
        editInfo = findViewById(R.id.editInfo);
        viewShifts = findViewById(R.id.shiftsView);
        viewTips = findViewById(R.id.tipsView);
        fragment = findViewById(R.id.fragmentContainerView2);
        fm = getSupportFragmentManager();
        user_id = getIntent().getStringExtra("id");

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, UserEdit.class);
                i.putExtra("id", user_id);
                startActivity(i);
            }
        });

        viewShifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, ShiftInformation.class);
                i.putExtra("id", user_id);
                startActivity(i);
            }
        });

        viewTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserHomePage.this, TipsSummary.class);
                i.putExtra("id", user_id);
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


    @Override
    public void respond() throws ParseException {

        date1 = new Date();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.fragmentContainerView2, ClockOut.class, null);
        transaction.commit();
    }

    @Override
    public void respond2() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date2 = new Date();
        double diff = date2.getTime() - date1.getTime();
        double difference = TimeUnit.SECONDS.convert((long) diff, TimeUnit.MILLISECONDS);
        System.out.println(diff);
        double hours = difference;
        System.out.println(hours);


        Intent i = new Intent(UserHomePage.this, AfterClockOut.class);

        i.putExtra("id", user_id);
        i.putExtra("hours", String.format("%.2f", hours));
        i.putExtra("date", formatter.format(date2));
        startActivity(i);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.fragmentContainerView2, ClockIn.class, null);
        transaction.commit();
    }
}