package com.test.demoactivitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    private final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LogUtil.i(TAG, "onCreate() -- 222222222");
    }


    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume() -- 222222222");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause() -- 222222222");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop() -- 222222222");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy() -- 222222222");
    }

    public void btnClick2(View view) {
        if (view.getId() == R.id.act_2_btn_back) {
            finish();
        } else if (view.getId() == R.id.act_2_btn_next) {
            startActivity(new Intent(this, ThirdActivity.class));
        }
    }
}