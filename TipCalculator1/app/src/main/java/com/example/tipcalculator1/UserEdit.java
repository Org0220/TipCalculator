package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class UserEdit extends AppCompatActivity {
ImageButton goBack;
    Button edit;
    EditText firstName, lastName, position;
    DataBaseHelperUser userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        goBack = findViewById(R.id.logout);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        position = findViewById(R.id.position);
        edit = findViewById(R.id.editUser);
        userDb = new DataBaseHelperUser(this);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDb.update(getIntent().getStringExtra("id"),
                        firstName.getText().toString(), lastName.getText().toString(), position.getText().toString());
                finish();
            }
        });
    }
}