package com.cakes.demobezierpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class SinWaveTestOneView extends View {
    private PaintFlagsDrawFilter mDrawFilter;
    private Paint mWavePaint;
    private float mOffset1 = 0.0f;
    private float mOffset2 = 0.0f;
    private float mSpeed1 = 0.05f;
    private float mSpeed2 = 0.07f;

    public SinWaveTestOneView(Context context) {
        super(context);
        init();
    }

    public SinWaveTestOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SinWaveTestOneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        // 初始绘制波纹的画笔
        mWavePaint = new Paint();
        // 去除画笔锯齿
        mWavePaint.setAntiAlias(true);
        // 设置风格为实线
        mWavePaint.setStyle(Paint.Style.FILL);
        mWavePaint.setColor(Color.RED);
        mDrawFilter = new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    private int increase = 1;
    private Random random = new Random();

    private float fontY, endY = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 从canvas层面去除绘制时锯齿

        increase = random.nextInt(10) + 1;
        canvas.setDrawFilter(mDrawFilter);
        increase = random.nextInt(300);
        for (int i = 0; i < getWidth(); i++) {

            // y = A * sin( wx + b) + h ; A： 浪高； w：周期；b：初相；
            endY = (float) (increase * Math.sin(2 * Math.PI / getWidth() * i + mOffset1) + 300);
            //画第一条波浪

            if (i > 0) {
                canvas.drawLine(i - 1, fontY, i, endY, mWavePaint);
            }
            fontY = endY;

            //画第二条波浪
//            float endY2 = (float) (increase * Math.sin(2 * Math.PI / getWidth() * i + mOffset2) + 300);
//            mWavePaint.setColor(Color.RED);
            //  canvas.drawLine(i, 600, i, endY2, mWavePaint);
        }

        if (mOffset1 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset1 = 0;
        }
        mOffset1 += mSpeed1;

        if (mOffset2 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset2 = 0;
        }
        mOffset2 += mSpeed2;
        //刷新

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 100);

    }

    private Handler handler = new Handler() {
    };
}