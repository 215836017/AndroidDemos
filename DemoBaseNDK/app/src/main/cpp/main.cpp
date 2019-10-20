#include <stdio.h>
#include <stdlib.h>

#include <jni.h>
#include <cstring>

#include <android/log.h>
#include "global.h"
#include "test.h"
#include "utils/logUtil.h"

#ifndef _Included_com_test_demobasendk_jni_JniUtil
#define _Included_com_test_demobasendk_jni_JniUtil
#ifdef __cplusplus
extern "C" {
#endif  // https://www.cnblogs.com/neil-yang/p/3271749.html

void showNDKVersion(JNIEnv *env) {
    LOGI("NDKVersion is: %d", env->GetVersion());
    LOGI("baseNDK_Verion is: %f", baseNDK_Verion);
}

JNIEXPORT void JNICALL Java_com_test_demobasendk_jni_JniUtil_setJniLogLevel
        (JNIEnv *env, jclass clazz, jint logLevel) {

    jint level = logLevel;
    printf("Java_com_test_demobasendk_jni_JniUtil_setJniLogLevel()--logLevel = %d, level = %d",
           logLevel, level);
    jniSetLogLevel(level);
}

JNIEXPORT jint JNICALL Java_com_test_demobasendk_jni_JniUtil_setInt
        (JNIEnv *env, jclass clazz, jint param) {
    showNDKVersion(env);
    jint temp = param;
    LOGI("JniUtil_setInt() -- param = %d, temp = %d", param, temp); // param打印的是地址。
    return temp + 654;
}

JNIEXPORT jintArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setIntArray
        (JNIEnv *env, jclass clazz, jintArray param) {
    LOGI("JniUtil_setInt() -- param = %d", param);  // 打印的是数组的首地址

    //获取传入数组的长度
    jsize len = env->GetArrayLength(param);
    // 申请一块内存
    jintArray result = env->NewIntArray(len);
    // 获取传入的数组
    jint *body = env->GetIntArrayElements(param, 0);

    int i;
    for (i = 0; i < len; i++) {
        LOGI("JniUtil_setIntArray() -- param[%d] = %d", i, body[i]);
        body[i] *= 2;
    }
    env->SetIntArrayRegion(result, 0, len, body);

    return result;
}

JNIEXPORT jlong JNICALL Java_com_test_demobasendk_jni_JniUtil_setLong
        (JNIEnv *env, jclass clazz, jlong param) {
    jlong result = param;
    LOGD("JniUtil_setLong() -- the param = %d， result = %d", param, result);
    result += 1000;
    return result;
}

JNIEXPORT jlongArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setLongArray
        (JNIEnv *env, jclass clazz, jlongArray param) {

    jsize len = env->GetArrayLength(param);
    jlongArray result = env->NewLongArray(len);
    jlong *body = env->GetLongArrayElements(param, 0);

    int i;
    for (i = 0; i < len; i++) {
        LOGD("JniUtil_setLongArray() -- param[%d] = %d", i, body[i]);
        body[i] -= 1000;
    }
    env->SetLongArrayRegion(result, 0, len, body);

    return result;
}

JNIEXPORT jfloat JNICALL Java_com_test_demobasendk_jni_JniUtil_setFloat
        (JNIEnv *env, jclass clazz, jfloat param) {
    LOGI("JniUtil_setFloat() param = %f", param);
    return param * 3;
}

JNIEXPORT jfloatArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setFloatArray
        (JNIEnv *env, jclass clazz, jfloatArray param) {
    jsize len = env->GetArrayLength(param);
    LOGI("JniUtil_setFloatArray() -- array.length = " + len);
    jfloatArray result = env->NewFloatArray(len);
    jfloat *body = env->GetFloatArrayElements(param, 0);

    int i = 0;
    for (; i < len; i++) {
        LOGI("JniUtil_setFloatArray() -- param[%d] = %f", i, body[i]);
    }
    env->SetFloatArrayRegion(result, 0, len, body);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_test_demobasendk_jni_JniUtil_setDouble
        (JNIEnv *env, jclass clazz, jdouble param) {
    LOGI("JniUtil_setDouble() -- the param = %lf", param);
    jdouble result = param * 10;
    return result;
}

JNIEXPORT jdoubleArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setDoubleArray
        (JNIEnv *env, jclass clazz, jdoubleArray param) {
    jsize len = env->GetArrayLength(param);
    LOGI("JniUtil_setDoubleArray() -- param.length = %d", len);
    jdoubleArray result = env->NewDoubleArray(len);
    jdouble *body = env->GetDoubleArrayElements(param, 0);

    for (int i = 0; i < len; i++) {
        LOGI("setDoubleArray() -- the param[%d] = %lf", i, param[i]);
    }
    env->SetDoubleArrayRegion(result, 0, len, body);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_test_demobasendk_jni_JniUtil_setBoolean
        (JNIEnv *env, jclass clazz, jboolean param) {

    unsigned int b = param;
    LOGI("JniUtil_setBoolean() -- the param is %d", b);
    jboolean result = true;
    return result;
}

JNIEXPORT jbooleanArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setBooleanArray
        (JNIEnv *env, jclass clazz, jbooleanArray param) {
    jsize len = env->GetArrayLength(param);
    LOGI("JniUtil_setBooleanArray() -- the param.length = ", len);
//    jbooleanArray result = env->NewBooleanArray(len);
//    jboolean *body = env->GetBooleanArrayElements(param, 0);
//    for (int i = 0; i < len; i++) {
//
//            LOGI("");
//
//    }
    jboolean arr[2] = {true, false};
    // jbooleanArray result = {true, false};
    return NULL;
}

JNIEXPORT jstring JNICALL Java_com_test_demobasendk_jni_JniUtil_setString
        (JNIEnv *env, jclass clazz, jstring param) {

    LOGI("JniUtil_setString() -- the param111 is %s", param);
    //获取字符串指针，必须使用指针，不能使用char strContent[],因为GetStringUTFChars()返回值为const char *;
    const char *strContent = env->GetStringUTFChars(param, JNI_FALSE);
    LOGI("JniUtil_setString() -- the param222 is %s", strContent);
    char str[] = "欢迎使用JNI";

    //字符串拼接,实现strContent+str1,因为strcat的第一个参数必须为非const类型(可变)，所以不能直接使用strcat()
    //创建一个新的字符串指针
//    char *strTemp = static_cast<char *>(malloc(strlen(str) + 1));
    char *strTemp = (char *) (malloc(strlen(strContent) + strlen(str) + 1));
    //拷贝常量到字符串指针
    strcpy(strTemp, strContent);
    //拼接str1到strTemp
    strcat(strTemp, str);
    jstring result = env->NewStringUTF(strTemp);
    return result;
}

JNIEXPORT jobjectArray JNICALL Java_com_test_demobasendk_jni_JniUtil_setStringArray
        (JNIEnv *env, jclass clazz, jobjectArray param) {
    int len = env->GetArrayLength(param);
    LOGI("JniUtil_setStringArray() -- the param.lenght = %d", len);

    int i = 0;
    for (i = 0; i < len; i++) {
        jobject obj = env->GetObjectArrayElement(param, i);
        jstring str = (jstring) obj;
        const char *szStr = env->GetStringUTFChars(str, 0);
        LOGI("JniUtil_setStringArray(): %d--%s", i, szStr);

        // env->ReleaseStringChars(str, szStr);
    }

    //-----返回----
    jstring str;
    jobjectArray args = 0;
    jsize size = 5;
    char *sa[] = {"Hello,", "world!", "NDK", "JNI", "Android"};
    int j = 0;
    jclass objClass = env->FindClass("java/lang/String");
    args = env->NewObjectArray(size, objClass, 0);
    for (j = 0; j < size; j++) {
        str = env->NewStringUTF(sa[j]);
        env->SetObjectArrayElement(args, j, str);
    }
    return args;
}


#ifdef __cplusplus
}
#endif
#endif