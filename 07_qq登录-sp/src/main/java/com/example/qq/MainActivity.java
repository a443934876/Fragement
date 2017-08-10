package com.example.qq;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText et_qq;
    private EditText et_password;
    private CheckBox cb_remeember;
    private Button login;
    //1.声明sp
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remeember = (CheckBox) findViewById(R.id.cb_remeember);
        login = (Button) findViewById(R.id.login);
        //2.获取到一个参数
        sp=MainActivity.this.getSharedPreferences("config",0);
        String qq=sp.getString("qq","");
        String pwd=sp.getString("pwd","");
        et_qq.setText(qq);
        et_password.setText(pwd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qq = et_qq.getText().toString().trim();
                String pwd = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(qq) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "用户密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cb_remeember.isChecked()) {
                    Log.i(TAG, "记住密码");
                    //3.得到sp文件的编辑器
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("qq",qq);
                    editor.putString("pwd",pwd);
                    //4.保存数据完毕，一定要记得调用commit方法
                    editor.commit();




                } else {
                    Log.i(TAG, "不需要记住密码");
                }




            }
        });

    }


}
