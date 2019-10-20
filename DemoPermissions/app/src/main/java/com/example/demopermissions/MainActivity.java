package com.example.demopermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考链接：
 * 1. https://www.jianshu.com/p/ccea3c2f9cfa
 * 2. https://www.cnblogs.com/diyishijian/p/5629545.html
 * 3. https://www.cnblogs.com/zhangqie/p/7562959.html
 * 4. https://www.jianshu.com/p/2fe4fb3e8ce0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String tag = "AppMainActivity.java";

    private final int MY_PERMISSION_REQ_PHONE = 0x11;
    private final int MY_PERMISSION_REQ_CAMERA = 0x12;

    private final String[] permissions = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE
    };

    private Button btnPerOne, btnPerMulti, btnCamera, btnPhone, btnSms, btnStorage;

    private List<String> rejectPermissions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSION_REQ_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("apply is OK");
            } else {
                showToast("apply is rejected");
            }
        } else if (requestCode == MY_PERMISSION_REQ_CAMERA) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequest = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequest) {
                        showToast("permission is not apply");
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void init() {
        btnPerOne = findViewById(R.id.mainAct_btn_permission_one);
        btnPerMulti = findViewById(R.id.mainAct_btn_permission_multi);
        btnCamera = findViewById(R.id.mainAct_btn_camera);
        btnPhone = findViewById(R.id.mainAct_btn_phone);
        btnSms = findViewById(R.id.mainAct_btn_sms);
        btnStorage = findViewById(R.id.mainAct_btn_storage);

        btnPerOne.setOnClickListener(this);
        btnPerMulti.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnSms.setOnClickListener(this);
        btnStorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainAct_btn_permission_one:
                // 单个授权
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager
                            .PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSION_REQ_PHONE);
                    } else {
                        showToast("CALL_PHONE权限已申请！");
                    }
                }
                break;

            case R.id.mainAct_btn_permission_multi:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    rejectPermissions.clear();
                    for (int i = 0; i < permissions.length; i++) {
                        if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                                PackageManager.PERMISSION_GRANTED) {
                            rejectPermissions.add(permissions[i]);
                        }
                    }
                    if (rejectPermissions.isEmpty()) {
                        showToast("需要的多个都已经授权了");
                    } else {
                        String[] perms = rejectPermissions.toArray(new String[rejectPermissions.size()]);
                        ActivityCompat.requestPermissions(this, perms, MY_PERMISSION_REQ_CAMERA);
                    }
                }
                break;

            case R.id.mainAct_btn_camera:

                break;

            case R.id.mainAct_btn_phone:

                break;

            case R.id.mainAct_btn_sms:

                break;

            case R.id.mainAct_btn_storage:

                break;

            default:
                break;

        }
    }

    private void showToast(String msg) {
        Log.d(tag, msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
