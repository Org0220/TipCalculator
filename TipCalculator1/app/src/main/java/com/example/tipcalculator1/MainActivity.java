package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DataBaseHelperUser userDb;
    EditText username, password;
    Button login;
    private String adminUsername = "Shahbah";
    private String adminPassword = "1234";
    Intent i;
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
                String userName =  username.getText().toString();
                String pass = password.getText().toString();
                System.out.println(userName);
                System.out.println(pass);
                if(!userName.equals(adminUsername) || !pass.equals(adminPassword)) {
                    Cursor user = userDb.getUser(userName, pass);
                    if(user.moveToNext()) {
                        i = new Intent(MainActivity.this, UserCreation.class);
                        startActivity(i);
                    }
                } else {
                    i = new Intent(MainActivity.this, AdminPage.class);
                    startActivity(i);
                }
            }
        });

    }
}