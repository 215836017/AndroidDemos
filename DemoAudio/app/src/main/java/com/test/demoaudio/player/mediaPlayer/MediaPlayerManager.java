package com.test.demoaudio.player.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.test.demoaudio.utils.LogUtil;

import java.io.IOException;


public class MediaPlayerManager {

    private final String tag = "MediaPlayerManager.java";
    private Context context;
    private Handler handler;
    private MediaPlayer mediaPlayer;

    private boolean isGetDuration = false;
    private int musicDuration;

    public MediaPlayerManager(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
        mediaPlayer = new MediaPlayer();

        mediaPlayerSetListeners();
    }

    private void mediaPlayerSetListeners() {
//        mediaPlayer.setOnBufferingUpdateListener();
//        mediaPlayer.setOnCompletionListener();
//        mediaPlayer.setOnDrmConfigHelper();
//        mediaPlayer.setOnDrmInfoListener();
//        mediaPlayer.setOnDrmPreparedListener();
//        mediaPlayer.setOnErrorListener();
//        mediaPlayer.setOnInfoListener();
//        mediaPlayer.setOnMediaTimeDiscontinuityListener();
//        mediaPlayer.setOnPreparedListener();
//        mediaPlayer.setOnSeekCompleteListener();
//        mediaPlayer.setOnSubtitleDataListener();
//        mediaPlayer.setOnTimedMetaDataAvailableListener();
//        mediaPlayer.setOnTimedTextListener();
//        mediaPlayer.setOnVideoSizeChangedListener();

        mediaPlayer.setOnErrorListener(onErrorListener);
        mediaPlayer.setOnPreparedListener(onPreparedListener);
        mediaPlayer.setOnSeekCompleteListener(onSeekCompleteListener);
        mediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    public void setMediaplayerDataSource(String[] dataSource) {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(dataSource[0]);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(MediaplayerCode.ErrorCode.MSG_ERROR_SET_DATA_SOURCE_FAIL);
        }
    }


    public void changeMusic(String musicPath) {

    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public int getMusicDuration() {
        return musicDuration;
    }

    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    public void setLoop(boolean isLoop) {
        mediaPlayer.setLooping(isLoop);
    }

    public void start() {
        mediaPlayer.start();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public void next(String musicPath){

    }
    public void release() {
        if (null != mediaPlayer) {

            if (mediaPlayer.isPlaying()) {


                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }

    MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            LogUtil.d(tag, "onErrorListener -- what = " + what + ", extra = " + extra);
            return false;
        }
    };

    MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            if (!isGetDuration) {
                musicDuration = mp.getDuration();
                isGetDuration = true;
            }
            handler.sendEmptyMessage(MediaplayerCode.EventCode.MSG_EVENT_PERPARE_OK);
        }
    };

    MediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new MediaPlayer.OnSeekCompleteListener() {
        @Override
        public void onSeekComplete(MediaPlayer mp) {

        }
    };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            handler.sendEmptyMessage(MediaplayerCode.EventCode.MSG_EVENT_PLAY_FINISH);
        }
    };
}
