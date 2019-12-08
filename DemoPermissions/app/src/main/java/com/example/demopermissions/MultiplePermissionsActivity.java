package com.example.demopermissions;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * 同时申请多个权限
 */
public class MultiplePermissionsActivity extends AppCompatActivity {

    private final String TAG = "MultiplePermissionsActivity";

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
        LogUtil.w(TAG, "onRequestPermissionsResult() -- permissions.len = " + permissions.length
                + ", grantResults.len = " + grantResults.length);
        for (int i = 0; i < grantResults.length; i++) {
            LogUtil.i(TAG, "onRequestPermissionsResult() grantResults[" + i + "] = " + grantResults[i]
                    + ", permissions[" + i + "] = " + permissions[i] + ", shouldShowAgain  = " +
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]));

        }
        if (requestCode == CODE_REQUEST_PERMISSIONS) {
            handleResult(permissions, grantResults);
        }

        /*
        1. 总是允许
       com.example.demopermisson I/MultiplePermissionsActivity: demo_permission onRequestPermissionsResult()
        grantResults[0] = 0, permissions[0] = android.permission.WRITE_EXTERNAL_STORAGE, shouldShowAgain  = false
        2. 禁止，但是没有勾选“不再询问”：
        com.example.demopermisson I/MultiplePermissionsActivity: demo_permission onRequestPermissionsResult()
         grantResults[0] = -1, permissions[0] = android.permission.WRITE_EXTERNAL_STORAGE, shouldShowAgain  = true
        3. 禁止，并且勾选了“不再询问”：
        com.example.demopermisson I/MultiplePermissionsActivity: demo_permission onRequestPermissionsResult()
         grantResults[1] = -1, permissions[1] = android.permission.READ_CONTACTS, shouldShowAgain  = false
         */

        /*
        同一组权限的申请：
        
         */
    }

    private void handleResult(String[] permissions, int[] grantResults) {
        // todo 如果只同意了部分权限呢???
    }

}
