package com.example.a20_;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private static final int SUCCESS = 1;
    private static final int ERROR=2;

    private EditText et_path;
    private Button bt_vi;
    private ImageView iv;
    //1.定义一个消息处理器（秘书）
    private Handler handler=new Handler(){
        //3.秘书处理消息的方法
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS:
                    Bitmap bitmap= (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.this,"获取图片失败",Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_path= (EditText) findViewById(R.id.et_path);
        bt_vi= (Button) findViewById(R.id.bt_vi);
        iv= (ImageView) findViewById(R.id.iv);
        bt_vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String path=et_path.getText().toString().trim();
                if (TextUtils.isEmpty(path)){
                    Toast.makeText(MainActivity.this,"对不起，图片路径不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(){
                    @Override
                    public void run() {
//                        开启新的子线程 去请求网络获取数据
//                   显示互联网上的图片
                        //利用秘书给老板发消息，让主线程更新ui

                        try {
//                    1.得到图片的url路径
                            URL url=new URL(path);
//                    2.通过路径打开一个http的链接
                            HttpURLConnection connection= (HttpURLConnection) url.openConnection();//ftp http https rtsp
                            connection.setRequestMethod("GET");//设置请求参数为get，默认的请求方式就是get
                            connection.setConnectTimeout(5000);//设置请求服务器的超时时间
//                    3.得到服务器的返回信息
                            String type= connection.getContentType();
                            int length=connection.getContentLength();
                            Log.i("TAG", "服务器资源的长度："+length);
                            Log.i("TAG", "服务器返回的数据类型："+type);
                            int code= connection.getResponseCode();//200 ok  404 资源没找到 503服务器内部错误
                            if (code==200){
                                InputStream is= connection.getInputStream();
                                Bitmap bitmap= BitmapFactory.decodeStream(is);
                                Message msg=Message.obtain();
                                msg.what=SUCCESS;
                                msg.obj= bitmap;
                                handler.sendMessage(msg);
//                                iv.setImageBitmap(bitmap);
                                is.close();
                            }
                        } catch (Exception e) {
                            Message msg=Message.obtain();
                            handler.sendMessage(msg);
                            msg.what = ERROR;
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
