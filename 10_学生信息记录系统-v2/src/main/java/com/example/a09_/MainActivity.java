package com.example.a09_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

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
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                    Toast.makeText(MainActivity.this, "学生的姓名或者学号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder sb = new StringBuilder();
                int id = rg_sex.getCheckedRadioButtonId();
                String sex = "male";
                if (id == R.id.rb_male) {
                    //男性
                    sex = "male";
                } else {
                    //女性
                    sex = "female";
                }
                try {
                    //采用Android的api面向对象的生成xml文件
                    //1.得到xml文件的序列化器
                    XmlSerializer serializer = Xml.newSerializer();
                    //2.指定序列化器的初始参数
                    File file = new File(getFilesDir(), name + ".xml");
                    FileOutputStream fos = new FileOutputStream(file);
                    serializer.setOutput(fos, "utf-8");
                    //3.写xml文件
                    serializer.startDocument("utf-8", true);
                    serializer.startTag(null, "student");
                    serializer.startTag(null, "name");
                    serializer.text(name);
                    serializer.endTag(null, "name");
                    serializer.startTag(null, "number");
                    serializer.text(number);
                    serializer.endTag(null, "number");
                    serializer.startTag(null, "sex");
                    serializer.text(sex);
                    serializer.endTag(null, "sex");
                    serializer.endTag(null, "student");
                    serializer.endDocument();
                    fos.close();
                    Toast.makeText(MainActivity.this, "保存数据成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "保存数据失败", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
