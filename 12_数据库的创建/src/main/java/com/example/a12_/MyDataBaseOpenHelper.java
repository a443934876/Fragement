package com.example.a12_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDataBaseOpenHelper extends SQLiteOpenHelper {
/**
 * @param context 上下文
 *  test.db 数据库文件的名称
 *  factory 默认游标工厂 从数据库文件的头部开始的
 *  version 数据库的版本号 最小是1
 * */
    public MyDataBaseOpenHelper(Context context) {
        super(context, "test.db",null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
