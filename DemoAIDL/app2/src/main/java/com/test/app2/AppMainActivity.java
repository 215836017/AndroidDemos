package com.test.app2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.test.aidl.CalculateInterface;

/**
 * 客户端
 */
public class AppMainActivity extends AppCompatActivity {

    private final String tag = "app2-MainActivity.java";

    private EditText edNumA, edNumB;
    private TextView textResult;
    private Button btnCal;

    private CalculateInterface mService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(tag, "onServiceConnected() ");
            mService = CalculateInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(tag, "onServiceDisconnected() ");
            mService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        doBindService();

        init();
    }

    private void doBindService() {
        Intent intent = new Intent("com.test.demoaidl.CalculateService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    private void init() {
        edNumA = findViewById(R.id.app2_ed_numA);
        edNumB = findViewById(R.id.app2_ed_numB);
        textResult = findViewById(R.id.app2_textView);
        btnCal = findViewById(R.id.app2_button);
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }


    private float numA, numB;
    private String result;

    private void calculate() {
//        numA = Float.parseFloat(edNumA.getText().toString().trim());
//        numB = Float.parseFloat(edNumB.getText().toString().trim());
        numA = 2.1f;
        numB = 3;
        try {
            mService.myPrint("test AIDL -- Hello, this is from client");
            result = mService.calculate(numA, numB, Calculate.type_multiply);
        } catch (Exception e) {
            Log.e(tag, "mService.calculate() is failed: " + e.getMessage());
            result = "result = null";
        }

        textResult.setText(result);
    }

}
