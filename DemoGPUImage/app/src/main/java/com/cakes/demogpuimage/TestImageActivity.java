package com.cakes.demogpuimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;

public class TestImageActivity extends AppCompatActivity {

    private final String TAG = "TestImageActivity";

    private LinearLayout layoutRoot;
    private ImageView imageView;
    private SeekBar seekBar;

    private Bitmap bitmap;
    private GPUImage gpuImage;
    private FragFilterList fragFilterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);

        initViews();
        initGpuImage();

//        gpuImage.deleteImage();

        seekBar.setMax(12);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        fragFilterList = new FragFilterList();
        fragFilterList.setListenerInFragment(onListenerInFragment);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_image_layout_root, fragFilterList)
                .commit();
    }

    private void initViews() {
        layoutRoot = findViewById(R.id.activity_image_layout_root);
        imageView = findViewById(R.id.image_src);
        seekBar = findViewById(R.id.seekBar);
    }

    private void initGpuImage() {
        gpuImage = new GPUImage(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_b);
        gpuImage.setImage(bitmap);
    }

    //方法的定义，定义一个有参数，有返回值的方法，参数接收SeekBar传过来的progress，返回值为bitmap
    public Bitmap getGPUImageWithBar(int progress) {

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

    private int itemPosition;
    private String itemValue;
    private FragFilterList.OnListenerInFragment onListenerInFragment = new FragFilterList.OnListenerInFragment() {
        @Override
        public void onItemClicked(int pos, String value) {
            itemPosition = pos;
            itemValue = value;

        }
    };
}
