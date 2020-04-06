package com.cakes.demoprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Intent intent = new Intent();

    public void mainBtnClick(View view) {

        if (view.getId() == R.id.button_progressBar) {
            intent.setClass(this, ProgressBarActivity.class);

        } else if (view.getId() == R.id.button_seekBar) {
            intent.setClass(this, SeekBarActivity.class);

        } else if (view.getId() == R.id.button_ratingBar) {
            intent.setClass(this, RatingBarActivity.class);
        }

        startActivity(intent);
    }
}
