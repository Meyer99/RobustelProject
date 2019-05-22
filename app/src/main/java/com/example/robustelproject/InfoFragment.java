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
    private TextView tvTime;
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
        new TimeThread().start();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(),"温度: " +
                      ConstValue.temperatureList.get(ConstValue.temperatureList.size()-1) +"//" +
                      "湿度: "+ConstValue.humidityList.get(ConstValue.humidityList.size()-1) ,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public class TimeThread extends Thread
    {
        @Override
        public void run ()
        {
            do {
                try
                {
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
