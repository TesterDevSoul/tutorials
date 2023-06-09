package top.testeru;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AppiumKeyWords {
    private static AndroidDriver androidDriver;
    private static AppiumKeyWords instance;

    //    私有构造函数，防止外部实例化
    private AppiumKeyWords() {
        //        初始化 WebDriver
        //driver需要声明
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
        //在假设客户端退出并结束会话之前，Appium 将等待来自客户端的新命令多长时间（以秒为单位） http请求等待响应最长5分钟
        // newCommandTimeout
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300000);
        //默认权限通过  autoGrantPermissions
        desiredCapabilities.setCapability(SupportsAutoGrantPermissionsOption.AUTO_GRANT_PERMISSIONS_OPTION,
                true);
        try {
            //1、打开app操作
            androidDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),desiredCapabilities);
            //隐式等待
            androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    // 获取 AppiumKeyWords 实例
    public static AppiumKeyWords getInstance() {
        if (instance == null) {
            // 如果 instance 为空，创建一个实例
            synchronized (AppiumKeyWords.class) {
                if (instance == null) {
                    instance = new AppiumKeyWords();
                }
            }
        }
        return instance;
    }
    private By by(Map<String, Object> map){
        System.out.println("by");
        System.out.println(map);
        String by = map.get("by").toString();
        String expression = map.get("expression").toString();
        // 根据 by 和 expression 参数创建相应的 By 对象
        if (by.equalsIgnoreCase("id")) {
            return By.id(expression);
        } else if (by.equalsIgnoreCase("xpath")) {
            return By.xpath(expression);
        } else if (by.equalsIgnoreCase("css")) {
            return By.cssSelector(expression);
        } else if (by.equalsIgnoreCase("name")) {
            return By.name(expression);
        } else if (by.equalsIgnoreCase("class")) {
            return By.className(expression);
        }else if(by.equalsIgnoreCase("android")){
            System.out.println("androidUIAutomator");
            return AppiumBy.androidUIAutomator(expression);
        }
        else {
            throw new IllegalArgumentException("Unsupported by type: " + by);
        }
    }
    public WebElement find(Map<String, Object> map){
        System.out.println("find");
        System.out.println(map);
//        return null;
        WebElement webElement = androidDriver.findElement(by(map));
        System.out.println(webElement);
        return webElement;
    }
    public List<WebElement> finds(Map<String, Object> map){
        System.out.println("find");
        System.out.println(map);
//        return null;
        List<WebElement> webElements = androidDriver.findElements(by(map));
        System.out.println(webElements);
        return webElements;
    }
    public void click(Map<String, Object> map){
        System.out.println("click");
        System.out.println(map);
//        return null;
        WebElement webElement = androidDriver.findElement(by(map));
        webElement.click();
    }

    public void waitUtil(Map<String, Object> map){
        System.out.println("waitUtil");
        System.out.println(map);
        WebDriverWait wait = new WebDriverWait(androidDriver,
                Duration.ofSeconds(20), //每隔多少秒去查找一次显示等待的条件
                Duration.ofSeconds(1));//总共查找等待条件的时间
        String key = map.get("key").toString();
        HashMap<String, String> value = (HashMap<String, String>) map.get("value");
        if(key.equals("contains")){
            if(value.get("seq").equals("pageSource")){
                //显示等待判断条件
                wait.until(webDriver -> {
                    String pageSource = webDriver.getPageSource();
                    return StringUtils.contains(pageSource, value.get("searchSeq"));
                });
            }

        }
    }

    public void send(Map<String, Object> map){
        System.out.println("send");
        System.out.println(map);
        Map<String, Object> byMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getKey().equals("by") || entry.getKey().equals("expression")) {
                byMap.put(entry.getKey(), entry.getValue());
            }
        }
        WebElement webElement = null;
        if(map.entrySet().size() == 3){
            webElement = find(byMap);
        }else {
            List<WebElement> webElementList = finds(byMap);
            webElement = webElementList.get(Integer.parseInt(map.get("index").toString()));
        }

        webElement.clear();
        webElement.sendKeys(map.get("text").toString());
    }

}
