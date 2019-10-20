package com.test.demointentservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.test.demointentservice.service.MyIntentService;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        System.out.println("onReceive.java -- action = " + action);
        Intent mIntent = new Intent(context, MyIntentService.class);
        if (action.equals(MyIntentService.ACTION_BAZ)) {
            mIntent.setAction(MyIntentService.ACTION_BAZ);
        } else if (action.equals(MyIntentService.ACTION_FOO)) {
            mIntent.setAction(MyIntentService.ACTION_FOO);
        } else if (action.equals(MyIntentService.ACTION_QWE)) {
            mIntent.setAction(MyIntentService.ACTION_QWE);
        }
        context.startService(mIntent);
    }
}
