package com.test.demondk_cmake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AppMainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.button) {
            int result = JniUtil.testJni(123, "ndk_CMake_demo");

            textView.setText("from Jni, result = " + result);
        }
    }
}
