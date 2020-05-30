package com.cakes.demogpuimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import jp.co.cyberagent.android.gpuimage.GPUImage;

public class TestImageActivity extends AppCompatActivity {

    private final String TAG = "TestImageActivity";

    private FrameLayout layoutRoot;
    private ImageView imageView;
    private SeekBar seekBar;

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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_b);
        gpuImage.setImage(bitmap);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Bitmap bitmapResult = GPUImageUtil.getFilterByType(itemPosition, itemValue, gpuImage, progress);
            if (null != bitmapResult) {
                imageView.setImageBitmap(bitmapResult);
            } else {
                Toast.makeText(TestImageActivity.this, "未实现效果", Toast.LENGTH_LONG).show();
            }

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
            layoutRoot.setVisibility(View.GONE);
        }
    };
}
