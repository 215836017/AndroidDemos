package com.example.demopermissions;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    /*** Toast对象 */
    private static Toast toast = null;
    /*** 之前最后一次显示的内容 */
    private static String lastMsg;
    /*** 之前最后一次的时间 */
    private static long lastTime = 0;
    /*** 最新时间 */
    private static long nowTime = 0;

    public static void showToast(Context context, String message, int showTime) {

        if (showTime <= 1000) {
            showTime = Toast.LENGTH_LONG;
        }
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), message, showTime);
            toast.show();
            lastTime = System.currentTimeMillis();
        } else {
            nowTime = System.currentTimeMillis();
            if (message.equals(lastMsg)) {
                if (nowTime - lastTime > showTime) {
                    toast.show();
                }
            } else {
                lastMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        lastTime = nowTime;
    }

    public static void showToast(Context context, String message) {
        LogUtil.i("ToastUtils", "showToast() -- message = " + message);
        showToast(context, message, 0);
    }

    public static void cancelToast() {
        if (null != toast) {
            toast.cancel();
        }
    }
}