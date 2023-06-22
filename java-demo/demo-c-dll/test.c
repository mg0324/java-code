//
// Created by mango on 2022/5/23.
//

#include "org_mango_demo__case_native2_NativeTest.h"

JNIEXPORT jint JNICALL Java_org_mango_demo__1case_native2_NativeTest_add
        (JNIEnv *env, jclass c, jint a, jint b){
    printf("call native from c method\n");
    printf("a=%d\nb=%d\n",a,b);
    return a+b;
}
