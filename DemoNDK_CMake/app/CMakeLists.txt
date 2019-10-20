cmake_minimum_required(VERSION 3.4.1)

include_directories(src/main/cpp/)
add_library( # Specifies the name of the library.
        testCMake

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/native-lib.cpp
        src/main/cpp/test.cpp)
find_library(log-lib log)
find_library(android_lib android)

target_link_libraries(
        testCMake

        ${android_lib}
        ${log-lib})
