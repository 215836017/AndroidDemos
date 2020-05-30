package com.cakes.demogpuimage;

import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageExposureFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFalseColorFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageLevelsFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageMonochromeFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBDilationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageRGBFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageWhiteBalanceFilter;

public class GPUImageUtil {

    private static final String TAG = "GPUImageUtil";

    /*
        filterList.add("Saturation");
        filterList.add("Contrast");
        filterList.add("Brightness");
        filterList.add("Levels");
        filterList.add("Exposure");
        filterList.add("RGB");
        filterList.add("RGB Diation");
        filterList.add("Hue");
        filterList.add("White Balance");
        filterList.add("Monochrome");
        filterList.add("False Color");
        filterList.add("Sharpen");
        filterList.add("Transform Operation");
        filterList.add("Gamma");
        filterList.add("Highlights and Shadows");
        filterList.add("Haze");
        filterList.add("Sepia Tone");
        filterList.add("Color Inversion");
        filterList.add("Solarize");
        filterList.add("Vibrance");
        filterList.add("Luminance");
        filterList.add("Luminance Threshold");
        filterList.add("Pixellate");
        filterList.add("Halftone");
        filterList.add("Crosshatch");
        filterList.add("Sobel Edge Detection");
        filterList.add("Threshold Sobel EdgeDetection");
        filterList.add("Sketch Filter");
        filterList.add("Toon Filter");
        filterList.add("SmoothToon Filter");
        filterList.add("CGA Colorspace Filter");
        filterList.add("Posterize");
        filterList.add("Convolution 3x3");
        filterList.add("Emboss Filter");
        filterList.add("Laplacian");
        filterList.add("Chroma Keying");
        filterList.add("Kuwahara Filter");
        filterList.add("Vignette");
        filterList.add("Gaussian Blur");
        filterList.add("Box Blur");
        filterList.add("Bilateral Blur");
        filterList.add("Zoom Blur");
        filterList.add("Swirl Distortion");
        filterList.add("Bulge Distortion");
        filterList.add("Sphere Refraction");
        filterList.add("Glass Sphere Refraction");
        filterList.add("Dilation");
        filterList.add("Dissolve Blend");
        filterList.add("Chroma Key Blend");
        filterList.add("Add Blend");
        filterList.add("Divide Blend");
        filterList.add("Multiply Blend");
        filterList.add("Overlay Blend");
        filterList.add("Lighten Blend");
        filterList.add("Darken Blend");
        filterList.add("Color Burn Blend");
        filterList.add("Color Dodge Blend");
        filterList.add("Linear Burn Blend");
        filterList.add("Screen Blend");
        filterList.add("Difference Blend");
        filterList.add("Subtract Blend");
        filterList.add("Exclusion Blend");
        filterList.add("HardLight Blend");
        filterList.add("SoftLight Blend");
        filterList.add("Color Blend");
        filterList.add("Hue Blend");
        filterList.add("Saturation Blend");
        filterList.add("Luminosity Blend");
        filterList.add("Normal Blend");
        filterList.add("Source Over Blend");
        filterList.add("Alpha Blend");
        filterList.add("Non Maximum Suppression");
        filterList.add("Opacity");
        filterList.add("Weak Pixel Inclusion Filter");
        filterList.add("Color Matrix");
        filterList.add("Directional Sobel Edge Detection");
        filterList.add("Lookup");
        filterList.add("Tone Curve (*.acv files)");
     */
    public static Bitmap getFilterByType(int filterListPosition, String filterName, GPUImage gpuImage, int progress) {

        LogUtil.d(TAG, "getFilterByType() -- filterListPosition = " + filterListPosition
                + ", filterName = " + filterName);

        if (filterName.equals("Saturation")) {
            return getSaturationFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Contrast")) {
            return getContrastFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Brightness")) {
            return getBrightnessFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Levels")) {
            return getLevelsFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Exposure")) {
            return getExposureFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("RGB")) {
            return getRGBFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("RGB Diation")) {
            return getRGBDilationFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Hue")) {
            return getHueFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("White Balance")) {
            return getWhiteBalanceFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("Monochrome")) {
            return getMonochromeFilterWithProgress(gpuImage, progress);

        } else if (filterName.equals("False Color")) {
            return getFalseColorFilterWithProgress(gpuImage, progress);

        }

        return null;
    }

    /**
     * 设置饱和度的滤镜
     */
    private static Bitmap getSaturationFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageSaturationFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置对比度的滤镜
     */
    private static Bitmap getContrastFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageContrastFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置亮度的滤镜
     */
    private static Bitmap getBrightnessFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageBrightnessFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置色阶滤镜
     */
    private static Bitmap getLevelsFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageLevelsFilter());
        // todo GPUImageLevelsFilter还有更多的配置方法
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置曝光度滤镜
     */
    private static Bitmap getExposureFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageExposureFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置RGB滤镜
     */
    private static Bitmap getRGBFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageRGBFilter(progress, progress, progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置RGB扩展边缘模糊滤镜，有色彩
     */
    private static Bitmap getRGBDilationFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageRGBDilationFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置色度滤镜
     */
    private static Bitmap getHueFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageHueFilter(progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置白平横滤镜
     */
    private static Bitmap getWhiteBalanceFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageWhiteBalanceFilter(progress, progress));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置单色滤镜
     */
    private static Bitmap getMonochromeFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageMonochromeFilter(progress, new float[]{0.6f, 0.45f, 0.3f, 1.0f}));
        return gpuImage.getBitmapWithFilterApplied();
    }

    /**
     * 设置色彩替换（替换亮部和暗部色彩）滤镜
     */
    private static Bitmap getFalseColorFilterWithProgress(GPUImage gpuImage, int progress) {
        gpuImage.setFilter(new GPUImageFalseColorFilter(progress, progress, progress, progress, progress, progress));
        return gpuImage.getBitmapWithFilterApplied();
    }


}
