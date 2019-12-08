package com.example.demopermissions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/*
 * 申请有两种方式：https://blog.csdn.net/bilionera/article/details/75670016
 * 1. 程序启动后就申请全部的权限
 * 2. 程序启动后在用到的时候再去申请相应的权限
 *
 * Android 6.0 运行时权限处理完全解析: https://blog.csdn.net/lmj623565791/article/details/50709663
 *
 * 动态权限申请库： https://blog.csdn.net/xinpengfei521/article/details/78780840
 *                 https://blog.csdn.net/mj_air/article/details/84891450
 * 4. https://github.com/getActivity/XXPermissions
 *    https://github.com/MjCodeTinker/DynamicPermissionMaster
 */

public class OnePermissionActivity extends AppCompatActivity {

    private final String TAG = "OnePermissionActivity";
    private final int CODE_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_permission);

    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn_writeFile) {
            applyFilePermission();
        }
    }

    private void applyFilePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PERMISSION_GRANTED) {
            Toast.makeText(this, "已经申请了该权限", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "no no 该权限", Toast.LENGTH_LONG).show();
            // todo 第三个参数的范围 0 ~ 65535
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        // todo 如果是在Service 或者 BroadcastReceiver中申请权限呢？
        /*
        问题：由于ActivityCompat.requestPermissions()的第一个参数是Activity，那么如果在Service 或者 BroadcastReceiver中申请权限呢？
        几种思路：
        1. 在Service或BroadcastReceiver中创建一个没有界面的Activity
        2. 在启动Service或BroadcastReceiver前申请需要的权限，如果申请失败就不启动
        3. 可以考虑向service传入一个activity的对象，或者在service中获取activity的一个对象，亦或者使用一个activity的静态对象。另外，
        service建立连接的时候，可以使用一个ServiceConnection的onServiceConnected方法来完成权限的申请，即当服务建立连接时申请权限。 -- 需要验证是否能弹出权限框(网上查到可以申请访问但进不去要你同意才行)
         */

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.w(TAG, "onRequestPermissionsResult() -- permissions.len = " + permissions.length
                + ", grantResults.len = " + grantResults.length);
        for (int i = 0; i < grantResults.length; i++) {
            LogUtil.i(TAG, "onRequestPermissionsResult() grantResults[" + i + "] = " + grantResults[i]
                    + ", permissions[" + i + "] = " + permissions[i]);
        }

        switch (requestCode) {
            case CODE_REQUEST_WRITE_EXTERNAL_STORAGE:
                test(permissions, grantResults);
                break;

        }
    }

    private AlertDialog mDialog;
    private AlertDialog alertDialog;
    private static final int NOT_NOTICE = 2;//如果勾选了不再询问

    private void test(String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PERMISSION_GRANTED) {
                // 选择了“始终允许”
                Toast.makeText(this, "权限：" + permissions[i] + "申请成功", Toast.LENGTH_LONG).show();

            } else {   //选择禁止,
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i])) {  // 选择禁止, 但没有勾选不再询问

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("permission")
                            .setMessage("点击允许才可以使用我们的app哦")
                            .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    if (alertDialog != null && alertDialog.isShowing()) {
                                        alertDialog.dismiss();
                                    }
                                    ActivityCompat.requestPermissions(OnePermissionActivity.this,
                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            CODE_REQUEST_WRITE_EXTERNAL_STORAGE);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (null != mDialog && mDialog.isShowing()) {
                                mDialog.dismiss();
                            }
                        }
                    });
                    alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();

                } else { //用户选择了禁止, 并且勾选了不再询问

//                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                    builder.setTitle("permission")
//                            .setMessage("点击允许才可以使用我们的app哦")
//                            .setPositiveButton("去允许", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (null != mDialog && mDialog.isShowing()) {
//                                        mDialog.dismiss();
//                                    }
//                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                    Uri uri = Uri.fromParts("package", getPackageName(), null);//注意就是"package",不用改成自己的包名
//                                    intent.setData(uri);
//                                    startActivityForResult(intent, NOT_NOTICE);
//                                }
//                            })
//                            ;
//                    mDialog = builder.create();
//                    mDialog.setCanceledOnTouchOutside(false);
//                    mDialog.show();

                    Toast.makeText(OnePermissionActivity.this, "如果需要使用该功能请开启权限哦", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOT_NOTICE) {
            applyFilePermission();//由于不知道是否选择了允许所以需要再次判断
        }
    }
}
