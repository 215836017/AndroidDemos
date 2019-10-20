package com.test.demohandlerthread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class AppMainActivity extends AppCompatActivity {

    private TextView textView1, textView2;

    private Handler mainHandler;
    private Handler workHandler;
    private HandlerThread handlerThread;

    private int count = 0;
    private final int MSG_TEST1 = 0x10;
    private final int MSG_TEST2 = 0x11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.mainAct_text1);
        textView2 = findViewById(R.id.mainAct_text2);

        initHandlers();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 如果不需要在“后台”更新，则在这里释放。如果需要，则在onDestory中释放
        workHandler.removeMessages(MSG_TEST2);
        if (null != handlerThread) {
            handlerThread.quitSafely();
            handlerThread = null;
        }
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn1) {
            workHandler.sendEmptyMessage(MSG_TEST1);

        } else if (view.getId() == R.id.mainAct_btn_start) {
            workHandler.sendEmptyMessageDelayed(MSG_TEST2, 1000);

        } else if (view.getId() == R.id.mainAct_btn_stop) {
            workHandler.removeMessages(MSG_TEST2);
        }
    }

    private void initHandlers() {
        mainHandler = new Handler();

        handlerThread = new HandlerThread("mHandlerThread");
        handlerThread.start();  // 也不一定是刚进入界面就开启子线程

        workHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case MSG_TEST1:

                        try {
                            // workHandler属于子线程，所以可以直接在这里sleep, 验证方法：直接在这里更新UI会报错
                            Thread.sleep(3000);
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView1.setText("延时3秒后更新文本");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;

                    case MSG_TEST2:
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                count++;
                                textView2.setText("timeCount: " + count);
                                workHandler.sendEmptyMessageDelayed(MSG_TEST2, 1000);
                            }
                        });
                        break;
                }

            }
        };
    }
}
