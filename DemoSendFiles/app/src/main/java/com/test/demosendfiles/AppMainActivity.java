package com.test.demosendfiles;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppMainActivity extends AppCompatActivity {

    private final String TAG = "AppMainActivity.java";

    private EditText editIp;

    private final int START_ACTIVITY_REQUEST = 0x11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        Environment.getExternalStorageDirectory();  // 过时了...

    }

    private void initViews() {
        editIp = findViewById(R.id.mainAct_edit_ip);

        String ip = SPUtils.getInstance(this).getString(SPUtils.SP_SERVER_IP);
        if (!TextUtils.isEmpty(ip)) {
            editIp.setText(ip);
        }
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn_send) {
            String ip = editIp.getText().toString().trim();
//            if (isIP(ip)) {
//                startActivityForResult(new Intent(this, ChoseAndSendFilesActivity.class),
//                START_ACTIVITY_REQUEST);

            SPUtils.getInstance(this).putString(SPUtils.SP_SERVER_IP, ip);
//            } else {
//                Toast.makeText(this, "please input correct IP", Toast.LENGTH_LONG).show();
//            }

            startActivityForResult(new Intent(this, ChoseAndSendFilesActivity.class),
                    START_ACTIVITY_REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_ACTIVITY_REQUEST) {
            if (resultCode == ChoseAndSendFilesActivity.SEND_SUCC) {
                Toast.makeText(this, "send succ", Toast.LENGTH_SHORT).show();
            } else if (resultCode == ChoseAndSendFilesActivity.SEND_FAIL) {
                Toast.makeText(this, "send failed, check the IP is correct", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();

        return ipAddress;
    }

}
