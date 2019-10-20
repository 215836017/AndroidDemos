package com.test.demogetwifissid;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * 参考链接：
 * 1. https://www.jianshu.com/p/bdcec333ba2b
 */
public class AppMainActivity extends AppCompatActivity {

    private TextView textView;

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getWifiSSID();
    }

    private void init() {
        textView = findViewById(R.id.mainAct_text);
        // 给TextView添加垂直滚动
        textView.setMovementMethod(new ScrollingMovementMethod());
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    private void getWifiSSID() {
        if (wifiManager.isWifiEnabled()) {

            StringBuffer sb = new StringBuffer();

            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            List<ScanResult> scanResults = wifiManager.getScanResults();

            String bssid = connectionInfo.getBSSID();
            String ssid = connectionInfo.getSSID();

            // 在手机中看到的wifi名称就是SSID
            sb.append("已连接:");
            sb.append("\nbssid: " + bssid + ", ssid: " + ssid);

            sb.append("\n\nConfigured:");
            for (WifiConfiguration wc : configuredNetworks) {
                sb.append("\nbssid: " + wc.BSSID + ", ssid: " + wc.SSID);
            }

            sb.append("\n\nScanResults:");
            for (ScanResult s : scanResults) {

                sb.append("\nbssid: " + s.BSSID + ", ssid: " + s.SSID + ", level: " + s.level);
            }
            String str = sb.toString();
            textView.setText(str);
            Log.i("testWifi", str);

        } else {
            textView.setText("wifi not open");
        }
    }
}
