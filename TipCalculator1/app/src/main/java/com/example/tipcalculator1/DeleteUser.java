package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
                Cursor res = userDb.getALlData();
                boolean doesExist = false;
                while (res.moveToNext()) {
                    if (username.getText().toString().equalsIgnoreCase(res.getString(1))) {
                        doesExist = true;
                    }
                }
                if (doesExist && !username.getText().toString().isEmpty()) {
                    userDb.deleteData(username.getText().toString());
                    Toast.makeText(getApplicationContext(), "User Deleted!", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Enter Valid Username", Toast.LENGTH_LONG).show();
            }
        });
    }
}