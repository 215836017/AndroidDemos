package com.test.demoactivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    private final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thrid);
        LogUtil.w(TAG, "onCreate() -- 333");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.w(TAG, "onResume() -- 333");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.w(TAG, "onPause() -- 333");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.w(TAG, "onStop() -- 333");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.w(TAG, "onDestroy() -- 333");
    }

    public void btnClick3(View view) {
        if (view.getId() == R.id.act_3_btn_back) {
            finish();
        }
    }
}