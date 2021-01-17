package com.test.demoactivitylifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class MyApplication extends Application {

    private final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        initActivityLifecycleCallbacks();
    }

    /**
     * 在application里监听所以activity生命周期的回调
     */
    private void initActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                showActivityState(activity, "onActivityCreated");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                showActivityState(activity, "onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                showActivityState(activity, "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                showActivityState(activity, "onActivityPaused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                showActivityState(activity, "onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                showActivityState(activity, "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                showActivityState(activity, "onActivityDestroyed");
            }
        });
    }

    private void showActivityState(Activity activity, String state) {
        if (activity instanceof MainActivity) {
            LogUtil.e(TAG, "MainActivity 111  -- " + state);

        } else if (activity instanceof SecondActivity) {
            LogUtil.e(TAG, "SecondActivity 222  -- " + state);

        } else if (activity instanceof ThirdActivity) {
            LogUtil.e(TAG, "ThirdActivity 333  -- " + state);
        }
    }

    /*
    2020-11-17 14:19:20.257 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityCreated
2020-11-17 14:19:20.628 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onCreate() -- 111111
2020-11-17 14:19:20.633 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityStarted
2020-11-17 14:19:20.644 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityResumed
2020-11-17 14:19:20.645 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onResume() -- 111111
2020-11-17 14:19:31.044 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityPaused
2020-11-17 14:19:31.045 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onPause() -- 111111
2020-11-17 14:19:31.116 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityCreated
2020-11-17 14:19:31.311 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onCreate() -- 222222222
2020-11-17 14:19:31.316 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityStarted
2020-11-17 14:19:31.329 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityResumed
2020-11-17 14:19:31.330 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onResume() -- 222222222
2020-11-17 14:19:31.703 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivitySaveInstanceState
2020-11-17 14:19:31.706 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityStopped
2020-11-17 14:19:31.708 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onStop() -- 111111



2020-11-17 14:19:38.422 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityPaused
2020-11-17 14:19:38.423 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onPause() -- 222222222
2020-11-17 14:19:38.468 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityCreated
2020-11-17 14:19:38.570 6093-6093/com.test.demoactivitylifecycle W/ThirdActivity: life_log onCreate() -- 333
2020-11-17 14:19:38.574 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityStarted
2020-11-17 14:19:38.586 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityResumed
2020-11-17 14:19:38.588 6093-6093/com.test.demoactivitylifecycle W/ThirdActivity: life_log onResume() -- 333
2020-11-17 14:19:38.943 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivitySaveInstanceState
2020-11-17 14:19:38.949 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityStopped
2020-11-17 14:19:38.949 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onStop() -- 222222222




2020-11-17 14:19:43.220 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityPaused
2020-11-17 14:19:43.221 6093-6093/com.test.demoactivitylifecycle W/ThirdActivity: life_log onPause() -- 333
2020-11-17 14:19:43.262 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityStarted
2020-11-17 14:19:43.265 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityResumed
2020-11-17 14:19:43.266 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onResume() -- 222222222
2020-11-17 14:19:43.451 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityStopped
2020-11-17 14:19:43.452 6093-6093/com.test.demoactivitylifecycle W/ThirdActivity: life_log onStop() -- 333
2020-11-17 14:19:43.454 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log ThirdActivity 333  -- onActivityDestroyed
2020-11-17 14:19:43.459 6093-6093/com.test.demoactivitylifecycle W/ThirdActivity: life_log onDestroy() -- 333



2020-11-17 14:19:47.374 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityPaused
2020-11-17 14:19:47.374 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onPause() -- 222222222
2020-11-17 14:19:47.406 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityStarted
2020-11-17 14:19:47.408 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityResumed
2020-11-17 14:19:47.409 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onResume() -- 111111
2020-11-17 14:19:47.607 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityStopped
2020-11-17 14:19:47.608 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onStop() -- 222222222
2020-11-17 14:19:47.610 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log SecondActivity 222  -- onActivityDestroyed
2020-11-17 14:19:47.612 6093-6093/com.test.demoactivitylifecycle I/SecondActivity: life_log onDestroy() -- 222222222



2020-11-17 14:19:50.745 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityPaused
2020-11-17 14:19:50.746 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onPause() -- 111111
2020-11-17 14:19:51.109 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityStopped
2020-11-17 14:19:51.112 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onStop() -- 111111
2020-11-17 14:19:51.114 6093-6093/com.test.demoactivitylifecycle E/MyApplication: life_log MainActivity 111  -- onActivityDestroyed
2020-11-17 14:19:51.118 6093-6093/com.test.demoactivitylifecycle D/MainActivity: life_log onDestroy() -- 111111

     */
}
