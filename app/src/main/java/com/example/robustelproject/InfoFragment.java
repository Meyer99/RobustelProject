package com.example.robustelproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robustelproject.mqtt.ConstValue;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment
{
    private static final int msgKey1 = 1;
    private static final int msgKey2 = 2;
    private TextView tvTime, tvTemperature, tvHumidity;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("M月d日  kk:mm:ss", sysTime);
                    tvTime.setText(sysTimeStr);
                    break;
                case msgKey2:
                    if (ConstValue.temperatureList.size() == 0){
                        //Toast.makeText(getContext(), "等待数据传入，请稍后再试1",Toast.LENGTH_SHORT).show();
                    }else
                    {
                       // Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                        tvTemperature.setText((String) ConstValue.temperatureList.get(ConstValue.temperatureList.size() - 1)
                                + " °C");
                        tvHumidity.setText((String) ConstValue.humidityList.get(ConstValue.humidityList.size()-1) + " %");}
                    break;
                default:
                    break;
            }
        }
    };

    public InfoFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        tvTime = (TextView) view.findViewById(R.id.tvTime);
        tvTemperature = (TextView) view.findViewById(R.id.tvTemperature);
        tvHumidity = (TextView) view.findViewById(R.id.tvHumidity);
        if (ConstValue.temperatureList.size() > 0)
        {
            tvTemperature.setText((String) ConstValue.temperatureList.get(ConstValue.temperatureList.size() - 1)
                    + " °C");
            tvHumidity.setText((String) ConstValue.humidityList.get(ConstValue.humidityList.size()-1) + " %");
        }
        new TimeThread().start();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (ConstValue.temperatureList.size() == 0){
                    Toast.makeText(getContext(), "等待数据传入，请稍后再试",Toast.LENGTH_SHORT).show();
                }else
                {
                Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                tvTemperature.setText((String) ConstValue.temperatureList.get(ConstValue.temperatureList.size() - 1)
                + " °C");
                tvHumidity.setText((String) ConstValue.humidityList.get(ConstValue.humidityList.size()-1) + " %");
                /*Toast.makeText(getContext(),"温度: " +
                      ConstValue.temperatureList.get(ConstValue.temperatureList.size()-1) +"//" +
                      "湿度: "+ConstValue.humidityList.get(ConstValue.humidityList.size()-1) ,Toast.LENGTH_SHORT).show();*/
            }
            }
        });

        return view;
    }

    public class TimeThread extends Thread
    {
        @Override
        public void run ()
        {
            int count = 0;
            do {
                try
                {
                    count ++;
                    if (count % 10 == 0)
                    {
                        Message msg = new Message();
                        msg.what = msgKey2;
                        mHandler.sendMessage(msg);
                    }
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            } while(true);
        }
    }

}
