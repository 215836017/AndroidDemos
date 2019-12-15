package com.test.demoaudio.player.mediaplayer;

public class MediaplayerCode {

    /**
     * 事件码：1xx -- 2xx
     */
    public static class EventCode {
        public static final int MSG_EVENT_PERPARE_OK = 100;
        public static final int MSG_EVENT_PLAY_FINISH = 101;
    }

    /**
     * 错误码：4xx -- 5xx
     */
    public static class ErrorCode {
        public static final int MSG_ERROR_SET_DATA_SOURCE_FAIL = 400;
    }


}
