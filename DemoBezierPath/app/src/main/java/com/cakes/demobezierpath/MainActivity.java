package com.cakes.demobezierpath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Intent intent = new Intent();

    public void mainBtnClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn_sinWave:
                intent.setClass(this, SinWaveActivity.class);
                break;

            case R.id.main_btn_test_1:
                intent.setClass(this, TestOneActivity.class);
                break;

            case R.id.main_btn_second_bezier:
                intent.setClass(this, SecondBezierActivity.class);
                break;

            case R.id.main_btn_second_bezier_test:
                intent.setClass(this, SecondBezierTestActivity.class);
                break;

        }

        startActivity(intent);
    }
}
