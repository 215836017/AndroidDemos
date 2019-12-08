package com.test.demoaudio.player.mediaPlayer;

import java.util.Random;

public class MusicManager {

    public static final int play_mode_list_order = 0;
    public static final int play_mode_list_loop = 1;
    public static final int play_mode_singal_loop = 2;
    public static final int play_mode_random = 3;

    private String[] pathSdcard = {
            "sdcard/a.mp3",
            "sdcard/b.mp3",
            "sdcard/c.mp3"
    };

    public String[] getPathSdcard() {
        return pathSdcard;
    }

    public int changeMusicOrder(int currentPlayIndex, int musicCount, boolean isNext) {

        int index = 0;
        if (isNext) {
            if (currentPlayIndex == (musicCount - 1)) {
                index = 0;
            } else {
                index = currentPlayIndex++;
            }
        } else {
            if (currentPlayIndex == 0) {
                index = musicCount - 1;
            } else {
                index = currentPlayIndex--;
            }
        }
        return index;
    }

    public int changeMusicRandom(int musicCount) {

        Random random = new Random();
        return random.nextInt(musicCount);
    }
}
