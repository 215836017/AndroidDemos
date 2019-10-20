package com.test.demondk_mk.jni;

public class JniUtil {

    static {
        System.loadLibrary("testJni");
    }

    public static native int getIntFromJni(int num, String name);
}
