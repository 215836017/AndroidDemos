package com.cakes.demoasynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask2 extends AsyncTask<String, String, String> {

    private Context context;
    private TextView textView;

    public MyAsyncTask2(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        textView.setText("过滤的结果： ");
    }


    @Override
    protected String doInBackground(String... input) {

        for (String str : input) {
            if (str.contains("abc")) {
                publishProgress(str);
            }
        }

        //完成之后会自动调用onPostExecute()
        return "过滤完成";
    }

    @Override
    protected void onProgressUpdate(String... update) {
        super.onProgressUpdate(update);

        textView.setText(textView.getText().toString() + update[0] + "  ");
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);


        //Toast.makeText(context, "过滤完成", Toast.LENGTH_SHORT).show();

        //接收到doInBackground返回的数据
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

}
