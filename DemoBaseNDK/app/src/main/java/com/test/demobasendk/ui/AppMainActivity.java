package com.test.demobasendk.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.test.demobasendk.LogUtil;
import com.test.demobasendk.R;
import com.test.demobasendk.jni.JniUtil;

public class AppMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final String tag = "AppMainActivity.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        JniUtil.setJniLogLevel(JniUtil.JniLogLevel.LOG_LEVEL_ALL);  // 设置Jni中的log打印
    }

    private void initView() {
        ListView listView = findViewById(R.id.mainAct_listView);
        ListAdapter adapter = new ListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        callMethod(position);
    }

    private void callMethod(int position) {
        showToast("pos = " + position);
        switch (position) {
            case 0:
                showToast("go to next Activity");
                break;
            case 1:
                LogUtil.i(tag, "setInt = " + JniUtil.setInt(123));
                break;

            case 2:
                LogUtil.i(tag, "setIntArray = " + JniUtil.setIntArray(new int[]{123, 456})[0]);
                break;

            case 3:
                LogUtil.i(tag, "setLong = " + JniUtil.setLong(System.currentTimeMillis()));
                break;

            case 4:
                LogUtil.i(tag, "setLongArray = " + JniUtil.setLongArray(new long[]{System.currentTimeMillis(), System.currentTimeMillis() - 1000}));
                break;

            case 5:
                LogUtil.i(tag, "setFloat = " + JniUtil.setFloat(12.34f));
                break;

            case 6:
                LogUtil.i(tag, "setFloatArray = " + JniUtil.setFloatArray(new float[]{12.34f, 0.123f})[0]);
                break;

            case 7:
                LogUtil.i(tag, "setFloat = " + JniUtil.setDouble(12.000034));
                break;

            case 8:
                LogUtil.i(tag, "setDoubleArray = " + JniUtil.setDoubleArray(new double[]{12.000034, 0.11112222})[0]);
                break;

            case 9:
                LogUtil.i(tag, "setFloat = " + JniUtil.setBoolean(true));
                break;

            case 10:
                boolean[] b = JniUtil.setBooleanArray(new boolean[]{true, false});
                if (null == b) {
                    LogUtil.i(tag, "setBooleanArray() -- return is null");
                } else {
                    LogUtil.i(tag, "setBooleanArray() -- return is not null");
                }
                break;

            case 11:
                LogUtil.i(tag, "setFloat = " + JniUtil.setString("test_String"));
                break;

            case 12:
                LogUtil.i(tag, "setDoubleArray = " + JniUtil.setStringArray(new String[]{"NDK", "JNI", "Android"})[2]);
                break;

            case 13:
                JniUtil.startNewThread();
                break;

            case 14:
                break;
            case 15:
                break;
            case 16:
                break;
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        LogUtil.d(tag, msg);
    }
}
