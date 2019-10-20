package com.test.demobasendk.jni;

public class JniUtil {

    static {
        System.loadLibrary("baseNDK");
    }

    /*** 设置JNI中日志的级别，默认为 */
    public static native void setJniLogLevel(int logLevel);

    public static native int setInt(int param);

    public static native int[] setIntArray(int[] params);

    public static native long setLong(long param);

    public static native long[] setLongArray(long[] params);

    public static native float setFloat(float param);

    public static native float[] setFloatArray(float[] params);

    public static native double setDouble(double param);

    public static native double[] setDoubleArray(double[] params);

    public static native boolean setBoolean(boolean param);

    public static native boolean[] setBooleanArray(boolean[] params);

    public static native String setString(String param);

    public static native String[] setStringArray(String[] params);

    public static native void startNewThread();  // 在JNI中开启新的线程

    public static class JniLogLevel {
        /*** JNI中log级别：不打印任何日志 */
        public static final int LOG_LEVEL_NO = 0;
        /*** JNI中log级别：打印所有日志*/
        public static final int LOG_LEVEL_ALL = 1;
        /*** JNI中log级别：打印INFO日志 */
        public static final int LOG_LEVEL_INFO = 2;
        /*** JNI中log级别：打印ERROR日志 */
        public static final int LOG_LEVEL_ERROR = 3;
        /*** JNI中log级别：打印DEBUG日志 */
        public static final int LOG_LEVEL_DEBUG = 4;
        /*** JNI中log级别：打印VERBOSE日志 */
        public static final int LOG_LEVEL_VERBOSE = 5;
        /*** JNI中log级别：打印VERBOSE日志 */
        public static final int LOG_LEVEL_WARN = 6;

    }
}
