package top.testeru.appium;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.testeru.util.AppiumServer;

public class AppiumServerStartTest extends AppiumServerTest{
    @Test
    @Order(1)
    public void appiumserverStart(){
        appiumServer.startServer();
    }
}
