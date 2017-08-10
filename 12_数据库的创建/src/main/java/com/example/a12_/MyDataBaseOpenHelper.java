package com.example.a12_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class MyDataBaseOpenHelper extends SQLiteOpenHelper {
/**
 * @param context 上下文
 *  test.db 数据库文件的名称
 *  factory 默认游标工厂 从数据库文件的头部开始的
 *  version 数据库的版本号 最小是1
 * */
    public MyDataBaseOpenHelper(Context context) {
        super(context, "test.db",null, 2);
    }


    /*
    * 当数据库第一次被创建的时候调用的方法，这个方法只会执行一次，一般在这个方法里面初始化数据的表结构
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table student (_id integer primary key autoincrement, name varchar(20),phone varchar(30))");
    }
/*
* 当数据库需要被更新的时候调用的方法
* 数据库只能升级不能降级
* */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("TAG", "数据库更新了");
        sqLiteDatabase.execSQL("alter table student add account varchar(20)");
    }
}
