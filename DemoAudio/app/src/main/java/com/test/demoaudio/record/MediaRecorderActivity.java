package com.test.demoaudio.record;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.test.demoaudio.R;

import java.io.IOException;

/**
 * MediaRecorder 类可用于音频和视频的捕获。再构造了一个MediaRecorder对象之后，为了捕获音频，必须调用setAudioEncoder
 * 和setAudioSource这俩个方法。假设不调用这些方法，那么将不会录制音频（视频也相同不会），另外，MediaRecorder在准备录制之前
 * 通常还会调用setOutputFormat 和setOutputFile，在实例化MediaRecorder之后。应该调用的第一个方法是setAudioSource。
 * 它採用一个AudioSource内部类中定义的常量作为參数，我们通常使用的常量是MediaRecorder。AudioSource.MIC.
 * * 依据顺序，下一个调用的就是setOutputFormat ，这种方法採用在MediaRecorder.OutputFormat内部类中指定的常量作为參数：
 * 1）MediaRecorder.OutputFormat.MPEG_4:这个常量指定输出的文件将是一个MPEG_4文件，包括音频跟视频轨
 * 2）MediaRecorder.OutputFormat.RAW_AMR;这个常量表示输出一个没有不论什么容器类型的原始文件，仅仅有在捕获没有视频的音频且音频编码器是AMR_NB时才会使用这个常量。
 * 3）MediaRecorder.OutputFormat.THREE_GPP:这个常量指定输出的文件将是一个3gpp文件（.3gp）。它可能同一时候包括音频跟视频轨
 * MediaRecorder音频编码。在设置输出格式之后，能够调用setAudioEncoder方法来设置应该使用编码器，可能的值指定为MediaRecorder.AudioEncoder类中的常量。出来使用DEFAULT之外，仅仅存在一个其它的值：MediaRecorder.AudioEncoder.AMR_NB,这是自适应多速率窄带编解码器。
 * 这样的编解码器针对语音进行了优化，因此不适应于语音之外的其它内容。默认情况下他的採样率是8kHz,码率在 4.75~12.2kbps之间。这个俩个数据对于录制语音之外的其它内容而言很低。可是，这是当前可用于MediaRecorder的唯一选择。
 *
 * 参考链接：
 * https://www.cnblogs.com/plokmju/p/android_MediaRecorder.html
 */
public class MediaRecorderActivity extends AppCompatActivity {


    private TextView textView;
    private int timeCount = 0;

    private MediaRecorder mediaRecorder;


    private final int MSG_UPDATE = 0x11;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_UPDATE) {
                textView.setText(TimeUtil.getTime(timeCount));

                timeCount++;
                handler.sendEmptyMessageDelayed(MSG_UPDATE, 1000);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recorder);

        initViews();
        init();
    }

    @Override
    protected void onDestroy() {

        if (isRocrding && null != mediaRecorder) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        super.onDestroy();
    }

    public void mediaRecordBtnClick(View view) {
        if (view.getId() == R.id.mediaRecord_btnStart) {
            startRecord();
        } else if (view.getId() == R.id.mediaRecord_btnStop) {
            stopRecord();
        }
    }

    private void initViews() {
        textView = findViewById(R.id.mediaRecord_text);
    }

    private void init() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  // 设置音频录入源
        // 设置录制音频的输出格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // 设置音频的编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // 设置录制音频文件输出文件路径
        String fileName = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/recordAudio/mediaRecord.amr";
        mediaRecorder.setOutputFile(fileName);

        mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
            @Override
            public void onError(MediaRecorder mr, int what, int extra) {
                stopRecord();
            }
        });

        mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mr, int what, int extra) {

            }
        });
    }

    private boolean isRocrding = false;

    private void startRecord() {
        handler.sendEmptyMessage(MSG_UPDATE);

        try {
            mediaRecorder.prepare();
            isRocrding = true;
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecord() {
        handler.removeMessages(MSG_UPDATE);
        isRocrding = false;
        if (null != mediaRecorder) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }

    }

}
