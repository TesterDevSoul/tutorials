package top.testeru.page;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

//企业微信首页
public class MainPage extends AppBasePage{
    public MainPage() {
    }

    //跳转到通讯录页面
    @Step("跳转到通讯录页面")
    public ConcatPage toConcatPage(){
        click(AppiumBy.xpath("//*[@text=\"通讯录\"]"));
        //显示等待判断页面跳转成功
        waitUtil().until(webDriver -> webDriver.getPageSource().contains("添加客户"));

        return new ConcatPage(androidDriver);
    }
}
