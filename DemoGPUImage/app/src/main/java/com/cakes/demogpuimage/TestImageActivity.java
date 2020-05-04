package com.cakes.demogpuimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;

public class TestImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private SeekBar seekBar;

    private GPUImage gpuImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);

        imageView = findViewById(R.id.image_src);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(12);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    //方法的定义，定义一个有参数，有返回值的方法，参数接收SeekBar传过来的progress，返回值为bitmap
    public Bitmap getGPUImageWithBar(int progress) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_b);

        gpuImage = new GPUImage(this);
        gpuImage.setImage(bitmap);
        //设置饱和度的滤镜
        gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        bitmap = gpuImage.getBitmapWithFilterApplied();
        return bitmap;
    }


    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            imageView.setImageBitmap(getGPUImageWithBar(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
