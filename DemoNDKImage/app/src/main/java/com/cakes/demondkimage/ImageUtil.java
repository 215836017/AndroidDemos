package com.cakes.demondkimage;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ImageUtil {

    private static final float BRIGHTNESS = 0.2f;

    public static Bitmap getBitmap(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565); // 创建一张“白纸”

        // 开始处理像素点
        int a, r, g, b;
        int ri, gi, bi;
        // 调节亮度
        int bab = (int) (255 * BRIGHTNESS);

        // 遍历像素点
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // 获取每一个像素点的颜色值
                int color = bitmap.getPixel(x, y);
                a = Color.alpha(color);
                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);

                // 处理：图像变黑
                ri = r - bab;
                gi = g - bab;
                bi = b - bab;

                // 边界检测，防止过头
                r = ri > 255 ? 255 : (ri < 0 ? 0 : ri);
                g = gi > 255 ? 255 : (gi < 0 ? 0 : gi);
                b = bi > 255 ? 255 : (bi < 0 ? 0 : bi);

                resultBitmap.setPixel(x, y, Color.argb(a, r, g, b));
            }
        }

        return resultBitmap;
    }
}
