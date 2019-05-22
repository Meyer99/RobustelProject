package com.example.robustelproject.mqtt;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionTool {
    public static boolean requestPermission(Activity activity, int permissionCODE){
        switch(permissionCODE){
            case ConstValue.ALL_PERMISSIONS_REQUEST_CODE:
                all_permission_request(activity);
                break;
        }

        return true;
    }

    private static void all_permission_request(Activity activity){
        List<String> permissionList = new ArrayList<>();

        for(String permission : ConstValue.permissions){
            if (ContextCompat.checkSelfPermission(activity, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if(!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(activity,
                    permissionList.toArray(new String[permissionList.size()]),
                    ConstValue.ALL_PERMISSIONS_REQUEST_CODE);
        }
    }
}
