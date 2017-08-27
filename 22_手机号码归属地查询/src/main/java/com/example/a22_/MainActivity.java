package com.example.a22_;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import utils.StreamTools;

public class MainActivity extends AppCompatActivity {
    private static final int SUCCESS = 1;
    private static final int ERROR_PHONE=2;
    private static final int ERROR = 3;
    private EditText et_phone;
    private TextView tv_phone;
    private TextView tv_address;
    private TextView tv_types;
    private Button bt_test;
    private ProgressDialog pd;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            switch (msg.what) {
                case SUCCESS:
                    try {
                        JSONObject dataJSON= (JSONObject) msg.obj;
                        String address=dataJSON.getString("province")+dataJSON.getString("city");
                        tv_address.setText(address);
                        tv_phone.setText(dataJSON.getString("mobile"));
                        tv_types.setText(dataJSON.getString("types"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case ERROR_PHONE:
                    Toast.makeText(MainActivity.this, "对不起输入正确的电话号码", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.this, "对不起获取计算信息失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_types = (TextView) findViewById(R.id.tv_types);
        bt_test = (Button) findViewById(R.id.bt_test);
        bt_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(MainActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                //http://sj.apidata.cn/?mobile=13488888888
                final String path = "http://sj.apidata.cn/?mobile=" + phone;
                pd=new ProgressDialog(MainActivity.this);
                pd.setMessage("正在获取数据请稍后......");
                pd.show();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(path);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(5000);
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream is = connection.getInputStream();
                                String json= StreamTools.readStream(is);
                                JSONObject jsonObj=new JSONObject(json);
                                String result= jsonObj.getString("status");
                                if ("1".equals(result)){
                                    //号码正确
                                    JSONObject dataJSON= jsonObj.getJSONObject("data");
                                    Message msg=Message.obtain();
                                    msg.obj=dataJSON;
                                    msg.what=SUCCESS;
                                    handler.sendMessage(msg);
                                }else{
                                    //号码错误
                                    Message msg=Message.obtain();
                                    msg.what=ERROR_PHONE;
                                    handler.sendMessage(msg);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message msg = Message.obtain();
                            msg.what = ERROR;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();
            }
        });
    }
}
