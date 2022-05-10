package com.example.tipcalculator1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DataBaseHelperUser userDb;
    EditText username, password;
    Button login, mapsB;
    private String adminEmail = "shahbah@gmail.com";
    private String adminPassword = "1234";
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.Login);
        mapsB = findViewById(R.id.mapsB);
        username = findViewById(R.id.username);
        EditText email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        userDb = new DataBaseHelperUser(this);
        username.setText("");
        password.setText("");

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    i = new Intent(MainActivity.this, AdminPage.class);
                    Toast.makeText(MainActivity.this, "Firebase sign in", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                } else {

                }
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String pass = password.getText().toString();
                if (userName.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill in credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userName.equalsIgnoreCase(adminEmail))
                    mAuth.signInWithEmailAndPassword(userName, pass);
                else {
                    Cursor user = userDb.getUser(username.getText().toString(), password.getText().toString());

                    if (user != null) {
                        if (user.moveToNext()) {
                            if (user.getString(3).isEmpty() && user.getString(4).isEmpty() && user.getString(5).isEmpty()) {
                                i = new Intent(MainActivity.this, UserCreation.class);

                            } else {
                                i = new Intent(MainActivity.this, UserHomePage.class);
                            }
                            i.putExtra("id", user.getString(0));
                            startActivity(i);
                        } else {

                            Toast.makeText(MainActivity.this, "Please enter a valid username and password", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        mapsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);

    }

}