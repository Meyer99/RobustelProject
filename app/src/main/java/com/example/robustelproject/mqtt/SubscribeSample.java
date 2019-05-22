package com.example.robustelproject.mqtt;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONObject;

public class SubscribeSample {

    public SubscribeSample() throws MqttException {

        try {
            ConstValue.temperatureList.add(23);
            ConstValue.humidityList.add(23);

            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(ConstValue.HOST, ConstValue.clientid, new MemoryPersistence());
            Log.e("INFO: ", "##################1");
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            Log.e("INFO: ", "##################2");
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            Log.e("INFO: ", "##################3");
            // 设置连接的用户名
            options.setUserName(ConstValue.userName);
            Log.e("INFO: ", "##################4");
            // 设置连接的密码
            options.setPassword(ConstValue.passWord.toCharArray());
            Log.e("INFO: ", "##################5");
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            Log.e("INFO: ", "##################6");
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            Log.e("INFO: ", "##################7");
            // 设置回调函数
            client.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    Log.e("info","connectionLost");
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Log.e("info","topic:"+topic);
                    Log.e("info","Qos:"+message.getQos());

                    String messageJSON = new String(message.getPayload());
                    Log.e("info","message content:"+messageJSON);//new String(message.getPayload())

                    ConstValue.temperatureList.add(JSONtool.getInfo(messageJSON,ConstValue.KEY_TEMPERATURE));
                    Log.e("JSON: ",JSONtool.getInfo(messageJSON,ConstValue.KEY_TEMPERATURE));
                    ConstValue.humidityList.add(JSONtool.getInfo(messageJSON,ConstValue.KEY_HUMIDITY));
                    Log.e("JSON: ",JSONtool.getInfo(messageJSON,ConstValue.KEY_HUMIDITY));
                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.e("info","deliveryComplete---------"+ token.isComplete());
                }

            });
            client.connect(options);
            //订阅消息
            client.subscribe(ConstValue.TOPIC_SUBSCRIBE_SUCCESS, ConstValue.qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
