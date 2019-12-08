package com.example.demopermissions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 参考链接：
 * 1. https://www.jianshu.com/p/ccea3c2f9cfa
 * 2. https://www.cnblogs.com/diyishijian/p/5629545.html
 * 3. https://www.cnblogs.com/zhangqie/p/7562959.html
 * 4. https://www.jianshu.com/p/2fe4fb3e8ce0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Intent intent = new Intent();

    public void mainActBtnClick(View view) {
        switch (view.getId()) {
            case R.id.mainact_btn_one_permission:
                intent.setClass(this, OnePermissionActivity.class);
                break;

            case R.id.mainact_btn_multiple_permission:
                intent.setClass(this, MultiplePermissionsActivity.class);
                break;

            case R.id.mainact_btn_permission_callback:
                intent.setClass(this, PermissionCallbackActivity.class);
                break;
        }

        startActivity(intent);
    }
}
