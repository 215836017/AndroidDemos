package com.example.demopermissions;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static void testWriteFile() {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testFile.txt";

        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            if (null != file) {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } else {
                return;
            }

            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("write file for test permisson".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
