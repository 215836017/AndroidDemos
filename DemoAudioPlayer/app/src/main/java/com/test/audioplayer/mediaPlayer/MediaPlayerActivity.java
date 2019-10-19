package com.test.audioplayer.mediaPlayer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.test.audioplayer.R;

/**
 * 使用MediaPlayer播放音频资源， 缺点：只是一个demo，不支持后台播放。
 */
public class MediaPlayerActivity extends AppCompatActivity {

    // ------------------UI views start-------------
    private ConstraintLayout layoutChoseLoadMode;
    private Button btnRaw, btnSdcard, btnUri;

    private ConstraintLayout layoutcontrol;
    private ProgressBar progressBar;
    private Button btnStart, btnPreviout, btnNext;
    private RadioGroup radioGroup;
    // ------------------UI views end-------------

    private String[] musicPath;
    /*** 当前正在播放的下标 */
    private int currentPlayIndex = 0;
    /*** 当前播放模式：顺序播放，列表循环，单曲循环，随机播放，这四种里面的一种情况 */
    private int playMode = 0;
    private MusicManager musicManager;
    private MediaPlayerManager mediaPlayerManager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            handleMeg(msg);
        }
    };

    private void handleMeg(Message msg) {
        switch (msg.what) {
            case MediaplayerCode.EventCode.MSG_EVENT_PERPARE_OK:
                int musicDuration = mediaPlayerManager.getMusicDuration();
                break;

            case MediaplayerCode.EventCode.MSG_EVENT_PLAY_FINISH:
                doWorkForPalyFinish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        init();
        addListeners();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayerManager.stop();
        mediaPlayerManager.release();
    }

    private void init() {
        // chose load mode
        layoutChoseLoadMode = findViewById(R.id.mediaPlayerAct_layout_choseLoadMode);
        btnRaw = findViewById(R.id.mediaPlayerAct_btn_load_raw);
        btnSdcard = findViewById(R.id.mediaPlayerAct_btn_load_sdcard);
        btnUri = findViewById(R.id.mediaPlayerAct_btn_load_uri);
        // audio control
        layoutcontrol = findViewById(R.id.mediaPlayerAct_layout_control);
        progressBar = findViewById(R.id.mediaPlayerAct_progressBar);
        btnStart = findViewById(R.id.mediaPlayerAct_btn_start);
        btnPreviout = findViewById(R.id.mediaPlayerAct_btn_previout);
        btnNext = findViewById(R.id.mediaPlayerAct_btn_next);
        radioGroup = findViewById(R.id.mediaPlayerAct_radioGroup);

        musicManager = new MusicManager();
        mediaPlayerManager = new MediaPlayerManager(this, handler);
    }

    private void addListeners() {
        btnRaw.setOnClickListener(choseLoadModeListener);
        btnSdcard.setOnClickListener(choseLoadModeListener);
        btnUri.setOnClickListener(choseLoadModeListener);

        btnStart.setOnClickListener(audioControlListener);
        btnPreviout.setOnClickListener(audioControlListener);
        btnNext.setOnClickListener(audioControlListener);

        radioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    View.OnClickListener choseLoadModeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.mediaPlayerAct_btn_load_raw:
                    showDialog(getString(R.string.mediaPlayer_raw_reason));
                    break;

                case R.id.mediaPlayerAct_btn_load_sdcard:
                    musicPath = musicManager.getPathSdcard();
                    layoutChoseLoadMode.setVisibility(View.GONE);
                    layoutcontrol.setVisibility(View.VISIBLE);
                    mediaPlayerManager.setMediaplayerDataSource(musicPath);
                    break;

                case R.id.mediaPlayerAct_btn_load_uri:
                    showDialog(getString(R.string.mediaPlayer_uri_reason));
                    break;
            }
        }
    };

    View.OnClickListener audioControlListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mediaPlayerAct_btn_start:
                    if (mediaPlayerManager.isPlaying()) {
                        btnStart.setText(getString(R.string.audio_start));
                        mediaPlayerManager.pause();

                    } else {
                        btnStart.setText(getString(R.string.audio_pause));
                        mediaPlayerManager.start();
                    }
                    break;

                case R.id.mediaPlayerAct_btn_previout:
                    currentPlayIndex = musicManager.changeMusicOrder(currentPlayIndex, musicPath.length, false);
                    mediaPlayerManager.changeMusic(musicPath[currentPlayIndex]);
                    break;

                case R.id.mediaPlayerAct_btn_next:
                    currentPlayIndex = musicManager.changeMusicOrder(currentPlayIndex, musicPath.length, true);
                    mediaPlayerManager.changeMusic(musicPath[currentPlayIndex]);
                    break;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.mediaPlayerAct_rb_listOrder:
                    playMode = MusicManager.play_mode_list_order;
                    break;

                case R.id.mediaPlayerAct_rb_listLoop:
                    playMode = MusicManager.play_mode_list_loop;
                    break;

                case R.id.mediaPlayerAct_rb_signalLoop:
                    playMode = MusicManager.play_mode_singal_loop;
                    break;

                case R.id.mediaPlayerAct_rb_random:
                    playMode = MusicManager.play_mode_random;
                    break;
            }
        }
    };

    /**
     * 当前播放完成后的动作
     */
    private void doWorkForPalyFinish() {

        // TODO 不要忘了更新界面
        switch (playMode) {
            case MusicManager.play_mode_list_order:
                if (currentPlayIndex < (musicPath.length - 1)) {
                    currentPlayIndex = musicManager.changeMusicOrder(currentPlayIndex, musicPath.length, true);
                }
                break;

            case MusicManager.play_mode_list_loop:
                if (currentPlayIndex == (musicPath.length - 1)) {
                    currentPlayIndex = 0;
                } else {
                    currentPlayIndex = musicManager.changeMusicOrder(currentPlayIndex, musicPath.length, true);
                }
                break;

            case MusicManager.play_mode_singal_loop:
                // currentPlayIndex 不变
                break;

            case MusicManager.play_mode_random:
                currentPlayIndex = musicManager.changeMusicRandom(musicPath.length - 1);
                break;

        }
        mediaPlayerManager.next(musicPath[currentPlayIndex]);
    }

    private void showDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.mediaPlayer_dialog_sorry))
                .setMessage(msg)
                .setNegativeButton(getString(R.string.mediaPlayer_dialog_sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}