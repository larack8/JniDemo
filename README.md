
### 1. 第一步：编写kt代码

```kotlin
class NativeLib {

    /**
     * A native method that is implemented by the 'netconfig' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'netconfig' library on application startup.
        init {
            System.loadLibrary("netconfig")
        }
    }
}
```

### 2. 第二步：编写C代码

```c
    #include <jni.h>
    #include <string>
     
    extern "C" JNIEXPORT jstring JNICALL
    Java_com_larack_netconfig_NativeLib_stringFromJNI(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
```

### 3. 第二步：编写cmake

```cmake
cmake_minimum_required(VERSION 3.18.1)


project("netconfig")


add_library( # Sets the name of the library.
        netconfig

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        netconfig.cpp)


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_link_libraries( # Specifies the target library.
        netconfig

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
```

### 4. 编写build.gralde配置

```groovy
plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
}

android {
    compileSdk 32

    defaultConfig {
        minSdk 29
        targetSdk 32

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles "consumer-rules.pro"
        externalNativeBuild {
            cmake {
                cppFlags ""
            }
        }
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    externalNativeBuild {
        cmake {
            path "src/main/cpp/CMakeLists.txt"
            version "3.18.1"
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.4.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}
```

### 5. 第五步：调用jni并运行android程序

```kotlin
value = NativeLib().stringFromJNI()
```

> Hello from C++"


blog 地址：[https://www.cnblogs.com/larack/p/16501583.html](https://www.cnblogs.com/larack/p/16501583.html)
