package com.example.demopermissions;

import android.Manifest;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionUtil implements ActivityCompat.OnRequestPermissionsResultCallback {

    private final String TAG = "PermissionUtil";

    private final int CODE_REQUEST_PERMISSION = 100;

    public void applyPermission(Activity activity, String[] permissions) {

        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_REQUEST_PERMISSION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }
}
