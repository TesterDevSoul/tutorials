package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject selenium-example
 * @Description 多窗口测试
 * @createTime 2023年04月25日 21:32:00
 */
public class HandleTest {
    public static WebDriver webDriver;
    public static WebDriverWait wait;

    @BeforeAll
    public static void bf(){
        //判断当前电脑是否有Chrome浏览器
        Optional<Path> chromeBrowserPath = WebDriverManager.chromedriver().getBrowserPath();

        if(chromeBrowserPath.isPresent()){
            //WebDriverManager去创建driver打开浏览器：打开浏览器为空白页面
            webDriver = WebDriverManager.chromedriver().create();
            //webDriver = new ChromeDriver();
        }
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(15), Duration.ofSeconds(2));

    }
    @Test
    public void moreHandleWithCeshiren() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        webDriver.get(url);
        sleep(3000);
        //获取当前窗口句柄
        String originalWindow = webDriver.getWindowHandle();
        sleep(3000);
        webDriver.findElement(By.xpath("//*[@slot=\"title\"]")).click();
        //获取所有窗口句柄
        Set<String> allWindows = webDriver.getWindowHandles();
        sleep(3000);
        System.out.println("标题1:"+webDriver.getTitle());

        // 切换到新窗口
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String title = webDriver.getTitle();
        System.out.println("标题2:" + title);
        executableList.add(() -> assertThat(
                title,
                containsString("测试人社区")));
        // 切换回默认内容
        webDriver.switchTo().window(originalWindow);
        sleep(3000);
        String title1 = webDriver.getTitle();
        executableList.add(() -> assertThat(
                title1,
                equalTo("霍格沃兹测试开发")));
        assertAll(executableList);
    }


    @Test
    public void moreHandle() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://sahitest.com/demo/";
        webDriver.get(url);
        sleep(3000);
        //获取当前窗口句柄
        String originalWindow = webDriver.getWindowHandle();
        sleep(3000);
        webDriver.findElement(By.linkText("Window Open Test")).click();
        //获取所有窗口句柄
        Set<String> allWindows = webDriver.getWindowHandles();
        sleep(3000);
        System.out.println("标题1:"+webDriver.getTitle());

        // 切换到新窗口
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        String title = webDriver.getTitle();
        System.out.println("标题2:" + title);
        executableList.add(() -> assertThat(
                title,
                containsString("")));
        // 切换回默认内容
        webDriver.switchTo().window(originalWindow);
        sleep(3000);
        String title1 = webDriver.getTitle();
        executableList.add(() -> assertThat(
                title1,
                equalTo("Sahi Tests")));
        assertAll(executableList);
    }


    @Test
    public void moreFrame() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://sahitest.com/demo/iframesTest.htm";
        webDriver.get(url);
        sleep(3000);

        // 在窗口中查找 frame，并切换到该 frame
        webDriver.switchTo().frame(0);

        executableList.add(() -> assertThat(
                "",
                equalTo("霍格沃兹测试开发")));
        assertAll(executableList);
    }
    @Test
    public void switchFrame() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable";
        webDriver.get(url);

        webDriver.switchTo().frame("iframeResult");
        String draggable = webDriver.findElement(By.id("draggable")).getText();
        System.out.println(draggable);
        executableList.add(() -> assertThat(
                draggable,
                equalTo("请拖拽我！")));


        webDriver.switchTo().parentFrame();

        webDriver.findElement(By.id("submitBTN")).click();
        String submitBTN = webDriver.findElement(By.id("submitBTN")).getText();
        System.out.println(submitBTN);

        executableList.add(() -> assertThat(
                submitBTN,
                equalTo("点击运行 》")));
        sleep(2000);
        assertAll(executableList);
    }
}
