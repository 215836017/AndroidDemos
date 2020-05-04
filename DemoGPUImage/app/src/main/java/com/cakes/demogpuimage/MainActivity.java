package com.cakes.demogpuimage;

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
        if (view.getId() == R.id.activity_main_btn_image) {
            intent.setClass(this, TestImageActivity.class);

        } else if (view.getId() == R.id.activity_main_btn_camera) {
            intent.setClass(this, TestCameraActivity.class);
        }

        startActivity(intent);
    }
}
