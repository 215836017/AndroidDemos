package com.example.demopermissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

//public class PermissionCallbackActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
public class PermissionCallbackActivity extends Activity {
    /**
     * 1. 如果继承的是Activity而没有实现OnRequestPermissionsResultCallback接口的话，不会回调onRequestPermissionsResult方法.
     * 2. 继承了AppCompatActivity就不用实现OnRequestPermissionsResultCallback接口了，是因为AppCompatActivity继承了
     * FragmentActivity，FragmentActivity已经实现了OnRequestPermissionsResultCallback接口。
     */
//    ActivityCompat.OnRequestPermissionsResultCallback callback = new ActivityCompat.OnRequestPermissionsResultCallback() {
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        }
//    };

    private final String TAG = "PermissionCallbackActivity";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_permission);
        context = this.getApplicationContext();
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn_writeFile) {
            applyMultiplePermissions();
        }
    }

    private String[] requestPermissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS};
    private final int CODE_REQUEST_PERMISSIONS = 0x10;

    private void applyMultiplePermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {
            ToastUtils.showToast(this, "已经申请了读取文件的权限");
        } else {
            ToastUtils.showToast(this, "no no 读取文件的权限");
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {
            ToastUtils.showToast(this, "已经申请了写入文件的权限");
        } else {
            ToastUtils.showToast(this, "no no 写入文件的权限");
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                == PERMISSION_GRANTED) {
            ToastUtils.showToast(this, "已经申请了读取联系人的权限");
        } else {
            ToastUtils.showToast(this, "no no 读取联系人的权限");
        }

        ActivityCompat.requestPermissions(this, requestPermissions, CODE_REQUEST_PERMISSIONS);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // todo 未实现接口也回调了， why？
        LogUtil.w(TAG, "onRequestPermissionsResult() -- permissions.len = " + permissions.length
                + ", grantResults.len = " + grantResults.length);
        for (int i = 0; i < grantResults.length; i++) {
            LogUtil.i(TAG, "onRequestPermissionsResult() grantResults[" + i + "] = " + grantResults[i]
                    + ", permissions[" + i + "] = " + permissions[i]);
        }
        if (requestCode == CODE_REQUEST_PERMISSIONS) {
            handleResult(permissions, grantResults);
        }
    }

    private void handleResult(String[] permissions, int[] grantResults) {

    }

}
