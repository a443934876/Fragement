package com.example.a09_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button save;
    private EditText et_name;
    private EditText et_number;
    private RadioGroup rg_sex;
    private Button read;
    private TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = (Button) findViewById(R.id.save);
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        read = (Button) findViewById(R.id.read);
        tv_result= (TextView) findViewById(R.id.tv_result);

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
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            /*
            * 根据姓名读取学生的信息
            * */
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "对不起，请输入要查询的学生的姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                //读取学生的信息
                File file = new File(getFilesDir(), name + ".xml");

                if (file.exists() && file.length() > 0) {
                    try {
                        //学生信息的xml文件存在
                        //1.获取到一个xml解析器
                        XmlPullParser pullParser = Xml.newPullParser();
                        //2.设置解析器的初始化参数
                        FileInputStream inputStream = new FileInputStream(file);
                        pullParser.setInput(inputStream, "utf-8");
                        //3.解析xml文件
                        int type = pullParser.getEventType();//得到第一个事件的类型
                        Log.i(TAG, "type:" + type);
                        StringBuilder sb=new StringBuilder();

                        while (type != XmlPullParser.END_DOCUMENT) {
                            if (type == XmlPullParser.START_TAG) {
                                //开始节点
                                //判断节点的名称
                                if ("name".equals(pullParser.getName())) {
                                    String nameStr = pullParser.nextText();
                                    Log.i(TAG, "姓名:" + nameStr);
                                    sb.append("姓名:" + nameStr+"\n");
                                } else if ("number".equals(pullParser.getName())) {
                                    String numberStr = pullParser.nextText();
                                    Log.i(TAG, "学号:" + numberStr);
                                    sb.append("学号:" +  numberStr+"\n");
                                } else if ("sex".equals(pullParser.getName())) {
                                    String sexStr = pullParser.nextText();
                                    Log.i(TAG, "性别:" + sexStr);
                                    sb.append("性别:" + sexStr+"\n");
                                }
                            }
                            type = pullParser.next();
                            Log.i(TAG, "type:" + type);
                        }
                        inputStream.close();
                        tv_result.setText(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "解析学生信息失败", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "查无此人", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
