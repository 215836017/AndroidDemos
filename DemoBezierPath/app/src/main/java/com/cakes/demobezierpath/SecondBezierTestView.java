package com.cakes.demobezierpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class SecondBezierTestView extends View {


    private int[] lineOneX = new int[9];
    private int[] lineOneY = new int[9];

    private Point[] lineTwo = new Point[9];

    private Path mPath;
    private Path mPath2;
    private Paint mPaintBezier;
    private Paint mPaintFlag;
    private Paint mPaintFlagText;

    public SecondBezierTestView(Context context) {
        this(context, null);
    }

    public SecondBezierTestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondBezierTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < lineTwo.length; i++) {
            lineTwo[i] = new Point(0, 0);
        }
    }

    int centerH = 0;
    int speed_x = 50;
    int intervarl_x = 150;
    int intervarl_y = 80;
    private boolean isAbove = false;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始时确定起点、终点和控制点的坐标
        centerH = h / 2;

        for (int i = 0; i < lineOneX.length; i++) {
            lineOneX[i] = intervarl_x * (i + 1);
            lineTwo[i].x = intervarl_x * (i + 1) + speed_x;
            if (i % 2 == 0) {
                lineOneY[i] = centerH;
                lineTwo[i].y = centerH;
            } else {
                if (isAbove) {
                    isAbove = false;
                    lineOneY[i] = centerH - intervarl_y;
                    lineTwo[i].y = centerH + intervarl_y;
                } else {
                    isAbove = true;
                    lineOneY[i] = centerH + intervarl_y;
                    lineTwo[i].y = centerH - intervarl_y;
                }
            }
        }

        mPath = new Path();
        mPath2 = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath2.reset();

        mPath.moveTo(lineOneX[0], lineOneY[0]);
        mPath2.moveTo(lineTwo[0].x, lineTwo[0].y);
        int j;
        for (int i = 1; i < lineOneY.length; i++) {
            j = i + 1;
            mPath.quadTo(lineOneX[i], lineOneY[i], lineOneX[j], lineOneY[j]);
            mPath2.quadTo(lineTwo[i].x, lineTwo[i].y, lineTwo[j].x, lineTwo[j].y);
            i++;
        }

        mPaintBezier.setColor(Color.GREEN);
        canvas.drawPath(mPath, mPaintBezier);

        mPaintBezier.setColor(Color.BLUE);
        canvas.drawPath(mPath2, mPaintBezier);
    }

    private Handler handler = new Handler();
}
