    package com.example.a12_;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //这一句代码执行完毕数据是不会被创建的
        MyDataBaseOpenHelper helper=new MyDataBaseOpenHelper(this);
        //下面这一行代码执行了 数据库才会被创建
        helper.getWritableDatabase();

    }
}
