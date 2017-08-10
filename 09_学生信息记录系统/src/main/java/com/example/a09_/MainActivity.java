package com.example.a09_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button save;
    private EditText et_name;
    private EditText et_number;
    private RadioGroup rg_sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String number = et_number.getText().toString().trim();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(number)){
                    Toast.makeText(MainActivity.this,"学生的姓名或者学号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder sb=new StringBuilder();
                int id = rg_sex.getCheckedRadioButtonId();
                String sex="male";
                if (id == R.id.rb_male) {
                    //男性
                    sex="male";
                } else{
                    //女性
                    sex="female";
                }
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
                sb.append("<student>");
                sb.append("<name>");
                sb.append(name);
                sb.append("</name>");
                sb.append("<number>");
                sb.append(number);
                sb.append("</number>");
                sb.append("<sex>");
                sb.append(sex);
                sb.append("</sex>");
                sb.append("</student>");
                //ctrl + alt + t 捕获异常
                try {
                    File file=new File(getFilesDir(),name+".xml");
                    FileOutputStream fos=new FileOutputStream(file);
                    fos.write(sb.toString().getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this,"数据保存失败",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        });
    }
}
