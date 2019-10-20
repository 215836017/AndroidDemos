cmake_minimum_required(VERSION 3.4.1)

include_directories(src/main/cpp/)
add_library( # Specifies the name of the library.
        baseNDK

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/main.cpp
        src/main/cpp/global.cpp
        src/main/cpp/test.cpp

        src/main/cpp/utils/logUtil.cpp
        )

find_library(log-lib log)
find_library(android_lib android)

target_link_libraries(
        baseNDK

        ${android_lib}
        ${log-lib})
