package com.tencent.bugly.demo.mockcrash;

/**
 * Description:
 *
 * @author: yue
 * Time: 2020/5/25
 */
public class CrashMock {
    public static void mockJavaCrash() {
        throw new RuntimeException();
    }
}
