LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := main.cpp \
                   test.cpp
LOCAL_C_INCLUDES := com_test_demondk_mk_jni_JniUtil.h
LOCAL_MODULE := libtestJni
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog
LOCAL_LDFLAGS := -llog
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_PATH := $(TARGET_OUT_SHARED_LIBRARIES)
include $(BUILD_SHARED_LIBRARY)