# Native Crash 分析

## 背景

对于Android APP而言，Native层Crash相比于Java层更难捕获与定位，Log中堆栈信息非常少。本次结合Bugly的使用，总结Native Crash分析。

## Bugly的集成

### 1.Bugly官网申请APP ID

官网地址：https://bugly.qq.com/v2/index

![image-20220411164457708](C:\Users\GH\AppData\Roaming\Typora\typora-user-images\image-20220411164457708.png)

### 2.配置远程依赖

```groovy
dependencies {
    implementation 'com.tencent.bugly:crashreport:latest.release'
    implementation 'com.tencent.bugly:nativecrashreport:latest.release'
}
```

### 3.初始化Bugly

```
//Application中初始化
CrashReport.initCrashReport(this, "ed8d153690", BuildConfig.DEBUG);
```

### 4.测试Native Crash

```java
CrashReport.testNativeCrash();
```

### 5.查看异常上报

![image-20220411165502256](C:\Users\GH\AppData\Roaming\Typora\typora-user-images\image-20220411165502256.png)

```
出错堆栈

1	#00 pc 0000000000007058 /data/app/~~dZ7nMLZgpWS7eNmGSc00eA==/com.yue.bugly.demo-LY9IeIV7FQL1DaX3KYuYAA==/lib/arm64/libBugly-ext.so (doANativeCrash+256) [arm64-v8a:4.0.4:24c1c2f2005ea7e20337bba1bbeb2476]
2	#01 pc 0000000000008984 /data/app/~~dZ7nMLZgpWS7eNmGSc00eA==/com.yue.bugly.demo-LY9IeIV7FQL1DaX3KYuYAA==/lib/arm64/libBugly-ext.so (doACrash+144) [arm64-v8a:4.0.4:24c1c2f2005ea7e20337bba1bbeb2476]
3	#02 pc 0000000000222244 /apex/com.android.art/lib64/libart.so (art_quick_generic_jni_trampoline+148) [arm64-v8a::824f7ca55f9cdc7b2f2fa3478577b69e]
4	#03 pc 0000000000218964 /apex/com.android.art/lib64/libart.so (art_quick_invoke_stub+548) [arm64-v8a::824f7ca55f9cdc7b2f2fa3478577b69e]
5	#04 pc 0000000000285ff0 /apex/com.android.art/lib64/libart.so (art::ArtMethod::Invoke(art::Thread*, unsigned int*, unsigned int, art::JValue*, char const*)+192) [arm64-v8a::824f7ca55f9cdc7b2f2fa3478577b69e]
6	java:
7	com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.testNativeCrash(:532)
8	com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.testNativeCrash(:542)
9	com.tencent.bugly.crashreport.crash.c.a(:281)
10	com.tencent.bugly.crashreport.CrashReport.testNativeCrash(:183)
11	com.tencent.bugly.crashreport.CrashReport.testNativeCrash(:161)
12	com.tencent.bugly.demo.MainActivity.onClick(:48)
```

## 配置符号表

### 什么是符号表？

符号表是内存地址与函数名、文件名、行号的**映射表**。符号表元素如下所示：

<起始地址> <结束地址> <函数> [<文件名:行号>] 对于native crash，为了能快速并准确地定位用户APP发生**Crash的代码位置**，Bugly使用**符号表**
对APP发生Crash的程序**堆栈**进行**解析**和**还原**。

![Alt text](https://bugly.qq.com/docs/img/symbol-android/3001.png)