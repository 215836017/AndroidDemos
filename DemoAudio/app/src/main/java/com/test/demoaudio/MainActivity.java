package com.test.demoaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.demoaudio.player.AudioTrack.AudioTrackActivity;
import com.test.demoaudio.player.Ringtone.RingtoneActivity;
import com.test.demoaudio.player.mediaPlayer.MediaPlayerActivity;
import com.test.demoaudio.player.soundPool.SoundPoolActivity;
import com.test.demoaudio.record.AudioRecordActivity;
import com.test.demoaudio.record.MediaRecorderActivity;

/**
 * https://www.cnblogs.com/HDK2016/p/8043247.html
 * <p>
 * https://download.csdn.net/download/u010623392/10790842?tdsourcetag=s_pcqq_aiomsg
 * https://blog.csdn.net/leixiaohua1020
 * http://www.rosoo.net/
 * http://bbs.chinaffmpeg.com/forum.php
 */
public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Class[] actvities = {
            SoundPoolActivity.class,
            MediaPlayerActivity.class,
            AudioTrackActivity.class,
            RingtoneActivity.class,
            AudioRecordActivity.class,
            MediaRecorderActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        listView = findViewById(R.id.mainAct_listView);
        String[] datas = getResources().getStringArray(R.array.audioPlayer_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, actvities[position]));
            }
        });
    }
//    public void btnClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.mainAct_btn_localRecorder:
//                intent.setAction(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
//                startActivityForResult(intent, requestRecordCode);
//                break;
//
//            case R.id.mainAct_btn_mediaRecorder:
//                intent.setClass(this, MediaRecorderActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.mainAct_btn_audioRecord:
//                intent.setClass(this, AudioRecordActivity.class);
//                startActivity(intent);
//                break;
//        }
//    }

}
