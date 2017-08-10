package com.example.studentinfosystem.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;

import com.example.studentinfosystem.db.StudentDBOpenHelper;

/**
 * 学生信息数据库的dao（data access objcet）
 */

public class StudentDao {
    private StudentDBOpenHelper helper;
/**
 * 只有一个有参的构造方法，要求必须传入上下文
 * */
    public StudentDao(Context context) {
        helper=new StudentDBOpenHelper(context);
    }
    /**
     * 添加一个学生
     * @param name 姓名
     * @param sex  性别，male female
     */
    public void add(String name, String sex) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("insert into student (name,sex) values (?,?)",new Object[]{name,sex});
        db.close();//释放资源
    }
    /**
     * 删除一个学生
     * @param name 姓名
     *
     */
    public void delete(String name) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from student where name=?",new Object[]{name});
        db.close();//释放资源
    }
    /**
     * 修改一个学生信息
     * @param name 姓名
     * @param newsex  新的性别
     */
    public void update(String name, String newsex) {
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("update student set sex=? where name=?",new Object[]{newsex,name});
        db.close();//释放资源
    }
    /**
     * 查询学生的性别
     * @param name 姓名
     * @return 学生性别
     */
    public void find(String name) {
        SQLiteDatabase db=helper.getReadableDatabase();
        //结果集 游标
        Cursor cursor=db.rawQuery("select sex from student where name=?",new String[]{name});
        db.close();//释放资源
    }
}
