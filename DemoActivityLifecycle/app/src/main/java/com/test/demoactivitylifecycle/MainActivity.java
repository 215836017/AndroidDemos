package com.test.demoactivitylifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 10) {
                ActivityUtil.getPackageAndActivityName(MainActivity.this);
                handler.sendEmptyMessageDelayed(10, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_leave_notify);
        LogUtil.d(TAG, "onCreate() -- 111111");

//        handler.sendEmptyMessageDelayed(10, 1000);

        testJson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume() -- 111111");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause() -- 111111");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop() -- 111111");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy() -- 111111");
    }

    public void btnClick1(View view) {
        if (view.getId() == R.id.act_1_btn_back) {
            finish();
        } else if (view.getId() == R.id.act_1_btn_next) {
            startActivity(new Intent(this, SecondActivity.class));
        }
    }

    private void testJson() {
        LogUtil.d(TAG, "testJson()  start......");
        String json = "{\"RealtimePressure\":{\"pressure\":-1},\"isSOS\":false,\"enable\":false}";

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject queryControlCfg = jsonObject.getJSONObject("RealtimePressure");
            LogUtil.d(TAG, "testJson() -- 0000 -- queryControlCfg = " + queryControlCfg);
            boolean pressureIsNull = queryControlCfg.isNull("pressure");
            int pressure = queryControlCfg.getInt("pressure");
            LogUtil.d(TAG, "testJson() -- 222222 -- pressureIsNull = " + pressureIsNull + ", pressure = " + pressure);

            boolean enableIsNull = jsonObject.isNull("enable");
            boolean enable = jsonObject.getBoolean("enable");
            LogUtil.d(TAG, "testJson() -- 222222 -- enableIsNull = " + enableIsNull + ", enable = " + enable);
        } catch (JSONException e) {
            LogUtil.e(TAG, "testJson()  error = " + e.getMessage());
            e.printStackTrace();
        }
    }
}