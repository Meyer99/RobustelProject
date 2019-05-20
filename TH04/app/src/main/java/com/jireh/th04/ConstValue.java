package com.jireh.th04;

import android.Manifest;

import java.util.ArrayList;

//存储app中的常量数
public class ConstValue {
    //用于Mqtt连接的常量
    public static String HOST = "ssl://g40udfd.mqtt.iot.gz.baidubce.com:1884";
    public static String TOPIC_SUBSCRIBE_SUCCESS = "$baidu/iot/shadow/myMonitor/update/accepted";
    public static int qos = 0;
    public static String clientid = "aaaa";
    public static String userName = "g40udfd/android";
    public static String passWord = "9rjvnudzj4ejrxxy";

    //用于存储温湿度的信息
    public static ArrayList temperatureList = new ArrayList();
    public static ArrayList humidityList = new ArrayList();

    //解析JSON的key值
    public final static String KEY_REPORT = "reported";
    public final static String KEY_TEMPERATURE = "temperature";
    public final static String KEY_HUMIDITY = "humidity";

    //各种权限的代号
    public static final int ALL_PERMISSIONS_REQUEST_CODE = 0;
    public static final int INTERNET_REQUEST_CODE = 1;
    public static final int ACCESS_NETWORK_STATE_REQUEST_CODE = 2;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 3;
    public static final int READ_PHONE_STATE_REQUEST_CODE = 4;

    public static final String[] permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
    };


}
