package com.cakes.demogpuimage;

import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;

public class GPUImageUtil {

    public static void getSaturationFilterWithProgress(GPUImage gpuImage, Bitmap bitmap, int progress) {
        //设置饱和度的滤镜
        gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        bitmap = gpuImage.getBitmapWithFilterApplied();
    }
}
