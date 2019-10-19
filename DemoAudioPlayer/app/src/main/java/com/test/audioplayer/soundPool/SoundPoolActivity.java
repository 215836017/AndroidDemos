package com.test.audioplayer.soundPool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.audioplayer.R;

/**
 * 使用SoundPool播放音效，不需要任何权限。
 */
public class SoundPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn01, btn02, btn03, btnStop;

    private SoundPoolUtils soundPoolUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        init();
    }

    private void init() {
        soundPoolUtils = new SoundPoolUtils(this);

        btn01 = findViewById(R.id.soundPoolAct_btn_play_01);
        btn02 = findViewById(R.id.soundPoolAct_btn_play_02);
        btn03 = findViewById(R.id.soundPoolAct_btn_play_03);
        btnStop = findViewById(R.id.soundPoolAct_btn_stop);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.soundPoolAct_btn_play_01:
                soundPoolUtils.playSoundForHassium();
                break;

            case R.id.soundPoolAct_btn_play_02:
                soundPoolUtils.playSoundForRingSynthShree();
                break;

            case R.id.soundPoolAct_btn_play_03:
                soundPoolUtils.playSoundForUmbriel();
                break;

            case R.id.soundPoolAct_btn_stop:
                soundPoolUtils.stopSound();
                break;
        }
    }
}
