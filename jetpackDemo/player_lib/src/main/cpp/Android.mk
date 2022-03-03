#Android.mk
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := avformat
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libavformat.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avcodec
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libavcodec.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avfilter
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libavfilter.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := avutil
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libavutil.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swresample
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libswresample.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := swscale
LOCAL_SRC_FILES := ${TARGET_ARCH_ABI}/libswscale.a
include $(PREBUILT_STATIC_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE    := player-lib
LOCAL_SRC_FILES := \
                player_lib.cpp
LOCAL_C_INCLUDES :=$(LOCAL_PATH)/include
LOCAL_STATIC_LIBRARIES := avformat avcodec avfilter avutil swresample swscale
LOCAL_CPPFLAGS:=-std=c++11
LOCAL_LDLIBS += -llog

include $(BUILD_SHARED_LIBRARY)

