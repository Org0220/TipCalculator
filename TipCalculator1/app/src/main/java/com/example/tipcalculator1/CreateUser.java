package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateUser extends AppCompatActivity {
Button create;
EditText username, password;
ImageButton goBack;
DataBaseHelperUser userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        username = findViewById(R.id.tips);
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
                Cursor res = userDb.getALlData();
                boolean doesExist = false;
                while(res.moveToNext()) {
                    if(username.getText().toString().equalsIgnoreCase(res.getString(1))){
                        doesExist = true;
                    }
                }
                if (doesExist ){
                    Toast.makeText(getApplicationContext(),"This Username Already Exists",Toast.LENGTH_SHORT).show();
                }
                else if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill in credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    userDb.createUserWithAdmin(username.getText().toString(), password.getText().toString());

                    Toast.makeText(getApplicationContext(),"User Created!",Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });

    }
}