package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CreateUser extends AppCompatActivity {
Button create;
EditText username, password;
ImageButton goBack;
DataBaseHelperUser userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        create = findViewById(R.id.createUser);
        userDb = new DataBaseHelperUser(this);
        goBack = findViewById(R.id.logout);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDb.createUserWithAdmin(username.getText().toString(), password.getText().toString());
            }
        });

    }
}