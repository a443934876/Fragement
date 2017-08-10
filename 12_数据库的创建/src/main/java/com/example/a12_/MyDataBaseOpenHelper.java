package com.example.a12_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/8/10.
 */

public class MyDataBaseOpenHelper extends SQLiteOpenHelper {
/**
 * @param context 上下文
 * @param name 数据库文件的名称
 * @param factory 默认游标工厂 从数据库文件的头部开始的
 * @param version 数据库的版本号 最小是1
 * */
    public MyDataBaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "test.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
