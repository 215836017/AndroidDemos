
#include <stdarg.h>
#include <string>
#include <stdio.h>
#include <sys/resource.h>
#include <android/log.h>

#include "logUtil.h"

static int g_logLevel = 1;

void jniSetLogLevel(int logLevel) {
    g_logLevel = logLevel;
}

char *jniLog(const char *format, ...) {

    va_list ap;
    char buf[LOG_BUF_SIZE] = {0};
    va_start(ap, format);
    vsnprintf(buf, LOG_BUF_SIZE - 1, format, ap);
    va_end(ap);

    return buf;
}

void jniLogI(const char *format, ...) {
    if (LOG_LEVEL_INFO == g_logLevel || LOG_LEVEL_ALL == g_logLevel) {
        __android_log_write(ANDROID_LOG_INFO, LOG_TAG, jniLog(format));
    }
}

void jniLogD(const char *format, ...) {
    if (LOG_LEVEL_DEBUG == g_logLevel || LOG_LEVEL_ALL == g_logLevel) {
        __android_log_write(ANDROID_LOG_DEBUG, LOG_TAG, jniLog(format));
    }
}

void jniLogE(const char *format, ...) {

    if (LOG_LEVEL_ERROR == g_logLevel || LOG_LEVEL_ALL == g_logLevel) {
        __android_log_write(ANDROID_LOG_ERROR, LOG_TAG, jniLog(format));
    }
}

void jniLogV(const char *format, ...) {
    if (LOG_LEVEL_VERBOSE == g_logLevel || LOG_LEVEL_ALL == g_logLevel) {
        __android_log_write(ANDROID_LOG_VERBOSE, LOG_TAG, jniLog(format));
    }
}
void jniLogW(const char *format, ...) {
    if (LOG_LEVEL_WARN == g_logLevel || LOG_LEVEL_ALL == g_logLevel) {
        __android_log_write(ANDROID_LOG_WARN, LOG_TAG, jniLog(format));
    }
}