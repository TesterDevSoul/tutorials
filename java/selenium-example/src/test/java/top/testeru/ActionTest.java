package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject selenium-example
 * @Description 交互测试
 * https://sahitest.com/demo/clicks.htm
 * @createTime 2023年04月25日 14:49:00
 */
public class ActionTest {
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

    //鼠标的单击、双击、右键点击操作
    @Test
    public void mouseClick() throws InterruptedException {

        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/clicks.htm";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        //双击
        WebElement doubleBtn = webDriver.findElement(By.xpath("//*[@value=\"dbl click me\"]"));
        actions.doubleClick(doubleBtn).perform();
        executableList.add(() -> assertThat(
                webDriver.getPageSource(),
                containsString("[DOUBLE_CLICK]")));

        //单击
        WebElement clickBtn = webDriver.findElement(By.xpath("//*[@value=\"click me\"]"));
        actions.click(clickBtn).perform();
        executableList.add(() -> assertThat(
                webDriver.getPageSource(),
                containsString("[CLICK]")));

        //右键点击
        WebElement rightClickBtn = webDriver.findElement(By.xpath("//*[@value=\"right click me\"]"));
        actions.contextClick(rightClickBtn).perform();
        executableList.add(() -> assertThat(
                webDriver.getPageSource(),
                containsString("[RIGHT_CLICK]")));

        assertAll(executableList);
    }


    @Test
    public void actionOnHover() throws InterruptedException{
        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/mouseover.htm";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        WebElement WriteEle = webDriver.findElement(By.xpath("//*[@value=\"Write on hover\"]"));
        //悬停显示文本
        actions.moveToElement(WriteEle).perform();
        sleep(3000);
        //t1
        String value = webDriver.findElement(By.name("t1")).getAttribute("value");
        //sleep(3000);
        executableList.add(() -> assertThat(
                value,
                containsString("Mouse moved")));

        WebElement BlankEle = webDriver.findElement(By.xpath("//*[@value=\"Blank on hover\"]"));
        //悬停不显示文本
        actions.moveToElement(BlankEle).perform();
        //t1
        String valueBlank = webDriver.findElement(By.name("t1")).getAttribute("value");
        //sleep(3000);
        executableList.add(() -> assertThat(
                valueBlank,
                containsString("")));
        assertAll(executableList);
    }

    @Test
    public void actiondragAndDrop() throws InterruptedException {

        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/dragDropMooTools.htm";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        //拖拽的元素
        WebElement draggerEle = webDriver.findElement(By.id("dragger"));
        WebElement item1Ele = webDriver.findElement(By.xpath("//*[text()=\"Item 1\"]"));
        //从draggerEle元素拖拽到item1Ele
        actions.dragAndDrop(draggerEle, item1Ele).perform();
        WebElement drop1Ele = webDriver.findElement(By.xpath("//div[2]"));
        executableList.add(() -> assertThat(
                drop1Ele.getText(),
                equalTo("dropped")));
        sleep(3000);
        assertAll(executableList);
    }
    //drag3 拖动到 id="div1"中  
    @Test
    public void actiondragAndDropTransfer() throws InterruptedException {
        // TODO: 2023/4/25 未实现图像拖拽
        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/dragDropDataTransfer.htm";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        //拖拽的元素
        WebElement draggerEle = webDriver.findElement(By.id("drag3"));
        WebElement item1Ele = webDriver.findElement(By.id("div1"));
        //从draggerEle元素拖拽到item1Ele
        //actions.dragAndDrop(draggerEle, item1Ele).perform();

        actions.clickAndHold(draggerEle)
                .moveToElement(item1Ele)
                .release()
                .build()
                .perform();
        sleep(3000);
    }
    @Test
    public void actiondragAndDropLogin() throws InterruptedException {

        List<Executable> executableList = new ArrayList<>();
        String url = "https://vip.ceshiren.com/#/ui_study/action_chains";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        //拖拽的元素
        WebElement draggerEle = webDriver.findElement(By.id("item1"));
        WebElement item1Ele = webDriver.findElement(By.id("item3"));
        //从draggerEle元素拖拽到item1Ele
        actions.dragAndDrop(draggerEle, item1Ele).perform();
        executableList.add(() -> assertThat(
                webDriver.getPageSource(),
                containsString("验证通过")));
        sleep(3000);
        assertAll(executableList);
    }


    @Test
    public void actionSendAndEnter() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/keypress.htm";
        webDriver.get(url);

        //开始使用鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        String beforeValue = webDriver.findElement(By.name("t3")).getAttribute("value");
        executableList.add(() -> assertThat(
                beforeValue,
                equalTo("Loaded")));
        WebElement sendEle = webDriver.findElement(By.name("t2"));

        actions
                .sendKeys(sendEle, "Selenium")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        String afterValue = webDriver.findElement(By.name("t3")).getAttribute("value");
        executableList.add(() -> assertThat(
                afterValue,
                equalTo("submitted")));
        sleep(3000);
        assertAll(executableList);
    }



    @Test
    public void actionKey() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();
        String url = "https://www.google.com/";
        webDriver.get(url);
        // 打开 Google 首页
        sleep(3000);

        // 找到搜索框元素
        WebElement searchBox = webDriver.findElement(By.name("q"));

        // 创建 Actions 实例
        Actions actions = new Actions(webDriver);

        // 在搜索框中输入文本
        actions.sendKeys(searchBox, "Selenium")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        // 切换到搜索结果页面的第一个链接
        WebElement firstLink = webDriver.findElement(By.className("l"));
        actions.keyDown(Keys.COMMAND)
                .click(firstLink)
                .keyUp(Keys.COMMAND)
                .build()
                .perform();


        sleep(3000);
        assertAll(executableList);
    }


    //打开新窗口
    @Test
    public void actionNewPage() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();
        String url = "https://sahitest.com/demo/";
        webDriver.get(url);

        WebElement AlertEle = webDriver.findElement(By.xpath("//*[text()=\"Alert Test\"]"));
        executableList.add(() -> assertThat(
                webDriver.getTitle(),
                equalTo("Sahi Tests")));
        // 创建 Actions 实例
        Actions actions = new Actions(webDriver);
        actions.keyDown(Keys.COMMAND)
                .click(AlertEle)
                .keyUp(Keys.COMMAND)
                .build()
                .perform();

        // 切换到新窗口
        String originalWindow = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }


        executableList.add(() -> assertThat(
                webDriver.getTitle(),
                equalTo("Alert Test")));
        sleep(3000);
        assertAll(executableList);
    }


    //复制粘贴
    //
    @Test
    public void ctrlV() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();
        String url = "https://www.google.com/";
        webDriver.get(url);
        sleep(3000);
        WebElement searchEle = webDriver.findElement(By.name("q"));
        searchEle.sendKeys("Selenium4");
        sleep(3000);
        // 创建 Actions 实例
        Actions actions = new Actions(webDriver);
        actions
                //.sendKeys(searchEle, "Selenium")
                .keyDown(Keys.CONTROL)
                .sendKeys("x")
                .keyUp(Keys.CONTROL)
                .perform();  // 剪切操作

        //actions
        //        .keyDown(Keys.SHIFT)
        //        .sendKeys(searchEle, "Selenium")//输入内容shift+Selenium大写
        //        .keyDown(Keys.COMMAND)
        //        .sendKeys("xvvvvv")//剪切复制
        //        .keyUp(Keys.COMMAND)
        //        .build()
        //        .perform();

        //executableList.add(() -> assertThat(
        //        webDriver.getTitle(),
        //        equalTo("Alert Test")));
        sleep(3000);
        assertAll(executableList);
    }


}
