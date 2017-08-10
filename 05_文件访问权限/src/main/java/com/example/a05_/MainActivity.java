package com.example.a05_;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button getPrivateFile;
    private Button getPublicFile;
    private Button getReadOnlyFile;
    private Button getWriteOnlyFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPrivateFile= (Button) findViewById(R.id.getPrivateFile);
        getPublicFile= (Button) findViewById(R.id.getPublicFile);
        getReadOnlyFile= (Button) findViewById(R.id.getReadOnlyFile);
        getWriteOnlyFile= (Button) findViewById(R.id.getWriteOnlyFile);
        /*
        * 生成一个私有文件，默认生成文件是私有的
        * */
        getPrivateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            /*
            * 生成一个私有的文件
            * */
            public void onClick(View view) {

                try {
                    File file=new File(getFilesDir(),"private.txt");
                    FileOutputStream fos=new FileOutputStream(file);
                    fos.write("private".getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        /*
        * 生成一个可读可写的文件，公开权限的文件
        * */
        getPublicFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos=openFileOutput("public.txt", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
                    fos.write("public".getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        getReadOnlyFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos=openFileOutput("ReadOnly.txt", Context.MODE_WORLD_READABLE);
                    fos.write("ReadOnly".getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getWriteOnlyFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos=openFileOutput("WriteOnly.txt", Context.MODE_WORLD_WRITEABLE);
                    fos.write("WriteOnly".getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this, "创建成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
