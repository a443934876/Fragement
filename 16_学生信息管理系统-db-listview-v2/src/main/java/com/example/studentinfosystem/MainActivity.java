package com.example.studentinfosystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentinfosystem.dao.StudentDao;
import com.example.studentinfosystem.domain.Student;

import java.util.List;
import java.util.Random;

import javax.crypto.spec.IvParameterSpec;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private RadioGroup rg_sex;
    private Button save;
    private StudentDao dao;
    private LinearLayout ll_result;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        save = (Button) findViewById(R.id.save);
        //找到界面下方的LietView
        lv = (ListView) findViewById(R.id.lv);
        dao = new StudentDao(MainActivity.this);
        refreshData();
        String [] sex1={"male","female"};
        String [] name1={"刘一","陈二","张三","李四","王五","赵六","孙七","周八","吴九","郑十"};
        Random random=new Random();
        for (int i=0 ;i<100;i++){
            String name2=name1[random.nextInt(9)];
            String sex2=sex1[random.nextInt(2)];
            dao.add(name2,sex2);
        }
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
//                refreshData();
            }
        });

    }


    /*
     * 获取数据库的全部记录，刷新显示数据
     */
    private void refreshData() {
        final List<Student> students = dao.findAll();
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {

                    return students.size() ;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                View view1 = null;
                if (view == null) {
                    //把一个布局xml文件转化成view对象
                    view1 = View.inflate(MainActivity.this, R.layout.item, null);
                } else {
                    view1 = view;
                }
                //在view里面查找孩子控件
                TextView tv_name = view.findViewById(R.id.tv_name);
                ImageView iv_sex = view.findViewById(R.id.iv_sex);
                Student student = students.get(i);
                String sex = student.getSex();
                if ("male".equals(sex)) {
                    iv_sex.setImageResource(R.drawable.nan);
                } else {
                    iv_sex.setImageResource(R.drawable.nv);
                }
                tv_name.setText(student.getName());

                return view1;
            }
        });
    }
}
