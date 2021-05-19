package com.example.huhong.painDiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huhong.R;
import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.UserDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.room.dao.UserDao;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.example.huhong.painDiary.room.entity.User;

public class RegisterActivity extends AppCompatActivity {

    UserDatabase userDatabase;
    UserDao userDao;
    PainRecordDatabase painRecordDatabase;
    PainRecordDao painRecordDao;
    EditText username;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "user_database")
                .allowMainThreadQueries()
                .build();
        userDao = userDatabase.getUserDao();

        painRecordDatabase = Room.databaseBuilder(this, PainRecordDatabase.class, "pain_record")
                .allowMainThreadQueries()
                .build();
        painRecordDao = painRecordDatabase.getPainRecordDao();


        findViewById(R.id.register_submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.register_username);
                password = findViewById(R.id.register_password);
                confirmPassword = findViewById(R.id.register_comfirm_password);
                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();
                String confirmPasswordString = confirmPassword.getText().toString();
                if(TextUtils.isEmpty(usernameString) || TextUtils.isEmpty(passwordString)){
                    Toast.makeText(getApplicationContext(), "Username and password cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else if(!passwordString.equals(confirmPasswordString)){
                    Toast.makeText(getApplicationContext(), "The two passwords are inconsistent!", Toast.LENGTH_LONG).show();
                }
                else{
                    userDao.insertUsers(new User(usernameString, passwordString));
                    painRecordDao.insertRecords(new PainRecord(usernameString));
                    Toast.makeText(getApplicationContext(), "register successfully!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}