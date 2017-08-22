package com.example.studentinfosystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;

import com.example.studentinfosystem.db.StudentDBOpenHelper;
import com.example.studentinfosystem.domain.Student;

import java.util.ArrayList;
import java.util.List;

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
     *@return result 添加到数据库的哪一行，-1添加失败
     */
    public long add(String name, String sex) {
        SQLiteDatabase db=helper.getWritableDatabase();
//        db.execSQL("insert into student (name,sex) values (?,?)",new Object[]{name,sex});
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("sex",sex);
        long result=db.insert("student",null,values);
        db.close();//释放资源
        return result;
    }
    /**
     * 删除一个学生
     * @param name 姓名
     *@return result 删除了几行，0代表删除失败
     */
    public int delete(String name) {
        SQLiteDatabase db=helper.getWritableDatabase();
//        db.execSQL("delete from student where name=?",new Object[]{name});
        int result=db.delete("student","name=?",new String[]{name});
        db.close();//释放资源
        return result;
    }
    /**
     * 修改一个学生信息
     * @param name 姓名
     * @param newsex  新的性别
     * @return 更新了几行，0更新失败
     */
    public int update(String name, String newsex) {
        SQLiteDatabase db=helper.getWritableDatabase();
//        db.execSQL("update student set sex=? where name=?",new Object[]{newsex,name});
        ContentValues values=new ContentValues();
        values.put("sex",newsex);
        int result=db.update("student",values,"name=?",new String[]{name});
        db.close();//释放资源
        return result;
    }
    /**
     * 查询学生的性别
     * @param name 姓名
     * @return 学生性别
     */
    public String find(String name) {
        String sex=null;
        SQLiteDatabase db=helper.getReadableDatabase();
        //结果集 游标
//        Cursor cursor=db.rawQuery("select sex from student where name=?",new String[]{name});
        Cursor cursor= db.query("student",new String[]{"sex"},"name=?",new String[]{name},null,null,null);
        boolean result=cursor.moveToNext();
        if (result){
            sex=cursor.getString(0);
        }
        cursor.close();
        db.close();//释放资源
        return sex;
    }
    /*
    * 获取全部的学生信息
    * */
    public List<Student> findAll(){
        List<Student> students=new ArrayList<Student>();
        SQLiteDatabase db=helper.getReadableDatabase();
//        Cursor cursor= db.rawQuery("select name,sex from student",null);
        Cursor cursor=db.query("student",new String[]{"name","sex"},null,null,null,null,null);
        while (cursor.moveToNext()){
            String name=cursor.getString(0);
            String sex=cursor.getString(1);
            Student student=new Student();
            student.setName(name);
            student.setSex(sex);
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }

}
