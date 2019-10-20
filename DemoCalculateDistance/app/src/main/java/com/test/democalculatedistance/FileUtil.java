package com.test.democalculatedistance;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {

    private static final String TAG = "FileUtil.java";

    private static File getFile() {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/testLocate/location.txt";
        File file = new File(filePath);
        File dir = file.getParentFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }

        return file;
    }

    public static void writeToFile(final String msg) {
        final File file = getFile();

        new Thread() {
            @Override
            public void run() {
                super.run();
                LogUtil.d(TAG, "start to write file...");
                FileOutputStream fileOutputStream = null;
                BufferedWriter out = null;
                try {
                    fileOutputStream = new FileOutputStream(file, true);
                    out = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    out.write(msg);
                } catch (Exception e) {
                } finally {
                    if (null != out) {
                        try {
                            out.close();
                            out = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (null != fileOutputStream) {
                        try {
                            fileOutputStream.close();
                            fileOutputStream = null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }.start();
    }
}
