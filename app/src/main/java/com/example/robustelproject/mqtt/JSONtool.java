package com.example.robustelproject.mqtt;

import org.json.JSONObject;

public class JSONtool {
    private static JSONObject parseJSONWithJSONObject(String jsonData){
        try{
            JSONObject jsonObject = new JSONObject(jsonData);
            return jsonObject;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static String getInfo(String jsonData, String key){
        JSONObject jsonObject = parseJSONWithJSONObject(jsonData);

        try{
            String info =  jsonObject.getString(ConstValue.KEY_REPORT);
            jsonObject = parseJSONWithJSONObject(info);
            info = jsonObject.getString(key);
            return info;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
