package com.cakes.demoprogressbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity extends AppCompatActivity {

    private TextView textView;
    private SeekBar seekBar;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 10) {
                seekBar.setProgress(timeCount);
                textView.setText("timeCount = " + timeCount);

            } else if (msg.what == 11) {
                textView.setText("timeCount = " + timeCount);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);

        textView = findViewById(R.id.textShowProgress);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeCount = progress;
                handler.sendEmptyMessage(11);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private boolean isRunning = false;

    public void seekbarBtnClick(View view) {
        if (view.getId() == R.id.seekbar_start) {
            startRun();

        } else if (view.getId() == R.id.seekbar_stop) {
            isRunning = false;
        }
    }

    private int timeCount = 0;

    private void startRun() {
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
