package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.Alert;
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
 * @Description 弹窗测试
 * @createTime 2023年04月26日 15:45:00
 */
public class AlterTest {

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
    public void alterCeshiren() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        webDriver.get(url);
        //sleep(3000);

        webDriver.findElement(By.id("warning_btn")).click();
        //获取弹窗Alert对象
        Alert alert = webDriver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        //sleep(3000);

        executableList.add(() -> assertThat(
                text,
                equalTo("alert哦，这是一条alert消息")));
        assertAll(executableList);
    }

    @Test
    public void alterTest() throws InterruptedException {
        List<Executable> executableList = new ArrayList<>();

        String url = "https://sahitest.com/demo/alertTest.htm";
        webDriver.get(url);
        //sleep(3000);

        webDriver.findElement(By.xpath("//*[@value=\"Click For Alert\"]")).click();
        //获取弹窗Alert对象
        Alert alert = webDriver.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        //sleep(3000);

        executableList.add(() -> assertThat(
                text,
                equalTo("Alert Message")));
        assertAll(executableList);
    }

}
