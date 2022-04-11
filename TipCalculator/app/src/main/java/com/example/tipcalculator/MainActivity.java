package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button login;
EditText username, password;
String adminUsername = "soupysoup";
String adminPassword = "aramberberian124";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.Login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                    if(username.getText().toString() != adminUsername || password.getText().toString() != adminPassword) {
                        intent = new Intent(MainActivity.this, UserCreation.class);
                    } else {
                        intent = new Intent(MainActivity.this, AdminPage.class);
                    }
                startActivity(intent);
            }
        });

    }
}