
#define LOG_TAG "baseNDK_log"
#define LOG_BUF_SIZE 1024

extern "C" void jniSetLogLevel(int logLevel);
extern "C" void jniLogI(const char *format, ...);
extern "C" void jniLogD(const char *format, ...);
extern "C" void jniLogE(const char *format, ...);
extern "C" void jniLogV(const char *format, ...);
extern "C" void jniLogW(const char *format, ...);

enum jniLogLevel {
    LOG_LEVEL_ALL = 1,
    LOG_LEVEL_INFO,
    LOG_LEVEL_ERROR,
    LOG_LEVEL_DEBUG,
    LOG_LEVEL_VERBOSE,
    LOG_LEVEL_WARN,

};

#define LOGI(...)  jniLogI(__VA_ARGS__)
#define LOGD(...)  jniLogD(__VA_ARGS__)
#define LOGE(...)  jniLogE(__VA_ARGS__)
#define LOGV(...)  jniLogV(__VA_ARGS__)
#define LOGW(...)  jniLogW(__VA_ARGS__)
