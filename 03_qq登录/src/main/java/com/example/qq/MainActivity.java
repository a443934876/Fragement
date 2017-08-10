package com.example.qq;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remeember = (CheckBox) findViewById(R.id.cb_remeember);
        login = (Button) findViewById(R.id.login);
        //获取原来在文件中保存的QQ号和密码，回显到界面上。
        //File file = new File("/data/data/com.example.qq/info.txt");
        File file =new File(this.getFilesDir(),"info");
        if (file.exists() & file.length() > 0) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String info = br.readLine();
                String qq = info.split("##")[0];
                String pwd = info.split("##")[1];
                et_qq.setText(qq);
                et_password.setText(pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


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
                } else {
                    Log.i(TAG, "不需要记住密码");
                }

                try {
//                    File file = new File("/data/data/com.example.qq/info.txt");
                    File file= new File(getFilesDir(),"info");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write((qq + "##" + pwd).getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "数据保存失败", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
