package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DeleteUser extends AppCompatActivity {
    Button delete;
    ImageButton goBack;
    EditText username;
    DataBaseHelperUser userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        username = findViewById(R.id.tips);
        delete = findViewById(R.id.deleteUser);
        userDb = new DataBaseHelperUser(this);
        goBack = findViewById(R.id.logout);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack = findViewById(R.id.logout);
                goBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userDb.deleteData(username.getText().toString());
                    }
                });
            }
        });
    }
}