package com.test.audioplayer.soundPool;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.test.audioplayer.R;

public class SoundPoolUtils {

    private static final String tag = "SoundPoolUtils.java";
    /**
     * 当前正在播放的音效id
     */
    private int playedId = -1;
    private boolean isPlaying = false;

    // SoundPool对象
    private SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    private int loadRingSynthShree;
    private int loadHassium;
    private int loadUmbriel;

    public SoundPoolUtils(Context context) {

        // 加载音效
        loadRingSynthShree = mSoundPlayer.load(context, R.raw.ring_synth_three, 1);
        loadHassium = mSoundPlayer.load(context, R.raw.hassium, 1);
        loadUmbriel = mSoundPlayer.load(context, R.raw.umbriel, 1);
    }

    /**
     * 播放ring_synth_three音效
     */
    public void playSoundForRingSynthShree() {
        isPlaying = true;
        playedId = mSoundPlayer.play(loadRingSynthShree, 1, 1, 0, -1, 1);
    }

    /**
     * 播放hassium音效
     */
    public void playSoundForHassium() {
        isPlaying = true;
        playedId = mSoundPlayer.play(loadHassium, 1, 1, 0, -1, 1);
    }
    /**
     * 播放umbriel音效
     */
    public void playSoundForUmbriel() {
        isPlaying = true;
        playedId = mSoundPlayer.play(loadUmbriel, 1, 1, 0, -1, 1);
    }

    /**
     * 停止当前音效的播放, 并释放SoundPool资源
     */
    public void stopSound() {
        if (null != mSoundPlayer) {
            if (isPlaying) {
                mSoundPlayer.stop(playedId);
                isPlaying = false;
            }

            mSoundPlayer.release();
            mSoundPlayer = null;
        }
    }
}