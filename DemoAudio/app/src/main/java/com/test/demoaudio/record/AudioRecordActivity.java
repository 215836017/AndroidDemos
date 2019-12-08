package com.test.demoaudio.record;

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

import androidx.appcompat.app.AppCompatActivity;

import com.test.demoaudio.R;
import com.test.demoaudio.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用AudioRecord的类。AudioRecord是三个方法中最灵活的（由于他同意訪问原始音频流），可是他是拥有最少的内置功能。如不会自己主动压缩音频等等。
 * 使用AudioRecord的基础知识很easy。我们仅仅须要构造一个AudioRecord类型的对象，并传入各种不同配置參数。
 * 须要制定的第一个值就是音频源。以下使用值与之前用于MediaRecorder的值同样，其在MediaRecorder.AudioSource 中定义。实际上。这意味着能够使用MediaRecorder.AudioSource.MIC;
 * int audiosource = MediaRecorder.AudioSource.MIC;
 * 须要指定的下一个值是录制的採样率，应以赫兹为单位，我们知道。MediaRecorder採样的音频是8000赫兹。
 * 而CD质量的音频一般是44100赫兹（44100Hz）。Hz或赫兹是每秒的样本数量。
 * 不同的Android手机硬件将可以以不同的採样率进行採样。对于我的这个样例将以11025Hz的採样率来进行採样，这是一个经常使用的採样率。
 * int sampleRateInHz = 11025;
 * 接下来，须要指定捕获的音频通道的数量，在AudioFormat类中指定了用于此參数的常量。并且可依据名称理解他们。
 * 比如使用单声道配置: int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;
 * 随后。须要指定音频格式。在AudioFormat类中也指定了一下各种可能的常量。
 * 这四个选择中，我们选择PCM_16位和PCM 8位。 PCM代表脉冲编码调制（Pulse Code Modulation） 他实际上是原始的音频样本。
 * 因此能够设置每一个样本的分辨率为16位或8位。16位将占用很多其它的控件和处理能力，但表示的音频将更接近真实。
 * int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
 * 最后将须要指定缓冲区大小。实际上能够查询AudioRecord类以获得最小缓冲区大小。查询方式是调用getMinBufferSize的静态方法，同一时候传入採样率，通道配置以及音频格式。
 * int bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,channelConfig,audioFormat);
 * <p>
 * 参考链接：
 * https://www.cnblogs.com/renhui/p/7457321.html
 *
 * Android AudioRecord录制wav格式的音频: https://blog.csdn.net/chezi008/article/details/53064604
 */
public class AudioRecordActivity extends AppCompatActivity {

    private final String TAG = "AudioRecordActivity";
    private TextView textView;
    private Button button;

    private int timeCount = 0;

//    private final int SAMPLERATEINHZ = 32 * 1000;
//    private final int CHANNELCONFIG = 0;
//    private final int AUDIOFORMAT = 0;

    private final int SAMPLERATEINHZ = 16 * 1000;
    private final int AUDIOFORMAT = AudioFormat.ENCODING_PCM_16BIT;
    private final int CHANNELCONFIG = AudioFormat.CHANNEL_IN_MONO;
    private boolean isRecording = false;
    private File rawFile;

    private AudioRecord audioRecord;
    private int recordBuffSize = 0;
    private byte[] recordDatas;

    private final int MSG_UPDATE = 0x11;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE) {
                textView.setText(TimeUtil.getTime(timeCount));

                if (isRecording) {
                    timeCount++;
                    handler.sendEmptyMessageDelayed(MSG_UPDATE, 1000);
                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        initViews();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != audioRecord) {
            audioRecord.release();
            audioRecord = null;
        }
    }

    private void initViews() {
        textView = findViewById(R.id.audioRecord_text);
        button = findViewById(R.id.audioRecord_btnStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording) {
                    stopRecord();
                } else {
                    startRecord();
                }
            }
        });
    }

    private void init() {
        recordBuffSize = AudioRecord.getMinBufferSize(SAMPLERATEINHZ, CHANNELCONFIG, AUDIOFORMAT);
        recordDatas = new byte[recordBuffSize];
        LogUtil.i(TAG, "init() -- recordBuffSize = " + recordBuffSize);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLERATEINHZ,
                CHANNELCONFIG, AUDIOFORMAT, recordBuffSize);

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recordAudio/test.raw";
        rawFile = new File(filePath);
        if (!rawFile.exists()) {
            File dir = rawFile.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                rawFile.createNewFile();
            } catch (Exception e) {
            }
        }
    }

    private void startRecord() {
        isRecording = true;
        button.setText("结束录音");
        handler.sendEmptyMessage(MSG_UPDATE);
        doRecord();
    }

    private void stopRecord() {
        isRecording = false;
        button.setText("开始录音");
    }

    private void doRecord() {
        new Thread() {

            @Override
            public void run() {
                super.run();

                isRecording = true;
                FileOutputStream fo = null;
                try {
                    fo = new FileOutputStream(rawFile);
                    audioRecord.startRecording();
                    int audioRecordState = audioRecord.getState();
                    LogUtil.i(TAG, "audioRecordState = " + audioRecordState);
                    int len;
                    while (isRecording) {
                        len = audioRecord.read(recordDatas, 0, recordBuffSize);
                        LogUtil.i(TAG, "len = " + len);
                        if (AudioRecord.ERROR_INVALID_OPERATION != len) {
                            LogUtil.i(TAG, "recordDatas.len = " + recordDatas.length);
                            fo.write(recordDatas);
                        }
                    }

                } catch (Exception e) {
                    LogUtil.e(TAG, "doRecord()--error: " + e.getMessage());
                } finally {
                    if (null != fo) {
                        try {
                            fo.close();
                            fo = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
