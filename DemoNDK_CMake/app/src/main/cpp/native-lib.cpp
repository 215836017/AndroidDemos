#include <stdio.h>
#include <stdlib.h>

#include <jni.h>
#include <android/log.h>

#include "test.h"

#define LOG_TAG "Jni_log"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG ,__VA_ARGS__)

#ifdef __cplusplus
extern "C" {
#endif  // https://www.cnblogs.com/neil-yang/p/3271749.html

JNIEXPORT jint JNICALL Java_com_test_demondkCmake_JniUtil_testJni
        (JNIEnv *env, jclass thiz, jint num, jstring name) {
    LOGI("from java, num = %d", num);

    return getNum();
}

#ifdef __cplusplus
}
#endif
#endif