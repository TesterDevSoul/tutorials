package top.testeru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

import static java.lang.Thread.sleep;
import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject wechat-combat
 * @Description 添加成员测试用例
 * @createTime 2023年05月06日 16:08:00
 */
public class AddMemberTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    public static WebDriver webDriver;
    public static WebDriverWait wait;
    static List<HashMap<String,Object>> cookies = null;

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
        //企业微信扫码登录
        cookieLogin();
    }

    private static void cookieLogin() {
        webDriver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");


        if(!Paths.get("cookie.yaml").toFile().exists()){//如果当前项目下cookie文件不存在----扫码登录
            //cookie文件写入
            wait.until(webDriver1 -> StringUtils.contains(webDriver1.getCurrentUrl(),"wework_admin/frame"));
            //获取登录成功的cookie，存入yaml文件
            Set<Cookie> cookies = webDriver.manage().getCookies();

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
                    webDriver.manage().addCookie(cookie1);
                });
        //刷新浏览器   --- 跳转到首页
        webDriver.navigate().refresh();
        //PageFactory.initElements(driver, this);

    }

    //1. 进入通讯录页面
    //2. 点击添加成员按钮
    //3. 填写成员信息
    //4. 点击添加
    //断言
    @Test
    @DisplayName("通讯录页面添加成员")
    public void addMemberWithConcatPage(){
        //元素定位
//1. 跳转到通讯录页面
        logger.info("跳转到通讯录页面");
        //01- 元素定位「8大元素定位中的id定位」点击通讯录 id="menu_contacts"
        By menuContacts = By.id("menu_contacts");
        //02- 查找元素 返回元素对象
        WebElement menuContactsEle = webDriver.findElement(menuContacts);
        //03- 元素操作 点击
        menuContactsEle.click();

        //强制等待
        //try {
        //    sleep(15000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}

//2. 显示等待页面跳转
        logger.info("显示等待页面跳转");

        wait.until(webDriver1 -> webDriver1.getPageSource().contains("搜索成员、部门、标签"));

//3. 跳转到添加成员页面
        logger.info("跳转到添加成员页面");
        //01- 元素定位「8大元素定位中的css定位」 查找到2个元素
        By addMember = By.cssSelector(".js_has_member .js_add_member");
        // 02- 查找元素 返回元素对象 list集合里面的下标为0的元素
        WebElement addMemberEle = webDriver.findElement(addMember);
        //添加全局隐式等待，为了页面加载时常问题
        //有时点击页面没有反应，为了正常点击跳转，最多点击3次
        for (int i = 0; i < 3; i++) {
            if(!webDriver.getPageSource().contains("username")){
                //03- 元素操作 点击
                addMemberEle.click();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                break;
            }
        }

//4. 填写成员信息

        //    姓名：    id="username"
        WebElement unameEle = webDriver.findElement(By.id("username"));
        unameEle.clear();
        unameEle.sendKeys("测试人002");
        logger.info("姓名：{}", "测试人002");
        //    账号：    id="memberAdd_acctid"
        WebElement acctidEle = webDriver.findElement(By.id("memberAdd_acctid"));
        acctidEle.clear();
        acctidEle.sendKeys("20221030223312");
        logger.info("账号：{}", "20221030223312");

        //    邮箱：    name="biz_mail"
        WebElement bizMailEle = webDriver.findElement(By.name("biz_mail"));
        bizMailEle.clear();
        bizMailEle.sendKeys("202210301gaigai002");
        logger.info("邮箱：{}", "202210301gaigai002");

        //    手机号：    name="mobile"
        WebElement mobileEle = webDriver.findElement(By.xpath("//*[@name=\"mobile\"]"));
        mobileEle.clear();
        mobileEle.sendKeys("13912765433");
        logger.info("手机号：{}", "13912765433");


//5. 点击保存按钮
        // a标签   保存
        webDriver.findElement(By.linkText("保存")).click();
        logger.info("点击保存按钮");

        //强制等待
        //try {
        //    sleep(10000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
//6. 等待页面跳转
        /**
         * 显示等待
         * class="js_member_count"
         * ..........
         */
        logger.info("等待页面跳转");
        wait.until(webDriver -> !webDriver.getPageSource().contains("企业邮箱："));

        //断言
        //member_colRight_memberTable_td   27
        List<WebElement> tdEleList = webDriver.findElements(By.className("member_colRight_memberTable_td"));
        List<String> tdTitleList = new ArrayList<>();
        tdEleList.forEach(
                td -> {
                    String title = td.getAttribute("title");
                    tdTitleList.add(title);

                }
        );

        logger.info("获取所有成员信息：{}",tdTitleList.toString());
        assertThat("成员添加失败",tdTitleList.contains("测试人002"));
    }
}
