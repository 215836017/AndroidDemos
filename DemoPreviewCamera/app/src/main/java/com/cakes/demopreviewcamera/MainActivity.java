package com.cakes.demopreviewcamera;

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

    public void mainActBtnClick(View view) {
        if (view.getId() == R.id.btn_SurfaceView) {
            intent.setClass(this, SurfaceViewActivity.class);

        } else if (view.getId() == R.id.btn_TextureView) {
            intent.setClass(this, TextureViewActivity.class);

        } else if (view.getId() == R.id.btn_GLSurfaceView) {
            intent.setClass(this, GLSurfaceViewActivity.class);

        }

        startActivity(intent);
    }
}
