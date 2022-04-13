#include <jni.h>
#include <string>
#include <algorithm>
#include <iostream>
#include <android/log.h>


#define TAG "yue_"
#define LOG_D(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)
#define LOG_E(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__)


jstring encrypt(JNIEnv *env, jobject instance, jstring plainText) {
//    int a = 1/0;
    return reinterpret_cast<jstring>(123);
}


static JNINativeMethod gMethods[] = {{"encrypt", "(Ljava/lang/String;)Ljava/lang/String;", (void *) encrypt}};

int JNI_OnLoad(JavaVM *vm, void *re) {
    JNIEnv *env;
    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }
    jclass javaClass = env->FindClass("com/tencent/bugly/demo/TestJNI");
    if (javaClass == NULL) {
        return JNI_ERR;
    }
    if (env->RegisterNatives(javaClass, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}

