package com.example.huhong.painDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huhong.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        auth = FirebaseAuth.getInstance();

        findViewById(R.id.signinsignupbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.signinsigninbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = findViewById(R.id.signinusername);
                TextView password = findViewById(R.id.signinpassword);
                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();
                Task<AuthResult> task = auth.signInWithEmailAndPassword(usernameString, passwordString);
                task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                        intent.putExtra("username", usernameString);
                        Toast.makeText(getApplicationContext(), "welcome " + usernameString + " !", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "username or password is wrong!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}