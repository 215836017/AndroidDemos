package com.cakes.demoprogressbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar p0, p1, p2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                p0.setProgress(timeCount);
                p1.setProgress(timeCount);
                p2.setProgress(timeCount);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        p0 = findViewById(R.id.ProgressBar_00);
        p1 = findViewById(R.id.ProgressBar_01);
        p2 = findViewById(R.id.ProgressBar_02);
    }


    private boolean isRunning = false;

    public void progressClick(View view) {
        if (view.getId() == R.id.button_start) {
            startRun();
        } else if (view.getId() == R.id.button_stop) {
            isRunning = false;
        }
    }

    private int timeCount = 0;

    public void startRun() {
        if (isRunning) {
            return;
        }
        isRunning = true;

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (isRunning) {

                    try {
                        sleep(1000);
                        timeCount++;
                        handler.sendEmptyMessage(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }
}
