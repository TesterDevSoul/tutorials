package top.testeru.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject wechat-combat
 * @Description 基本页面工厂
 * @createTime 2023年05月06日 19:22:00
 */
public class BaseProPage {
    public static final Logger logger = getLogger(lookup().lookupClass());

    public WebDriver driver;
    static List<HashMap<String,Object>> cookies = null;
    public static WebDriverWait wait;

    public BaseProPage(WebDriver driver) {
        this.driver = driver;
        //显示等待声明
        //wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }
    public BaseProPage() {
        if(null == driver){
            //cookie文件是否删除的判断
            //如果cookie文件存在
            //cookieFileisDelete();
            //driver = new ChromeDriver();
            driver = WebDriverManager.chromedriver().create();//调用Chrome浏览器
            //隐式等待
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            //显示等待声明
            wait = new WebDriverWait(driver, Duration.ofSeconds(15), Duration.ofSeconds(2));

            //cookie登录
            cookieFileisDelete();
            cookieLogin();
        }
        //窗口最大化
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);

    }

    private static void cookieFileisDelete() {
        File cookieFile = Paths.get("cookie.yaml").toFile();
        if(cookieFile.exists()){
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};

            try {
                cookies = mapper.readValue(cookieFile, typeReference);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //读取出来的cookie对象获取 expiry 不为空的list集合
            List<Long> expiryList = new ArrayList<>();

            cookies.forEach(
                    cookie -> {

                        if(null != cookie.get("expiry")){
                            expiryList.add(Long.parseLong(cookie.get("expiry").toString()));
                        }
                    }
            );
            logger.info("expiryList:" + expiryList);//[1699260234000,1670316244000]
            //Long getCookieTime = expiryList.get(0) - 一年的毫秒级的数值;31536000000
            Long getCookieTime = expiryList.get(0) - 31536000000L;
            //     7200s
            long now = System.currentTimeMillis();//当前的毫秒级别
            if( (now - getCookieTime)/1000  > 7200){
                //cookie文件删除操作
                if(cookieFile.delete()){
                    logger.info("文件删除成功");
                }else{
                    logger.info("文件删除失败");
                }
            }
        }
    }

    private void cookieLogin() {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");


        if(!Paths.get("cookie.yaml").toFile().exists()){//如果当前项目下cookie文件不存在----扫码登录
            //cookie文件写入
            wait.until(webDriver1 -> StringUtils.contains(webDriver1.getCurrentUrl(),"wework_admin/frame"));
            //获取登录成功的cookie，存入yaml文件
            Set<Cookie> cookies = driver.manage().getCookies();

            //yaml文件保存
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            //new File(路径)     -------->     Paths.get(路径).toFile()
//        mapper.writeValue(new File("cookie.yaml"), cookies);
            try {
                mapper.writeValue(Paths.get("cookie.yaml").toFile(), cookies);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        //cookie文件读取

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};

        try {
            cookies = mapper.readValue(Paths.get("cookie.yaml").toFile(), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cookies.stream()
                .filter(cookie ->
                        StringUtils.contains(cookie.get("domain").toString(),
                                ".work.weixin.qq.com"))

                //只要是".work.weixin.qq.com" 说明是企业微信的cookie
                .forEach(cookie -> {
                    //cookie放入浏览器操作
                    Cookie cookie1 = new Cookie(
                            cookie.get("name").toString(),
                            cookie.get("value").toString(),
                            cookie.get("path").toString()
                    );
                    driver.manage().addCookie(cookie1);
                });
        //刷新浏览器   --- 跳转到首页
        driver.navigate().refresh();
        //PageFactory.initElements(driver, this);

    }

    @Step("元素查找:{by}")
    public WebElement find(By by){
        WebElement element = driver.findElement(by);
        HighElement(element);
        //截图
        screen();
        //元素高亮去除
        UnHighElement(element);
        return element;
    }
    public void quite(){
        driver.quit();//浏览器退出操作
    }
    //click
    public void click(By by){
        find(by).click();
    }
    public void click(WebElement element){
        HighElement(element);
        //截图
        screen();
        //元素高亮去除
        UnHighElement(element);
        element.click();
    }
    //send  --- clear、sendKeys
    public void send(By by, String text){
        WebElement element = find(by);
        element.clear();
        element.sendKeys(text);
    }
    public void send(WebElement element, String text){
        HighElement(element);
        //截图
        screen();
        //元素高亮去除
        UnHighElement(element);
        element.clear();
        element.sendKeys(text);
    }
    //getText -- element.getText()
    public String getText(By by){
        WebElement element = find(by);
        return element.getText();
    }
    //finds  -- List<Elements>
    @Step("元素查找:{by}")
    public List<WebElement> finds(By by){
        List<WebElement> elements = driver.findElements(by);

        elements.forEach(webElement -> {
            HighElement(webElement);
        });
        //截图
        screen();

        elements.forEach(webElement -> {
            //元素高亮去除
            UnHighElement(webElement);
        });

        return elements;
    }

    public List<String> getTexts(By by){
        List<String> texts = new ArrayList<>();
        finds(by).forEach(ele -> {
            texts.add(ele.getText());
        });
        return texts;
    }
    public List<String> getAttributes(By by, String attribute){
        List<String> texts = new ArrayList<>();
        finds(by).forEach(ele -> {
            texts.add(ele.getAttribute(attribute));
        });
        return texts;
    }
    public List<String> getAttributes(List<WebElement> elements, String attribute){
        List<String> texts = new ArrayList<>();
        elements.forEach(ele -> {
            texts.add(ele.getAttribute(attribute));
        });
        return texts;
    }

    public String page() {
        return driver.getPageSource();
    }

    //截图
    private void screen(){
        long now = System.currentTimeMillis();
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path jpg = Paths.get("jpg", now + ".jpg");
        File screenJpg = jpg.toFile();
        try {
            FileUtils.copyFile(screenshotAs, screenJpg);
            //allure报告添加截图  --  allure添加附件
            Allure.addAttachment(jpg.toString(),
                    "image/jpg",
                    Files.newInputStream(jpg),
                    "jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //js 元素高亮
    private WebDriver HighElement(WebElement webElement){
        if(driver instanceof JavascriptExecutor)
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'",webElement);
        return driver;
    }

    private WebDriver UnHighElement(WebElement webElement){
        if(driver instanceof JavascriptExecutor)
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''",webElement);
        return driver;
    }

    //显示等待
    public void waitWithPage(String text){
        wait.until(webDriver -> webDriver.getPageSource().contains(text));

    }


}
