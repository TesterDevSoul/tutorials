package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

import static java.lang.Thread.sleep;


/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject selenium-example
 * @Description 下拉菜单示例
 * https://sahitest.com/demo/selectTest.htm
 * @createTime 2023年04月25日 11:12:00
 */
public class SelectTest {
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
    public void selectBySelfHtml() throws InterruptedException {
        String url = "file:///Users/gaigai/tutorials/selenium-combat/select.html";
        webDriver.get(url);
        sleep(3000);
        WebElement seleEle = webDriver.findElement(By.name("selectList"));
        //必须是select标签
        Select select = new Select(seleEle);
        //索引定位
        select.selectByIndex(1);
        sleep(3000);
        //可见文本
        select.selectByVisibleText("38岁以上");
        sleep(3000);
        //Value值
        select.selectByValue("youth");
        sleep(3000);
    }
    //取消选择
    @Test
    public void deselectBySelfHtml() throws InterruptedException {
        String url = "file:///Users/gaigai/tutorials/selenium-combat/selectmore.html";
        webDriver.get(url);
        sleep(3000);
        WebElement seleEle = webDriver.findElement(By.name("selectList"));
        //必须是select标签
        Select select = new Select(seleEle);
        //索引定位
        select.selectByIndex(1);
        select.selectByIndex(3);
        sleep(3000);

        select.deselectByIndex(1);
        sleep(3000);

        //可见文本
        select.selectByVisibleText("18岁以下");
        select.selectByVisibleText("38岁以上");
        sleep(3000);

        select.deselectByVisibleText("38岁以上");
        sleep(3000);

        //Value值
        select.selectByValue("youth");
        select.selectByValue("teens");
        sleep(3000);

        select.deselectByValue("youth");
        sleep(3000);
        select.deselectByValue("youth");
        sleep(3000);

        select.deselectAll();
        sleep(3000);
    }

    @Test
    public void selectBy() throws InterruptedException {
        String url = "https://sahitest.com/demo/selectTest.htm";
        webDriver.get(url);
        sleep(2000);
        //第一个
        WebElement seleEle1 = webDriver.findElement(By.id("s1Id"));
        //必须是select标签
        Select select1 = new Select(seleEle1);
        //索引定位
        select1.selectByIndex(1);
        sleep(2000);


        WebElement seleEle3 = webDriver.findElement(By.id("s3Id"));
        //必须是select标签
        Select select3 = new Select(seleEle3);

        //Value值
        select3.selectByValue("o2val");
        sleep(2000);
        //可见文本
        select3.selectByVisibleText("    With spaces");
        sleep(2000);
    }


    //取消选择
    @Test
    public void deselectBy() throws InterruptedException {
        String url = "https://sahitest.com/demo/selectTest.htm";
        webDriver.get(url);
        sleep(3000);
        WebElement seleEle = webDriver.findElement(By.id("s4Id"));
        //必须是select标签
        Select select = new Select(seleEle);
        //索引定位
        select.selectByIndex(1);
        select.selectByIndex(2);
        select.selectByIndex(3);
        sleep(3000);

        select.deselectByIndex(1);
        sleep(3000);

        //可见文本
        select.selectByVisibleText("    With spaces");
        sleep(3000);

        select.deselectByVisibleText("o3");
        sleep(3000);

        //Value值
        //选中所有value都是o4val的内容
        select.selectByValue("o4val");
        sleep(3000);
        select.deselectByValue("o4val");
        sleep(3000);


        select.deselectAll();
        sleep(3000);
    }

}
