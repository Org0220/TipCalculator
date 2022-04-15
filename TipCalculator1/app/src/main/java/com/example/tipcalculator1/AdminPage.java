package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPage extends AppCompatActivity {
Button createUser, deleteUser, viewUsers;
ImageButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        createUser = findViewById(R.id.createUser);
        deleteUser = findViewById(R.id.deleteUser);
        viewUsers = findViewById(R.id.viewUsers);
        logout = findViewById(R.id.logout);



        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this,CreateUser.class);
                startActivity(i);
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this,DeleteUser.class);
                startActivity(i);
            }
        });

        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPage.this, UsersInfo.class);
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