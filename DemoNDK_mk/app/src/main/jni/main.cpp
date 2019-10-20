
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <jni.h>
#include <android/log.h>

#include "com_test_demondk_mk_jni_JniUtil.h"
#include "test.h"

#define LOG_TAG "Jni_log"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG ,__VA_ARGS__)

#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT jint JNICALL Java_com_test_demondk_1mk_jni_JniUtil_getIntFromJni
        (JNIEnv *env, jclass thiz, jint num, jstring name) {

//    const char *c_str = NULL;
//    char buf[128] = {0};
//    c_str = env->GetStringUTFChars(name, 0);   /* 获得传入的字符串，将其转换为native Strings */
//    if (c_str == NULL) {
//        return 0;
//    }

    LOGI("from java, num = %d", num);
//    env->ReleaseStringUTFChars(name, c_str);  /* 通知JVM释放String所占的内存 */

    return getNum();
}

#ifdef __cplusplus
}
#endif
#endif