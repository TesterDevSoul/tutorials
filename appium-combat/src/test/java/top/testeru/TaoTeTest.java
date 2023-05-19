package top.testeru;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class TaoTeTest {
    @Test
    public void add() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //平台名称 安卓系统就是Android 苹果手机就是iOS platformName
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //使用的driver uiautomator2 automationName
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //设备的系统版本 安卓手机的系统版本，非小米、华为系统版本号  adb shell getprop ro.build.version.release
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0.0");
        //启动的app的包名 第三方app：adb shell pm list packages -3    mm wework
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.taobao.litetao");
        //启动的app的页面  adb shell monkey -p com.tencent.wework -vvv 1
        /**
         * // Allowing start of Intent {
         * act=android.intent.action.MAIN
         * cat=[android.intent.category.LAUNCHER]
         * cmp=com.tencent.wework/.launch.LaunchSplashActivity } in package com.tencent.wework
         */
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.taobao.ltao.maintab.MainFrameActivity");
        //设备名称
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "8c5f5f92");
        //设备的UDID；adb devices -l 获取，多设备的时候要指定，若不指定默认选择列表的第一个设备
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "8c5f5f92");
        //app不重置
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        //运行失败的时候打印page source到appium-log   printPageSourceOnFindFailure
        desiredCapabilities.setCapability(MobileCapabilityType.PRINT_PAGE_SOURCE_ON_FIND_FAILURE, true);
        //在假设客户端退出并结束会话之前，Appium 将等待来自客户端的新命令多长时间（以秒为单位） http请求等待响应最长5分钟  newCommandTimeout
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300000);
        //默认权限通过  autoGrantPermissions
        desiredCapabilities.setCapability(SupportsAutoGrantPermissionsOption.AUTO_GRANT_PERMISSIONS_OPTION, true);

//------------------------------------------------------------  用例步骤    ------------------------------------------------------------
        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        //1、打开app操作
        AndroidDriver driver = new AndroidDriver(url, desiredCapabilities);
        //添加隐式等待  15秒
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        sleep(10000);
        System.out.println(driver.getPageSource());
    }
}
