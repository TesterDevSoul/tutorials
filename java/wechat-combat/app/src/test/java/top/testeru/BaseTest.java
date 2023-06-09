package top.testeru;

import org.junit.After;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import top.testeru.page.MainPage;
import top.testeru.util.AppiumServer;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {
    public static final Logger logger = getLogger(lookup().lookupClass());
//    private static AppiumServer appiumServer;

    public static MainPage mainPage;

    @BeforeAll
    public static void bf(){
//        appiumServer.startServer();
        mainPage = new MainPage();//进入企业微信首页-消息页面
    }


//    @AfterAll
//    public static void af(){
//        appiumServer.shutdown();
//    }


}
