package com.test.demobanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.test.demobanner.test1.ViewPagerActivity;
import com.test.demobanner.test2.RecyclerActivity;

/**
 * 参考链接：
 * https://blog.csdn.net/u014133119/article/details/80954317
 * https://blog.csdn.net/sinat_28864443/article/details/83501444
 */
public class AppMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Intent intent = new Intent();

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn_01) {
            intent.setClass(this, ViewPagerActivity.class);

        } else if (view.getId() == R.id.mainAct_btn_02) {
            intent.setClass(this, RecyclerActivity.class);

        }

        startActivity(intent);
    }
}
