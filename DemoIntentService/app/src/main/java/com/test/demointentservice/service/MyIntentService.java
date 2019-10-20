package com.test.demointentservice.service;

import android.app.IntentService;
import android.content.Intent;

public class MyIntentService extends IntentService {

    public static final String ACTION_FOO = "com.test.demointentservice.action.FOO";
    public static final String ACTION_BAZ = "com.test.demointentservice.action.BAZ";
    public static final String ACTION_QWE = "com.test.demointentservice.action.QWE";

    private String testStr;

    public MyIntentService() {
        super("MyIntentService");

        testStr = "testStr123";
        System.out.println("MyIntentService() start, testStr = " + testStr);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MyIntentService.java --- onCreate() ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        System.out.println("MyIntentService.java --- onStart() ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("MyIntentService.java --- onStartCommand() ");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                testStr = "testStr123_FOO";
                System.out.println("MyIntentService.java --- ACTION_FOO, testStr = " + testStr);

            } else if (ACTION_BAZ.equals(action)) {
                testStr = "testStr123_BAZ";
                System.out.println("MyIntentService.java --- ACTION_BAZ, testStr = " + testStr);

            } else if (ACTION_QWE.equals(action)) {
//                testStr = "testStr123_QWE";
                System.out.println("MyIntentService.java --- ACTION_QWE, testStr = " + testStr);
                // MyIntentService.java --- ACTION_QWE, testStr = testStr123
            }
        }
    }

    /*
    根据声明周期及测试字符串testStr的打印结果可知：
    用startService(Intent)每次调用都会新建一个IntentService的实例。
     */
}
