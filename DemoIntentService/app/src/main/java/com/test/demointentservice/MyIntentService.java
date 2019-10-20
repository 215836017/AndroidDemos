package com.test.demointentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 详解：https://blog.csdn.net/VNanyesheshou/article/details/75125909
 * 坑1：https://blog.csdn.net/richardli1228/article/details/46413351
 */
public class MyIntentService extends IntentService {


    private int count1 = 0;
    private int count2 = 0;

    public MyIntentService() {
        super("aaa");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyIntentService.java", "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService.java", "onDestroy()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService.java", "onHandleIntent()");
        int key = intent.getIntExtra("key", 0);
        switch (key) {
            case 1:
                while (count1 < 20) {
                    count1++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("MyIntentService.java", "111111111111111111111");
                }
                break;

            case 2:
                while (count2 <= 18) {
                    count2++;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("MyIntentService.java", "222222222222222222222222222");
                }
                break;
        }
        /**
         * 运行结果：
         * 先执行1的流程，等1中的20秒执行完成后，会再次打印onHandleIntent()，然后执行2中的18秒。
         */
        /*
07-30 14:33:40.560 13553-13553/com.test.demointentservice D/MyIntentService.java: onCreate()
07-30 14:33:40.560 13553-13567/com.test.demointentservice D/MyIntentService.java: onHandleIntent()
07-30 14:33:41.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:42.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:43.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:44.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:45.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:46.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:47.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:48.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:49.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:50.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:51.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:52.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:53.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:54.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:55.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:56.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:57.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:58.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:33:59.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:34:00.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 111111111111111111111
07-30 14:34:00.580 13553-13567/com.test.demointentservice D/MyIntentService.java: onHandleIntent()
07-30 14:34:02.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:04.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:06.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:08.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:10.570 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:12.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:14.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:16.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:18.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:20.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:22.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:24.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:26.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:28.590 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:30.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:32.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:34.590 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:36.580 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:38.590 13553-13567/com.test.demointentservice D/MyIntentService.java: 222222222222222222222222222
07-30 14:34:38.590 13553-13553/com.test.demointentservice D/MyIntentService.java: onDestroy()

         */
    }
}
