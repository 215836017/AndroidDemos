package com.test.demoimageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class AppMainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;

    private final int[] imageRes = {
            R.drawable.my_image_01,
            R.drawable.my_image_02,
            R.drawable.my_image_03,
            R.drawable.my_image_04,
            R.drawable.my_image_05,
            R.drawable.my_image_06
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        imageSwitcher = findViewById(R.id.mainAct_imageSwitcher);

        //通过代码设定从左缓进，从右换出的效果。
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new ImageView(AppMainActivity.this);
            }
        });

        index = 0;
        imageSwitcher.setImageResource(imageRes[index]);

        findViewById(R.id.mainAct_btn_pre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(true);
            }
        });

        findViewById(R.id.mainAct_btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(false);
            }
        });
    }

    private int index = 0;

    private void setImage(boolean isNext) {
        if (!isNext) {
            if (index == 0) {
                index = imageRes.length - 1;
            }
            imageSwitcher.setImageResource(imageRes[index]);
            index--;
        } else {
            if (index == imageRes.length - 1) {
                index = 0;
            }
            imageSwitcher.setImageResource(imageRes[index]);
            index++;
        }
    }
}
