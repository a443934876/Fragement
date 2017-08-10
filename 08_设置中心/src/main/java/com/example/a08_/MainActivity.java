package com.example.a08_;

import android.content.SharedPreferences;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CheckBox cb;
    private SeekBar seekBar;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb = (CheckBox) findViewById(R.id.cb);
        seekBar = (SeekBar) findViewById(R.id.SeekBar);
        sp = getSharedPreferences("config", 0);
        //回显控件的状态
        boolean b = sp.getBoolean("b", false);
        cb.setChecked(b);
        int progress=sp.getInt("progress",0);
        seekBar.setProgress(progress);



        //给chenckbox注册一个勾选状态变化的监听器
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //勾选状态发生变化调用的方法
                Log.i(TAG, "当前状态：" + b);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("b", b);
                editor.commit();
            }
        });
        //注册一个拖动变化的监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //在拖动的过程中调用的方法
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            //开始拖动调用的方法
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //停止拖动调用的方法
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                Log.i(TAG, "当前进度：" + progress);
                SharedPreferences.Editor editor=sp.edit();
                editor.putInt("progress",progress);
                editor.commit();
            }
        });


    }
}
