LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := primenumber-jni
LOCAL_SRC_FILES := primenumber-jni.c

include $(BUILD_SHARED_LIBRARY)