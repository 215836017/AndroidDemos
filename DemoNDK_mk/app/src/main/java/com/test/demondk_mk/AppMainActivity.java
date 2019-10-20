package com.test.demondk_mk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.demondk_mk.jni.JniUtil;

public class AppMainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mainAct_text);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_button) {
            int result = JniUtil.getIntFromJni(123, "Hello_Jni");
            textView.setText("text from C is: " + result);

        }
    }
}
