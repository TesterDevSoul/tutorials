package top.testeru.appium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class AppiumServerEndTest extends AppiumServerTest{
    @Test
    public void appiumserverEnd(){
        appiumServer.shutdown();
    }
}
