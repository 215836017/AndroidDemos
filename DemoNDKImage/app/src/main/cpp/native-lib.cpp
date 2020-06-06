#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_cakes_demondkimage_MainActivity_stringFromJNI(
        JNIEnv *env, jobject clazz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jintArray JNICALL
Java_com_cakes_demondkimage_MainActivity_getImage(
        JNIEnv *env, jobject clazz, jintArray buff, jint width, jint height) {


    return NULL;
}
