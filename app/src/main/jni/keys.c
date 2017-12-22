#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_meuapttestemobile_presentation_Shot_ShotActivity_getNativeKey(JNIEnv *env,
                                                                       jobject instance) {
    return (*env)->  NewStringUTF(env, "84ec8334d80acb8d849f9d2f24038d25df170bcce1b3d0d998543cfb81bb7e8e");
}