cmake_minimum_required(VERSION 3.19)
project(c_dll C)

set(CMAKE_C_STANDARD 99)

add_library(c_dll SHARED org_mango_demo__case_native2_NativeTest.h test.c)