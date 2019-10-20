package com.test.demondk_cmake;

public class JniUtil {

    static {
        System.loadLibrary("");
    }

    public static native int testJni(int num, String str);
}
