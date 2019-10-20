package com.test.demointentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.demointentservice.service.MyIntentService;
import com.test.demointentservice.service.MyService;

public class AppMainActivity extends AppCompatActivity {

    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // startSendBroadcast();
        startTestService();
    }

    private void startSendBroadcast() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    intent.setAction(MyIntentService.ACTION_BAZ);
                    sendBroadcast(intent);

                    sleep(4000);

                    intent.setAction(MyIntentService.ACTION_FOO);
                    sendBroadcast(intent);

                    sleep(1000);
                    intent.setAction(MyIntentService.ACTION_QWE);
                    sendBroadcast(intent);
                } catch (Exception e) {
                }
            }
        }.start();
    }

    private void startTestService() {
        final Intent intent2Service = new Intent(this, MyService.class);
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    stopService(intent2Service);

                    sleep(2000);

                    startService(intent2Service);

                    sleep(3000);

                    stopService(intent2Service);
                } catch (Exception e) {
                }

            }
        }.start();
    }
}
