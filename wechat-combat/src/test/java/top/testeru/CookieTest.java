package top.testeru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import java.sql.Date;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class CookieTest {

    static final Logger logger = getLogger(lookup().lookupClass());


    @Test
    @DisplayName("扫码登录获取cookie")
    public void getCookieToYaml() throws IOException {

        //WebDriverManager去创建driver打开浏览器：打开浏览器为空白页面
        WebDriver webDriver = WebDriverManager.chromedriver().create();

        //企业微信登录地址
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //webDriver.navigate().to("https://work.weixin.qq.com/wework_admin/loginpage_wx");

        //扫码登录cookie获取
        logger.info("开始进行等待手工扫码登录");
        //方式一：强制等待 sleep
        //try {
        //    sleep(15000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}

        //方式二：显示等待 显示等待条件；时间灵活
        WebDriverWait wait = new WebDriverWait(
                                                webDriver,
                                                Duration.ofSeconds(15),
                                                Duration.ofSeconds(2));
        wait.until(
                //webDriver1 -> webDriver1.getCurrentUrl().contains("wework_admin/frame")
                webDriver1 ->
                        StringUtils.contains(webDriver1.getCurrentUrl(),"wework_admin/frame")
        );

        logger.info("扫码登录后页面跳转成功");
        //获取登录成功的cookie，存入yaml文件
        Set<Cookie> cookies = webDriver.manage().getCookies();
        logger.info("获取登录成功的cookie");
        //yaml文件保存
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String path = "cookie.yaml";
        //直接传入文件，不需要关闭流 没有显式地打开文件流，内部自动打开并关闭文件流，因此不需要手动关闭流
        //new File(路径)     -------->     Paths.get(路径).toFile()
        //mapper.writeValue(new File("cookie.yaml"), cookies);
        mapper.writeValue(Paths.get(path).toFile(), cookies);
        logger.info("cookie存入yaml文件");
        //如果传入的是流 Writer/OutputStream 需要关闭流，避免造成内存泄漏
        //FileWriter fileWriter = null;
        //try {
        //    fileWriter = new FileWriter(path);
        //    mapper.writeValue(fileWriter, cookies);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //} finally {
        //    if (fileWriter != null) {
        //        try {
        //            fileWriter.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //}

        assertThat("cookie对象为空", !cookies.isEmpty());
    }

    //cookie放入

    //1.yaml文件读取
    //2.cookie放入对应浏览器
    //3.跳转页面
    @Test
    @DisplayName("企业微信自动登录")
    public void cookieLogin() throws IOException {
        //WebDriverManager去创建driver打开浏览器：打开浏览器为空白页面
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //企业微信登录地址
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        logger.info("解析cookie.yaml文件");
        //yaml文件解析对象
        List<HashMap<String,Object>> cookies = null;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>(){};
        cookies = mapper.readValue(Paths.get("cookie.yaml").toFile(), typeReference);
        logger.info("cookie放入浏览器");
        //cookie放入浏览器
        cookies.stream()
                .filter(cookie ->
                        StringUtils
                                .contains(
                                        cookie.get("domain").toString(),
                                        ".work.weixin.qq.com"))
                //只要是".work.weixin.qq.com" 说明是企业微信的cookie
                .forEach(cookie -> {
                        //cookie放入浏览器操作
                    //logger.info("cookie: " + cookie);
                    /**
                     *   name: "wwrtx.c_gdpr"
                     *   value: "0"
                     *   path: "/"
                     *   domain: ".work.weixin.qq.com"
                     *   expiry: 1698653535000
                     *   sameSite: null
                     *   httpOnly: false
                     *   secure: false
                     * ........
                     * public Cookie(String name, String value, String path, Date expiry) {
                     */
                    //logger.info("生成Cookie对象");
                    Cookie cookie1 = new Cookie(
                                            cookie.get("name").toString(),
                                            cookie.get("value").toString(),
                                            cookie.get("path").toString()
                                          //Date.valueOf(cookie.get("expiry").toString())
                                            );
                    //logger.info("浏览器添加Cookie对象");
                    webDriver.manage().addCookie(cookie1);
                });
        logger.info("刷新浏览器");
        //刷新浏览器
        webDriver.navigate().refresh();

        String url = webDriver.getCurrentUrl();
        //断言  如果失败提示：登录失败   JUnit5 Hamcrest均可
        assertThat("登录失败", url, is(containsString("wework_admin/frame")));
    }

}
