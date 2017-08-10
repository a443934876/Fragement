package com.example.studentinfosystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.studentinfosystem.dao.StudentDao;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private RadioGroup rg_sex;
    private Button save;
    private StudentDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name= (EditText) findViewById(R.id.et_name);
        rg_sex= (RadioGroup) findViewById(R.id.rg_sex);
        save= (Button) findViewById(R.id.save);
        dao=new StudentDao(MainActivity.this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this,"请输入学生的姓名",Toast.LENGTH_SHORT).show();
                    return;
                }
                int id=rg_sex.getCheckedRadioButtonId();
                String sex="male";
                if (id==R.id.rb_male){
                    sex="male";
                }else {
                    sex="female";
                }
                dao.add(name,sex);
                Toast.makeText(MainActivity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
            }
        });





    }
}
