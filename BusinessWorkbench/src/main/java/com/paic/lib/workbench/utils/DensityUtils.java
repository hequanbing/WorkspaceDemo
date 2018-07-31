package com.paic.lib.workbench.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 分辨率工具类
 *
 * @author renhui
 */
public class DensityUtils {

    /**
     * 获取屏幕宽度(推荐使用该方法)
     *
     * @param mActivity Activity
     * @return 宽度
     */
    public static int getScreenWidth(Activity mActivity) {
        if (mActivity == null) {
            return 0;
        }
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 获取屏幕高度(推荐使用该方法)
     *
     * @param mActivity Activity
     * @return 高度
     */
    public static int getScreenHeight(Activity mActivity) {
        if (mActivity == null) {
            return 0;
        }
        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }

    public static int calcOfProportionWidth(Context context,int realTotalWidth,int realWidth){
        return realWidth * getScreenWidth((Activity) context) / realTotalWidth;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px 的单位 转成为 dp(像素)
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context context
     * @param pxValue spValue
     * @return 文字大小
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context context
     * @param spValue spValue
     * @return 文字大小
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static final int VALID_VALUE_ZERO = 0;
    public static final float MIN_TEXT_SIZE = 5.0f;

    public static boolean effectiveValue(final int dpValue) {
        return VALID_VALUE_ZERO < dpValue;
    }

    public static boolean effectiveValue(final float spValue) {
        return MIN_TEXT_SIZE < spValue;
    }
}