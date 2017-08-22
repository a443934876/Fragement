package com.example.a17_;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button bt_confirm01;
    private Button bt_confirm02;
    private Button bt_confirm03;
    private Button bt_confirm04;
    private Button bt_confirm05;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_confirm01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("警告");
                builder.setMessage("是否继续");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "确认", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        findViewById(R.id.bt_confirm02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择您的性别：");
                final String[] items = {"男", "女"};
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您的性别是：" + items[i], Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消选择", null);
                builder.show();
            }
        });
        findViewById(R.id.bt_confirm03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择您爱吃的水果");
                final String[] items = new String[]{"黄瓜", "苹果", "香蕉", "橘子", "西瓜"};
                final boolean[] checkeditems = new boolean[]{true, false, false, false, false};

                builder.setMultiChoiceItems(items, checkeditems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        Toast.makeText(MainActivity.this, items[i] + b, Toast.LENGTH_SHORT).show();
                        checkeditems[i] = b;
                    }
                });
                builder.setNegativeButton("取消选择", null);
                builder.show();
            }
        });
        findViewById(R.id.bt_confirm04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd =new ProgressDialog(MainActivity.this);
                pd.setTitle("提醒");
                pd.setMessage("正在加载数据。。。请稍后");
                pd.show();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    };
                }.start();
            }
        });
        findViewById(R.id.bt_confirm05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd =new ProgressDialog(MainActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(100);
                pd.setTitle("提醒");
                pd.setMessage("正在加载数据。。。请稍后");
                pd.show();
                new Thread(){
                    @Override
                    public void run() {
                        for (int i=0;i<=100;i++) {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            pd.setProgress(i);

                        }
                        pd.dismiss();
                    };
                }.start();
            }
        });

    }
}
