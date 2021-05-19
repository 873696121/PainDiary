package com.example.huhong.painDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huhong.R;
import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private PainRecordDatabase painRecordDatabase;
    private PainRecordDao painRecordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        painRecordDatabase = Room.databaseBuilder(getApplicationContext(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
        painRecordDao = painRecordDatabase.getPainRecordDao();
        auth = FirebaseAuth.getInstance();
        findViewById(R.id.signupbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = findViewById(R.id.signupusername);
                TextView password = findViewById(R.id.signupeditpassword);
                TextView confirmPassword = findViewById(R.id.signupconfirmpassword);
                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();
                String confirmPasswordString = confirmPassword.getText().toString();
                if(passwordString == null || usernameString == null || confirmPasswordString == null){
                    Toast.makeText(getApplicationContext(), "Username and password cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else if(!passwordString.equals(confirmPasswordString)) {
                    Toast.makeText(getApplicationContext(), "The two passwords are inconsistent!", Toast.LENGTH_LONG).show();
                }
                auth.createUserWithEmailAndPassword(usernameString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "sign up successfully!", Toast.LENGTH_LONG).show();
                            painRecordDao.insertRecords(new PainRecord(usernameString));
                            Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "sign up failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}