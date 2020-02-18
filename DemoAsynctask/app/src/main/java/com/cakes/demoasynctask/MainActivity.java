package com.cakes.demoasynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 参考链接： http://blog.csdn.net/liuhe688/article/details/6532519
 */
public class MainActivity extends Activity {

    private TextView textView, textView2;
    private Button button_start1, btn_stop, button_start2;

    private String[] names = {"dwrrr23134", "abcdeewewfdsf", "123abc", "eedabc", "aaaadd", "efd",
            "pllplplabc", "abcabcab", "1121212", "12123abcaa", "12abc12", "aaabd", "bcaacbabc",
            "sdffafea", "abcccc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        button_start1 = (Button) findViewById(R.id.button_start1);
        btn_stop = (Button) findViewById(R.id.button_stop);

        button_start2 = (Button) findViewById(R.id.button_start2);

        final MyAsyncTask task = new MyAsyncTask();

        button_start1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                task.execute();
            }
        });

        btn_stop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                task.cancel(true);
            }
        });


        button_start2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new MyAsyncTask2(MainActivity.this, textView2).execute(names);
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Integer, String, String>{

        private boolean isRunning = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            textView.setTag("00 : 00");
        }


        @Override
        protected String doInBackground(Integer... arg0) {

            int fen = 0, miao = 0;
            String str_fen = "", str_miao = "";

            while(isRunning){

                if(miao < 10){
                    str_miao = "0" + miao;
                }else if(miao == 60){
                    miao = 0;
                    fen++;
                    str_miao = "00";
                }else{
                    str_miao = miao + "";
                }

                if(fen < 10){
                    str_fen = "0" + fen;
                }else{
                    str_fen = fen + "";
                }

                //通过publishProgress()方法通知onProgressUpdate()进行更新UI
                publishProgress(str_fen + " : " + str_miao);


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                miao ++;
            }


            return "计时器停止了";
        }


        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            textView.setText(values[0]);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            textView.setText(result);
        }

        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);


            isRunning = false;
        }
    }

}
