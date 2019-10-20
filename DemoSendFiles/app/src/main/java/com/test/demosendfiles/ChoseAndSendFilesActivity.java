package com.test.demosendfiles;

import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class ChoseAndSendFilesActivity extends AppCompatActivity {

    public static final int SEND_SUCC = 0;
    public static final int SEND_FAIL = 1;

    private Button btnSend;
    private TextView textDirs;
    private RecyclerView recyclerView;
    private MyRecycAdapter recycAdapter;

    private final File sdcardRootFile = Environment.getExternalStorageDirectory();
    private File currentDir;
    private String serverIp;

    private Sender sender;
    private int selectedFileCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chose_files);

        serverIp = SPUtils.getInstance(this).getString(SPUtils.SP_SERVER_IP);
        initViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initViews() {
        btnSend = findViewById(R.id.choseAct_btn_send);
        textDirs = findViewById(R.id.choseAct_text_dirs);
        recyclerView = findViewById(R.id.choseAct_recyclerView);

        textDirs.setText(sdcardRootFile.getAbsolutePath());

        recycAdapter = new MyRecycAdapter(this, sdcardRootFile, serverIp);
        recycAdapter.setOnUpdateListener(onUpdateListener);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(recycAdapter);
    }

    public void choseActBtnClick(View view) {
        if (view.getId() == R.id.choseAct_btn_back) {
            goBack();
        } else if (view.getId() == R.id.choseAct_btn_send) {
            if (selectedFileCount <= 0) {
                Toast.makeText(this, "no file selected", Toast.LENGTH_SHORT).show();
            } else {
                recycAdapter.sendFiles();
            }
        }
    }

    private void goBack() {
        if (currentDir.getAbsolutePath().equals(sdcardRootFile.getAbsolutePath())) {
            this.finish();
        } else {
            recycAdapter.updateDatasForBack();
        }
    }


    MyRecycAdapter.OnUpdateListener onUpdateListener = new MyRecycAdapter.OnUpdateListener() {
        @Override
        public void changeCurrentFile(File file) {
            currentDir = file;
            textDirs.setText(currentDir.getAbsolutePath());
        }

        @Override
        public void changeFileCount(int fileCount) {
            selectedFileCount = fileCount;
            if (fileCount <= 0) {
                btnSend.setText("SNED");
                btnSend.setClickable(false);
            } else {
                btnSend.setText("SEND(" + fileCount + ")");
                btnSend.setClickable(true);
            }
        }
    };
}
