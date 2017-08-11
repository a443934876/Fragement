package com.example.a15_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //mvc中的view，视图
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找控制器
        lv = (ListView) findViewById(R.id.lv);
        //设置控制器 controller
        lv.setAdapter(new MyAdapter());
    }
        //BaseXXX SimpleXXX DefalutXXX
        /*
        * 控制器 用来控制listview如何显示
        * */
    private class MyAdapter extends BaseAdapter {
        //返回listview里面有单色个item
        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv=new TextView(MainActivity.this);
            tv.setText("我的文本："+i);
            tv.setTextSize(24);
            return tv;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


    }
}

