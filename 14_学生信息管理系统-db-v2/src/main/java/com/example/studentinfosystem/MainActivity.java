package com.example.studentinfosystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentinfosystem.dao.StudentDao;
import com.example.studentinfosystem.domain.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private RadioGroup rg_sex;
    private Button save;
    private StudentDao dao;
    private LinearLayout ll_result;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        save = (Button) findViewById(R.id.save);
        delete= (Button) findViewById(R.id.delete);
        //找到界面下方的空白的线性布局
        ll_result = (LinearLayout) findViewById(R.id.ll_result);
        dao = new StudentDao(MainActivity.this);
        refreshData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "请输入学生的姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = rg_sex.getCheckedRadioButtonId();
                String sex = "male";
                if (id == R.id.rb_male) {
                    sex = "male";
                } else {
                    sex = "female";
                }
                dao.add(name, sex);
                Toast.makeText(MainActivity.this, "数据添加成功", Toast.LENGTH_SHORT).show();
                refreshData();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "请输入学生的姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                dao.delete(name);
                Toast.makeText(MainActivity.this, "数据删除成功", Toast.LENGTH_SHORT).show();
                refreshData();
            }
        });



    }

    /*
     * 获取数据库的全部记录，刷新显示数据
     */
    private void refreshData() {
        List<Student> students = dao.findAll();
        ll_result.removeAllViews();//把原来的数据给清除
        for (Student student : students) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(student.toString());
            ll_result.addView(tv);
        }
    }
}
