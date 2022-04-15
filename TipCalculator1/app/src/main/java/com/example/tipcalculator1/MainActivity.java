package com.example.tipcalculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
        userDb = new DataBaseHelperUser(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName =  username.getText().toString();
                String pass = password.getText().toString();
                if(!userName.equals(adminUsername) || !pass.equals(adminPassword)) {

                    Cursor user = userDb.getUser(username.getText().toString(), password.getText().toString());

                    if(user != null){
                        if(user.moveToNext()) {
                            if(user.getString(3).isEmpty() && user.getString(4).isEmpty() && user.getString(5).isEmpty()) {
                                i = new Intent(MainActivity.this, UserCreation.class);

                            } else{
                                i = new Intent(MainActivity.this, UserHomePage.class);
                            }
                            i.putExtra("id", user.getString(0));
                            startActivity(i);
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Please enter a valid username and password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    i = new Intent(MainActivity.this, AdminPage.class);
                    startActivity(i);
                }
            }
        });

    }
}