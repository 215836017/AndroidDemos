package com.test.demoaudio.record;

import android.Manifest;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.demoaudio.BaseActivity;
import com.test.demoaudio.R;
import com.test.demoaudio.utils.LogUtil;
import com.test.demoaudio.utils.TimeUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AudioRecordActivity extends BaseActivity {

    private final String TAG = "AudioRecordActivity";
    private TextView textView;
    private Button btnPcm;
    private Button btnWav;

    private int timeCount = 0;

    private final int SAMPLERATEINHZ = 16 * 1000; // 16000, 44100...
    private final int AUDIOFORMAT = AudioFormat.ENCODING_PCM_16BIT;  // 16bits, 32bits
    private final int CHANNELCONFIG = AudioFormat.CHANNEL_IN_MONO;
    private boolean isRecording = false;

    private AudioRecord audioRecord;
    private int recordBuffSize = 0;
    private byte[] recordDatas;

    private final int MSG_UPDATE_TIME = 0x11;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE_TIME) {
                textView.setText(TimeUtil.getTime(timeCount));

                if (isRecording) {
                    timeCount++;
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, 1000);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != audioRecord) {
            if (audioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
                audioRecord.stop();
            }
            audioRecord.release();
            audioRecord = null;
        }
    }

    private void initViews() {
        textView = findViewById(R.id.audioRecord_text);
        btnPcm = findViewById(R.id.audioRecord_btn_record_pcm);
        btnWav = findViewById(R.id.audioRecord_btn_record_wav);
        btnPcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermission(Manifest.permission.RECORD_AUDIO)) {
                    showToast("需要在系统设置中同意该APP的录音权限！");
                    return;
                }
                initAudio();
                handlePcmEvent();
            }
        });

        btnWav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasPermission(Manifest.permission.RECORD_AUDIO)) {
                    showToast("需要在系统设置中同意该APP的录音权限！");
                    return;
                }
                initAudio();
                handleWavEvent();
            }
        });
    }

    private void initAudio() {
        if (null == audioRecord) {
            recordBuffSize = AudioRecord.getMinBufferSize(SAMPLERATEINHZ, CHANNELCONFIG, AUDIOFORMAT);
            recordDatas = new byte[recordBuffSize];
            LogUtil.i(TAG, "initAudio() -- recordBuffSize = " + recordBuffSize);
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLERATEINHZ,
                    CHANNELCONFIG, AUDIOFORMAT, recordBuffSize);
        }
    }

    private File createFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                file.createNewFile();

                return file;
            } catch (Exception e) {
            }
        }

        return null;
    }

    private void handlePcmEvent() {
        if (isRecording) {
            isRecording = false;
            btnPcm.setText("开始录制PCM");
            btnWav.setClickable(true);

        } else {

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordAudio/test.raw";
            File file = createFile(filePath);
            if (null == file) {
                showToast("PCM文件创建失败");
                return;
            }
            isRecording = true;
            btnPcm.setText("停止录制PCM");
            btnWav.setClickable(false);
            handler.sendEmptyMessage(MSG_UPDATE_TIME);
            recordPcm(file);
        }
    }

    private void handleWavEvent() {
        if (isRecording) {
            isRecording = false;
            btnWav.setText("开始录制WAV");
            btnPcm.setClickable(true);

        } else {

            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordAudio/test.raw";
            File file = createFile(filePath);
            if (null == file) {
                showToast("WAV文件创建失败");
                return;
            }
            isRecording = true;
            btnWav.setText("停止录制WAV");
            btnPcm.setClickable(false);
            handler.sendEmptyMessage(MSG_UPDATE_TIME);
            recordWav(file);
        }
    }

    private void recordPcm(final File file) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                FileOutputStream fo = null;
                try {
                    audioRecord.startRecording();
                    int audioRecordState = audioRecord.getState();
                    LogUtil.i(TAG, "audioRecordState = " + audioRecordState);
                    int len;
                    fo = new FileOutputStream(file);
                    while (isRecording) {
                        len = audioRecord.read(recordDatas, 0, recordBuffSize);
                        LogUtil.i(TAG, "len = " + len);
                        if (AudioRecord.ERROR_INVALID_OPERATION != len) {
                            LogUtil.i(TAG, "recordDatas.len = " + recordDatas.length);
                            fo.write(recordDatas);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }.start();
    }

    /**
     * 唯一需要注意的一点是在保存wav音频文件的时候，在pcm的基础上需要给它增加wav header信息，否则文件无法打开。
     */
    private void recordWav(final File file) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                FileOutputStream fo = null;
                ByteArrayOutputStream baos = null;
                int len = 0;
                try {
                    fo = new FileOutputStream(file);
                    baos = new ByteArrayOutputStream();
                    while (isRecording) {
                        len = audioRecord.read(recordDatas, 0, recordBuffSize);
                        if (AudioRecord.ERROR_INVALID_OPERATION != len) {
                            LogUtil.i(TAG, "recordDatas.len = " + recordDatas.length);
                            baos.write(recordDatas, 0, len);
                        }
                    }

                    byte[] allAuidoBytes = baos.toByteArray();
                    fo.write(PcmToWavUtil.getWavHeader(CHANNELCONFIG, allAuidoBytes.length, SAMPLERATEINHZ));
                    fo.write(allAuidoBytes);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
