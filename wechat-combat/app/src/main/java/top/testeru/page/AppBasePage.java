package top.testeru.page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.app.SupportsAutoGrantPermissionsOption;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import top.testeru.entity.AddMember;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Arrays;
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

    private void killPort(int port){
        String os = System.getProperty("os.name");
        logger.info("当前操作系统：{}" + os);

        if (os.contains("macOS")){
            // 使用lsof命令获取指定端口的进程PID
            String command = "lsof -i tcp:";
            // 使用lsof命令获取指定端口的进程PID
            String  killCommand = "kill -9";
            commandRun(port, command, killCommand);
        }else if(os.contains("Windows")){
            String command = "cmd.exe /c netstat -ano | findstr :";
            String killCommand = "cmd.exe /c taskkill /F /PID ";
            commandRun(port, command, killCommand);
        }


    }

    private static void commandRun(int port, String command, String killCommand) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command + port);
            Process process = processBuilder.start();

            // 读取lsof命令 /netstat命 的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("LISTEN")) {
                    // 提取PID
                    String[] columns = line.split("\\s+");
                    System.out.println("columns: " + columns);
                    String pid = columns[1];

                    // 使用kill命令杀死进程
                    ProcessBuilder killProcessBuilder = new ProcessBuilder(killCommand + pid);
                    killProcessBuilder.start();

                    logger.info("端口 {} 的进程已被终止", port);
                    break;
                }
            }
            process.waitFor();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AppBasePage() {
        //driver需要声明
        if (null == androidDriver){
            deletedir();
            mkdir();
            killPort(4723);
            //appium server
            CommandLine commandLine = new CommandLine("appium");
            commandLine.addArgument("-g");
            commandLine.addArgument("appiumserver.log");
            commandLine.addArgument("--port");
            commandLine.addArgument("4723");

            // 创建一个线程来执行命令
            Thread commandThread = new Thread(() -> {
                            //使用Apache Commons Exec库来执行外部命令
                            //DefaultExecuteResultHandler是一个实现了ExecuteResultHandler接口的默认执行结果处理程序。
                            // 它用于在执行命令后处理命令的结果。
                            DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
                            //设置执行结果处理程序（DefaultExecuteResultHandler）和执行器（DefaultExecutor）的一些属性
                            //DefaultExecutor是一个执行器，用于执行外部命令。它提供了一些属性和方法来配置和管理执行过程。
                            DefaultExecutor executor = new DefaultExecutor();
                            //设置执行器期望的命令退出值。
                            executor.setExitValue(1);
                            try {
                                executor.execute(commandLine, handler);
                                // 等待命令执行完成
                                handler.waitFor();
                                // 处理执行结果
                                int exitValue = handler.getExitValue();
                                if (exitValue == 0) {
                                    logger.info("appium server命令执行成功");
                                } else {
                                    logger.info("appium server命令执行失败");
                                }
                            } catch (ExecuteException e) {
                                // 处理命令执行异常
                                e.printStackTrace();
                            } catch (Exception e) {
                                // 处理其他异常
                                e.printStackTrace();
                            }
            });

            // 启动命令执行线程
            commandThread.start();


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

    private void deletedir() {
        try {
            File file = Paths.get("png").toFile();
            if(file.exists() && file.isDirectory()){
                FileUtils.deleteDirectory(file);
                logger.info(" png 文件夹删除");
            }else {
                logger.info(" png 文件夹不存在");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void mkdir() {
        File file = Paths.get("png").toFile();
        if(!file.exists() || !file.isDirectory()){
            file.mkdirs();
            logger.info("创建 png 文件夹");
        }else {
            logger.info(" png 文件夹已经存在");

        }
    }
    //@Step("元素定位：{by}")
    public WebElement find(By by){
        return find(by,true);
    }
    @Step("元素定位：{by}")
    public WebElement find(By by,Boolean flag){
        WebElement element = androidDriver.findElement(by);
        logger.info("元素定位：{}",by);
        if(flag){
            try {
                ElementScreenBase(element, by.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
    public WebDriverWait waitUtil(){
        WebDriverWait wait = new WebDriverWait(androidDriver,
                Duration.ofSeconds(20), //每隔多少秒去查找一次显示等待的条件
                Duration.ofSeconds(1));//总共查找等待条件的时间
        return wait;
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
    //返回 安卓手机的滑动返回
    public void back(){
        logger.info("滑动返回");
        Dimension dimension = androidDriver.manage().window().getSize();
        //x 0 0.5
        Point startPoint = new Point((int) (dimension.width * 0), (int) (dimension.height * 0.5));
        //x 0.9 0.5
        Point endPoint = new Point((int) (dimension.width * 0.9), (int) (dimension.height * 0.5));
        swipe(startPoint, endPoint);

    }
    public void swipe(Point startPoint, Point endPoint){

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence dragNDrop = new Sequence(finger, 1)
                                //手指移动到起始坐标点
                                .addAction(finger.createPointerMove(Duration.ofMillis(0),
                                        PointerInput.Origin.viewport(), startPoint.x, startPoint.y))
                                //手指按下
                                .addAction(finger.createPointerDown(0))
                                //滑动到第二个坐标点 滑动时间是1秒
                                .addAction(finger.createPointerMove(Duration.ofMillis(1000),
                                        PointerInput.Origin.viewport(),endPoint.x, endPoint.y))
                                //手指释放
                                .addAction(finger.createPointerUp(0));
        androidDriver.perform(Arrays.asList(dragNDrop));

    }

    //getText
    public String text(By by, boolean flag){
       return find(by,flag).getText();
    }
    public String text(By by){
        return find(by).getText();
    }
    public String page(){
        return androidDriver.getPageSource();
    }


    //截图
    private void ElementScreenBase(WebElement element, String message) throws IOException {
        //Appium截图 不是与手机1:1截图
        File screenshot = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
        //元素定位标红
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        int eleX = elementLocation.x;
        int eleY = elementLocation.y;
        int eleH = elementSize.height;
        int eleW = elementSize.width;
        logger.info("location=${} size=${} x=${} y=${} width=${} height=${}",
                elementLocation,elementSize,eleX,eleY,eleW,
                eleH);
        //读取截图文件
        BufferedImage img = ImageIO.read(screenshot);
        //创建一个 Graphics2D，可用于绘制到此 BufferedImage 中
        Graphics2D graph = img.createGraphics();
        //BasicStroke 指定线宽的实心  描边 Shape 的 Stroke 对象
        graph.setStroke(new BasicStroke(5));
        graph.setColor(Color.RED);//绘制形状的颜色
        graph.drawRect(eleX, eleY, eleW, eleH);//绘制指定矩形的轮廓
        graph.dispose();//处理此图形上下文并释放它正在使用的任何系统资源
        Path pngPath = getPngPath();
        //ImageIO.write 方法将图像写入文件时，不需要手动关闭流。
        // 根据官方文档，ImageIO.write 方法会自动将数据写入文件并关闭相关资源。
        ImageIO.write(img, "png", pngPath.toFile());
        Allure.addAttachment(message,"image/png",new FileInputStream(pngPath.toFile()),".png");
    }

    private Path getPngPath() {
        long l = System.currentTimeMillis();
        return Paths.get("png",l+".png");
    }
}
