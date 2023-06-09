///*
// * @Author: 霍格沃兹测试开发学社-盖盖
// * @Desc: '更多测试开发技术探讨，请访问：https://ceshiren.com/t/topic/15860'
// */
//
//import com.ceshiren.FakerUtil;
//import io.appium.java_client.AppiumBy;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;
//import io.appium.java_client.remote.AndroidMobileCapabilityType;
//import io.appium.java_client.remote.MobileCapabilityType;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.remote.DesiredCapabilities;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//import static java.lang.Thread.sleep;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertAll;
//
//public class AddMemberTest {
//    @Test
//    public void add() throws MalformedURLException, InterruptedException {
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        //平台名称 安卓系统就是Android 苹果手机就是iOS platformName
//        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        //使用的driver uiautomator2 automationName
//        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//        //设备的系统版本 安卓手机的系统版本，非小米、华为系统版本号  adb shell getprop ro.build.version.release
//        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0.0");
//        //启动的app的包名 第三方app：adb shell pm list packages -3    mm wework
//        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tencent.wework");
//        //启动的app的页面  adb shell monkey -p com.tencent.wework -vvv 1
//        /**
//         * // Allowing start of Intent {
//         * act=android.intent.action.MAIN
//         * cat=[android.intent.category.LAUNCHER]
//         * cmp=com.tencent.wework/.launch.LaunchSplashActivity } in package com.tencent.wework
//         */
//        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".launch.LaunchSplashActivity");
//        //设备名称
//        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "8c5f5f92");
//        //设备的UDID；adb devices -l 获取，多设备的时候要指定，若不指定默认选择列表的第一个设备
//        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "8c5f5f92");
//        //app不重置
//        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//        //运行失败的时候打印page source到appium-log   printPageSourceOnFindFailure
//        desiredCapabilities.setCapability(MobileCapabilityType.PRINT_PAGE_SOURCE_ON_FIND_FAILURE, true);
//        //在假设客户端退出并结束会话之前，Appium 将等待来自客户端的新命令多长时间（以秒为单位） http请求等待响应最长5分钟  newCommandTimeout
//        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300000);
//        //默认权限通过  autoGrantPermissions
//        desiredCapabilities.setCapability(SupportsAutoGrantPermissionsOption.AUTO_GRANT_PERMISSIONS_OPTION, true);
//
////------------------------------------------------------------  用例步骤    ------------------------------------------------------------
//        URL url = new URL("http://0.0.0.0:4723/wd/hub");
//        //1、打开app操作
//        AndroidDriver driver = new AndroidDriver(url,desiredCapabilities);
//        //添加隐式等待  15秒
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//
//
//        //2、点击  tab栏 通讯录  click()  text=通讯录
//        driver.findElement(AppiumBy.xpath("//*[@text=\"通讯录\"]")).click();
////        driver.findElement(AppiumBy.cssSelector("[text=\"通讯录\"]")).click();
////        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"通讯录\")")).click();
//
//        sleep(2000);
//
//        //3、点击  添加成员      click()   text=添加成员
////        driver.findElement(AppiumBy.xpath("//*[@text=\"添加成员\"]")).click();
//        driver.findElement(AppiumBy.cssSelector("[text=\"添加成员\"]")).click();
////        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"添加成员\")")).click();
//
//        sleep(2000);
//
//        //4、点击手动输入添加  click()  text=手动输入添加
////        driver.findElement(AppiumBy.xpath("//*[@text=\"手动输入添加\"]")).click();
////        driver.findElement(AppiumBy.cssSelector("[text=\"手动输入添加\"]")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"手动输入添加\")")).click();
//
//        sleep(2000);
//
//
//        //5、输入姓名    android.widget.EditText       clear()  + sendKeys()  必填
//        String name = FakerUtil.get_name();
//        WebElement nameEle = driver.findElement(AppiumBy.className("android.widget.EditText"));
////        driver.findElement(AppiumBy.xpath("//*[@text=\"必填\"]"));
//        nameEle.clear();
//        nameEle.sendKeys(name);
//
//        //6、输入手机号         clear()  + sendKeys()
//        String zh_phone = FakerUtil.get_zh_phone();
//        WebElement phoneEle = driver.findElements(AppiumBy.className(
//                "android.widget.EditText")).get(1);
////        driver.findElement(AppiumBy.xpath("//*[@text=\"必填\"]"));
//        phoneEle.clear();
//        phoneEle.sendKeys(zh_phone);
//
//        //7、点击  保存        click()   text=保存
////        driver.findElement(AppiumBy.xpath("//*[@text=\"保存\"]")).click();
////        driver.findElement(AppiumBy.cssSelector("[text=\"保存\"]")).click();
//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiSelector().text(\"保存\")")).click();
//
//        sleep(2000);
//
//
//        //8、点击 返回按钮 回到通讯录页面    click()  class android.widget.TextView
//        // xpath表达式 ： //*[@text="添加成员"]/../../../preceding-sibling::*
//        driver.findElement(AppiumBy.className("android.widget.TextView")).click();
//
//        sleep(2000);
//
////------------------------------------------------------------  用例验证    ------------------------------------------------------------
//
//        //9、点击搜索按钮 click()  //android.widget.LinearLayout[3]//*[@class="android.widget.TextView"]   //android.widget.TextView   --- get(1)
////        driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[3]//*[@class=\"android.widget.TextView\"]")).click();
//        driver.findElements(AppiumBy.className("android.widget.TextView")).get(1).click();
//
//        sleep(2000);
//
//        //10、输入姓名 clear() + sendkeys()
////        driver.findElement(AppiumBy.xpath("//*[@text=\"搜索\"]"));
////        driver.findElement(AppiumBy.cssSelector("[text=\"搜索\"]"));
//        WebElement searchEle = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"搜索\")"));
//        searchEle.clear();
//        searchEle.sendKeys(name);
//        sleep(2000);
//
//        //11、点击联系人下的第一个结果  click()  进入到个人信息页面
//        //联系人父类的弟弟  xpath:  //*[@text="联系人"]/../following-sibling::*//*[@class="android.widget.TextView"]
//        driver.findElement(
//                AppiumBy.xpath(
//                        "//*[@text=\"联系人\"]/../following-sibling::*//*[@class=\"android.widget.TextView\"]"))
//                .click();
//
//        sleep(2000);
//
//        //12、个人信息页面 - 获取姓名  getText()
//        //头像右侧为姓名 xpath : //*[@class="android.widget.ImageView"]/following-sibling::*//*[@class="android.widget.TextView"]
//        WebElement getNameEle = driver.findElement(AppiumBy.xpath("//*[@class=\"android.widget.ImageView\"]/following-sibling::*//*[@class=\"android.widget.TextView\"]"));
//        String searchName = getNameEle.getText();
//
//
//        //13、获取手机号   getText()
//        //手机文本右侧为手机号 xpath: //*[@text="手机"]/following-sibling::*//*[@class="android.widget.TextView"]
//        WebElement getPhoneEle = driver.findElement(AppiumBy.xpath("//*[@text=\"手机\"]/following-sibling::*//*[@class=\"android.widget.TextView\"]"));
//        String searchPhone = getPhoneEle.getText();
//
//
//
//        //14、点击个人信息页面的返回按钮  click()
//        driver.findElement(AppiumBy.className("android.widget.TextView")).click();
//
//        sleep(2000);
//        //15、点击搜索页面的取消按钮 click()  android.widget.ImageView
//        driver.findElement(AppiumBy.className("android.widget.ImageView")).click();
//
//        //16、点击搜索页面返回按钮  click()
//        driver.findElement(AppiumBy.className("android.widget.TextView")).click();
//
//        //断言
//        assertAll(
//                //断言输入的姓名和搜索结果是否一致
//                () -> assertThat(searchName, is(equalTo(name))),
//                () -> assertThat(searchPhone, is(equalTo(zh_phone)))
//        );
//
//
//
//
//
//    }
//}
