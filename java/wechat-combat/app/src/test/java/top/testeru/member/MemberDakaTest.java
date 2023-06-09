package top.testeru.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import top.testeru.BaseTest;
import top.testeru.page.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.slf4j.LoggerFactory.getLogger;

public class MemberDakaTest extends BaseTest {


    WorkbenchPage workbenchPage;
    @BeforeEach
    public void be(){
        workbenchPage = mainPage
                .toWorkbenchPage();//跳转到打卡页面
    }
    @Test
    @Order(8)
    public void daka(){
        String daka = workbenchPage.daka();
        assertAll(
                ()-> assertThat(daka,is(containsString("班·正常")))
        );
    }
}
