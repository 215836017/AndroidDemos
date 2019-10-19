package com.test.audioplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.audioplayer.AudioTrack.AudioTrackActivity;
import com.test.audioplayer.Ringtone.RingtoneActivity;
import com.test.audioplayer.mediaPlayer.MediaPlayerActivity;
import com.test.audioplayer.soundPool.SoundPoolActivity;

/**
 * https://www.cnblogs.com/HDK2016/p/8043247.html
 *
 * https://blog.csdn.net/u014253332/article/details/86474950
 * https://blog.csdn.net/leixiaohua1020
 * http://www.rosoo.net/
 * http://bbs.chinaffmpeg.com/forum.php
 */
public class AppMainActivity extends AppCompatActivity {

    private ListView listView;
    private Class[] actvities = {
            SoundPoolActivity.class,
            MediaPlayerActivity.class,
            AudioTrackActivity.class,
            RingtoneActivity.class
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    @SuppressLint("ResourceType")
    private void init() {
        listView = findViewById(R.id.mainAct_listView);
        String[] datas = getResources().getStringArray(R.array.audioPlayer_items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(AppMainActivity.this, actvities[position]));
            }
        });
    }


}
