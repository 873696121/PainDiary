package com.example.huhong.painDiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huhong.R;
import com.example.huhong.painDiary.room.UserDatabase;
import com.example.huhong.painDiary.room.dao.UserDao;
import com.example.huhong.painDiary.room.entity.User;

public class LoginActivity extends AppCompatActivity {


    UserDatabase userDatabase;
    UserDao userDao;
    EditText username;
    EditText password;

//    private PainRecordDatabase painRecordDatabase;
//    private PainRecordDao painRecordDao;
//    private PainRecord painRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "user_database")
                .allowMainThreadQueries()
                .build();
        userDao = userDatabase.getUserDao();

        findViewById(R.id.login_register_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 登录跳转功能
        findViewById(R.id.login_login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = findViewById(R.id.login_username);
                password = findViewById(R.id.login_password);
                User user = userDao.getUser(username.getText().toString(), password.getText().toString());
                if(user == null){
                    Toast.makeText(getApplicationContext(), "username or password is wrong!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "welcome " + user.getUsername() + " !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("username", user.getUsername());
                    startActivity(intent);
                }
            }
        });

//        // 往数据库添加假数据用于生成饼图的按钮， 验收时会删除
//        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                painRecordDatabase = Room.databaseBuilder(getApplicationContext(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
//                painRecordDao = painRecordDatabase.getPainRecordDao();
//                painRecord = painRecordDao.getPainRecord("1");
//
//                Random random = new Random();
//                Date date = new Date();
//                Weather weather = new RetrofitUtil().getWeather();
//                Record[] records = new Record[]{
//                        new Record(date, 1, "neck", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 2, "back", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 3, "head", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 4, "knees", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 5, "abdomen", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 6, "neck", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 7, "head", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 8, "neck", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 9, "knees", 1, random.nextInt(10000), weather, 3, 10000),
//                        new Record(date, 10, "neck", 1, random.nextInt(10000), weather, 3, 10000)};
//
//                String[] days = {"2021-3-12", "2021-3-13", "2021-3-14", "2021-3-15", "2021-3-16", "2021-3-17", "2021-3-18", "2021-3-19", "2021-3-20", "2021-3-21"};
//
//                for (int i = 0; i < records.length; i ++) {
//                    painRecord.getMap().put(days[i], records[i]);
//                }
//                painRecordDao.updateRecords(painRecord);
//                PainRecord painRecord = painRecordDao.getPainRecord("1");
//                System.out.println(1);
//            }
//        });

    }
}