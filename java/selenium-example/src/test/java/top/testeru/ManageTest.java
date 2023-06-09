package top.testeru;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

import static java.lang.Thread.sleep;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject selenium-example
 * @Description
 * @createTime 2023年05月06日 14:36:00
 */
public class ManageTest {
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

    //
    @Test
    public void navigate() throws InterruptedException {
        webDriver.get("https://www.baidu.com/");
        sleep(2000);
        webDriver.manage().window().minimize();
        sleep(2000);
    }
}
