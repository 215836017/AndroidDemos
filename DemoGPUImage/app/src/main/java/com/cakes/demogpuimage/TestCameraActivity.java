package com.cakes.demogpuimage;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class TestCameraActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private GPUImageView gpuImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_camera);

        surfaceView = findViewById(R.id.surface_view);
        gpuImageView = findViewById(R.id.gpu_image_view);

        initCamera();
    }

    private int preWidth;
    private int preHeight;

    private void initCamera() {
        final Camera camera = Camera.open();
        if (null == camera) {
            return;
        }

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Camera.Parameters parameters = camera.getParameters();
                preWidth = parameters.getPreviewSize().width;
                preHeight = parameters.getPreviewSize().height;
                try {
                    camera.setPreviewDisplay(holder);
                    camera.setPreviewCallback(previewCallback);
                    camera.startPreview();
                } catch (Exception e) {
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


    }


    private Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {

            gpuImageView.updatePreviewFrame(data, preWidth, preHeight);
        }
    };
}
