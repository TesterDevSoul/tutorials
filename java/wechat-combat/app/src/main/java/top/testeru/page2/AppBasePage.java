package top.testeru.page2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import top.testeru.entity.AddMember;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

//关键字对应的page页面
public class AppBasePage {
    static final Logger logger = getLogger(lookup().lookupClass());

    AndroidDriver androidDriver;
    static AddMember user = new AddMember();

    public AppBasePage(AndroidDriver androidDriver) {
        this.androidDriver = androidDriver;
    }

    public AppBasePage() {
        //driver需要声明
        if (null == androidDriver){
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //平台名称 安卓系统就是Android 苹果手机就是iOS platformName
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            //使用的driver uiautomator2 automationName
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            //设备的系统版本 安卓手机的系统版本，非小米、华为系统版本号  adb shell getprop ro.build.version.release
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0.0");
            //启动的app的包名 第三方app：adb shell pm list packages -3    mm wework
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tencent.wework");
            //启动的app的页面  adb shell monkey -p com.tencent.wework -vvv 1
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".launch.LaunchSplashActivity");
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
            try {
                //1、打开app操作
                androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),desiredCapabilities);
                //隐式等待
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

   
    @Step("元素定位：{by}")
    public WebElement find(By by){
        WebElement element = androidDriver.findElement(by);
        logger.info("元素定位：{}",by);
        return element;
    }
    
    public List<WebElement> finds(By by){
        return androidDriver.findElements(by);
    }

    public void click(By by){
        find(by).click();
    }
    public void click(By by, int index){
        finds(by).get(index).click();
    }
    //输入操作
    public void send(By by, String str){
        WebElement webElement = find(by);
        webElement.clear();
        webElement.sendKeys(str);
    }
    public void send(By by, int index, String str){
        WebElement webElement = finds(by).get(index);
        webElement.clear();
        webElement.sendKeys(str);
    }
    public String text(By by){
        return find(by).getText();
    }

    public List<String> texts(By by){
        List<String> textList = new ArrayList<>();
        List<WebElement> findEles = finds(by);
        findEles.forEach(webElement -> {
            textList.add(webElement.getText());
        });
        return textList;
    }

    public WebDriverWait waitUtil(){
        WebDriverWait wait = new WebDriverWait(androidDriver,
                Duration.ofSeconds(20), //每隔多少秒去查找一次显示等待的条件
                Duration.ofSeconds(1));//总共查找等待条件的时间
        return wait;
    }
    public String page(){
        return androidDriver.getPageSource();
    }
}
