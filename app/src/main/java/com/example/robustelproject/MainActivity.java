package com.example.robustelproject;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.robustelproject.mqtt.ConstValue;
import com.example.robustelproject.mqtt.PermissionTool;
import com.example.robustelproject.mqtt.SubscribeSample;
import com.example.robustelproject.ui.main.SectionsPagerAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;


public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionTool.requestPermission(this, ConstValue.ALL_PERMISSIONS_REQUEST_CODE);
        /*TelephonyManager tm = (TelephonyManager) this.getSystemService(getApplication().TELEPHONY_SERVICE);
        try {
            ConstValue.clientid = tm.getLine1Number();
        } catch (SecurityException e) {
            Toast.makeText(this, "未获得相关权限，,当前为默认设备名，还请授予权限",Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }*/



        ConstValue.clientid = getRandomString(10);


        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        try{
            SubscribeSample subscribeSample = new SubscribeSample();
        }catch(Exception e){
            e.printStackTrace();
        }


        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                int currentItem = viewPager.getCurrentItem();
//                switch (currentItem)
//                {
//                    case 0:
//                        Toast.makeText(MainActivity.this,
//                                "刷新成功", Toast.LENGTH_SHORT).show();
//                        break;
//
//                    case 1:
//                        break;
//
//                    default:
//                        break;
//                }
//
//            }
//        });
    }

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}