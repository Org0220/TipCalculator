package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserCreation extends AppCompatActivity {
    Button create;
    EditText firstName, lastName, position;
    DataBaseHelperUser userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creation);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        position = findViewById(R.id.position);
        create = findViewById(R.id.createUser);
        userDb = new DataBaseHelperUser(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDb.update(getIntent().getStringExtra("id"),
                        firstName.getText().toString(), lastName.getText().toString(), position.getText().toString());
                Intent i = new Intent(UserCreation.this, UserHomePage.class);
                i.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(i);
            }
        });
    }
}