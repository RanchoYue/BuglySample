package com.tencent.bugly.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.demo.mockcrash.CrashMock;


/**
 * 简单测试crash
 * 注：如想查看crash日志，需先到http://bugly.qq.com/注册app，并配置appID，之后就可以在bugly查看到日志啦
 *
 * @author yue
 * @date 2016/5/23
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTestJavaCrash;
    private Button btnTestANRCrash;
    private Button btnTestNativeCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTestJavaCrash = (Button) findViewById(R.id.btnTestJavaCrash);
        btnTestANRCrash = (Button) findViewById(R.id.btnTestANRCrash);
        btnTestNativeCrash = (Button) findViewById(R.id.btnTestNativeCrash);
        btnTestJavaCrash.setOnClickListener(this);
        btnTestANRCrash.setOnClickListener(this);
        btnTestNativeCrash.setOnClickListener(this);
        TestJNI testJNI = new TestJNI();
        findViewById(R.id.btnLocalNativeCrash).setOnClickListener(view -> {
            testJNI.encrypt("hi crash");
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTestJavaCrash:
                CrashMock.mockJavaCrash();
                break;
            case R.id.btnTestANRCrash:
                CrashReport.testANRCrash();
                break;
            case R.id.btnTestNativeCrash:
                CrashReport.testNativeCrash();
                break;
        }
    }
}
