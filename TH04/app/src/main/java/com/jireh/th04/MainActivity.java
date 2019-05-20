package com.jireh.th04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //申请权限
        PermissionTool.requestPermission(this,ConstValue.ALL_PERMISSIONS_REQUEST_CODE);

        TelephonyManager tm = (TelephonyManager) this.getSystemService(getApplication().TELEPHONY_SERVICE);
        try {
            ConstValue.clientid = tm.getLine1Number();
        } catch (SecurityException e) {
            Toast.makeText(this, "未获得相关权限，还请授予权限",Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }

        final TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (status == 0) {
                        SubscribeSample subscribeSample = new SubscribeSample();
                        status = 1;
                        Toast.makeText(getBaseContext(), "已成功连接，请稍等重试", Toast.LENGTH_LONG).show();
                    } else if (ConstValue.temperatureList.isEmpty()) {
                        Toast.makeText(getBaseContext(), "正在等待数据传入，请稍后重试", Toast.LENGTH_LONG).show();
                    } else {
                        textView.append("温度：" +
                                ConstValue.temperatureList.get(ConstValue.temperatureList.size() - 1) +
                                "湿度：" +
                                ConstValue.humidityList.get(ConstValue.humidityList.size() - 1) + "\n");
                    }
                } catch (Exception e) {

                }
            }
        });


    }
}
