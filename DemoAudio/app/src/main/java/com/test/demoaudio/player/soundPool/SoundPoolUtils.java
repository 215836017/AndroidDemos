package com.test.demoaudio.player.soundpool;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.test.demoaudio.R;
import com.test.demoaudio.utils.LogUtil;

public class SoundPoolUtils {

    private static final String tag = "SoundPoolUtils";

    private int playedIdForRingSynthShree = -1;
    private int playedIdForHassium = -1;
    private int playedIdForUmbriel = -1;

    // SoundPool对象
    private SoundPool mSoundPlayer;
    private int loadRingSynthShree;
    private int loadHassium;
    private int loadUmbriel;

    public SoundPoolUtils(Context context) {

        init();
        // 加载音效
        loadRingSynthShree = mSoundPlayer.load(context, R.raw.ring_synth_three, 1);
        loadHassium = mSoundPlayer.load(context, R.raw.hassium, 1);
        loadUmbriel = mSoundPlayer.load(context, R.raw.umbriel, 1);
    }

    private void init() {
        if (null == mSoundPlayer) {
            mSoundPlayer = new SoundPool(10,
                    AudioManager.STREAM_SYSTEM, 5);
        }
    }

    /**
     * 播放ring_synth_three音效
     */
    public int playSoundForRingSynthShree() {
        init();
        playedIdForRingSynthShree = mSoundPlayer.play(loadRingSynthShree, 1, 1, 0, -1, 1);
        LogUtil.i(tag, "playSoundForRingSynthShree() -- playedIdForRingSynthShree = " + playedIdForRingSynthShree);
        return playedIdForRingSynthShree;
    }

    /**
     * 停止播放ring_synth_three音效
     */
    public void stopSoundForRingSynthShree() {
        mSoundPlayer.stop(playedIdForRingSynthShree);
    }

    /**
     * 播放hassium音效
     */
    public int playSoundForHassium() {
        init();
        playedIdForHassium = mSoundPlayer.play(loadHassium, 1, 1, 0, -1, 1);
        LogUtil.i(tag, "playSoundForHassium() -- playedIdForHassium = " + playedIdForHassium);
        return playedIdForHassium;
    }

    /**
     * 停止播放hassium音效
     */
    public void stopSoundForHassium() {
        mSoundPlayer.stop(playedIdForHassium);
    }

    /**
     * 播放umbriel音效
     */
    public int playSoundForUmbriel() {
        init();
        playedIdForUmbriel = mSoundPlayer.play(loadUmbriel, 1, 1, 0, -1, 1);
        LogUtil.i(tag, "playSoundForUmbriel() -- playedIdForUmbriel = " + playedIdForUmbriel);

        return playedIdForUmbriel;
    }

    /**
     * 停止播放umbriel音效
     */
    public void stopSoundForUmbriel() {
        mSoundPlayer.stop(playedIdForUmbriel);
    }

    /**
     * 停止当前音效的播放, 并释放SoundPool资源
     */
    public void stopSound() {
        if (null != mSoundPlayer) {
//            if (playedIdForUmbriel > 0) {
            mSoundPlayer.stop(playedIdForRingSynthShree);
//            }
//            if (playedIdForUmbriel > 0) {
            mSoundPlayer.stop(playedIdForHassium);
//            }
//            if (playedIdForUmbriel > 0) {
            mSoundPlayer.stop(playedIdForUmbriel);
//            }


            mSoundPlayer.release();
            mSoundPlayer = null;
        }
    }
}